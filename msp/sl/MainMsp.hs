module Main where

import Msp

import System.Environment

main :: IO ()
main =  
  do args <- getArgs
     strMsp <- readFile (head args)
     let progMsp = (read strMsp) :: Msp 
     haMsp progMsp
     putStrLn "End!"
