package gram;

//gram imports
import gram.i.iAdaptor;
import gram.i.types.*;

//antlr imports
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.tree.Tree;

//tom imports
import tom.library.utils.Viewer;
import tom.library.sl.*;

//java api imports
import java.util.*;
import java.lang.*;
import java.io.*;
import java.lang.Math;

//For metrics parse file
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
  %include{sl.tom}
  %include{util/HashMap.tom}
  %include{util/ArrayList.tom}
  %include{util/types/Collection.tom}
  %include{util/types/Set.tom}
  %include{../genI/gram/i/i.tom}
  %include{util/TreeSet.tom}

  private String actualFunctionName;
  HashMap<String, Argumentos> functionSignatures;
  HashMap<String, Argumentos> functionMap;
  HashMap<String, Integer> functionComments;
  private boolean callReturnNeeded;
  private int memAdress;
  StringBuilder functionsDeclarations;

  //Main Metrics Variables 
  String main_Description;
  double main_CYCLO;
  double main_LOC;
  double main_OPERATION;
  double main_NOM;
  double main_CLASS;
  double main_NOC;
  double main_PACKAGE;
  double main_CALLS;
  double main_FANOUT;
  double main_ANDC;
  double main_AHH;

  String metrics_STANDARDS;


  // Map of Metrics Variables 
  Integer numberFunctions;
  HashMap <String,Integer> functionsNumberParameters;
  HashMap <String,Integer> cyclomaticComplexityMap;
  HashMap <String,Integer> LongMap;
  HashMap <String,Integer> nOperationsMap;
  HashMap <String,Integer> nOperationsComparisonsMap;
  HashMap <String,Integer> opAtribMap;
  HashMap <String,Integer> nIncrDecrOpMap;
  HashMap <String,Integer> lnMap;
  HashMap <String,Integer> iplMap;
  HashMap <String,Integer> argsMap;
  Integer totalLinesOfCode;
  HashMap <String, TreeSet<String> > usedIdsMap;
  HashMap <String, Integer> numberIdsCallsMap;
  HashMap <String, TreeSet<String> > usedArgsMap;
  HashMap <String, TreeSet<String> > unusedArgsMap;
  HashMap <String, TreeSet<String> > unusedDeclarationsMap;
  HashMap <String, TreeSet <String> > operationsPerFunctionMap;
  HashMap <String , List<Double> > pyramidMetrics;
  HashMap <String , Integer> mapRefactor;

  /*** Separated Metrics ***/
  /** OpNum **/
  private static int nrMais;
  private static int nrVezes;
  private static int nrDivide;
  private static int nrMenos;
  private static int nrMod;

  /** OpComp **/
  private static int nrMaior;
  private static int nrMenor;
  private static int nrMaiorQ;
  private static int nrMenorQ;
  private static int nrDif;
  private static int nrIgual;

  /** OpInc **/
  private static int nrInc;
  private static int nrDec;

  /** OpAtribuicao **/
  private static int nrOpAtribAtrib;
  private static int nrOpAtribMult;
  private static int nrOpAtribDiv;
  private static int nrOpAtribSoma;
  private static int nrOpAtribSub;

  public Main() {
    this.main_Description = "global";
    this.main_CYCLO = 0.0;
    this.main_LOC = 0.0;
    this.main_OPERATION = 0.0;
    this.main_NOM = 0.0;
    this.main_CLASS = 0.0;
    this.main_NOC = 1.0;
    this.main_PACKAGE = 1.0;
    double main_CALLS = 0.0;
    double main_FANOUT = 0.0 ;
    double main_ANDC = 0.0;
    double main_AHH = 0.0;
    this.metrics_STANDARDS = "standards_c.txt";
    actualFunctionName = "";
    functionSignatures = new HashMap<String, Argumentos>();
    callReturnNeeded = true;
    functionsDeclarations = new StringBuilder();
    memAdress = 0;
    Integer numberFunctions = 0;
    functionsNumberParameters = new HashMap<String,Integer>();
    cyclomaticComplexityMap = new HashMap<String,Integer>();
    LongMap = new HashMap<String,Integer>();
    Integer totalLinesOfCode = 0;
    this.nOperationsMap = new HashMap <String,Integer>();
    this.operationsPerFunctionMap = new HashMap <String, TreeSet <String> >();
    this.nOperationsComparisonsMap = new HashMap <String,Integer>();
    this.nIncrDecrOpMap = new HashMap <String,Integer>();
    this.opAtribMap = new HashMap <String,Integer>();
    this.functionComments = new HashMap<String,Integer>();
    this.cyclomaticComplexityMap = new HashMap <String, Integer>();
    this.lnMap = new HashMap <String,Integer>();
    this.argsMap = new HashMap <String, Integer>();
    this.usedIdsMap = new HashMap <String, TreeSet<String> >();
    this.numberIdsCallsMap = new HashMap <String, Integer>();
    this.usedArgsMap = new HashMap <String, TreeSet<String> >();
    this.unusedArgsMap = new HashMap <String, TreeSet<String> >();
    this.unusedDeclarationsMap = new HashMap <String, TreeSet<String>>();
    this.iplMap = new HashMap <String,Integer>();
    this.pyramidMetrics = new HashMap <String, List<Double>>();
    this.mapRefactor = new HashMap <String, Integer> ();
  }

  public static void main(String[] args) {
    try {
      iLexer lexer = new iLexer(new ANTLRInputStream(System.in));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      iParser parser = new iParser(tokens);
      // Parse the input expression
      Tree b = (Tree) parser.prog().getTree();
      Instrucao p = (Instrucao) iAdaptor.getTerm(b);
      Main main = new Main();

      try {
        ArrayList<Integer> numInstrucao = new ArrayList<Integer>();
        numInstrucao.add(1);
        `TopDown(CollectNumberFuncs( main.functionSignatures, main.argsMap )).visit(p);
        `TopDown(CollectCiclomaticComplex( main.functionSignatures, main.cyclomaticComplexityMap )).visit(p);
        `TopDown(CollectComments( main.functionSignatures, main.functionComments )).visit(p);
        `TopDown(CollectOperations( main.functionSignatures, main.nOperationsMap, main.operationsPerFunctionMap )).visit(p);
        `TopDown(CollectNumOperationsComparisons( main.functionSignatures, main.nOperationsComparisonsMap )).visit(p);
        `TopDown(CollectNumOperationsIncrDecr( main.functionSignatures, main.nIncrDecrOpMap )).visit(p);
        `TopDown(CollectOpAtrib( main.functionSignatures, main.opAtribMap )).visit(p);
        `TopDown(collectIPL( main.iplMap )).visit(p);
        `TopDown(collectUsedIdsMap( main.usedIdsMap, main.numberIdsCallsMap )).visit(p);
        `TopDown(collectUnusedArguments(  main.unusedArgsMap , main.usedIdsMap )).visit(p);
        `TopDown(collectUnusedDeclarations(  main.unusedDeclarationsMap , main.usedIdsMap )).visit(p);
        `TopDown(collectLN(main.lnMap)).visit(p);

        Instrucao p2 = p;
        int numInst = numInstrucao.get(0)-1;
        LComentarios c = `Vazio();
        Expressao numInstExps = `Expressoes(Print(c,c,c,Int(numInst),c,c),Print(c,c,c,Char("#"),c,c));
        NumToInt n = new NumToInt(1);
        String numInstString = main.compileAnnotExpressoes(numInstExps, n);
        String instrucoes = "";
        if (args.length > 0) {
          if (args[0].equals("-fi") && args.length > 1) { 
            TreeSet<Integer> blocosMaisUsados = new TreeSet<Integer>();

            if(Main.parseFile(args[1],blocosMaisUsados)) { 
              numInstrucao.clear();
              numInstrucao.add(1);
              Instrucao p3 = `BottomUp(stratFaultInjectionWithKnowledge(numInstrucao, blocosMaisUsados)).visit(p2);
              instrucoes = main.compileAnnot(p3);
            } else { 
              System.out.println("Failed to parse blocks"); 
            } 
          }
          else if (args[0].equals("-bs")) {
            for ( String funcao : main.functionSignatures.keySet()){
              TreeSet argsNaoUsados = main.unusedArgsMap.get(funcao);
              Integer refactor = argsNaoUsados.size();
              main.mapRefactor.put( funcao , refactor );
            }
            for ( String funcao : main.functionSignatures.keySet()){
              TreeSet declaNaoUsados = main.unusedDeclarationsMap.get(funcao);
              Integer refactorDec = declaNaoUsados.size();
              Integer refactorArgs = main.mapRefactor.get(funcao);
              if (refactorDec > refactorArgs ){
                main.mapRefactor.put( funcao, refactorDec );
              }
            }
            Instrucao p3 = `TopDown(startBadSmells(main.usedIdsMap, main.mapRefactor, main.unusedDeclarationsMap )).visit(p);
            `TopDown(CollectNumberFuncs( main.functionSignatures, main.argsMap )).visit(p3);
            `TopDown(CollectCiclomaticComplex( main.functionSignatures, main.cyclomaticComplexityMap )).visit(p3);
            `TopDown(CollectComments( main.functionSignatures, main.functionComments )).visit(p3);
            `TopDown(CollectOperations( main.functionSignatures, main.nOperationsMap, main.operationsPerFunctionMap )).visit(p3);
            `TopDown(CollectNumOperationsComparisons( main.functionSignatures, main.nOperationsComparisonsMap )).visit(p3);
            `TopDown(CollectNumOperationsIncrDecr( main.functionSignatures, main.nIncrDecrOpMap )).visit(p3);
            `TopDown(CollectOpAtrib( main.functionSignatures, main.opAtribMap )).visit(p3);
            `TopDown(collectIPL( main.iplMap )).visit(p3);
            `TopDown(collectUsedIdsMap( main.usedIdsMap, main.numberIdsCallsMap )).visit(p3);
            `TopDown(collectUnusedArguments(  main.unusedArgsMap , main.usedIdsMap )).visit(p3);
            `TopDown(collectUnusedDeclarations(  main.unusedDeclarationsMap , main.usedIdsMap )).visit(p3);
            `TopDown(collectLN(main.lnMap)).visit(p3);
            instrucoes = main.compileAnnot(p3);
          }
          else {
            instrucoes = main.compileAnnot(p2);
          }
        }
        else {
          instrucoes = main.compileAnnot(p2);
        }
        String functionDeclarationsAndArguments = main.functionsDeclarations.toString();
        System.out.println(functionDeclarationsAndArguments + numInstString + instrucoes);
      } catch(VisitFailure e) {
        System.out.println("the strategy failed");
      }

      /* Export this representation to .dot file*/

      try{
        FileWriter out=new FileWriter("grafo.dot");
        Viewer.toDot(p,out);
      }
      catch (IOException e){
        System.out.println("ERROR in dot file"); 
      }

      /*************************************************** PRINTING METRICS VALUES ***********************************************************/

      /** 1) Metric to count number of functions **/
      main.numberFunctions = main.functionSignatures.size();
      try{
        File file = new File("metrics.txt");
        // creates the file
        file.createNewFile();
        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file); 
        // Writes the content to the file
        writer.write("Number of fuctions: " + main.numberFunctions +"\n");
        int maximo_complexidade = 0;  
        for ( String funcao : main.functionSignatures.keySet() ){
          int complexidadeActual =  main.cyclomaticComplexityMap.get(funcao); 
          if ( complexidadeActual > maximo_complexidade ){
            maximo_complexidade = complexidadeActual;
          }
        }
        main.main_CYCLO = maximo_complexidade;

        writer.write("Calculated Cyclomatic Complexity: "+ maximo_complexidade +"\n");
        /** 2) Metric to count the number of arguments per function **/
        writer.write("Number of Arguments per function:\n");
        for (String funcao : main.functionSignatures.keySet()){
          int nargs = main.argsMap.get(funcao);
          writer.write("\t" + funcao +  " : " + nargs + "\n");
        }

        /** 3) Metric to count Cyclomatic Complexity Per Function **/
        writer.write("Cyclomatic Complexity per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.cyclomaticComplexityMap.get(funcao)+"\n" );
        }

        /** 4) Metric to count Number Of Operators Per Function **/
        writer.write("Number Operators per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.nOperationsMap.get(funcao)+"\n" );
        }

        /** 5) Metric to count Number Of Unique Operators Per Function **/
        writer.write("Number Of Unique Operators per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          TreeSet distinctOpps =  main.operationsPerFunctionMap.get(funcao);
          writer.write("\t" + funcao + " : "+ distinctOpps.size() + " \n" );
        }


        /** 6) Metric to count Number Of Operands Per Function **/
        writer.write("Number Operands per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.numberIdsCallsMap.get(funcao)+"\n" );
        }

        /** 7) Metric to count Number Of Unique Operands Per Function **/
        writer.write("Number Of Unique Operands per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          TreeSet distinctOperands =  main.usedIdsMap.get(funcao);
          writer.write("\t" + funcao + " : "+ distinctOperands.size() + " \n" );
        }

        /**
         * Halstead Measures (lifted from www.sei.cmu.edu and https://en.wikipedia.org/wiki/Halstead_complexity_measures)
         * =================
         * The Halstead measures are based on four scalar numbers derived directly
         * from a program's source code:
         * n1 = the number of distinct operators
         * n2 = the number of distinct operands
         * N1 = the total number of operators
         * N2 = the total number of operands
         * From these numbers, five measures are derived:
         * Measure Symbol Formula
         * Program length N N= N1 + N2
         * Program vocabulary n n= n1 + n2
         * Volume V V= N * (LOG2 n)
         * Difficulty D D= (n1/2) * (N2/n2)
         * Effort E E= D * V
         */

        /** 8) Metric to Compute Halstead Measures Per Function **/
        writer.write("Halstead Measures per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){

          int totalOperands = main.numberIdsCallsMap.get(funcao);
          int totalOperators =  main.nOperationsMap.get(funcao);
          int programLength = totalOperands + totalOperators;

          TreeSet distinctOperandsTree =  main.usedIdsMap.get(funcao);
          int distinctOperands =  distinctOperandsTree.size(); 

          TreeSet distinctOppsTree =  main.operationsPerFunctionMap.get(funcao);
          int distinctOperators =    distinctOppsTree.size() ;

          int  vocabulary = distinctOperators + distinctOperands;

          double volume, difficulty, effort, time_program, delivered_bugs;
          if (distinctOperators > 0 && distinctOperands > 0)
          {
            volume = programLength * Math.log(vocabulary)/Math.log(2);

            /*The difficulty measure is related to the difficulty of 
              the program to write or understand, e.g. when doing code review. */
            difficulty = distinctOperators/2 * (totalOperands/distinctOperands); 
            /**/
            effort = difficulty * volume;
            time_program = effort / 18;
            delivered_bugs = volume / 3000;
            writer.write("\t" + funcao + " : " + "\n" );
            writer.write("\t\tVolume: " + volume + "\n" );
            writer.write("\t\tDifficulty: " + difficulty + "\n" );
            writer.write("\t\tEffort: " + effort + "\n" );
            writer.write("\t\tTime required to program: " + time_program + "\n" );
            writer.write("\t\tNumber of delivered bugs: " + delivered_bugs + "\n" );
          }
        }

        /** 9) Metric to count the Number Of Comments Per Function **/
        int number_comments = 0;
        for ( String funcao : main.functionSignatures.keySet() ){
          int comments =  main.functionComments.get(funcao); 
          number_comments += comments;
        }
        writer.write("Total comments present on file: " +  number_comments + "\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.functionComments.get(funcao)+"\n" );
        }

        /** 10) Metric for counting total number of lines per function 
         * Blank lines should not be counted
         * Comments should not be counted
         **/ 
        writer.write("Total lines of Code (LOC):\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.lnMap.get(funcao)+"\n" );
        }

        /** 11) Metric to count number path length of instructions*/
        writer.write("Instructions Path Lenght:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.iplMap.get(funcao)+"\n" );
        }

        /** 12) Metric to count Number of Operations Comparisons **/
        writer.write("Total Number of Operations Comparisons:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.nOperationsComparisonsMap.get(funcao)+"\n" );
        }    

        /** 13) Metric to count Number of Increment/Decrement Operations **/
        writer.write("Total Number of Increment/Decrement Operations:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.nIncrDecrOpMap.get(funcao)+"\n" );
        }     

        /** 14) Metric to count Number of Atribuicao Operations **/
        writer.write("Total Number of Atrib/Mult/Div/Soma/Sub:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.opAtribMap.get(funcao)+"\n" );
        }

        /** 15) Unused arguments per function  **/
        writer.write("Unused arguments per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          TreeSet argsNaoUsados = main.unusedArgsMap.get(funcao);
          writer.write("\t" + funcao + " ( "+ argsNaoUsados.size() + " unused argument ): " );
          Iterator<Integer> iteratorArgs = argsNaoUsados.iterator();
          while (iteratorArgs.hasNext()) {
            writer.write(iteratorArgs.next() + " ");
          }
          writer.write("\n" );
        }
        /** 16) Unused declarations per function  **/
        writer.write("Unused declarations per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          TreeSet declaNaoUsados = main.unusedDeclarationsMap.get(funcao);
          writer.write("\t" + funcao + " ( "+ declaNaoUsados.size() + " unused declarations ): " );
          Iterator<Integer> iteratorDecla = declaNaoUsados.iterator();
          while (iteratorDecla.hasNext()) {
            writer.write(iteratorDecla.next() + " ");
          }
          writer.write("\n" );
        }
        /** 17) Refactoring per function  **/
        writer.write("Refactoring avaibility per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          Integer flag = main.mapRefactor.get(funcao);
          String flagS = "not possible";
          if (flag != null ){
            if ( flag > 0 ){
              flagS = "possible";
            }
            writer.write("\t" + funcao + " : "+  flagS +"\n" );
          }
        }

        writer.flush();
        writer.close();
        /**************************************************************************************************************/

        BufferedWriter output = null;
        try {
          File fileParser = new File("metrics_to_gui.txt");
          output = new BufferedWriter(new FileWriter(fileParser));
          for (String funcao : main.functionSignatures.keySet() ){
            double CYCLO = (double) main.cyclomaticComplexityMap.get(funcao) ;
            double LOC = (double) main.lnMap.get(funcao) ;
            main.main_LOC += LOC;
            double OPERATION = (double) main.nOperationsMap.get(funcao) ;
            main.main_OPERATION += OPERATION;
            TreeSet distinctOppsTree =  main.operationsPerFunctionMap.get(funcao);
            double NOM = (double) distinctOppsTree.size()  ;
            main.main_NOM+=NOM;
            double CLASS = 1.0;
            main.main_CLASS = 1.0;
            double NOC = 1.0;
            main.main_NOC = 1.0;
            double PACKAGE = 1.0;
            main.main_PACKAGE = 1.0;
            TreeSet distinctOpps =  main.operationsPerFunctionMap.get(funcao);
            double CALLS = (double) distinctOpps.size();
            main.main_CALLS += CALLS;
            double FANOUT = 1.0;
            main.main_FANOUT = 1.0;
            double ANDC = 0.0;
            double IPL = main.iplMap.get(funcao);
            double AHH = 1 / IPL;
            output.write(funcao+";");
            double CYCLO_PER_LOC = CYCLO / LOC;
            output.write ( String.format("%.2f", CYCLO_PER_LOC )  + ";" );
            double LOC_PER_OPERATION = LOC / OPERATION;
            output.write ( String.format("%.2f", LOC_PER_OPERATION )  + ";" );
            double NOM_PER_CLASS = NOM / CLASS;
            output.write ( String.format("%.2f", NOM_PER_CLASS )  + ";" );
            double NOC_PER_PACKAGE = NOC / PACKAGE;
            output.write ( String.format("%.2f", NOC_PER_PACKAGE )  + ";" );
            double CALLS_PER_OPERATION = CALLS / OPERATION;
            output.write ( String.format("%.2f", CALLS_PER_OPERATION )  + ";" );
            double FANOUT_PER_CALL = FANOUT / CALLS;
            output.write ( String.format("%.2f", FANOUT_PER_CALL )  + ";" );
            output.write ( String.format("%.2f", ANDC )  + ";" );
            output.write ( String.format("%.2f", AHH )  + ";" );
            output.write ( String.format("%.2f", CYCLO )  + ";" );
            output.write ( String.format("%.2f", LOC )  + ";" );
            output.write ( String.format("%.2f", NOM)  + ";" );
            output.write ( String.format("%.2f", NOC )  + ";" );
            output.write ( String.format("%.2f", PACKAGE )  + ";" );
            output.write ( String.format("%.2f", FANOUT )  + ";" );
            output.write ( String.format("%.2f", CALLS )  + ";" );

            int totalOperands = main.numberIdsCallsMap.get(funcao);
            int totalOperators =  main.nOperationsMap.get(funcao);
            int programLength = totalOperands + totalOperators;
            TreeSet distinctOperandsTree =  main.usedIdsMap.get(funcao);
            int distinctOperands =  distinctOperandsTree.size(); 
            int distinctOperators =    distinctOppsTree.size() ;
            int  vocabulary = distinctOperators + distinctOperands;

            double VOLUME, DIFFICULTY, EFFORT, DELIVERED_BUGS, HALSTEAD;
            VOLUME = programLength * Math.log(vocabulary)/Math.log(2);
            /*The difficulty measure is related to the difficulty of 
              the program to write or understand, e.g. when doing code review. */
            DIFFICULTY = distinctOperators/2 * (totalOperands/distinctOperands); 
            /**/
            EFFORT = DIFFICULTY * VOLUME;
            DELIVERED_BUGS = VOLUME / 3000;
            double Maintainability = Math.max(0.01 ,(171 - 5.2 * Math.log(VOLUME) - 0.23 * CYCLO - 16.2 * Math.log(LOC))*100 / 171);
            output.write ( String.format("%.2f", VOLUME )  + ";" );
            output.write ( String.format("%.2f", DIFFICULTY )  + ";" );
            output.write ( String.format("%.2f", EFFORT )  + ";" );
            output.write ( String.format("%.2f", DELIVERED_BUGS )  + ";" );
            output.write ( String.format("%.4f",  1.0 / Maintainability )  + ";" );
            double NOCOM = main.functionComments.get(funcao);

            TreeSet argsNaoUsados = main.unusedArgsMap.get(funcao);
            double UNARG = argsNaoUsados.size();
            TreeSet declaNaoUsados = main.unusedDeclarationsMap.get(funcao);
            double UNDEC = declaNaoUsados.size();
            double NEGNEG = 0.0;
            double NEGIF = 0.0;
            output.write ( String.format("%.2f", NOCOM )  + ";" );
            output.write ( String.format("%.2f", IPL )  + ";" );
            output.write ( String.format("%.2f", UNARG )  + ";" );
            output.write ( String.format("%.2f", UNDEC )  + ";" );
            output.write ( String.format("%.2f", NEGNEG )  + ";" );
            output.write ( String.format("%.2f", NEGIF )  + ";" );
            double NARGS = main.argsMap.get(funcao);
            output.write ( String.format("%.2f", NARGS )  + ";" );
           output.write (  main.metrics_STANDARDS + "\n");

          }
          /*  output.write("Full Evaluation"+";");
              double main_CYCLO_PER_LOC = main.main_CYCLO / main.main_LOC;
              output.write ( String.format("%.2f", main_CYCLO_PER_LOC )  + ";" );
              double main_LOC_PER_OPERATION = main.main_LOC / main.main_OPERATION;
              output.write ( String.format("%.2f", main_LOC_PER_OPERATION )  + ";" );
              double main_NOM_PER_CLASS = main.main_NOM / main.main_CLASS;
              output.write ( String.format("%.2f", main_NOM_PER_CLASS )  + ";" );
              double main_NOC_PER_PACKAGE = main.main_NOC / main.main_PACKAGE;
              output.write ( String.format("%.2f", main_NOC_PER_PACKAGE )  + ";" );
              double main_CALLS_PER_OPERATION = main.main_CALLS / main.main_OPERATION;
              output.write ( String.format("%.2f", main_CALLS_PER_OPERATION )  + ";" );
              double main_FANOUT_PER_CALL = main.main_FANOUT / main.main_CALLS;
              output.write ( String.format("%.2f", main_FANOUT_PER_CALL )  + ";" );
              output.write ( String.format("%.2f", main.main_ANDC )  + ";" );
              output.write ( String.format("%.2f", main.main_AHH )  + ";" );
              output.write ( String.format("%.2f", main.main_CYCLO )  + ";" );
              output.write ( String.format("%.2f", main.main_LOC )  + ";" );
              output.write ( String.format("%.2f", main.main_NOM)  + ";" );
              output.write ( String.format("%.2f", main.main_NOC )  + ";" );
              output.write ( String.format("%.2f", main.main_PACKAGE )  + ";" );
              output.write ( String.format("%.2f", main.main_FANOUT )  + ";" );
              output.write ( String.format("%.2f", main.main_CALLS )  + ";" );
              output.write (  main.metrics_STANDARDS + "\n");
           */
        } catch ( IOException e ) {
          e.printStackTrace();
        } finally {
          if ( output != null ) output.close();
        }
      }

      catch (IOException e){
        System.out.println("ERROR in metrics file"); 
      }

      /*Export code generated to .txt file*/
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  /* Line number counting per function  */
  %strategy collectLNInside ( ln:HashMap, funcao:String ) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        ln.put(`nome, 2);
      }
      Atribuicao(_,_,_,_,_,_,_) -> {
        int valor_mapa = (int) ln.get(funcao);
        valor_mapa++;
        ln.put(funcao, valor_mapa);
      }
      Declaracao(_,_,_,_,_,_) -> {
        int valor_mapa = (int) ln.get(funcao);
        valor_mapa++;
        ln.put(funcao, valor_mapa);
      }
      If(_,_,_,condicao,_,_,inst1,inst2) -> {
        int valor_mapa = (int) ln.get(funcao);
        valor_mapa+=2;
        ln.put(funcao, valor_mapa);
        if (`inst2 != `Exp(Empty()) ){
          valor_mapa = (int) ln.get(funcao);
          valor_mapa+=2;
          ln.put(funcao, valor_mapa);
        }
      }
      While(_,_,_,condicao,_,_,inst,_) -> {
        int valor_mapa = (int) ln.get(funcao);
        valor_mapa+=2;
        ln.put(funcao, valor_mapa);
      }
      For(_,_,decl,_,condicao,_,_,exp,_,_,inst,_) -> {
        int valor_mapa = (int) ln.get(funcao);
        valor_mapa+=2;
        ln.put(funcao, valor_mapa);
      }
      Return(_,_,exp,_) -> {
        int valor_mapa = (int) ln.get(funcao);
        valor_mapa++;
        ln.put(funcao, valor_mapa);
      }
    }
  }

  /*Line number counting*/
  %strategy collectLN ( ln:HashMap ) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        ln.put(`nome, 2);
        `TopDown( collectLNInside( ln , nome )).visit(`inst);
      }
    }
  }

  %strategy collectUsedIdsInside ( idsUtilizados:TreeSet, numberIdsCallsMap:HashMap, funcao:String ) extends Identity() {
    visit Instrucao {
      Atribuicao(_,id,_,opAtrib,_,exp,_) -> {
        idsUtilizados.add(`id);
        int valor_mapa = (int) numberIdsCallsMap.get(funcao);
        valor_mapa++;
        numberIdsCallsMap.put(funcao, valor_mapa);
      }
    }
    visit Expressao {
      Id(id) -> { 
        idsUtilizados.add(`id);
        int valor_mapa = (int) numberIdsCallsMap.get(funcao);
        valor_mapa++;
        numberIdsCallsMap.put(funcao, valor_mapa);
      }
      IncAntes(opInc,id) -> { 
        idsUtilizados.add(`id);
        int valor_mapa = (int) numberIdsCallsMap.get(funcao);
        valor_mapa++;
        numberIdsCallsMap.put(funcao, valor_mapa);
      }
      IncDepois(opInc,id) -> { 
        idsUtilizados.add(`id);
        int valor_mapa = (int) numberIdsCallsMap.get(funcao);
        valor_mapa++;
        numberIdsCallsMap.put(funcao, valor_mapa);
      }
    }
  }

  /*Line number counting*/
  %strategy collectUsedIdsMap ( usedIdsMap:HashMap , numberIdsCallsMap:HashMap ) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        TreeSet <String> usedIds = new TreeSet <String> ();
        numberIdsCallsMap.put(`nome,0);
        `TopDown( collectUsedIdsInside( usedIds, numberIdsCallsMap, nome )).visit(`inst);
        usedIdsMap.put(`nome, usedIds );
      }
    }
  }

  /** Metric to compute total of Arguments per Function ***/ 
  %strategy getUnusedArgsFunction(unusedArgs:TreeSet, usedIds:TreeSet ) extends Identity() {
    visit Argumentos {
      Argumento(_,_,_,id,_) -> {
        if ( ! (usedIds.contains( `id )) ){
          unusedArgs.add( `id );
        }
      }
    }
  }

  /* Metric to collect unused arguments ids from each function */
  %strategy collectUnusedArguments ( unusedArgumentsMap:HashMap , usedIdsMap:HashMap ) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        TreeSet <String> unusedArgs = new TreeSet <String> ();
        TreeSet <String> usedIds = ( TreeSet <String> ) usedIdsMap.get(`nome);
        `TopDown(getUnusedArgsFunction(unusedArgs,usedIds)).visit(`argumentos);
        unusedArgumentsMap.put(`nome, unusedArgs );
      }
    }
  }

  /** Metric to compute total of Arguments per Function ***/ 
  %strategy getUnusedDeclarationsFunction(unusedDeclarations:TreeSet, usedIds:TreeSet ) extends Identity() {
    visit Declaracoes {
      Decl ( id,_,_,_,_ ) -> {
        if (!( usedIds.contains( `id ) )){
          unusedDeclarations.add( `id );
        }
      }
    }
  }

  /* Metric to collect unused declarations from each function */
  %strategy collectUnusedDeclarations ( unusedDeclarationsMap:HashMap , usedIdsMap:HashMap ) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        TreeSet <String> unusedDeclarations = new TreeSet <String> ();
        TreeSet <String> usedIds = ( TreeSet <String> ) usedIdsMap.get(`nome);
        `TopDown(getUnusedDeclarationsFunction(unusedDeclarations,usedIds)).visit(`inst);
        unusedDeclarationsMap.put(`nome, unusedDeclarations );
      }
    }
  }

  //Instruction Path lenght
  %strategy collectIPLInside ( ipl:HashMap, funcao:String ) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        ipl.put(`nome, 1);
      }
      Atribuicao(_,_,_,_,_,_,_) -> {
        int valor_mapa = (int) ipl.get(funcao);
        valor_mapa++;
        ipl.put(funcao, valor_mapa);
      }
      Declaracao(_,_,_,_,_,_) -> {
        int valor_mapa = (int) ipl.get(funcao);
        valor_mapa++;
        ipl.put(funcao, valor_mapa);
      }
      If(_,_,_,condicao,_,_,inst1,inst2) -> {
        int valor_mapa = (int) ipl.get(funcao);
        valor_mapa+=1;
        ipl.put(funcao, valor_mapa);
        if (`inst2 != `Exp(Empty()) ){
          valor_mapa = (int) ipl.get(funcao);
          valor_mapa+=1;
          ipl.put(funcao, valor_mapa);
        }
      }
      While(_,_,_,condicao,_,_,inst,_) -> {
        int valor_mapa = (int) ipl.get(funcao);
        valor_mapa+=1;
        ipl.put(funcao, valor_mapa);
      }
      For(_,_,decl,_,condicao,_,_,exp,_,_,inst,_) -> {
        int valor_mapa = (int) ipl.get(funcao);
        valor_mapa+=1;
        ipl.put(funcao, valor_mapa);
      }
      Return(_,_,exp,_) -> {
        int valor_mapa = (int) ipl.get(funcao);
        valor_mapa++;
        ipl.put(funcao, valor_mapa);
      }
    }
  }

  /*Line number counting*/
  %strategy collectIPL ( ipl:HashMap ) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        ipl.put(`nome, 1);
        `TopDown( collectIPLInside( ipl , nome )).visit(`inst);
      }
    }
  }

  %strategy startCollectCyclomaticInside( complexidade:HashMap, funcao:String , maximo_medido:int ) extends Identity() {
    visit Instrucao {
      For(_,_,_,_,_,_,_,_,_,_,inst,_) -> {
        maximo_medido++;
        int valor_mapa = (int) complexidade.get(funcao);
        if ( valor_mapa <= maximo_medido ){
          complexidade.put(funcao,maximo_medido);
        } 
      }
      If(_,_,_,_,_,_,inst,_) -> {
        maximo_medido++;
        int valor_mapa = (int) complexidade.get(funcao);
        if ( valor_mapa <= maximo_medido ){
          complexidade.put(funcao,maximo_medido);
        } 
      }
      While(_,_,_,_,_,_,inst,_) -> {
        maximo_medido++;
        int valor_mapa = (int) complexidade.get(funcao);
        if ( valor_mapa <= maximo_medido ){
          complexidade.put(funcao,maximo_medido);
        } 
      }
    }
  }

  %strategy startCollectCyclomatic(HashMap complexidade, String funcao) extends Identity() {
    visit Instrucao {
      For(_,_,_,_,_,_,_,_,_,_,inst,_) -> {
        int valor_mapa = (int) complexidade.get(funcao);
        if ( valor_mapa < 1 ){
          complexidade.put(funcao,1);
        } 
        `TopDown(startCollectCyclomaticInside(complexidade,funcao, 1)).visit(`inst);
      }
      If(_,_,_,_,_,_,inst,_) -> {
        int valor_mapa = (int) complexidade.get(funcao);
        if ( valor_mapa < 1 ){
          complexidade.put(funcao,1);
        } 
        `TopDown(startCollectCyclomaticInside(complexidade,funcao, 1)).visit(`inst);
      }
      While(_,_,_,_,_,_,inst,_) -> {
        int valor_mapa = (int) complexidade.get(funcao);
        if ( valor_mapa < 1 ){
          complexidade.put(funcao,1);
        } 
        `TopDown(startCollectCyclomaticInside(complexidade,funcao, 1)).visit(`inst);
      }
    }
  }

  /** Metric to compute *** Number of Operations *** **/
  %strategy startCollectNumOperations(HashMap numberOperations, String funcao) extends Identity() {
    visit OpAtribuicao {
      Atrib()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
      Mult()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
      Div()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
      Soma()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
      Sub()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
    }
    visit OpNum {
      Mais()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
      Vezes()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
      Divide()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
      Menos()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
      Mod()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
    }
    visit OpComp {
      Maior() -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
      Menor()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
      MaiorQ()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
      MenorQ()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
      Dif()   -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
      Igual()   -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
    }
    visit OpInc {
      Inc()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
      Dec()  -> {
        int valor_mapa = (int) numberOperations.get(funcao);
        valor_mapa++;
        numberOperations.put(funcao, valor_mapa);

      }
    }


  }

  /** Metric to compute *** Number of Operations *** **/
  %strategy startCollectDistinctOperations(TreeSet distinctOperations ) extends Identity() {
    visit OpAtribuicao {
      Atrib()  -> {
        distinctOperations.add("Atrib()");
      }
      Mult()  -> {
        distinctOperations.add("Mult()");
      }
      Div()  -> {
        distinctOperations.add("Div()");
      }
      Soma()  -> {
        distinctOperations.add("Soma()");
      }
      Sub()  -> {
        distinctOperations.add("Sub()");
      }
    }
    visit OpNum {
      Mais()  -> {
        distinctOperations.add("Mais()");
      }
      Vezes()  -> {
        distinctOperations.add("Vezes()");
      }
      Divide()  -> {
        distinctOperations.add("Divide()");
      }
      Menos()  -> {
        distinctOperations.add("Menos()");
      }
      Mod()  -> {
        distinctOperations.add("Mod()");
      }
    }
    visit OpComp {
      Maior() -> {
        distinctOperations.add("Maior()");
      }
      Menor()  -> {
        distinctOperations.add("Menor()");
      }
      MaiorQ()  -> {
        distinctOperations.add("MaiorQ()");
      }
      MenorQ()  -> {
        distinctOperations.add("MenorQ()");
      }
      Dif()   -> {
        distinctOperations.add("Dif()");
      }
      Igual()   -> {
        distinctOperations.add("Igual()");
      }
    }
    visit OpInc {
      Inc()  -> {
        distinctOperations.add("Inc()");
      }
      Dec()  -> {
        distinctOperations.add("Dec()");
      }
    }
  }

  %strategy CollectOperations(func:HashMap, numberOperations:HashMap, distinctOperations:HashMap ) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        func.put(`nome, `argumentos);
        numberOperations.put(`nome,0);
        TreeSet distinctOpps = new TreeSet <String>();
        `TopDown(startCollectNumOperations( numberOperations, nome )).visit(`inst);
        `TopDown(startCollectDistinctOperations(distinctOpps)).visit(`inst);
        distinctOperations.put(`nome,distinctOpps);
      }
    }
  }

  /** Metric to compute *** Number Of Comparisons Operations *** **/
  %strategy startCollectNumOperationsComparisons(HashMap numComp, String funcao) extends Identity() {
    visit OpComp {
      Maior() -> {
        int valor_mapa = (int) numComp.get(funcao);
        valor_mapa++;
        numComp.put(funcao, valor_mapa);
      }
      Menor() -> {
        int valor_mapa = (int) numComp.get(funcao);
        valor_mapa++;
        numComp.put(funcao, valor_mapa);
      }
      MaiorQ() -> {
        int valor_mapa = (int) numComp.get(funcao);
        valor_mapa++;
        numComp.put(funcao, valor_mapa);
      }
      MenorQ()  -> {
        int valor_mapa = (int) numComp.get(funcao);
        valor_mapa++;
        numComp.put(funcao, valor_mapa);
      }
      Dif()  -> {
        int valor_mapa = (int) numComp.get(funcao);
        valor_mapa++;
        numComp.put(funcao, valor_mapa);
      }
      Igual()  -> {
        int valor_mapa = (int) numComp.get(funcao);
        valor_mapa++;
        numComp.put(funcao, valor_mapa);
      }
    }
  }

  /** Metric to compute *** Number of increments/decrements *** (Increment and "Decrement" operations included)**/
  %strategy startCollectNumOfIncrementsAndDecrements(HashMap incrNum, String funcao) extends Identity() {
    visit OpInc {
      Inc() -> {
        int valor_mapa = (int) incrNum.get(funcao);
        valor_mapa++;
        incrNum.put(funcao, valor_mapa);
      }
      Dec() -> {
        int valor_mapa = (int) incrNum.get(funcao);
        valor_mapa++;
        incrNum.put(funcao, valor_mapa);
      }
    }
  }

  /** Metric to compute *** Number of Atrib/Mult/Div/Soma/Sub operations **/
  %strategy startCollectOpAtribOperations(HashMap numOpAtrib, String funcao) extends Identity() {
    visit OpAtribuicao {
      Atrib() -> {
        int valor_mapa = (int) numOpAtrib.get(funcao);
        valor_mapa++;
        numOpAtrib.put(funcao, valor_mapa);
      }
      Mult() -> {
        int valor_mapa = (int) numOpAtrib.get(funcao);
        valor_mapa++;
        numOpAtrib.put(funcao, valor_mapa);
      }
      Div() -> {
        int valor_mapa = (int) numOpAtrib.get(funcao);
        valor_mapa++;
        numOpAtrib.put(funcao, valor_mapa);
      }
      Soma()  -> {
        int valor_mapa = (int) numOpAtrib.get(funcao);
        valor_mapa++;
        numOpAtrib.put(funcao, valor_mapa);
      }
      Sub()  -> {
        int valor_mapa = (int) numOpAtrib.get(funcao);
        valor_mapa++;
        numOpAtrib.put(funcao, valor_mapa);
      }
    }
  }

  /** Metric to compute total of Comments ***/ 
  %strategy startCollectComments(HashMap comments, String funcao) extends Identity() {
    visit LComentarios {
      Comentario(Vazio) -> {
        int num_comentarios = (int) comments.get(funcao);
        num_comentarios++;
        comments.put(funcao, num_comentarios);
      }
    }
  }

  /** Metric to compute total of Arguments per Function ***/ 
  %strategy countArgsFunction(mapArgs:HashMap, funcao:String) extends Identity() {
    visit Argumentos {
      Argumento(_,_,_,id,_) -> {
        int num_args = (int) mapArgs.get(funcao);
        mapArgs.remove(funcao);
        num_args+=1;
        mapArgs.put(funcao, num_args);
      }
    }
  }

  /** Metric to compute total number of functions ***/
  %strategy CollectNumberFuncs(func:HashMap, mapArgs:HashMap) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        func.put(`nome, `argumentos);
        mapArgs.put(`nome, 0);
        `TopDown(countArgsFunction(mapArgs,nome)).visit(`argumentos);

      }
    }
  }

  %strategy CollectCiclomaticComplex(func:HashMap, complex_c:HashMap) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        func.put(`nome, `argumentos);
        complex_c.put(`nome,0);
        `TopDown(startCollectCyclomatic(complex_c,nome)).visit(`inst);
      }
    }
  }

  %strategy CollectComments(func:HashMap, comments:HashMap) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        func.put(`nome, `argumentos);
        comments.put(`nome, 0);
        `TopDown(startCollectComments(comments,nome)).visit(`inst);
      }
    }
  }

  %strategy CollectNumOperationsComparisons(func:HashMap, operationsNumComp:HashMap) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        func.put(`nome, `argumentos);
        operationsNumComp.put(`nome,0);
        `TopDown(startCollectNumOperationsComparisons(operationsNumComp,nome)).visit(`inst);
      }
    }
  }

  %strategy CollectNumOperationsIncrDecr(func:HashMap, incrDecrOp:HashMap) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        func.put(`nome, `argumentos);
        incrDecrOp.put(`nome,0);
        `TopDown(startCollectNumOfIncrementsAndDecrements(incrDecrOp,nome)).visit(`inst);
      }
    }
  }

  %strategy CollectOpAtrib(func:HashMap, opAtribMap:HashMap) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        func.put(`nome, `argumentos);
        opAtribMap.put(`nome,0);
        `TopDown(startCollectOpAtribOperations(opAtribMap,nome)).visit(`inst);
      }
    }
  }

  /***************************
   ** Refactoring Strategies **
   ***************************/

  %strategy RefactorNegIf() extends Identity() {
    visit Instrucao {
      If(c1,c2,c3,Nao(condicao),c4,c5,i1,i2) -> { return `If(c1,c2,c3,condicao,c4,c5,i2,i1); }
    }
  }

  %strategy RefactorNegNeg() extends Identity() {
    visit Expressao {
      Nao(Nao(c1)) -> { return `c1; }
    }
  }

  public static Argumentos removeArgumentosNaoUtilizados(Argumentos args, TreeSet<String> idsUtilizados) {
    %match(args) {
      ListaArgumentos(arg1,tailArg*) -> {
        %match(arg1) {
          a@Argumento(_,_,_,idArg,_) -> {
            if (idsUtilizados.contains(`idArg))
              return `ListaArgumentos(a,removeArgumentosNaoUtilizados(tailArg*,idsUtilizados));
            else
              return removeArgumentosNaoUtilizados(`tailArg*,idsUtilizados);
          }
        }
      }
    }
    return args;
  }

  public static Instrucao removeDeclaracoesNaoUtilizadas ( Instrucao inst, TreeSet<String> idsNaoUtilizados ) {
    %match(inst) {
      Declaracao(_,_,_,declaracoes,_,_) -> {
        %match(declaracoes) {
          ListaDecl( dec1,taliDec*) -> {
            %match(dec1) {
              Decl(id,_,_,_,_) -> {
                if ( idsNaoUtilizados.contains(`id))
                  return `Exp(Empty());
              }
            }
          }
        }
      }
    }
    return inst;
  }

  %strategy startBadSmells( mapaIdsUtilizados:HashMap , mapaRefactor:HashMap, mapaIdsNaoUtilizados:HashMap ) extends Identity() {
    visit Instrucao {
      If(c1,c2,c3,Nao(condicao),c4,c5,inst1,inst2) -> {
        return `If(c1,c2,c3,condicao,c4,c5,inst2,inst1);
      }
      Funcao(c1,tipo,c2,nome,c3,c4,argumentos,c5,c6,inst,c7) -> {
        Integer refactorFlag = (Integer) mapaRefactor.get(`nome);
        if ( refactorFlag > 0 ){
          TreeSet<String> idsUtilizados = (TreeSet<String>) mapaIdsUtilizados.get(`nome);
          TreeSet<String> idsNaoUtilizados = (TreeSet<String>) mapaIdsNaoUtilizados.get(`nome);
          Argumentos args = removeArgumentosNaoUtilizados(`argumentos,idsUtilizados);
          Instrucao instrucoes = removeDeclaracoesNaoUtilizadas (`inst, idsNaoUtilizados);
          `nome = `nome + "_refactored";
          return `Funcao(c1,tipo,c2,nome,c3,c4,args,c5,c6,instrucoes,c7);
        }
      }
    }
  }

  %strategy CollectFuncsSignature(signatures:HashMap) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        signatures.put(`nome, `argumentos);
      }
    }
  }

  %strategy stratPrintAnnotations(ArrayList numInstrucao) extends Identity() {
    visit Instrucao {
      i@Atribuicao(_,_,_,_,_,_,_) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        LComentarios c = `Vazio();
        numInstrucao.add(num+1);
        if (num > 1)
          return `SeqInstrucao(i,Exp(Print(c,c,c,Char(","),c,c)),Exp(Print(c,c,c,Int(num),c,c)));
        else
          return `SeqInstrucao(i,Exp(Print(c,c,c,Int(num),c,c)));
      }
      i@If(_,_,_,condicao,_,_,inst1,inst2) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        LComentarios c = `Vazio();
        numInstrucao.add(num+1);
        if (num > 1)
          return `SeqInstrucao(i,Exp(Print(c,c,c,Char(","),c,c)),Exp(Print(c,c,c,Int(num),c,c)));
        else
          return `SeqInstrucao(i,Exp(Print(c,c,c,Int(num),c,c)));
      }
      i@While(_,_,_,condicao,_,_,inst,_) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        LComentarios c = `Vazio();
        numInstrucao.add(num+1);
        if (num > 1)
          return `SeqInstrucao(i,Exp(Print(c,c,c,Char(","),c,c)),Exp(Print(c,c,c,Int(num),c,c)));
        else
          return `SeqInstrucao(i,Exp(Print(c,c,c,Int(num),c,c)));
      }
      i@For(_,_,decl,_,condicao,_,_,exp,_,_,inst,_) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        LComentarios c = `Vazio();
        numInstrucao.add(num+1);
        if (num > 1)
          return `SeqInstrucao(i,Exp(Print(c,c,c,Char(","),c,c)),Exp(Print(c,c,c,Int(num),c,c)));
        else
          return `SeqInstrucao(i,Exp(Print(c,c,c,Int(num),c,c)));
      }
      i@Return(_,_,exp,_) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        LComentarios c = `Vazio();
        numInstrucao.add(num+1);
        if (num > 1)
          return `SeqInstrucao(i,Exp(Print(c,c,c,Char(","),c,c)),Exp(Print(c,c,c,Int(num),c,c)));
        else
          return `SeqInstrucao(i,Exp(Print(c,c,c,Int(num),c,c)));
      }
    }
    visit Expressao {
      e@ExpNum(exp1,_,op,_,exp2) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        LComentarios c = `Vazio();
        numInstrucao.add(num+1);
        if (num > 1)
          return `Expressoes(e,Print(c,c,c,Char(","),c,c),Print(c,c,c,Int(num),c,c));
        else
          return `Expressoes(e,Print(c,c,c,Int(num),c,c));
      }
      e@Ou(cond1,_,_,cond2) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        LComentarios c = `Vazio();
        numInstrucao.add(num+1);
        if (num > 1)
          return `Expressoes(e,Print(c,c,c,Char(","),c,c),Print(c,c,c,Int(num),c,c));
        else
          return `Expressoes(e,Print(c,c,c,Int(num),c,c));
      }
      e@E(cond1,_,_,cond2) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        LComentarios c = `Vazio();
        numInstrucao.add(num+1);
        if (num > 1)
          return `Expressoes(e,Print(c,c,c,Char(","),c,c),Print(c,c,c,Int(num),c,c));
        else
          return `Expressoes(e,Print(c,c,c,Int(num),c,c));
      }
      e@Comp(exp1,_,opComp,_,exp2) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        LComentarios c = `Vazio();
        numInstrucao.add(num+1);
        if (num > 1)
          return `Expressoes(e,Print(c,c,c,Char(","),c,c),Print(c,c,c,Int(num),c,c));
        else
          return `Expressoes(e,Print(c,c,c,Int(num),c,c));
      }
    }
  }

  %strategy stratFaultInjection() extends Identity() {
    visit Instrucao {
      If(c1,c2,c3,condicao,c4,c5,inst1,inst2) -> {
        return `If(c1,c2,c3,condicao,c4,c5,inst2,inst1);
      }
      While(c1,c2,c3,condicao,c4,c5,inst,c6) -> {
        return `While(c1,c2,c3,Nao(condicao),c4,c5,inst,c6);
      }
      For(c1,c2,decl,c3,condicao,c4,c5,exp,c6,c7,inst,c8) -> {
        return `For(c1,c2,decl,c3,Nao(condicao),c4,c5,exp,c6,c7,inst,c8);
      }
    }
  }

  %strategy stratFaultInjectionWithKnowledge(ArrayList numInstrucao,Set blocos) extends Identity() {
    visit Instrucao {
      i@Atribuicao(_,_,_,_,_,_,_) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        numInstrucao.add(num+1);
        if (blocos.contains((Integer) num))
          return `i;
      }
      If(c1,c2,c3,condicao,c4,c5,inst1,inst2) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        numInstrucao.add(num+1);
        if (blocos.contains((Integer) num))
          return `If(c1,c2,c3,condicao,c4,c5,inst2,inst1);
      }
      While(c1,c2,c3,condicao,c4,c5,inst,c6) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        numInstrucao.add(num+1);
        if (blocos.contains((Integer) num))
          return `While(c1,c2,c3,Nao(condicao),c4,c5,inst,c6);
      }
      For(c1,c2,decl,c3,condicao,c4,c5,exp,c6,c7,inst,c8) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        numInstrucao.add(num+1);
        if (blocos.contains((Integer) num))
          return `For(c1,c2,decl,c3,Nao(condicao),c4,c5,exp,c6,c7,inst,c8);
      }
      i@Return(_,_,exp,_) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        numInstrucao.add(num+1);
      }
    }
    visit Expressao {
      e@ExpNum(exp1,_,op,_,exp2) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        numInstrucao.add(num+1);
      }
      e@Ou(cond1,_,_,cond2) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        numInstrucao.add(num+1);
      }
      e@E(cond1,_,_,cond2) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        numInstrucao.add(num+1);
      }
      e@Comp(exp1,_,opComp,_,exp2) -> {
        int num = (Integer) numInstrucao.remove((int) 0);
        numInstrucao.add(num+1);
      }
    }
  }

  private String compileAnnot(Instrucao inst) {
    NumToInt numInstrucao = new NumToInt(1);
    String toReturn = compileAnnotInstrucao(inst, numInstrucao);
    //return toReturn.concat("Halt");
    return toReturn.substring(0,toReturn.length()-1);
  }

  private String compileAnnotInstrucao(Instrucao i, NumToInt numInstrucao) {
    %match(i) {
      Atribuicao(_,id,_,opAtrib,_,exp,_) -> {
        String genExp = `compileAnnotExpressoes(exp, numInstrucao);
        String prefix;
        if (actualFunctionName.equals(""))
          prefix = "";
        else
          prefix = actualFunctionName + "_";

        %match(opAtrib) {
          Atrib() -> { return "Pusha \"" + prefix + `id + "\"," + genExp + "Store,"; }
          Mult() -> { return "Pusha \"" + prefix + `id + "\",Pusha \"" + prefix + `id + "\",Load," + genExp + "Mul,Store,"; }
          Div() -> { return "Pusha \"" + prefix + `id + "\",Pusha \"" + prefix + `id + "\",Load," + genExp + "Div,Store,"; }
          Soma() -> { return "Pusha \"" + prefix + `id + "\",Pusha \"" + prefix + `id + "\",Load," + genExp + "Add,Store,"; }
          Sub() -> { return "Pusha \"" + prefix + `id + "\",Pusha \"" + prefix + `id + "\",Load," + genExp + "Sub,Store,"; }
        }
        return "";
      }

      Declaracao(_,tipo,_,decls,_,_) -> {
        String genDecl = `compileAnnotDeclaracoes(decls, tipo, numInstrucao);
        functionsDeclarations.append(genDecl);
        return "";
      }

      If(_,_,_,condicao,_,_,inst1,inst2) -> {
        String genCondicao = `compileAnnotExpressoes(condicao, numInstrucao);
        String genInst1 = `compileAnnotInstrucao(inst1, numInstrucao);
        String genInst2 = `compileAnnotInstrucao(inst2, numInstrucao);
        int num = numInstrucao.inc();

        return genCondicao + "Jumpf \"senao" + num + "\"," + genInst1 + "Jump \"fse" + num + "\",ALabel \"senao" + num + "\"," + genInst2 + "ALabel \"fse" + num + "\",";
      }

      While(_,_,_,condicao,_,_,inst,_) -> {
        String genCondicao = `compileAnnotExpressoes(condicao, numInstrucao);
        String genInst = `compileAnnotInstrucao(inst, numInstrucao);
        int num = numInstrucao.inc();

        return "ALabel \"enq" + num + "\"," + genCondicao + "Jumpf \"fenq" + num + "\"," + genInst + "Jump \"enq" + num +"\"," + "ALabel \"fenq" + num + "\",";
      }

      For(_,_,decl,_,condicao,_,_,exp,_,_,inst,_) -> {
        String genDecl = `compileAnnotInstrucao(decl, numInstrucao);
        String genCondicao = `compileAnnotExpressoes(condicao, numInstrucao);
        String genExp = `compileAnnotExpressoes(exp, numInstrucao);
        String genInst = `compileAnnotInstrucao(inst, numInstrucao);

        int num = numInstrucao.inc();
        String labelInit = "ALabel \"for" + num + "\",";
        String jump = "Jumpf \"ffor"+ num + "\",";
        String labelJump = "ALabel \"ffor" + num + "\",";
        String labelEnd = "Jump \"for" + num + "\",";

        functionsDeclarations.append(genDecl);

        return labelInit.concat(genCondicao).concat(jump).concat(genInst).concat(genExp).concat(labelEnd).concat(labelJump);
      }

      Return(_,_,exp,_) -> {
        String genExp = `compileAnnotExpressoes(exp, numInstrucao);
        String prefix = "f:";
        String ret = "Ret,";
        String storeVarFunct = "Pusha \"" + prefix + actualFunctionName + "\"," + genExp + "Store,";

        return storeVarFunct;
      }

      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        int actualMemAddress = memAdress;
        memAdress++;
        int sizeAddress = 1;

        actualFunctionName = `nome;
        String prefix = "f:";
        String functionDeclaration = "Decl \"" + prefix + `nome + "\" " + actualMemAddress + " " +  sizeAddress + ",";
        String functionRet = "";
        %match(tipo) {
          DVoid() -> { if (!actualFunctionName.equals("main")) functionRet = "Ret,"; }
          _ -> { if(!actualFunctionName.equals("main")) functionRet = "Ret,"; }
        }
        String halt = actualFunctionName.equals("main") ? "Halt," : "";
        String genArgs = `compileArguments(nome, argumentos);

        functionsDeclarations.append(functionDeclaration);
        functionsDeclarations.append(genArgs);

        String genInst = `compileAnnotInstrucao(inst, numInstrucao);
        String function = "ALabel \"f:" + `nome + "\"," + genInst + functionRet + halt;

        return function;
      }

      Exp(exp) -> {
        callReturnNeeded = false;
        String exp = `compileAnnotExpressoes(exp, numInstrucao);
        callReturnNeeded = true;

        return exp;
      }

      SeqInstrucao(inst1, inst*) -> {
        String genInst = `compileAnnotInstrucao(inst1, numInstrucao);
        String seqInst = genInst.concat(`compileAnnotInstrucao(inst*, numInstrucao));

        return seqInst;
      }
    }
    return "";
  }

  private String compileArguments(String functionName, Argumentos args) {
    %match(args) {
      ListaArgumentos(arg1,tailArg*) -> {
        return compileArguments(functionName, `arg1) + compileArguments(functionName, `tailArg);
      }
      Argumento(_,_,_,idArg,_) -> {
        int actualMemAddress = memAdress;
        memAdress++;
        int sizeAddress = 1;

        String prefix = functionName + "_";
        String declaration = "Decl \"" + prefix + `idArg + "\" " + actualMemAddress + " " +  sizeAddress + ",";

        return declaration;
      }
    }
    return "";
  }

  public String compileAnnotDeclaracoes(Declaracoes decl, DefTipo tipo, NumToInt numInstrucao) {
    %match(decl) {
      ListaDecl(dec1,tail*) -> {
        String gen = `compileAnnotDeclaracoes(dec1, tipo, numInstrucao);
        String gen2 = `compileAnnotDeclaracoes(tail*, tipo, numInstrucao);

        return gen + gen2;
      }

      Decl(id,_,_,exp,_) -> {
        String genExp = `compileAnnotExpressoes(exp, numInstrucao);
        String prefix;
        if (actualFunctionName.equals(""))
          prefix = "";
        else
          prefix = actualFunctionName + "_";

        String storeValue;
        if (genExp.equals(""))
          storeValue = "";
        else
          storeValue = "Pusha \"" + prefix + `id + "\"," + genExp + "Store,";

        int actualMemAddress = memAdress;
        memAdress++;
        int sizeAddress = 1;

        %match(tipo) {
          DInt() -> { return "Decl \"" + prefix + `id + "\" " + actualMemAddress + " " +  sizeAddress + "," + storeValue; }
          DChar() -> { return "Decl \"" + prefix + `id + "\" " +  actualMemAddress + " " +  sizeAddress + "," + storeValue; }
          DBoolean() -> { return "Decl \"" + prefix + `id + "\" " +  actualMemAddress + " " +  sizeAddress + "," + storeValue; }
          DFloat() -> { return "Decl \"" + prefix + `id + "\" " +  actualMemAddress + " " +  sizeAddress + "," + storeValue; }
          DVoid() -> { return "Decl \"" + prefix + `id + "\" " +  actualMemAddress + " " +  sizeAddress + "," + storeValue; }
        }
        return "";
      }
    }
    return "";
  }

  private String compileAnnotExpressoes(Expressao e, NumToInt numInstrucao) {
    %match(e) {
      ExpNum(exp1,_,op,_,exp2) -> {
        String genExp1 = `compileAnnotExpressoes(exp1, numInstrucao);
        String genExp2 = `compileAnnotExpressoes(exp2, numInstrucao);

        %match(op) {
          Mais() -> { return genExp1 + genExp2 + "Add,"; }
          Vezes() -> { return genExp1 + genExp2 + "Mul,"; }
          Divide() -> { return genExp1 + genExp2 + "Div,"; }
          Menos() -> { return genExp1 + genExp2 + "Sub,"; }
          Mod() -> { return genExp1.concat(genExp2); }
        }
        return "";
      }

      Id(id) -> { 
        String prefix;
        if (actualFunctionName.equals(""))
          prefix = "";
        else
          prefix = actualFunctionName + "_";

        return "Pusha \"" + prefix + `id + "\",Load,"; 
      }

      Pos(exp) -> { return `compileAnnotExpressoes(exp, numInstrucao); }

      Neg(exp) -> { return `compileAnnotExpressoes(exp, numInstrucao); }

      Nao(exp) -> { 
        String genExp = `compileAnnotExpressoes(exp, numInstrucao);
        return genExp + "Not,";
      }

      Call(_,id,_,_,parametros,_,_) -> { 
        Argumentos argumentos = functionSignatures.get(`id);
        String prefix = "f:";
        String loadReturn = callReturnNeeded ? "Pusha \"" + prefix + `id + "\",Load," : "";
        String genCallParameters = compileCallParameters(`id, argumentos, `parametros, numInstrucao);
        String call = "Call \"" + prefix + `id + "\",";
        return genCallParameters + call + loadReturn;
      }

      IncAntes(opInc,id) -> { 
        String prefix;
        if (actualFunctionName.equals(""))
          prefix = "";
        else
          prefix = actualFunctionName + "_";

        %match(opInc) {
          Inc() -> { return "Pusha \"" + prefix + `id + "\",Inc"; } 
          Dec() -> { return "Pusha \"" + prefix + `id + "\",Dec"; }
        }
        return `id;
      }

      IncDepois(opInc,id) -> { 
        String prefix;
        if (actualFunctionName.equals(""))
          prefix = "";
        else
          prefix = actualFunctionName + "_";

        %match(opInc) {
          Inc() -> { return "Pusha \"" + prefix + `id + "\",Inc,"; } 
          Dec() -> { return "Pusha \"" + prefix + `id + "\",Dec,"; }
        }
        return `id;
      }

      Condicional(condicao,_,_,exp1,_,_,exp2) -> {
        String genCondicao = `compileAnnotExpressoes(condicao, numInstrucao);
        String genExp1 = `compileAnnotExpressoes(exp1, numInstrucao);
        String genExp2 = `compileAnnotExpressoes(exp2, numInstrucao);

        return genCondicao.concat(genExp1).concat(genExp2);
      }

      Int(i) -> { return "Pushi "+ `i + ","; }

      Char(c) -> { return "Pushc '" + `c.charAt(0) + "',"; }

      True() -> { return "Pushb true,"; }

      False() -> { return "Pushb false,"; }

      Float(f) -> { return "Pushf "+`f + ","; }

      Ou(cond1,_,_,cond2) -> {
        String genCond1 = `compileAnnotExpressoes(cond1, numInstrucao);
        String genCond2 = `compileAnnotExpressoes(cond2, numInstrucao);

        return genCond1 + genCond2 + "Or,";
      }

      E(cond1,_,_,cond2) -> {
        String genCond1 = `compileAnnotExpressoes(cond1, numInstrucao);
        String genCond2 = `compileAnnotExpressoes(cond2, numInstrucao);

        return genCond1 + genCond2 + "And,";
      }

      Comp(exp1,_,opComp,_,exp2) -> {
        String genExp1 = `compileAnnotExpressoes(exp1, numInstrucao);
        String genExp2 = `compileAnnotExpressoes(exp2, numInstrucao);

        %match(opComp) {
          Maior() -> { return genExp1 + genExp2 + "Gt,"; }
          Menor() -> { return genExp1 + genExp2 + "Lt,"; }
          MaiorQ() -> { return genExp1 + genExp2 + "GoEq,"; }
          MenorQ() -> { return genExp1 + genExp2 + "LoEq,"; }
          Dif() -> { return genExp1 + genExp2 + "Neq,"; }
          Igual() -> { return genExp1 + genExp2 + "Eq,"; }
        }
      }

      Input(_,_,_,tipo,_,_) -> {
        %match(tipo) {
          DInt() -> { return "IIn int,"; }
          DChar() -> { return "IIn char,"; }
          DBoolean() -> { return "IIn boolean,"; }
          DFloat() -> { return "IIn float,"; }
        }
      }

      Print(_,_,_,exp,_,_) -> {
        String genExp = `compileAnnotExpressoes(exp, numInstrucao);

        return genExp + "IOut,";
      }

      Expressoes(exp1, exp*) -> {
        String genExp = `compileAnnotExpressoes(exp1, numInstrucao);
        String exps = genExp.concat(`compileAnnotExpressoes(exp*, numInstrucao));

        return exps;
      }

      Empty() -> { return ""; }
    }
    return "";
  }

  private String compileCallParameters(String functionName, Argumentos argumentos, Parametros parametros, NumToInt numInstrucao) {
    %match (parametros, argumentos){
      ListaParametros(param1,tailParam*), ListaArgumentos(arg1,tailArg*) -> {
        return compileCallParameters(functionName, `arg1, `param1, numInstrucao) + compileCallParameters(functionName, `tailArg, `tailParam, numInstrucao);
      }
      Parametro(_,exp,_), Argumento(_,_,_,idArg,_) -> {
        String genExp = `compileAnnotExpressoes(exp, numInstrucao);
        String prefix = functionName + "_";
        return "Pusha \"" + prefix + `idArg + "\"," + genExp + "Store,";
      }
    }
    return "";
  }

  private String genAnnotation(int i) {
    if (i == 1) {
      return "Pushi "+i+",IOut,";
    }
    else {
      return "Pushc ',',IOut,Pushi "+i+",IOut,";
    }
  }

  private static boolean parseFile(String filename, TreeSet<Integer> blocos) { 
    try { 
      BufferedReader br = new BufferedReader( new FileReader(filename) ); 
      String line = ""; 
      StringTokenizer token = null; 

      while((line = br.readLine()) != null) { 
        token = new StringTokenizer(line, ","); 

        while(token.hasMoreTokens()) { 
          String tokenS = token.nextToken();
          blocos.add(Integer.parseInt(tokenS));
        } 
      } 

      return true; 
    } catch(Exception e) { 
      return false; 
    }
  }
}

class NumToInt{
  private int num;

  public NumToInt(int num) {
    this.num = num;
  }

  public NumToInt(){
    num = 0;
  }

  public int inc(){
    return num++;
  }

  public int get() {
    return num;
  }
}

