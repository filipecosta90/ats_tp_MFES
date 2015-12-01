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



\def\C2{$C_{\_\_}^{2}$}

\newtheorem{exercicio}{}[section]

\title{\sf   Processamento de Linguagens e Compiladores \\
\begin{tabular}{c}
{\small LMCC}, {\small Universidade do Minho} \\
{\small Ano lectivo 2006/2007}  \\
{\small Jo�o Saraiva}  \\
{\small Ficha Te�rico-Pr�tica N$�$11} \\ 
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


\section{MSP em Haskell}


\begin{code}
--
--
-- Processamento de Linguagens e Compila��o
-- 2006/2007
--


module Msp where

import Data.Char
import Data.List


\end{code}


A estrutura abstracta de MSP � definida pelo seguinte tipo de dados
alg�brico:


\begin{code}

data Msp   = Msp [Decl] [Instr]
	   deriving (Show , Read , Eq , Ord)

data Decl  = Decl String Integer Integer   -- name size address
	   deriving (Show , Read , Eq , Ord)

data Instr = ALabel String      -- label

           | Call String        -- call a function
           | Ret                -- return from a function

           | Add                -- arithmetic and boolean instructions
           | And 
           | Div 
           | Eq 
           | Gt 
           | Lt 
           | Minus 
           | Mul 
           | Neq 
           | Not 
           | Or 
           | Sub 

           | Halt                -- Halt the machine
                                 -- IO:
           | IIn                 --    input
           | IOut                --    output  
           | IOutC               --    output char

           | Jump  String        -- Jump Instructions
           | Jumpf String 

           | Pusha String        -- Stack Operations
           | Pushi Integer
           | Load 
           | Store 
	   deriving (Show , Read , Eq , Ord)
\end{code}



Um exemplo de um programa MSP em nota��o abstracta � seguinte:

\begin{code}
prog1 = Msp [ Decl "a1" 0  10 , Decl "a2" 10  1 ]
            [ Pushi 12
            , IOut 
            , Halt ]

-- Ou usando read para ler de uma string:

prog2 :: Msp
prog2 = read "Msp [ Decl a1 0  10, Decl a2 10  1 ] [ Pushi 12, IOut, Halt ]"

\end{code}





\begin{exercicio}

Escreva um programa MSP directamente em Haskell para modelar o
seguinte programana linguagem \C2:

\begin{verbatim}
int aux;
int f;
f = 3 * 4;
aux = f + 4;
\end{verbatim}


\end{exercicio}


\begin{code}



\end{code}





\begin{exercicio} 

Utilize os combinadores de Pretty Print apresentados na aula anterior
de modo a ter uma fun��o que produz MSP em nota��o concreta e
\textit{alindada}.

\end{exercicio}


\begin{code}



\end{code}






\section{M�quina Virtual de MSP em Haskell}






\subsection{Stack}


\begin{code}

emptyStack = []

-- push :: Int -> [Int] -> [Int]
push v stack = v : stack

pop  []     = error "Pop of an empty stack"
pop  (h:t)  = t

top []      = error "Top of an empty stack!"
top (h:t)   = h

\end{code}

\section{Symbols}



\begin{code}

type Symbol = (String        -- name
              ,Integer       -- size
              ,Integer)      -- address in the heap

-- lookupSymb :: String -> [Symbol] -> Symbol
lookupSymb n []     =  error "Symbol not in the heap!"
lookupSymb n (h:t)  |  n == n'    = h
                    |  otherwise  = lookupSymb n t 
  where (n',s,a) = h
\end{code}  


\subsection{Heap}


\begin{code}

-- allocMem :: [b] -> Int -> [b]
allocMem mem nbytes = mem ++ (map (\v -> 0) [1..nbytes])


-- allocMem mem nbytes = mem ++ (replicate nbytes 0)


--updateMemAddress :: [a] -> Int -> a -> [a]
updateMemAddress (h:t) 0 v = v:t
updateMemAddress (h:t) i v = h : updateMemAddress t (i-1) v


-- getMemAddress :: [a] -> Int -> a
getMemAddress mem address = ith mem  address

ith (h:t) 0 = h
ith (h:t) n = ith t (n-1)

\end{code}


\subsection{Debug e Trace}


\begin{code}

debug' p (stack,heap,symbs) = 
  do  putStrLn ""
      putStrLn ("Instruction: " ++ (show $ head p))
      putStrLn ("Stack      : " ++ (show stack))
      putStrLn ("Heap       : " ++ (show heap))
--      getChar      


debug p (stack,heap,symbs) = do putStr ""

\end{code}



\subsection{A M�quina Virtual haMsp}



\begin{code}


haMsp :: Msp -> IO ([Integer], [Integer], [Symbol])
haMsp prog = runMspProg prog 

runMspProg :: Msp -> IO ([Integer], [Integer], [Symbol])
runMspProg (Msp decls instr) = runMSP instr instr initialState
  where (heap,symb)  = runMspDecls decls ([],[])
        initialState = (emptyStack,heap,symb)

\end{code}




As declara��es de vari�veis da heap s�o armazenadas numa tabela de
s�mbolos.

\begin{code}

runMspDecls ((Decl n a s) : t) (heap,symbs) =  runMspDecls t (heap',symbs')
  where  symbs' = (n,a,s) : symbs
         heap'  = allocMem heap s
  
runMspDecls [] (heap,symbs)  = (heap,symbs)

\end{code}



Instru��es para parar a m�quina:

\begin{code}


runMSP prog [] state = return state

runMSP prog (Halt :t) (stack,heap,symbs) = 
    do putStrLn ("Stack:"   ++ show stack)
       putStrLn ("Heap:"    ++ show heap)
       putStrLn ("Symbols:" ++ show symbs)
       return (stack,heap,symbs)

\end{code}



\subsection{Stack Instructions}

\begin{code}
runMSP prog p@(Pushi i:t) state = 
    do  debug p state
        let (stack,heap,symbs)  =  state
        let state'              =  (push i stack , heap , symbs)
        runMSP prog t state' 

runMSP prog p@(Pusha n :t) state = 
    do  debug p state
        let (stack,heap,symbs)  =  state
        let  (n',a,s)           =  lookupSymb n symbs  
        let  state'             =  (push a stack , heap , symbs)
        runMSP prog t state' 

runMSP prog p@(Store :t) state  =
    do  debug p state 
        let (stack,heap,symbs)  =  state
        let v                   =  top stack
        let stack'              =  pop stack
        let address             =  top stack'
        let heap'               =  updateMemAddress heap address v
        runMSP prog t (pop stack' , heap' , symbs)

runMSP prog p@(Load :t) state  =
    do  debug p state 
        let (stack,heap,symbs)  =  state
        let address             =  top stack
        let v                   =  getMemAddress heap address
        let stack'              =  push v (pop stack)
        runMSP prog t (stack' , heap , symbs)

\end{code}



\paragraph{Calling Functions}


\begin{code}


runMSP prog p@(Call n:t) state = 
    do  debug p state
        let (stack,heap,symbs)  =  state
        let pc       =  npc (length prog)  (length t)
        let stack'   =  push pc stack
        jmp prog n (stack',heap,symbs)


runMSP prog p@(Ret : t) state =  
    do  debug p state
        let (stack,heap,symbs)  =  state
        let v                   =  top stack
        let stack'              =  pop stack
        let prog'               =  drop (toInt' v) prog 
        runMSP prog prog' (stack',heap,symbs)
 
\end{code}



\paragraph{IO Instructions}

\begin{code}
runMSP prog p@(IOut :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
--        mapM putChar (show $ top stack)
        putStrLn (show $ top stack)
        runMSP prog t (pop stack , heap , symbs)

runMSP prog p@(IOutC :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        putChar (chr (toInt' (top stack)))
        runMSP prog t (pop stack , heap , symbs)

runMSP prog (IIn :t) (stack,heap,symbs) = 
    do  putStrLn ("Introduza um inteiro:")
        v <- getLine          
        let v' = (read v):: Integer
--        let (address:stack') = stack 
--        let heap' = updateMemAddress heap address v'       
        runMSP prog t (v':stack , heap , symbs)
\end{code}


\paragraph{Arithmetic Instructions}

\begin{code}
runMSP prog p@(Add :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let stack'''      =  push (op1 + op2) stack''
        runMSP prog t (stack''' , heap , symbs)

runMSP prog p@(Mul :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let stack'''      =  push (op1 * op2) stack''
        runMSP prog t (stack''' , heap , symbs)

runMSP prog p@(Sub :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let stack'''      =  push (op2 - op1) stack''
        runMSP prog t (stack''' , heap , symbs)

runMSP prog p@(Div :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let stack'''      =  push (op2 `div` op1) stack''
        runMSP prog t (stack''' , heap,symbs)


runMSP prog p@(Eq :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let v  = if op1 == op2 then 1 else 0
        let stack'''      =  push v stack''
        runMSP prog t (stack''' , heap , symbs)

runMSP prog p@(Neq :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let v  = if op1 == op2 then 0 else 1
        let stack'''      =  push v stack''
        runMSP prog t (stack''' , heap, symbs)


runMSP prog p@(Gt :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let v  = if op1 < op2 then 1 else 0
        let stack'''      =  push v stack''
        runMSP prog t (stack''' , heap,symbs)


runMSP prog p@(Lt :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let v  = if op1 > op2 then 1 else 0
        let stack'''      =  push v stack''
        runMSP prog t (stack''' , heap,symbs)
\end{code}


\paragraph{Jump Instructions}


\begin{code}

runMSP prog p@(Jump l:t) state = 
    do  debug p state
        jmp prog l state

runMSP prog p@(Jumpf l:t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (v:stack')  =  stack
        if v == 1 then runMSP prog t (stack',heap,symbs)
                  else jmp prog l (stack',heap,symbs)

runMSP prog (ALabel n:t) state = runMSP prog t state


jmp prog label state = runMSP prog prog' state
  where (Just npc) = elemIndex (ALabel label) prog
        prog'      = drop (npc+1) prog


npc :: Int -> Int -> Integer
npc l1 l2 =  toInteger' (l1 - l2)

toInteger' :: Int -> Integer
toInteger' i = read (show i)

toInt' :: Integer -> Int
toInt' i = read (show i)

\end{code}



\subsection{Exemplos de Programas}


\begin{code}
prog3 = Msp [ Decl "a1" 0  10 , Decl "a2" 10  1 ]
            [ Pusha "a2"
            , IIn
            , Pusha "a2"
            , Load
            , IOut
            , Halt ]
\end{code}

\begin{code}
prog4 = Msp [ Decl "a1" 0  10 , Decl "a2" 10  1 ]
            [ Pusha "a2"
            , IIn
            , Store
            , Pushi 88    -- 'X'
            , IOutC
            , Halt
            ]

\end{code}



\section{Fun��o Principal}

\begin{code}

\end{code}

\end{document}




