

\begin{code}


module Msp where

import Data.Char
import Data.List
import Data_Lazy
import LrcPrelude

\end{code}


\section{Stack}


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

type Symbol = (String    -- name
              ,Int       -- size
              ,Int)      -- address in the heap

-- lookupSymb :: String -> [Symbol] -> Symbol
lookupSymb n []     =  error "Symbol not in the heap!"
lookupSymb n (h:t)  |  n == n'    = h
                    |  otherwise  = lookupSymb n t 
  where (n',s,a) = h
\end{code}  


\section{Heap}


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




\begin{code}

hamsp prog = runMSP prog prog (emptyStack,heap,symbs)  -- (emptyStack,heap,symbs)
  where heap = []
        symbs = []

runMSP prog []   state            = return state

runMSP prog (C_Halt_1 :t) (stack,heap,symbs) = 
    do putStrLn (show stack)
       putStrLn (show heap)
       putStrLn (show symbs)
       return (stack,heap,symbs)


\end{code}



\begin{code}

runMSP prog (C_Data_1 :t) state =  runMSP prog t state

runMSP prog (C_Cod_1 :t) state =  runMSP prog t state

runMSP prog (C_Var_1 n a s ty : t) (stack,heap,symbs) = runMSP prog t (stack,heap',symbs')
  where  symbs' = (n,a,s) : symbs
         heap'  = allocMem heap s
  

\end{code}



\section{Stack Instructions}

\begin{code}
runMSP prog p@(C_Pushi_1 i:t) state = 
    do  debug p state
        let (stack,heap,symbs)  =  state
        let state'              =  (push i stack , heap , symbs)
        runMSP prog t state' 

runMSP prog p@(C_Pusha_1 n i:t) state = 
    do  debug p state
        let (stack,heap,symbs)  =  state
        let  (n',a,s)           =  lookupSymb n symbs  
        let  state'             =  (push a stack , heap , symbs)
        runMSP prog t state' 

runMSP prog p@(C_Store_1 :t) state  =
    do  debug p state 
        let (stack,heap,symbs)  =  state
        let v                   =  top stack
        let stack'              =  pop stack
        let address             =  top stack'
        let heap'               =  updateMemAddress heap address v
        runMSP prog t (pop stack' , heap' , symbs)

runMSP prog p@(C_Load_1 :t) state  =
    do  debug p state 
        let (stack,heap,symbs)  =  state
        let address             =  top stack
        let v                   =  getMemAddress heap address
        let stack'              =  push v (pop stack)
        runMSP prog t (stack' , heap , symbs)

\end{code}



\paragraph{Calling Functions}


\begin{code}


runMSP prog p@(C_Call_1 n:t) state = 
    do  debug p state
        let (stack,heap,symbs)  =  state
        let pc       =  npc (length prog)  (length t)
        let stack'   =  push pc stack
        jmp prog n (stack',heap,symbs)


runMSP prog p@(C_Ret_1 : t) state =  
    do  debug p state
        let (stack,heap,symbs)  =  state
        let v                   =  top stack
        let stack'              =  pop stack
        let prog'               =  drop (toInt' v) prog 
        runMSP prog prog' (stack',heap,symbs)
 
\end{code}



\paragraph{IO Instructions}

\begin{code}
runMSP prog p@(C_IOut_1 :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        putStrLn (show $ top stack)
        runMSP prog t (pop stack , heap , symbs)

runMSP prog p@(C_IOutC_1 :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        putStrLn (show $ top stack)
        runMSP prog t (pop stack , heap , symbs)


runMSP prog (C_IIn_1 :t) (stack,heap,symbs) = 
    do  putStrLn ("Introduza um inteiro:")
        v <- getLine          
        let v' = (read v):: Integer

        runMSP prog t (push v' stack , heap , symbs)
\end{code}


\paragraph{Arithmetic Instructions}

\begin{code}
runMSP prog p@(C_Add_1 :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let stack'''      =  push (op1 + op2) stack''
        runMSP prog t (stack''' , heap , symbs)

runMSP prog p@(C_Mul_1 :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let stack'''      =  push (op1 * op2) stack''
        runMSP prog t (stack''' , heap , symbs)

runMSP prog p@(C_Sub_1 :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let stack'''      =  push (op2 - op1) stack''
        runMSP prog t (stack''' , heap , symbs)

runMSP prog p@(C_Div_1 :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let stack'''      =  push (op2 `div` op1) stack''
        runMSP prog t (stack''' , heap,symbs)


runMSP prog p@(C_Eq_1 :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let v  = if op1 == op2 then 1 else 0
        let stack'''      =  push v stack''
        runMSP prog t (stack''' , heap , symbs)

runMSP prog p@(C_Neq_1 :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let v  = if op1 == op2 then 0 else 1
        let stack'''      =  push v stack''
        runMSP prog t (stack''' , heap, symbs)


runMSP prog p@(C_Gt_1 :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let v  = if op1 < op2 then 1 else 0
        let stack'''      =  push v stack''
        runMSP prog t (stack''' , heap,symbs)


runMSP prog p@(C_Lt_1 :t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (op1:stack')  =  stack
        let (op2:stack'') =  stack'
        let v  = if op1 > op2 then 1 else 0
        let stack'''      =  push v stack''
        runMSP prog t (stack''' , heap,symbs)
\end{code}


\paragraph{Jump Instructions}


\begin{code}

runMSP prog p@(C_Jump_1 l:t) state = 
    do  debug p state
        jmp prog l state

runMSP prog p@(C_Jumpf_1 l:t) (stack,heap,symbs) = 
    do  debug p (stack,heap,symbs) 
        let (v:stack')  =  stack
        if v == 1 then runMSP prog t (stack',heap,symbs)
                  else jmp prog l (stack',heap,symbs)

runMSP prog (C_ALabel_1 n:t) state = runMSP prog t state


jmp prog label state = runMSP prog prog' state
  where (Just npc) = elemIndex (C_ALabel_1 label) prog
        prog'      = drop (npc+1) prog


npc :: Int -> Int -> Integer
npc l1 l2 =  toInteger' (l1 - l2)

toInteger' :: Int -> Integer
toInteger' i = read (show i)

toInt' :: Integer -> Int
toInt' i = read (show i)

\end{code}



\begin{code}

debug p (stack,heap,symbs) = 
  do  putStrLn ""
      putStrLn ("Instruction: " ++ (show $ head p))
      putStrLn ("Stack      : " ++ (show stack))
      putStrLn ("Heap       : " ++ (show heap))
--      getChar      

\end{code}



\begin{code}
-- Testing


prog1 = [ C_Var_1 (C_Ident_1 "a1") 0 10 C_Inttype_1
        , C_Var_1 (C_Ident_1 "a2") 10 1 C_Inttype_1
        , C_Pushi_1 12
        , C_IOut_1 
        , C_Halt_1 ]


prog11 = [ C_IIn_1  
         , C_IOut_1 
         , C_Halt_1 ]


prog2 = [ C_Pushi_1 12 
        , C_Pushi_1 20
        , C_Add_1 
        , C_Pushi_1 2     
        , C_Mul_1 
        , C_IOut_1 
        , C_Halt_1 ]


prog3 = [ C_Pushi_1 20 
        , C_Pushi_1 20
        , C_Eq_1 
        , C_IOut_1 
        , C_Halt_1 ]


prog4 = [ C_Pushi_1 12 
        , C_Pushi_1 20
        , C_Add_1 
        , C_Jump_1 (C_Ident_1 "l1")
        , C_Pushi_1 2     
        , C_Mul_1 
        , C_ALabel_1 (C_Ident_1 "l1")
        , C_IOut_1 
        , C_Halt_1 ]


prog5 = [ C_Pushi_1 20
        , C_Pushi_1 21
        , C_Eq_1
        , C_Jumpf_1 (C_Ident_1 "l1")
        , C_Pushi_1 2     
        , C_IOut_1
        , C_ALabel_1 (C_Ident_1 "l1")
        , C_Pushi_1 3     
        , C_IOut_1 
        , C_Halt_1 ]



prog6 = [ C_Var_1 (C_Ident_1 "a1") 0  3 C_Inttype_1
        , C_Var_1 (C_Ident_1 "a2") 3  2 C_Inttype_1
        , C_Pusha_1 (C_Ident_1 "a2") 1
        , C_IOut_1 
        , C_Halt_1 ]




prog7 = [ C_Var_1 (C_Ident_1 "a1") 0  3 C_Inttype_1
        , C_Var_1 (C_Ident_1 "a2") 3  2 C_Inttype_1
        , C_Pusha_1 (C_Ident_1 "a2") 1
        , C_Pushi_1 34
        , C_Store_1
        , C_Halt_1 ]


prog8 = [ C_Var_1 (C_Ident_1 "a1") 0  3 C_Inttype_1
        , C_Var_1 (C_Ident_1 "a2") 3  2 C_Inttype_1
        , C_Pusha_1 (C_Ident_1 "a2") 1
        , C_Pushi_1 34
        , C_Store_1
        , C_Call_1 (C_Ident_1 "main")
        , C_Pushi_1 99
        , C_IOut_1 
        , C_Halt_1 
        , C_ALabel_1 (C_Ident_1 "main")
        , C_Pushi_1 66
        , C_IOut_1 
        , C_Ret_1 ]





{-
char stri[10];

main() : int
{ 
  int x ;

  x = 4;
  print x;
};
-}

progR = [C_Data_1
        ,C_Var_1 (C_Ident_1 "main_1") 0 1 C_Inttype_1
        ,C_Var_1 (C_Ident_1 "x_1") 1 1 C_Inttype_1
        ,C_Var_1 (C_Ident_1 "f_1") 2 1 C_Inttype_1
        ,C_Var_1 (C_Ident_1 "factorial_2") 3 1 C_Inttype_1
        ,C_Var_1 (C_Ident_1 "n_2") 4 1 C_Inttype_1
        ,C_Var_1 (C_Ident_1 "res_2") 5 1 C_Inttype_1
        ,C_Cod_1
        ,C_Call_1 (C_Ident_1 "main")
        ,C_Halt_1
        ,C_ALabel_1 (C_Ident_1 "main")
        ,C_Pusha_1 (C_Ident_1 "x_1") 1
        ,C_Pushi_1 5
        ,C_Store_1
        ,C_Pusha_1 (C_Ident_1 "f_1") 1
        ,C_Pusha_1 (C_Ident_1 "n_2") 1
        ,C_Pusha_1 (C_Ident_1 "x_1") 1
        ,C_Load_1
        ,C_Store_1
        ,C_Call_1 (C_Ident_1 "factorial")
        ,C_Pusha_1 (C_Ident_1 "factorial_2") 1 
        ,C_Load_1
        ,C_Store_1
        ,C_Pusha_1 (C_Ident_1 "f_1") 1
        ,C_Load_1
        ,C_IOut_1
        ,C_Ret_1
        ,C_ALabel_1 (C_Ident_1 "factorial")
        ,C_Pusha_1 (C_Ident_1 "res_2") 1
        ,C_Pushi_1 1
        ,C_Store_1
        ,C_ALabel_1 (C_Ident_1 "while_1")
        ,C_Pusha_1 (C_Ident_1 "n_2") 1
        ,C_Load_1
        ,C_Pushi_1 0
        ,C_Gt_1
        ,C_Jumpf_1 (C_Ident_1 "e_whl_1")
        ,C_Pusha_1 (C_Ident_1 "res_2") 1 
        ,C_Pusha_1 (C_Ident_1 "res_2") 1
        ,C_Load_1
        ,C_Pusha_1 (C_Ident_1 "n_2") 1
        ,C_Load_1
        ,C_Mul_1
        ,C_Store_1
        ,C_Pusha_1 (C_Ident_1 "n_2") 1
        ,C_Pusha_1 (C_Ident_1 "n_2") 1
        ,C_Load_1
        ,C_Pushi_1 1
        ,C_Sub_1
        ,C_Store_1
        ,C_Jump_1 (C_Ident_1 "while_1")
        ,C_ALabel_1 (C_Ident_1 "e_whl_1")
        ,C_Pusha_1 (C_Ident_1 "factorial_2") 1 
        ,C_Pusha_1 (C_Ident_1 "res_2") 1
        ,C_Load_1
        ,C_Store_1
        ,C_Ret_1]


       


\end{code}
