%
% Este ficheiro contem um texto em Literate Haskell
%    Partes do ficheiro são texto em LaTeX e outras em Haskell
%    
%  Analise e Transformação de Software
%  UCE: ACS
%  2012/2013
%


\documentclass[11pt]{article}
\usepackage{a4wide}
\usepackage{graphics}
\usepackage{graphicx}
\usepackage{color}
\usepackage{alltt}
\usepackage[portuges]{babel}

\usepackage[latin1]{inputenc}
\usepackage{latexsym}


\usepackage[dvips]{epsfig}
\usepackage{epic}
\usepackage{eepic}


\newenvironment{code}
{\textbf{Haskell} \hspace{1cm} \hrulefill \\ 
\smallskip 
\begin{center}
\begin{minipage}{.80\textwidth} 
\begin{alltt}\small}
{\end{alltt}
\end{minipage}
\end{center}
\hrule\smallskip
}



\def\C2{$C_{\_\_}^{2}$}


\def\SL{{\sf\it sl}~}
\def\MSP{{\sf\it Msp}~}
\def\ie{{\sf\it ie}~}



\newtheorem{exercicio}{}[section]

\title{\sf   Análise e Transformação de Software \\
\begin{tabular}{c}
{\small Mestrado em Informática}, {\small Universidade do Minho} \\
{\small Ano lectivo 2012/2012}  \\
{\small João Saraiva}  \\
{\small A Linguagem \SL e sua Compilação para Msp} \\ 
\end{tabular}
}

\author{}
\date{}


%-------------------- Inicio do Documento -------------------------------

\begin{document}

\maketitle


Este texto está escrito em \textbf{literate Haskell}. Isto é, pode ser
interpretado como um documento \LaTeX\ ou como um puro programa na
linguagem Haskell.  

\section{A Linguagem SL - \textit{Simple Language}}

Neste documento definimos a linguagem \SL e a sua tradução para uma
máquina virtual muito simples \MSP (Mais Simples Possível). 

De seguida, definimos um módulo em Haskell com nome \texttt{SL}
(\textit{Simple Language}) onde apresentamos a difinição da linguagem
\SL e as funções que fazem a sua tradução para \MSP. A linguagem \SL é
apresentada na sua sintaxe abstrata, modelada por um tipo de dados
algébrico.

\begin{code}
--
--
-- Análise e Transformação de Software
-- Mestrado em Informática 
-- 2012/2013
--


module SL where

import Data.Char
import Data.List
import System.IO

-- import PP_PLC                             -- Combinadores de Pretty Printing
import Msp                                -- Máquina Virtual 


\end{code}


O seguinte tipo de dados algébrico define a \textit{sintaxe abstracta}
da linguagem \SL. Esta linguagem muito simples é constituída por uma
lista de declarações de variáveis seguida de uma lista de
instruções. As instruções consideradas são a atribuição a uma variável
de uma expressão aritmética, a instrução condicional, duas instruções
repetitivas e uma instrução de \textit{IO}.



\begin{code}
type Sl   = (Decls,[Stat])

type Decls = [(Nome,Tipo)]     -- Nome , Tipo ,
type Nome  = String
data Tipo  = Inteiro               -- apenas dois tipos: Int e Char
           | Caracter
           deriving (Show, Read)
  
data Stat = Atrib         String Expr           -- Atribuição
          | SeEntaoSenao  Expr [Stat] [Stat]    -- Condicional
          | Enq           Expr  [Stat]          -- Repetição
          | RepetirAte    [Stat] Expr           -- Repetição
          | Print         Expr                  -- IO: Output
          | PrintC        Integer               --     Output Char
          | Input         String                -- IO: Input
           deriving (Show, Read)

data Expr = SomaExp      Expr Expr              -- Expressões Aritméticas
          | MultExp      Expr Expr              -- muito simples
          | MQExp        Expr Expr       -- greater than
          | ConstInteira Integer
          | Variavel     String 
          deriving (Show, Read)
\end{code}



Utilizando os construtores deste tipo de dados algébrico, podemos
escrever directamente em Haskell o nosso primeiro programa \SL. Por
exemplo, o seguinte programa (em notação concreta) é escrito da
seguinte forma:

\begin{code}

progSl :: Sl
progSl = ( [("aux",Inteiro)]
         , [Atrib "aux" exp1]
         )

exp1 :: Expr
exp1 = SomaExp (ConstInteira 10) (MultExp (ConstInteira 15) (Variavel "aux"))

\end{code}

Podemos ainda utilizar os mecanismos oferecidos pela classe
\texttt{Show} e \texttt{Read}, que permitem mapear de e para string
valores do tipo \texttt{SL} (isto programas \SL).

\begin{code}

progSl1 :: Sl
progSl1 = read "([(\"aux\",Inteiro)],[Atrib \"aux\" (SomaExp (ConstInteira 10) (MultExp (ConstInteira 15) (Variavel \"aux\")))])"

\end{code}

Obviamente, que os mecanismos oferecidos pelo \texttt{Show} e
\texttt{Read} são muito limitados e um parser/unparser (ou pretty
printing) deverá ser definido para a linguagem \SL.


\section{Tradução de \SL para \MSP}

Nesta secção apresentamos a tradução de \SL para \MSP. Começamos por
definir as funções que convertem sub-linguagems de \SL, nomeadamente
expressões aritméticas, atribuições, estruturas condicionais,
estruturas repetitivas (\textit{enquanto} e \textit{repetir até}) e a
instrução de \textit{IO}. Posteriormente, definimos a função que aloca
memóriia na máquina virtual para as declarações/variáveis dos
programas \SL.

\subsection{Geração de Código para Expressões}

A tradução das expressões aritméticas existentes em \SL para \MSP é
muito simples. Basicamente, a função recursivamente converte as
subexpressões e depois adiciona a instrução SSP correspondente à
operação em \SL.

\begin{code}
genCodeExp :: Expr -> [Instr]

genCodeExp (SomaExp e1 e2) = e1_msp ++ e2_msp ++ [Add]
  where e1_msp = genCodeExp e1
        e2_msp = genCodeExp e2

genCodeExp (MultExp e1 e2) = e1_msp ++ e2_msp ++ [Mul]
  where e1_msp = genCodeExp e1
        e2_msp = genCodeExp e2

genCodeExp (MQExp e1 e2) = e1_msp ++ e2_msp ++ [Gt]
  where e1_msp = genCodeExp e1
        e2_msp = genCodeExp e2

genCodeExp (ConstInteira i) = [Pushi i ]

genCodeExp (Variavel    s)  = [ Pusha s , Load ]

\end{code}


Por exemplo, podemos traduzir a expressão \SL \texttt{exp1}
apresentada anteriormente usando esta função:

\begin{code}

exp1ToMsp :: [Instr]
exp1ToMsp = genCodeExp exp1

\end{code}


\subsection{Atribuição}

A função que gera código \MSP para atribuições recebe o nome da
variável (\ie, lado esquerdo da atribuição), a lista de instruções \MSP
correspondentes à expressão do lado direito e produz o código \MSP para
a atribuição.



\begin{code}

genCodeAssign :: String -> [Instr] -> [Instr]
genCodeAssign s msp_e = [ Pusha s ] ++ msp_e ++ [Store]
                     
\end{code}



\subsection{Estruturas Condicionais}

A tradução de estruturas condicionais para \MSP é feita de acordo com
 uma regra muito simples. Um estrutura condicional da forma:

\begin{verbatim}
        ...
        se condiçao ->  { stat 1
        senão       ->  { stat 2
        fse
        ...
\end{verbatim}

transforma-se no seguinte \textit{template} de código \MSP:

\begin{verbatim}
                ...
                <Teste condiçao>
                JMPF senao
                <stat 1>
                JMP fse
        senao : 
                <stat 2>
        fse   : 
                ...
\end{verbatim}


Considerando que foi já transformado para \MSP o teste da condição, e o
bloco de instruções associados aos dois ramos da computação, então a
função que modela o \texttt{If Then Else} escreve-se segindo
diretamente a regra apresentada:


\begin{code}

genSeEntaoSenao :: [Instr] -> [Instr] -> [Instr] -> [Instr]
genSeEntaoSenao c s1 s2 = c                 ++
                          [ Jumpf "senao" ] ++
                          s1                ++
                          [ Jump "fse" ]    ++
                          [ ALabel "senao"] ++
                          s2                ++
                          [ ALabel "fse"]
\end{code}


\subsection{Estruturas de Controlo Repititivas}

A tradução de estruturas repetitivas para MSP é também feita de acordo
 com uma regra muito simples. Um estrutura repetitiva da forma:


\begin{verbatim}
      enq  condição
           ->  { stat 1
                 ...
                 stat n
               }
      fenq
\end{verbatim}


transforma-se no seguinte \textit{template} de código MSP:


\begin{verbatim}
               ...
      enq:    <Teste condiçao>
              JMPF fenq
              <stat 1>
              ...
              <stat n>
              JMP enq
     fenq: 
              ...
\end{verbatim}


Escrever a função Haskell \texttt{genEnquanto} que dado o código Msp
da condição e dos statements, modela um \textit{While}.

\begin{code}

-- genEnquato :: [Instr] -> [Instr] -> [Instr]

\end{code}


Escrever a função Haskell \texttt{genRepetirAte} que modela a
estrutura repetitiva \textit{do .. while} usual em linguagem de
programação (por exemplo, Pascal).

\begin{code}

-- genRepetir :: [Instr] -> [Instr] -> [Instr]

\end{code}


\subsection{Alocação de Memória para Variáveis}


Antes de convertermos os statments de \SL para \MSP, temos de alocar
memória na zona de dados do \MSP para cada variável definida em \SL. De
modo a cada variável \MSP ter um endereço único na memória, vamos
considerar que cada variável ocupa apenas um endereço, e que elas
ocorrem consecutivamente na memória da máquina virtual. Para
implementarmos esta alocação utilizamos um parámetro que vai
especificando qual o endereço da variável.


\begin{code}


slDecls2msl ::  Decls -> [Decl]     -- Decls: SL
                                    -- [Decl] : Msp

slDecls2msl sl = slDecls2msl' sl 0

slDecls2msl' [] _ = []
slDecls2msl' ((n,t):xs) i =  Decl n i 1 :  slDecls2msl' xs (i+1)

\end{code}


Por exemplo, podemos declarar três variáveis em \SL e a função
\texttt{slDecls2msl} devolve a memória definida na máquina virtual.


\begin{code}

decls1 :: Decls
decls1 = [ ("aux",Inteiro), ("i",Inteiro) , ("a",Caracter)]

decls1Tomsp :: [Decl]
decls1Tomsp = slDecls2msl decls1

\end{code}



\subsection{A Função da Tradução de \SL para \MSP}

Tendo definido todas as funções que traduzem as sub-linguagens de \SL
para \MSP, agora facilmente definidos a função \texttt{sl2msp} que
traduz programas \SL para \MSP.

Antes disso, vamos só escrever a função que traduz as instruções \SL:

\begin{code}

genStat :: Stat -> [Instr]

genStat (Atrib s e) = let e_msp = genCodeExp e 
                      in  genCodeAssign s e_msp 
                   
genStat (SeEntaoSenao c s1 s2) = genSeEntaoSenao c_msp s1_msp s2_msp
  where c_msp  = genCodeExp c
        s1_msp = genStats s1
        s2_msp = genStats s2

genStat (Print e) =  let e_msp = genCodeExp e 
                     in  e_msp ++ [IOut]
genStat (PrintC i) =  [Pushi i,IOutC]

genStat (Input s) = [Pusha s,IIn,Store]


genStats :: [Stat] -> [Instr]
genStats  = concat . map genStat

\end{code}

e agora a função que traduz \SL para \MSP escreve-se facilmente da
seguinte forma:


\begin{code}

sl2msp :: Sl -> Msp
sl2msp (decls,stats) = Msp declsMsp instrMsp
   where declsMsp = slDecls2msl decls
         instrMsp = genStats stats

\end{code}


Para testarmos o nosso código vamos apresentamos um programa \SL mais
completo:

\begin{code}
progSL2 :: Sl
progSL2 = ( [("aux",Inteiro),("i",Inteiro)]
          , [ Atrib "aux" (ConstInteira 3)
            , Atrib "aux" (SomaExp (ConstInteira 10) 
                                   (MultExp (ConstInteira 15) (Variavel "aux")))
            , SeEntaoSenao (MQExp (Variavel "aux")
                           (ConstInteira 5))
                [Atrib "i" (ConstInteira 5)]
                [Atrib "i" (ConstInteira 6)]
            , Print (Variavel "i")
            , Print (ConstInteira 24)
            ]
          )
\end{code}


que podemos converter para \MSP usando a função \texttt{sl2msp}.


\begin{code}

progSlTomsp = sl2msp progSL2

\end{code}


\subsection{O Compilador de \SL}

De modo a podermos gerar código \MSP num ficheiro que é posteriormente
lido e executado pela máquina virtual, definimos um compilador de \SL
como uma função que dado um programa em \SL e o nome de um ficheior,
gera o código \MSP correspondente no ficheiro dado.

\begin{code}

compileSL2Msp :: Sl -> FilePath -> IO ()
compileSL2Msp sl file = writeFile file (show $ sl2msp sl)

\end{code}

Por exemplo, podemos compilar o programa \texttt{progSL2} para o
ficheiro \texttt{prog.msp} executando a seguinte expressão Haskell no
interpretador \texttt{ghci}:

\begin{verbatim}
        > compileSL2Msp progSL2 "prog.msp"
\end{verbatim}

E depois podemos utilizar a máquina virtual \texttt{msp} para executar
o programa gerado:

\begin{verbatim}
        > /msp p.msp 
        5
        24
        End!
\end{verbatim}


\subsection{O Interpretador de \SL}


Também podemos definir um interpretador, \textit{a la} \texttt{ghci}
do Haskell, onde o código SL é compilado em \textit{runtime} e
imediatamente executado:


\begin{code}

interpretadorSl sl = runMspProg $ sl2msp sl

\end{code}


\section{Localização de Falhas em Programas \SL}

Os algoritmos \textit{Spectrum-based Fault Localization} (SFL) tem
como objetivo localizar falhas num dado programa de software. Estes
algoritmos baseiam-se na execução do programa a analisar a partir de
um conjunto de casos de teste previamente definidos pelo programador.
Durante a execução do programa com esses \textit{inputs} o programa
deve monitorizar quais as suas componentes que são executadas. Estas
componetes podem corresponder a linhas de código do programa,
instruções, blocos de instruções, funções, módulos, etc. Isto é, podem
ter granularidades diferentes. A ideia fundamental dos algoritmos SFL
é identificar as componentes do programa que estão diretamente
relacionados com falhas ocorridas nos casos de teste.

Para identificar as componentes do programa relacionados com a falha
dos casos de teste, os programas devem registar a sequência de
execução das várias componentes durante a sua execução. Para fazer
este registo, os programas a analisar têm ser ser primeiro
\textit{instrumentados} de modo a fazerem o \textit{trace} das
componentes que se pretende identificar como causa da falha.

De seguida apresenta-se a função que instrumenta programas \SL. As
componentes que são instrumentadas são as instruções \SL
(\textit{statements}). Neste caso, após cada instrução é adicionada
uma instrução \texttt{Print} que produzirá em tempo de execução um
identificador dessa instrução sempre que ela for executada.

\begin{code}

instrumentaSL :: Sl -> Sl
instrumentaSL (dcs,sts) = let (stsi,j) = instrumentaSL_Stats sts 1
                          in  (dcs,stsi)

instrumentaSL_Stat :: Stat -> Integer -> ([Stat], Integer)
instrumentaSL_Stat (Atrib s e) i = 
                   (Atrib s e : monitoriza i , i+1) 


-- no tracing for IO statements
instrumentaSL_Stat (Input s) i = ([Input s] , i) 
instrumentaSL_Stat (Print e) i =  
                   ([Print e] , i)      
instrumentaSL_Stat (PrintC v) i =  
                   ([PrintC v] , i)      

instrumentaSL_Stat (SeEntaoSenao c s1 s2) i = 
                   (monitoriza i ++ [SeEntaoSenao c s1i s2i],h) 
  where 
        (s1i , j) = instrumentaSL_Stats s1 (i+1)
        (s2i , h) = instrumentaSL_Stats s2 j

instrumentaSL_Stats :: [Stat] -> Integer -> ([Stat],Integer)
instrumentaSL_Stats  []    i = ([],i)
instrumentaSL_Stats (s:ss) i = (ns ++ nss, h)
  where  (ns , j) = instrumentaSL_Stat s i
         (nss, h) = instrumentaSL_Stats ss j


monitoriza :: Integer -> [Stat]
monitoriza i = [ PrintC 115                -- print "s:" 
               , PrintC 58                 
               , Print (ConstInteira i)
--                , PrintC 10                  -- print newline
               ] 

\end{code}

Para gerar código \MSP para o programa \texttt{progSL2}
instrumentado fazemos:

\begin{code}

progSL2_SFL = compileSL2Msp (instrumentaSL progSL2) "prog_sfl.msp" 

\end{code}


Podemos agora definir esta função de uma forma genérica para
compilarmos programas SL instrumentados para \MSP:


\begin{code}

compileSL_sfl :: Sl -> FilePath -> IO ()
compileSL_sfl sl file = compileSL2Msp (instrumentaSL sl) file

\end{code}




\begin{code}
maiorDeDoisNums :: Sl
maiorDeDoisNums = ( [("a",Inteiro),("b",Inteiro),("res",Inteiro)]
          , [ Input "a" 
            , Input "b"
            , SeEntaoSenao (MQExp (Variavel "a")
                                  (Variavel "b"))
                [Atrib "res" (Variavel "a")]
                [Atrib "res" (Variavel "b")]
            , Print (Variavel "res")
            ]
          )
\end{code}




\section{Exercícios}



\begin{exercicio} 

Como facilmente se constata utilizando um programa SL que inclua duas
estruturas condicinais, as funções de geração de código produzem
labels iguais para instruções diferentes. Altere as funções de geração
de código de modo produzirem labels únicas e resolverem este
problema. Considere que as funções recebem um valor inteiro para
distinguir as etiquetas.

\end{exercicio}





\end{document}



