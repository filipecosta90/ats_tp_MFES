


msp : Msp.lhs MainMsp.hs
	ghc -o msp MainMsp.hs

doc: SL.lhs Msp.lhs
	pdflatex Msp.lhs
	pdflatex SL.lhs

clean:
	rm -rf *.aux *.hi *.log *.o 

cleanall:
	rm -rf *.aux *.hi *.log *.o ./msp *.pdf