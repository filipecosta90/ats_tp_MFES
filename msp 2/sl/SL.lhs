%
% Este ficheiro contem um texto em Literate Haskell
%    Partes do ficheiro s�o texto em LaTeX e outras em Haskell
%    
%  Analise e Transforma��o de Software
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

\title{\sf   An�lise e Transforma��o de Software \\
\begin{tabular}{c}
{\small Mestrado em Inform�tica}, {\small Universidade do Minho} \\
{\small Ano lectivo 2012/2012}  \\
{\small Jo�o Saraiva}  \\
{\small A Linguagem \SL e sua Compila��o para Msp} \\ 
\end{tabular}
}

\author{}
\date{}


%-------------------- Inicio do Documento -------------------------------

\begin{document}

\maketitle


Este texto est� escrito em \textbf{literate Haskell}. Isto �, pode ser
interpretado como um documento \LaTeX\ ou como um puro programa na
linguagem Haskell.  

\section{A Linguagem SL - \textit{Simple Language}}

Neste documento definimos a linguagem \SL e a sua tradu��o para uma
m�quina virtual muito simples \MSP (Mais Simples Poss�vel). 

De seguida, definimos um m�dulo em Haskell com nome \texttt{SL}
(\textit{Simple Language}) onde apresentamos a difini��o da linguagem
\SL e as fun��es que fazem a sua tradu��o para \MSP. A linguagem \SL �
apresentada na sua sintaxe abstrata, modelada por um tipo de dados
alg�brico.

\begin{code}
--
--
-- An�lise e Transforma��o de Software
-- Mestrado em Inform�tica 
-- 2012/2013
--


module SL where

import Data.Char
import Data.List
import System.IO

-- import PP_PLC                             -- Combinadores de Pretty Printing
import Msp                                -- M�quina Virtual 


\end{code}


O seguinte tipo de dados alg�brico define a \textit{sintaxe abstracta}
da linguagem \SL. Esta linguagem muito simples � constitu�da por uma
lista de declara��es de vari�veis seguida de uma lista de
instru��es. As instru��es consideradas s�o a atribui��o a uma vari�vel
de uma express�o aritm�tica, a instru��o condicional, duas instru��es
repetitivas e uma instru��o de \textit{IO}.



\begin{code}
type Sl   = (Decls,[Stat])

type Decls = [(Nome,Tipo)]     -- Nome , Tipo ,
type Nome  = String
data Tipo  = Inteiro               -- apenas dois tipos: Int e Char
           | Caracter
           deriving (Show, Read)
  
data Stat = Atrib         String Expr           -- Atribui��o
          | SeEntaoSenao  Expr [Stat] [Stat]    -- Condicional
          | Enq           Expr  [Stat]          -- Repeti��o
          | RepetirAte    [Stat] Expr           -- Repeti��o
          | Print         Expr                  -- IO: Output
          | PrintC        Integer               --     Output Char
          | Input         String                -- IO: Input
           deriving (Show, Read)

data Expr = SomaExp      Expr Expr              -- Express�es Aritm�ticas
          | MultExp      Expr Expr              -- muito simples
          | MQExp        Expr Expr       -- greater than
          | ConstInteira Integer
          | Variavel     String 
          deriving (Show, Read)
\end{code}



Utilizando os construtores deste tipo de dados alg�brico, podemos
escrever directamente em Haskell o nosso primeiro programa \SL. Por
exemplo, o seguinte programa (em nota��o concreta) � escrito da
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
\texttt{Read} s�o muito limitados e um parser/unparser (ou pretty
printing) dever� ser definido para a linguagem \SL.


\section{Tradu��o de \SL para \MSP}

Nesta sec��o apresentamos a tradu��o de \SL para \MSP. Come�amos por
definir as fun��es que convertem sub-linguagems de \SL, nomeadamente
express�es aritm�ticas, atribui��es, estruturas condicionais,
estruturas repetitivas (\textit{enquanto} e \textit{repetir at�}) e a
instru��o de \textit{IO}. Posteriormente, definimos a fun��o que aloca
mem�riia na m�quina virtual para as declara��es/vari�veis dos
programas \SL.

\subsection{Gera��o de C�digo para Express�es}

A tradu��o das express�es aritm�ticas existentes em \SL para \MSP �
muito simples. Basicamente, a fun��o recursivamente converte as
subexpress�es e depois adiciona a instru��o SSP correspondente �
opera��o em \SL.

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


Por exemplo, podemos traduzir a express�o \SL \texttt{exp1}
apresentada anteriormente usando esta fun��o:

\begin{code}

exp1ToMsp :: [Instr]
exp1ToMsp = genCodeExp exp1

\end{code}


\subsection{Atribui��o}

A fun��o que gera c�digo \MSP para atribui��es recebe o nome da
vari�vel (\ie, lado esquerdo da atribui��o), a lista de instru��es \MSP
correspondentes � express�o do lado direito e produz o c�digo \MSP para
a atribui��o.



\begin{code}

genCodeAssign :: String -> [Instr] -> [Instr]
genCodeAssign s msp_e = [ Pusha s ] ++ msp_e ++ [Store]
                     
\end{code}



\subsection{Estruturas Condicionais}

A tradu��o de estruturas condicionais para \MSP � feita de acordo com
 uma regra muito simples. Um estrutura condicional da forma:

\begin{verbatim}
        ...
        se condi�ao ->  { stat 1
        sen�o       ->  { stat 2
        fse
        ...
\end{verbatim}

transforma-se no seguinte \textit{template} de c�digo \MSP:

\begin{verbatim}
                ...
                <Teste condi�ao>
                JMPF senao
                <stat 1>
                JMP fse
        senao : 
                <stat 2>
        fse   : 
                ...
\end{verbatim}


Considerando que foi j� transformado para \MSP o teste da condi��o, e o
bloco de instru��es associados aos dois ramos da computa��o, ent�o a
fun��o que modela o \texttt{If Then Else} escreve-se segindo
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

A tradu��o de estruturas repetitivas para MSP � tamb�m feita de acordo
 com uma regra muito simples. Um estrutura repetitiva da forma:


\begin{verbatim}
      enq  condi��o
           ->  { stat 1
                 ...
                 stat n
               }
      fenq
\end{verbatim}


transforma-se no seguinte \textit{template} de c�digo MSP:


\begin{verbatim}
               ...
      enq:    <Teste condi�ao>
              JMPF fenq
              <stat 1>
              ...
              <stat n>
              JMP enq
     fenq: 
              ...
\end{verbatim}


Escrever a fun��o Haskell \texttt{genEnquanto} que dado o c�digo Msp
da condi��o e dos statements, modela um \textit{While}.

\begin{code}

-- genEnquato :: [Instr] -> [Instr] -> [Instr]

\end{code}


Escrever a fun��o Haskell \texttt{genRepetirAte} que modela a
estrutura repetitiva \textit{do .. while} usual em linguagem de
programa��o (por exemplo, Pascal).

\begin{code}

-- genRepetir :: [Instr] -> [Instr] -> [Instr]

\end{code}


\subsection{Aloca��o de Mem�ria para Vari�veis}


Antes de convertermos os statments de \SL para \MSP, temos de alocar
mem�ria na zona de dados do \MSP para cada vari�vel definida em \SL. De
modo a cada vari�vel \MSP ter um endere�o �nico na mem�ria, vamos
considerar que cada vari�vel ocupa apenas um endere�o, e que elas
ocorrem consecutivamente na mem�ria da m�quina virtual. Para
implementarmos esta aloca��o utilizamos um par�metro que vai
especificando qual o endere�o da vari�vel.


\begin{code}


slDecls2msl ::  Decls -> [Decl]     -- Decls: SL
                                    -- [Decl] : Msp

slDecls2msl sl = slDecls2msl' sl 0

slDecls2msl' [] _ = []
slDecls2msl' ((n,t):xs) i =  Decl n i 1 :  slDecls2msl' xs (i+1)

\end{code}


Por exemplo, podemos declarar tr�s vari�veis em \SL e a fun��o
\texttt{slDecls2msl} devolve a mem�ria definida na m�quina virtual.


\begin{code}

decls1 :: Decls
decls1 = [ ("aux",Inteiro), ("i",Inteiro) , ("a",Caracter)]

decls1Tomsp :: [Decl]
decls1Tomsp = slDecls2msl decls1

\end{code}



\subsection{A Fun��o da Tradu��o de \SL para \MSP}

Tendo definido todas as fun��es que traduzem as sub-linguagens de \SL
para \MSP, agora facilmente definidos a fun��o \texttt{sl2msp} que
traduz programas \SL para \MSP.

Antes disso, vamos s� escrever a fun��o que traduz as instru��es \SL:

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

e agora a fun��o que traduz \SL para \MSP escreve-se facilmente da
seguinte forma:


\begin{code}

sl2msp :: Sl -> Msp
sl2msp (decls,stats) = Msp declsMsp instrMsp
   where declsMsp = slDecls2msl decls
         instrMsp = genStats stats

\end{code}


Para testarmos o nosso c�digo vamos apresentamos um programa \SL mais
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


que podemos converter para \MSP usando a fun��o \texttt{sl2msp}.


\begin{code}

progSlTomsp = sl2msp progSL2

\end{code}


\subsection{O Compilador de \SL}

De modo a podermos gerar c�digo \MSP num ficheiro que � posteriormente
lido e executado pela m�quina virtual, definimos um compilador de \SL
como uma fun��o que dado um programa em \SL e o nome de um ficheior,
gera o c�digo \MSP correspondente no ficheiro dado.

\begin{code}

compileSL2Msp :: Sl -> FilePath -> IO ()
compileSL2Msp sl file = writeFile file (show $ sl2msp sl)

\end{code}

Por exemplo, podemos compilar o programa \texttt{progSL2} para o
ficheiro \texttt{prog.msp} executando a seguinte express�o Haskell no
interpretador \texttt{ghci}:

\begin{verbatim}
        > compileSL2Msp progSL2 "prog.msp"
\end{verbatim}

E depois podemos utilizar a m�quina virtual \texttt{msp} para executar
o programa gerado:

\begin{verbatim}
        > /msp p.msp 
        5
        24
        End!
\end{verbatim}


\subsection{O Interpretador de \SL}


Tamb�m podemos definir um interpretador, \textit{a la} \texttt{ghci}
do Haskell, onde o c�digo SL � compilado em \textit{runtime} e
imediatamente executado:


\begin{code}

interpretadorSl sl = runMspProg $ sl2msp sl

\end{code}


\section{Localiza��o de Falhas em Programas \SL}

Os algoritmos \textit{Spectrum-based Fault Localization} (SFL) tem
como objetivo localizar falhas num dado programa de software. Estes
algoritmos baseiam-se na execu��o do programa a analisar a partir de
um conjunto de casos de teste previamente definidos pelo programador.
Durante a execu��o do programa com esses \textit{inputs} o programa
deve monitorizar quais as suas componentes que s�o executadas. Estas
componetes podem corresponder a linhas de c�digo do programa,
instru��es, blocos de instru��es, fun��es, m�dulos, etc. Isto �, podem
ter granularidades diferentes. A ideia fundamental dos algoritmos SFL
� identificar as componentes do programa que est�o diretamente
relacionados com falhas ocorridas nos casos de teste.

Para identificar as componentes do programa relacionados com a falha
dos casos de teste, os programas devem registar a sequ�ncia de
execu��o das v�rias componentes durante a sua execu��o. Para fazer
este registo, os programas a analisar t�m ser ser primeiro
\textit{instrumentados} de modo a fazerem o \textit{trace} das
componentes que se pretende identificar como causa da falha.

De seguida apresenta-se a fun��o que instrumenta programas \SL. As
componentes que s�o instrumentadas s�o as instru��es \SL
(\textit{statements}). Neste caso, ap�s cada instru��o � adicionada
uma instru��o \texttt{Print} que produzir� em tempo de execu��o um
identificador dessa instru��o sempre que ela for executada.

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

Para gerar c�digo \MSP para o programa \texttt{progSL2}
instrumentado fazemos:

\begin{code}

progSL2_SFL = compileSL2Msp (instrumentaSL progSL2) "prog_sfl.msp" 

\end{code}


Podemos agora definir esta fun��o de uma forma gen�rica para
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




\section{Exerc�cios}



\begin{exercicio} 

Como facilmente se constata utilizando um programa SL que inclua duas
estruturas condicinais, as fun��es de gera��o de c�digo produzem
labels iguais para instru��es diferentes. Altere as fun��es de gera��o
de c�digo de modo produzirem labels �nicas e resolverem este
problema. Considere que as fun��es recebem um valor inteiro para
distinguir as etiquetas.

\end{exercicio}





\end{document}



