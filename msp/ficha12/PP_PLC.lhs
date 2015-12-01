%
% Este ficheiro contem um texto em Literate Haskell
%    Partes do ficheiro s�o texto em LaTeX e outras em Haskell
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
{\textbf{Solu��o} \hspace{1cm} \hrulefill \\ 
\smallskip 
\begin{center}
\begin{minipage}{.80\textwidth} 
\begin{alltt}\small}
{\end{alltt}
\end{minipage}
\end{center}
\hrule\smallskip
}


\newtheorem{exercicio}{}[section]

\title{\sf   Processamento de Linguagens e Compiladores \\
\begin{tabular}{c}
{\small LMCC}, {\small Universidade do Minho} \\
{\small Ano lectivo 2006/2007}  \\
{\small Jo�o Saraiva}  \\
{\small Ficha Te�rico-Pr�tica N$�$10} \\ 
\end{tabular}
}

\author{}
\date{}


%-------------------- Inicio do Documento -------------------------------

\begin{document}

\maketitle


Este texto est� escrito em \textbf{literate Haskell}. Isto �, pode ser
interpretado como um documento \LaTeX\ ou como um puro programa na
linguagem Haskell.  Responda �s perguntas sobre Haskell neste pr�prio
ficheiro para assim produzir o programa e a sua documenta��o.


\section{Pretty Printing}




\section{Combinadores de Pretty Printing em Haskell}



\begin{code}
--
--
-- Processamento de Linguagens e Compila��o
-- 2006/2007
--

module PP_PLC where


import LRC_Pretty                         -- Biblioteca de Combiandores
                                          -- de pretty printing
\end{code}



\paragraph{Combinador besides (\textit{ao lado de}):} O combinador
\textit{ao lado de} \texttt{>|<} permite mostrar dois documentos um ao
lado do outro. Por exemplo, se pretendermos mostrar a palavra
\texttt{ola} ao lado de \texttt{mundo} fazemos:


\begin{code}

ola_besides_mundo   = "ola" >|< "mundo!"

\end{code}



De modo a mostrar o resultado de pretty printing, a biblioteca cont�m
a fun��o \texttt{render} que dado o documento e a tamanho da p�gina
(n�mero de colunas) mostrar a sua representa��o alindada.



\begin{exercicio}

Utilizando a fun��o \texttt{render}. responda �s seguinte
perguntas:

\begin{enumerate} 

\item Mostre o resultado de pretty printing numa
p�gina com tamanho $30$.

\item Considere agora que a p�gina tem tamanho $5$, qual o resultado
de pretty printing?

\end{enumerate}

\end{exercicio}


\begin{code}

show_ola_mundo_30      = render ola_besides_mundo 30

show_ola_mundo_5      = render ola_besides_mundo 5

\end{code}


\begin{exercicio}

O combinador \textit{ao lado de com espa�o} \texttt{>\#<} inclu� um
espa�o entre os dois documentos. Utilize este combinador de modo a
mostrar as palavras \texttt{ola} e \texttt{mundo} separadas por uma
espa�o.

\end{exercicio}

\begin{code}

ola_besides_mundo'  = "ola" >#< "mundo!"

show_ola_mundo''    = render ola_besides_mundo' 30

\end{code}


\paragraph{Combinador above (\textit{por cima de}):} O combinador
\textit{por cima de} \texttt{>-<} permite mostrar dois documentos um
por cima do outro. 





\begin{exercicio}

Utilize o combinador \textit{por cima de} de modo a mostrar a palavra
\texttt{ola} por cima da palavra \texttt{mundo}.

\end{exercicio}

\begin{code}

ola_above_mundo     = "ola" >-< "mundo!"

show_ola_mundo'''   = render ola_above_mundo 30

\end{code}


Os combinadores \textit{ao lado de} e \textit{por cima de} pode sem
generalizado de modo a trabalharem sobre listas de documentos. Isto �,
listas de documentos em que os seus elememtos s�o mostrados na
horizontal e vertical, respectivamente.


\begin{code}

hlist, vlist :: PP a => [a] -> PP_Doc
hlist = foldr (>|<) (text "")
vlist = foldr (>-<) (text "")

\end{code}


\begin{exercicio}

Defina uma lista com quatro elementos que s�o as palavras \textit{ola}
e \textit{mundo} combinadas verticalmente. Qual o efeito de utilizar o
combinador \texttt{hlist} para combinar essa lista horizontalmente?

\end{exercicio}


\begin{code}

quatro_ola_above_mundo = [ ola_above_mundo , ola_above_mundo
                         , ola_above_mundo , ola_above_mundo ]

escada = render (hlist quatro_ola_above_mundo)


escada_30 = escada 30

escada_15 = escada 15
\end{code}


\paragraph{Combinador Indenta��o:} O combinador \texttt{indent}
permite mostrar um documento indentado n caracteres. 



\begin{code}

ola_above_mundo'     = "ola" >-< (indent 4 "mundo!")

show_ola_mundo''''   = render ola_above_mundo' 30

\end{code}




\begin{code}


quatro_ola_besides_mundo = [ ola_besides_mundo' , ola_besides_mundo'
                           , ola_besides_mundo' , ola_besides_mundo' ]


show_ola_mundo'''''  = render (fill quatro_ola_besides_mundo) 30

show_ola_mundo'''''' = render (fill quatro_ola_besides_mundo) 60

\end{code}



\begin{exercicio} 

Considere o tipo de dados abstracto \textit{Tabela} que define tabelas
abstractas apresentado na Ficha Te�rico-Pr�tica n$�$10. Defina a
fun��o de pretty printing \texttt{ppTabela} que apresenta uma tabela
na sua representa��o HTML.


\end{exercicio}


\begin{code}

-- ppTabela = 

\end{code}


\begin{exercicio} 

Considere o seguinte tipo de dados alg�brico que define a
\textit{sintaxe abstracta} de um sub-conjunto da linguagem C. Mais
concertamente, uma linguagem que � um subconjunto da lingaugem
\textsf{Bc}.


\begin{code}
type Bc = [Stat]

data Stat = Assign  String Exp 
	  | If_t_e  Exp [Stat] [Stat] 
	  | While   Exp [Stat] 

data Exp = AddExp Exp Exp 
	 | AndExp Exp Exp 
	 | DivExp Exp Exp 
	 | EqExp  Exp Exp 
	 | Factor Fac 
	 | GTExp  Exp Exp 
	 | LTExp  Exp Exp 
	 | MinExp Exp 
	 | MulExp Exp Exp 
	 | OrExp  Exp Exp 
	 | SubExp Exp Exp 

data Fac = BoolConst Bool 
	 | IntConst  Integer
	 | RealConst Float 
	 | Ident String 
\end{code}


Responda �s seguintes perguntas:


\begin{enumerate} 

\item Construa uma frase em sintaxe abstracta que
pertence a esta linguagem.

\item Utilizando os combinadores apesentados, definaa fun��o de
pretty printing \texttt{ppBC}.

\end{enumerate}


\end{exercicio}


\begin{code}

-- frase_bc = 

-- ppBC

\end{code}




\section{Combinadores com Layout Multiplo}




\begin{code}

hv_helloworld = join ("ola" >#< "mundo" >//< "ola" >-< "mundo")
hor_hw        = render hv_helloworld 20

hor_hw'       = render hv_helloworld 6

\end{code}


\begin{code}
(>^<)   :: (PP a, PP b) => a -> b -> PP_Doc
a >^< b = join (a >//< b)

\end{code}




\begin{exercicio} 

Considere de novo o seguinte tipo de dados alg�brico que define a
sintaxe abstracta da linguagem \textsf{Bc}. Apresente uma nova vers�o
da fun��o de pretty printing \texttt{ppBC'} que usa agora esta
possibilidade de layout multiplos.


\end{exercicio}


\begin{code}


\end{code}



\end{document}




