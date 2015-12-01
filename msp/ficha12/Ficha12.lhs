%
% Este ficheiro contem um texto em Literate Haskell
%    Partes do ficheiro são texto em LaTeX e outras em Haskell
%    
%  Processamento de Linguagens e Compiladores
%  2006/2007
%


\documentclass[12pt]{article}
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
{\textbf{Solução} \hspace{1cm} \hrulefill \\ 
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

\newtheorem{exercicio}{}[section]

\title{\sf   Processamento de Linguagens e Compiladores \\
\begin{tabular}{c}
{\small LMCC}, {\small Universidade do Minho} \\
{\small Ano lectivo 2006/2007}  \\
{\small João Saraiva}  \\
{\small Ficha Teórico-Prática N$º$12} \\ 
\end{tabular}
}

\author{}
\date{}


%-------------------- Inicio do Documento -------------------------------

\begin{document}

\maketitle


Este texto está escrito em \textbf{literate Haskell}. Isto é, pode ser
interpretado como um documento \LaTeX\ ou como um puro programa na
linguagem Haskell.  Responda às perguntas sobre Haskell neste próprio
ficheiro para assim produzir o programa e a sua documentação.


\section{Geração de MSP em Haskell}


Nesta ficha teórico-prática definimos um módulo para efectuar a
geração de código. Neste módulo definem-se funções genéricas para a
geração de código em Msp. Estas funções modelam em Msp estruturas
condicionais, repetitivas, etc.

\begin{code}
--
--
-- Processamento de Linguagens e Compilação
-- 2006/2007
--


module GenCodeMsp where

import Data.Char
import Data.List
-- import PP_PLC                             -- ficha 10
import Msp                                -- ficha 11


\end{code}


Antes de paresentarmos os execrcícios sobre geração de código vamos
analisar uma linguagem muito simples que vai ser usada ao longo da
ficha.


\begin{exercicio} 

Considere o seguinte tipo de dados algébrico que define a
\textit{sintaxe abstracta} de uma linguagem de programação muito
simples chamada \textit{Simple Language} (SL). 


\begin{code}
type Sl   = [Inst]

data Inst = Atrib         String Expr 
          | SeEntaoSenao  Expr [Inst] [Inst] 
          | Enq           Expr  [Inst] 
          | RepetirAte    [Inst] Expr          

data Expr = SomaExp      Expr Expr 
          | MultExp      Expr Expr
          | MQExp        Expr Expr
          | ConstInteira Integer
          | Variavel     String 
\end{code}


Responda às seguintes perguntas:


\begin{enumerate} 

\item Construa uma frase em sintaxe abstracta que pertence a esta
linguagem e que representa uma unica instrução \texttt{aux = 10 + 15 *
aux}.

\item Utilizando os combinadores de \textit{Pretty Printing}, da ficha
nº10, defina uma função que produz SL em notação \textit{a la} Pascal.

\end{enumerate}


\end{exercicio}


\begin{code}

exp1 = SomaExp (ConstInteira 10) (MultExp (ConstInteira 15) (Variavel "aux"))



\end{code}


\section{Geração de Código para Expressões}



\begin{exercicio} 

Escreva a função Haskell \texttt{genCodeExp} que dada uma expressão
aritmética gera o código correspondente em Msp.

\end{exercicio}




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


\section{Atribuição}


\begin{code}

-- a = exp1

atrib1 = Atrib "a" exp1

\end{code}




\begin{exercicio} 

Escreva a função Haskell \texttt{genCodeAssign} que dado o nome da
variavél (lado esquerdo da atribuição) e o codigo da expressão do lado
direito, gera código Msp para uma atribuição.

\end{exercicio}



\begin{code}

genCodeAssign :: String -> [Instr] -> [Instr]
genCodeAssign s msp_e = [ Pusha s ] ++ msp_e ++ [Store]
                     

\end{code}




\section{Estruturas Condicionais}


\begin{code}

-- a = 10 + 15 * aux
-- if x > 5 then b = 5 else b = 6

bc = [ atrib1 
     , SeEntaoSenao (MQExp (Variavel "x")
                           (ConstInteira 5))
              [Atrib "b" (ConstInteira 5)]
              [Atrib "b" (ConstInteira 6)]
     ]

\end{code}



\begin{exercicio} 


Considere a seguinte regra para modelar estruturas condicionais em
MSP:

\begin{verbatim}

se condiçao ->  { stat 1

   senão    ->  { stat 2
fse
\end{verbatim}

Transforma-se em:

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



Escreva a função Haskell \texttt{genSeEntaoSenao} que dado o código Msp
da condição os dos dois statements, modela o \texttt{If Then Else}.

\end{exercicio}



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






\section{Estruturas de Controlo Repititivas}




\begin{exercicio} 

Escreva um programa em sintaxe abstracta de SL que contenha uma
instrução \texttt{enq}


\end{exercicio}


\begin{code}


\end{code}



\begin{exercicio} 


Considere a seguinte regra para modelar estruturas prepetitivas
\textit{enquanto} em MSP:


\begin{verbatim}

enq  condição
  ->  { stat 1
        ...
        stat n
      }
fenq
\end{verbatim}



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



Escreva a função Haskell \texttt{genEnquato} que dado o código Msp da
condição e dos statements, modela um \textit{While}.

\end{exercicio}


\begin{code}


\end{code}



\begin{exercicio} 


Escreva a função Haskell \texttt{genRepetirAte} que modela a estrutura
repetitiva \textit{do .. while} usual em linguagem de programação (por
exemplo, Pascal).


\end{exercicio}


\begin{code}


\end{code}






\section{Geração de Código para SL}


\begin{exercicio} 

Considere a linguagem SL. Utilizando as funções de geração de código
desenvolvidas nesta ficha, escreva a função \texttt{sl2msp} que
compila SL para MSP:

\end{exercicio}



\begin{code}



{-
genStat :: Stat -> [Instr]

genStat (Assign s e) = let e_msp = genCodeExp e 
                         in Pusha s : e_msp ++ [Store]
                   
genStat (If_t_e c s1 s2) = genIfThenElse c s1 s2

genStats  = concat . map genStat

-}

\end{code}




\begin{exercicio} 

Como facilmente se constata utilizando um programa SL que inclua duas
estruturas condicinais, as funções de geração de código produzem
labels iguais para instruções diferentes. Altere as funções de geração
de código de modo produzirem labels únicas e resolverem este
problema. Considere que as funções recebem um valor inteiro para
distinguir as etiquetas.

\end{exercicio}




\end{document}





--  LocalWords:  exercicio
