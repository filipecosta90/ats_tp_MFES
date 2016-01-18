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

  //Metrics Variables    
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
  HashMap <String, TreeSet<String> > usedArgsMap;

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
    this.nOperationsComparisonsMap = new HashMap <String,Integer>();
    this.nIncrDecrOpMap = new HashMap <String,Integer>();
    this.opAtribMap = new HashMap <String,Integer>();
    this.functionComments = new HashMap<String,Integer>();
    this.cyclomaticComplexityMap = new HashMap <String, Integer>();
    this.lnMap = new HashMap <String,Integer>();
    this.argsMap = new HashMap <String, Integer>();
    this.usedIdsMap = new HashMap <String, TreeSet<String> >();
    this.usedArgsMap = new HashMap <String, TreeSet<String> >();
    this.iplMap = new HashMap <String,Integer>();
    /****** Separated Metrics Counters Inicialization ******/

    /** OpNum Inicialization **/
    this.nrMais = 0;
    this.nrVezes = 0;
    this.nrDivide = 0;
    this.nrMenos = 0;
    this.nrMod = 0;

    /** OpComp Inicialization **/
    this.nrMaior = 0;
    this.nrMenor = 0;
    this.nrMaiorQ = 0;
    this.nrMenorQ = 0;
    this.nrDif = 0;
    this.nrIgual = 0;

    /** OpInc Inicialization **/
    this.nrInc = 0;
    this.nrDec = 0;

    /** OpAtribuicao **/
    this.nrOpAtribAtrib = 0;
    this.nrOpAtribMult = 0;
    this.nrOpAtribDiv = 0;
    this.nrOpAtribSoma = 0;
    this.nrOpAtribSub = 0;

    /****************************************************/
  }


  /**
   * Halstead Measures (lifted from www.sei.cmu.edu)
   * =================
   * The Halstead measures are based on four scalar numbers derived directly
   * from a program's source code:
   * n1 = the number of distinct operators
   * 
   * n2 = the number of distinct operands
   * 
   * N1 = the total number of operators
   * 
   * N2 = the total number of operands
   * 
   * From these numbers, five measures are derived:
   * Measure Symbol Formula
   * 
   * Program length N N= N1 + N2
   * 
   * Program vocabulary n n= n1 + n2
   * 
   * Volume V V= N * (LOG2 n)
   * 
   * Difficulty D D= (n1/2) * (N2/n2)
   * 
   * Effort E E= D * V
   */
  /* void computeHalstead()
     {
     programLength = totalOperands + totalOperators;
     vocabulary = distinctOperators + distinctOperands;
     if (distinctOperators > 0 && distinctOperands > 0)
     {
     volume = programLength * Math.log(vocabulary)/Math.log(2);
     difficulty = distinctOperators/2 * (totalOperands/distinctOperands); 
     effort = difficulty * volume;
     }
     return;
     }
   */

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
        `TopDown(CollectNumberFuncs(main.functionSignatures, main.argsMap)).visit(p);
        `TopDown(CollectCiclomaticComplex(main.functionSignatures, main.cyclomaticComplexityMap)).visit(p);
        `TopDown(CollectComments(main.functionSignatures, main.functionComments)).visit(p);
        `TopDown(CollectNumOperations(main.functionSignatures, main.nOperationsMap)).visit(p);
        `TopDown(CollectNumOperationsComparisons(main.functionSignatures, main.nOperationsComparisonsMap)).visit(p);
        `TopDown(CollectNumOperationsIncrDecr(main.functionSignatures, main.nIncrDecrOpMap)).visit(p);
        `TopDown(CollectOpAtrib(main.functionSignatures, main.opAtribMap)).visit(p);
        `TopDown(collectIPL(main.iplMap)).visit(p);
        `TopDown(collectUsedIdsMap(main.usedIdsMap)).visit(p);
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
            Instrucao p3 = `TopDown(startBadSmells()).visit(p);
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

        /** 4) Metric to count Number Of Operations Per Function **/
        writer.write("Number Operations per function:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.nOperationsMap.get(funcao)+"\n" );
        }

        /** 5) Metric to count the Number Of Comments Per Function **/
        int number_comments = 0;  
        for ( String funcao : main.functionSignatures.keySet() ){
          int comments =  main.functionComments.get(funcao); 
          number_comments += comments;
        }
        writer.write("Total comments present on file:" +  number_comments + "\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.functionComments.get(funcao)+"\n" );
        }

        /** 6) Metric for counting number of lines of code (Total lines of code - LOC) 
         * Blank lines should not be counted
         * Comments should not be counted
         **/
        writer.write("Total lines of Code (LOC):\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.lnMap.get(funcao)+"\n" );
        }

        /** 7) Metric to count number path length of instructions*/
        writer.write("Instructions Path Lenght:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.iplMap.get(funcao)+"\n" );
        }

        /** 8) Metric to count Number of Operations Comparisons **/
        writer.write("Total Number of Operations Comparisons:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.nOperationsComparisonsMap.get(funcao)+"\n" );
        }    

        /** 9) Metric to count Number of Increment/Decrement Operations **/
        writer.write("Total Number of Increment/Decrement Operations:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.nIncrDecrOpMap.get(funcao)+"\n" );
        }     

        /** 10) Metric to count Number of Atribuicao Operations **/
        writer.write("Total Number of Atrib/Mult/Div/Soma/Sub:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.opAtribMap.get(funcao)+"\n" );
        }

        /** 10) Metric to  **/
        writer.write("Total Number of Atrib/Mult/Div/Soma/Sub:\n");
        for ( String funcao : main.functionSignatures.keySet()){
          writer.write("\t" + funcao + " : " + main.opAtribMap.get(funcao)+"\n" );
        }

        /******* Printing Separated Metrics ********/

        writer.flush();
        writer.close();
        /**************************************************************************************************************/

      }

      catch (IOException e){
        System.out.println("ERROR in metrics file"); 
      }

      /*Export code generated to .txt file*/
    } catch(Exception e) {
      e.printStackTrace();
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

  %strategy collectUsedIdsInside ( idsUtilizados:TreeSet) extends Identity() {
    visit Instrucao {
      Atribuicao(_,id,_,opAtrib,_,exp,_) -> {
        idsUtilizados.add(`id);
      }
    }
    visit Expressao {
      Id(id) -> { 
        idsUtilizados.add(`id);
      }
      IncAntes(opInc,id) -> { 
        idsUtilizados.add(`id);
      }
      IncDepois(opInc,id) -> { 
        idsUtilizados.add(`id);
      }
    }
  }

  /*Line number counting*/
  %strategy collectUsedIdsMap ( usedIdsMap:HashMap ) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        TreeSet <String> usedIds = new TreeSet <String> ();
        `TopDown( collectUsedIdsInside( usedIds )).visit(`inst);
        usedIdsMap.put(`nome, usedIds );
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

  %strategy startCollectComments(HashMap comments, String funcao) extends Identity() {
    visit LComentarios {
      Comentario(Vazio) -> {
        int num_comentarios = (int) comments.get(funcao);
        num_comentarios++;
        comments.put(funcao, num_comentarios);
      }
    }
  }

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

  /** métrica para contar o número de funções ***/
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

  %strategy CollectNumOperations(func:HashMap, operations:HashMap) extends Identity() {
    visit Instrucao {
      Funcao(_,tipo,_,nome,_,_,argumentos,_,_,inst,_) -> {
        func.put(`nome, `argumentos);
        operations.put(`nome,0);
        `TopDown(startCollectNumOperations(operations,nome)).visit(`inst);
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

  /** %checkBadSmells( smellsMap:HashMap ) extends Identity(){

  //todo


  }

  %RefactorBadSmells() extends Identity(){

  }*/

  /********************************************************************/
  %strategy startBadSmells() extends Identity() {
    visit Instrucao {
      If(c1,c2,c3,Nao(condicao),c4,c5,inst1,inst2) -> {
        return `If(c1,c2,c3,condicao,c4,c5,inst2,inst1);
      }
      Funcao(c1,tipo,c2,nome,c3,c4,argumentos,c5,c6,inst,c7) -> {
        TreeSet<String> idsUtilizados = new TreeSet<String>();
        `TopDown(collectUsedIdsInside(idsUtilizados)).visit(`inst);
        Argumentos args = removeArgumentosNaoUtilizados(`argumentos,idsUtilizados);
        return `Funcao(c1,tipo,c2,nome,c3,c4,args,c5,c6,inst,c7);
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
