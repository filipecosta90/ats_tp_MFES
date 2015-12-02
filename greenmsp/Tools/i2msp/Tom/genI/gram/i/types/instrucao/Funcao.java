
package gram.i.types.instrucao;



public final class Funcao extends gram.i.types.Instrucao implements tom.library.sl.Visitable  {
  
  private static String symbolName = "Funcao";


  private Funcao() {}
  private int hashCode;
  private static Funcao gomProto = new Funcao();
    private gram.i.types.LComentarios _c1;
  private gram.i.types.DefTipo _DefTipo;
  private gram.i.types.LComentarios _c2;
  private String _Nome;
  private gram.i.types.LComentarios _c3;
  private gram.i.types.LComentarios _c4;
  private gram.i.types.Argumentos _Argumentos;
  private gram.i.types.LComentarios _c5;
  private gram.i.types.LComentarios _c6;
  private gram.i.types.Instrucao _Instrucao;
  private gram.i.types.LComentarios _c7;

  /**
   * Constructor that builds a term rooted by Funcao
   *
   * @return a term rooted by Funcao
   */

  public static Funcao make(gram.i.types.LComentarios _c1, gram.i.types.DefTipo _DefTipo, gram.i.types.LComentarios _c2, String _Nome, gram.i.types.LComentarios _c3, gram.i.types.LComentarios _c4, gram.i.types.Argumentos _Argumentos, gram.i.types.LComentarios _c5, gram.i.types.LComentarios _c6, gram.i.types.Instrucao _Instrucao, gram.i.types.LComentarios _c7) {

    // use the proto as a model
    gomProto.initHashCode( _c1,  _DefTipo,  _c2,  _Nome,  _c3,  _c4,  _Argumentos,  _c5,  _c6,  _Instrucao,  _c7);
    return (Funcao) factory.build(gomProto);

  }

  /**
   * Initializes attributes and hashcode of the class
   *
   * @param  _c1
   * @param _DefTipo
   * @param _c2
   * @param _Nome
   * @param _c3
   * @param _c4
   * @param _Argumentos
   * @param _c5
   * @param _c6
   * @param _Instrucao
   * @param _c7
   * @param hashCode hashCode of Funcao
   */
  private void init(gram.i.types.LComentarios _c1, gram.i.types.DefTipo _DefTipo, gram.i.types.LComentarios _c2, String _Nome, gram.i.types.LComentarios _c3, gram.i.types.LComentarios _c4, gram.i.types.Argumentos _Argumentos, gram.i.types.LComentarios _c5, gram.i.types.LComentarios _c6, gram.i.types.Instrucao _Instrucao, gram.i.types.LComentarios _c7, int hashCode) {
    this._c1 = _c1;
    this._DefTipo = _DefTipo;
    this._c2 = _c2;
    this._Nome = _Nome.intern();
    this._c3 = _c3;
    this._c4 = _c4;
    this._Argumentos = _Argumentos;
    this._c5 = _c5;
    this._c6 = _c6;
    this._Instrucao = _Instrucao;
    this._c7 = _c7;

    this.hashCode = hashCode;
  }

  /**
   * Initializes attributes and hashcode of the class
   *
   * @param  _c1
   * @param _DefTipo
   * @param _c2
   * @param _Nome
   * @param _c3
   * @param _c4
   * @param _Argumentos
   * @param _c5
   * @param _c6
   * @param _Instrucao
   * @param _c7
   */
  private void initHashCode(gram.i.types.LComentarios _c1, gram.i.types.DefTipo _DefTipo, gram.i.types.LComentarios _c2, String _Nome, gram.i.types.LComentarios _c3, gram.i.types.LComentarios _c4, gram.i.types.Argumentos _Argumentos, gram.i.types.LComentarios _c5, gram.i.types.LComentarios _c6, gram.i.types.Instrucao _Instrucao, gram.i.types.LComentarios _c7) {
    this._c1 = _c1;
    this._DefTipo = _DefTipo;
    this._c2 = _c2;
    this._Nome = _Nome.intern();
    this._c3 = _c3;
    this._c4 = _c4;
    this._Argumentos = _Argumentos;
    this._c5 = _c5;
    this._c6 = _c6;
    this._Instrucao = _Instrucao;
    this._c7 = _c7;

    this.hashCode = hashFunction();
  }

  /* name and arity */

  /**
   * Returns the name of the symbol
   *
   * @return the name of the symbol
   */
  @Override
  public String symbolName() {
    return "Funcao";
  }

  /**
   * Returns the arity of the symbol
   *
   * @return the arity of the symbol
   */
  private int getArity() {
    return 11;
  }

  /**
   * Copy the object and returns the copy
   *
   * @return a clone of the SharedObject
   */
  public shared.SharedObject duplicate() {
    Funcao clone = new Funcao();
    clone.init( _c1,  _DefTipo,  _c2,  _Nome,  _c3,  _c4,  _Argumentos,  _c5,  _c6,  _Instrucao,  _c7, hashCode);
    return clone;
  }
  
  /**
   * Appends a string representation of this term to the buffer given as argument.
   *
   * @param buffer the buffer to which a string represention of this term is appended.
   */
  @Override
  public void toStringBuilder(java.lang.StringBuilder buffer) {
    buffer.append("Funcao(");
    _c1.toStringBuilder(buffer);
buffer.append(",");
    _DefTipo.toStringBuilder(buffer);
buffer.append(",");
    _c2.toStringBuilder(buffer);
buffer.append(",");
    buffer.append('"');
            for (int i = 0; i < _Nome.length(); i++) {
              char c = _Nome.charAt(i);
              switch (c) {
                case '\n':
                  buffer.append('\\');
                  buffer.append('n');
                  break;
                case '\t':
                  buffer.append('\\');
                  buffer.append('t');
                  break;
                case '\b':
                  buffer.append('\\');
                  buffer.append('b');
                  break;
                case '\r':
                  buffer.append('\\');
                  buffer.append('r');
                  break;
                case '\f':
                  buffer.append('\\');
                  buffer.append('f');
                  break;
                case '\\':
                  buffer.append('\\');
                  buffer.append('\\');
                  break;
                case '\'':
                  buffer.append('\\');
                  buffer.append('\'');
                  break;
                case '\"':
                  buffer.append('\\');
                  buffer.append('\"');
                  break;
                case '!':
                case '@':
                case '#':
                case '$':
                case '%':
                case '^':
                case '&':
                case '*':
                case '(':
                case ')':
                case '-':
                case '_':
                case '+':
                case '=':
                case '|':
                case '~':
                case '{':
                case '}':
                case '[':
                case ']':
                case ';':
                case ':':
                case '<':
                case '>':
                case ',':
                case '.':
                case '?':
                case ' ':
                case '/':
                  buffer.append(c);
                  break;

                default:
                  if (java.lang.Character.isLetterOrDigit(c)) {
                    buffer.append(c);
                  } else {
                    buffer.append('\\');
                    buffer.append((char) ('0' + c / 64));
                    c = (char) (c % 64);
                    buffer.append((char) ('0' + c / 8));
                    c = (char) (c % 8);
                    buffer.append((char) ('0' + c));
                  }
              }
            }
            buffer.append('"');
buffer.append(",");
    _c3.toStringBuilder(buffer);
buffer.append(",");
    _c4.toStringBuilder(buffer);
buffer.append(",");
    _Argumentos.toStringBuilder(buffer);
buffer.append(",");
    _c5.toStringBuilder(buffer);
buffer.append(",");
    _c6.toStringBuilder(buffer);
buffer.append(",");
    _Instrucao.toStringBuilder(buffer);
buffer.append(",");
    _c7.toStringBuilder(buffer);

    buffer.append(")");
  }


  /**
   * Compares two terms. This functions implements a total lexicographic path ordering.
   *
   * @param o object to which this term is compared
   * @return a negative integer, zero, or a positive integer as this
   *         term is less than, equal to, or greater than the argument
   * @throws ClassCastException in case of invalid arguments
   * @throws RuntimeException if unable to compare childs
   */
  @Override
  public int compareToLPO(Object o) {
    /*
     * We do not want to compare with any object, only members of the module
     * In case of invalid argument, throw a ClassCastException, as the java api
     * asks for it
     */
    gram.i.iAbstractType ao = (gram.i.iAbstractType) o;
    /* return 0 for equality */
    if (ao == this) { return 0; }
    /* compare the symbols */
    int symbCmp = this.symbolName().compareTo(ao.symbolName());
    if (symbCmp != 0) { return symbCmp; }
    /* compare the childs */
    Funcao tco = (Funcao) ao;
    int _c1Cmp = (this._c1).compareToLPO(tco._c1);
    if(_c1Cmp != 0) {
      return _c1Cmp;
    }

    int _DefTipoCmp = (this._DefTipo).compareToLPO(tco._DefTipo);
    if(_DefTipoCmp != 0) {
      return _DefTipoCmp;
    }

    int _c2Cmp = (this._c2).compareToLPO(tco._c2);
    if(_c2Cmp != 0) {
      return _c2Cmp;
    }

    int _NomeCmp = (this._Nome).compareTo(tco._Nome);
    if(_NomeCmp != 0) {
      return _NomeCmp;
    }


    int _c3Cmp = (this._c3).compareToLPO(tco._c3);
    if(_c3Cmp != 0) {
      return _c3Cmp;
    }

    int _c4Cmp = (this._c4).compareToLPO(tco._c4);
    if(_c4Cmp != 0) {
      return _c4Cmp;
    }

    int _ArgumentosCmp = (this._Argumentos).compareToLPO(tco._Argumentos);
    if(_ArgumentosCmp != 0) {
      return _ArgumentosCmp;
    }

    int _c5Cmp = (this._c5).compareToLPO(tco._c5);
    if(_c5Cmp != 0) {
      return _c5Cmp;
    }

    int _c6Cmp = (this._c6).compareToLPO(tco._c6);
    if(_c6Cmp != 0) {
      return _c6Cmp;
    }

    int _InstrucaoCmp = (this._Instrucao).compareToLPO(tco._Instrucao);
    if(_InstrucaoCmp != 0) {
      return _InstrucaoCmp;
    }

    int _c7Cmp = (this._c7).compareToLPO(tco._c7);
    if(_c7Cmp != 0) {
      return _c7Cmp;
    }

    throw new RuntimeException("Unable to compare");
  }

 /**
   * Compares two terms. This functions implements a total order.
   *
   * @param o object to which this term is compared
   * @return a negative integer, zero, or a positive integer as this
   *         term is less than, equal to, or greater than the argument
   * @throws ClassCastException in case of invalid arguments
   * @throws RuntimeException if unable to compare childs
   */
  @Override
  public int compareTo(Object o) {
    /*
     * We do not want to compare with any object, only members of the module
     * In case of invalid argument, throw a ClassCastException, as the java api
     * asks for it
     */
    gram.i.iAbstractType ao = (gram.i.iAbstractType) o;
    /* return 0 for equality */
    if (ao == this) { return 0; }
    /* use the hash values to discriminate */

    if(hashCode != ao.hashCode()) { return (hashCode < ao.hashCode())?-1:1; }

    /* If not, compare the symbols : back to the normal order */
    int symbCmp = this.symbolName().compareTo(ao.symbolName());
    if (symbCmp != 0) { return symbCmp; }
    /* last resort: compare the childs */
    Funcao tco = (Funcao) ao;
    int _c1Cmp = (this._c1).compareTo(tco._c1);
    if(_c1Cmp != 0) {
      return _c1Cmp;
    }

    int _DefTipoCmp = (this._DefTipo).compareTo(tco._DefTipo);
    if(_DefTipoCmp != 0) {
      return _DefTipoCmp;
    }

    int _c2Cmp = (this._c2).compareTo(tco._c2);
    if(_c2Cmp != 0) {
      return _c2Cmp;
    }

    int _NomeCmp = (this._Nome).compareTo(tco._Nome);
    if(_NomeCmp != 0) {
      return _NomeCmp;
    }


    int _c3Cmp = (this._c3).compareTo(tco._c3);
    if(_c3Cmp != 0) {
      return _c3Cmp;
    }

    int _c4Cmp = (this._c4).compareTo(tco._c4);
    if(_c4Cmp != 0) {
      return _c4Cmp;
    }

    int _ArgumentosCmp = (this._Argumentos).compareTo(tco._Argumentos);
    if(_ArgumentosCmp != 0) {
      return _ArgumentosCmp;
    }

    int _c5Cmp = (this._c5).compareTo(tco._c5);
    if(_c5Cmp != 0) {
      return _c5Cmp;
    }

    int _c6Cmp = (this._c6).compareTo(tco._c6);
    if(_c6Cmp != 0) {
      return _c6Cmp;
    }

    int _InstrucaoCmp = (this._Instrucao).compareTo(tco._Instrucao);
    if(_InstrucaoCmp != 0) {
      return _InstrucaoCmp;
    }

    int _c7Cmp = (this._c7).compareTo(tco._c7);
    if(_c7Cmp != 0) {
      return _c7Cmp;
    }

    throw new RuntimeException("Unable to compare");
  }

 //shared.SharedObject
  /**
   * Returns hashCode
   *
   * @return hashCode
   */
  @Override
  public final int hashCode() {
    return hashCode;
  }

  /**
   * Checks if a SharedObject is equivalent to the current object
   *
   * @param obj SharedObject to test
   * @return true if obj is a Funcao and its members are equal, else false
   */
  public final boolean equivalent(shared.SharedObject obj) {
    if(obj instanceof Funcao) {

      Funcao peer = (Funcao) obj;
      return _c1==peer._c1 && _DefTipo==peer._DefTipo && _c2==peer._c2 && _Nome==peer._Nome && _c3==peer._c3 && _c4==peer._c4 && _Argumentos==peer._Argumentos && _c5==peer._c5 && _c6==peer._c6 && _Instrucao==peer._Instrucao && _c7==peer._c7 && true;
    }
    return false;
  }


   //Instrucao interface
  /**
   * Returns true if the term is rooted by the symbol Funcao
   *
   * @return true, because this is rooted by Funcao
   */
  @Override
  public boolean isFuncao() {
    return true;
  }
  
  /**
   * Returns the attribute gram.i.types.LComentarios
   *
   * @return the attribute gram.i.types.LComentarios
   */
  @Override
  public gram.i.types.LComentarios getc1() {
    return _c1;
  }

  /**
   * Sets and returns the attribute gram.i.types.Instrucao
   *
   * @param set_arg the argument to set
   * @return the attribute gram.i.types.LComentarios which just has been set
   */
  @Override
  public gram.i.types.Instrucao setc1(gram.i.types.LComentarios set_arg) {
    return make(set_arg, _DefTipo, _c2, _Nome, _c3, _c4, _Argumentos, _c5, _c6, _Instrucao, _c7);
  }
  
  /**
   * Returns the attribute gram.i.types.DefTipo
   *
   * @return the attribute gram.i.types.DefTipo
   */
  @Override
  public gram.i.types.DefTipo getDefTipo() {
    return _DefTipo;
  }

  /**
   * Sets and returns the attribute gram.i.types.Instrucao
   *
   * @param set_arg the argument to set
   * @return the attribute gram.i.types.DefTipo which just has been set
   */
  @Override
  public gram.i.types.Instrucao setDefTipo(gram.i.types.DefTipo set_arg) {
    return make(_c1, set_arg, _c2, _Nome, _c3, _c4, _Argumentos, _c5, _c6, _Instrucao, _c7);
  }
  
  /**
   * Returns the attribute gram.i.types.LComentarios
   *
   * @return the attribute gram.i.types.LComentarios
   */
  @Override
  public gram.i.types.LComentarios getc2() {
    return _c2;
  }

  /**
   * Sets and returns the attribute gram.i.types.Instrucao
   *
   * @param set_arg the argument to set
   * @return the attribute gram.i.types.LComentarios which just has been set
   */
  @Override
  public gram.i.types.Instrucao setc2(gram.i.types.LComentarios set_arg) {
    return make(_c1, _DefTipo, set_arg, _Nome, _c3, _c4, _Argumentos, _c5, _c6, _Instrucao, _c7);
  }
  
  /**
   * Returns the attribute String
   *
   * @return the attribute String
   */
  @Override
  public String getNome() {
    return _Nome;
  }

  /**
   * Sets and returns the attribute gram.i.types.Instrucao
   *
   * @param set_arg the argument to set
   * @return the attribute String which just has been set
   */
  @Override
  public gram.i.types.Instrucao setNome(String set_arg) {
    return make(_c1, _DefTipo, _c2, set_arg, _c3, _c4, _Argumentos, _c5, _c6, _Instrucao, _c7);
  }
  
  /**
   * Returns the attribute gram.i.types.LComentarios
   *
   * @return the attribute gram.i.types.LComentarios
   */
  @Override
  public gram.i.types.LComentarios getc3() {
    return _c3;
  }

  /**
   * Sets and returns the attribute gram.i.types.Instrucao
   *
   * @param set_arg the argument to set
   * @return the attribute gram.i.types.LComentarios which just has been set
   */
  @Override
  public gram.i.types.Instrucao setc3(gram.i.types.LComentarios set_arg) {
    return make(_c1, _DefTipo, _c2, _Nome, set_arg, _c4, _Argumentos, _c5, _c6, _Instrucao, _c7);
  }
  
  /**
   * Returns the attribute gram.i.types.LComentarios
   *
   * @return the attribute gram.i.types.LComentarios
   */
  @Override
  public gram.i.types.LComentarios getc4() {
    return _c4;
  }

  /**
   * Sets and returns the attribute gram.i.types.Instrucao
   *
   * @param set_arg the argument to set
   * @return the attribute gram.i.types.LComentarios which just has been set
   */
  @Override
  public gram.i.types.Instrucao setc4(gram.i.types.LComentarios set_arg) {
    return make(_c1, _DefTipo, _c2, _Nome, _c3, set_arg, _Argumentos, _c5, _c6, _Instrucao, _c7);
  }
  
  /**
   * Returns the attribute gram.i.types.Argumentos
   *
   * @return the attribute gram.i.types.Argumentos
   */
  @Override
  public gram.i.types.Argumentos getArgumentos() {
    return _Argumentos;
  }

  /**
   * Sets and returns the attribute gram.i.types.Instrucao
   *
   * @param set_arg the argument to set
   * @return the attribute gram.i.types.Argumentos which just has been set
   */
  @Override
  public gram.i.types.Instrucao setArgumentos(gram.i.types.Argumentos set_arg) {
    return make(_c1, _DefTipo, _c2, _Nome, _c3, _c4, set_arg, _c5, _c6, _Instrucao, _c7);
  }
  
  /**
   * Returns the attribute gram.i.types.LComentarios
   *
   * @return the attribute gram.i.types.LComentarios
   */
  @Override
  public gram.i.types.LComentarios getc5() {
    return _c5;
  }

  /**
   * Sets and returns the attribute gram.i.types.Instrucao
   *
   * @param set_arg the argument to set
   * @return the attribute gram.i.types.LComentarios which just has been set
   */
  @Override
  public gram.i.types.Instrucao setc5(gram.i.types.LComentarios set_arg) {
    return make(_c1, _DefTipo, _c2, _Nome, _c3, _c4, _Argumentos, set_arg, _c6, _Instrucao, _c7);
  }
  
  /**
   * Returns the attribute gram.i.types.LComentarios
   *
   * @return the attribute gram.i.types.LComentarios
   */
  @Override
  public gram.i.types.LComentarios getc6() {
    return _c6;
  }

  /**
   * Sets and returns the attribute gram.i.types.Instrucao
   *
   * @param set_arg the argument to set
   * @return the attribute gram.i.types.LComentarios which just has been set
   */
  @Override
  public gram.i.types.Instrucao setc6(gram.i.types.LComentarios set_arg) {
    return make(_c1, _DefTipo, _c2, _Nome, _c3, _c4, _Argumentos, _c5, set_arg, _Instrucao, _c7);
  }
  
  /**
   * Returns the attribute gram.i.types.Instrucao
   *
   * @return the attribute gram.i.types.Instrucao
   */
  @Override
  public gram.i.types.Instrucao getInstrucao() {
    return _Instrucao;
  }

  /**
   * Sets and returns the attribute gram.i.types.Instrucao
   *
   * @param set_arg the argument to set
   * @return the attribute gram.i.types.Instrucao which just has been set
   */
  @Override
  public gram.i.types.Instrucao setInstrucao(gram.i.types.Instrucao set_arg) {
    return make(_c1, _DefTipo, _c2, _Nome, _c3, _c4, _Argumentos, _c5, _c6, set_arg, _c7);
  }
  
  /**
   * Returns the attribute gram.i.types.LComentarios
   *
   * @return the attribute gram.i.types.LComentarios
   */
  @Override
  public gram.i.types.LComentarios getc7() {
    return _c7;
  }

  /**
   * Sets and returns the attribute gram.i.types.Instrucao
   *
   * @param set_arg the argument to set
   * @return the attribute gram.i.types.LComentarios which just has been set
   */
  @Override
  public gram.i.types.Instrucao setc7(gram.i.types.LComentarios set_arg) {
    return make(_c1, _DefTipo, _c2, _Nome, _c3, _c4, _Argumentos, _c5, _c6, _Instrucao, set_arg);
  }
  
  /* AbstractType */
  /**
   * Returns an ATerm representation of this term.
   *
   * @return an ATerm representation of this term.
   */
  @Override
  public aterm.ATerm toATerm() {
    aterm.ATerm res = super.toATerm();
    if(res != null) {
      // the super class has produced an ATerm (may be a variadic operator)
      return res;
    }
    return atermFactory.makeAppl(
      atermFactory.makeAFun(symbolName(),getArity(),false),
      new aterm.ATerm[] {getc1().toATerm(), getDefTipo().toATerm(), getc2().toATerm(), (aterm.ATerm) atermFactory.makeAppl(atermFactory.makeAFun(getNome() ,0 , true)), getc3().toATerm(), getc4().toATerm(), getArgumentos().toATerm(), getc5().toATerm(), getc6().toATerm(), getInstrucao().toATerm(), getc7().toATerm()});
  }

  /**
   * Apply a conversion on the ATerm contained in the String and returns a gram.i.types.Instrucao from it
   *
   * @param trm ATerm to convert into a Gom term
   * @param atConv ATerm Converter used to convert the ATerm
   * @return the Gom term
   */
  public static gram.i.types.Instrucao fromTerm(aterm.ATerm trm, tom.library.utils.ATermConverter atConv) {
    trm = atConv.convert(trm);
    if(trm instanceof aterm.ATermAppl) {
      aterm.ATermAppl appl = (aterm.ATermAppl) trm;
      if(symbolName.equals(appl.getName()) && !appl.getAFun().isQuoted()) {
        return make(
gram.i.types.LComentarios.fromTerm(appl.getArgument(0),atConv), gram.i.types.DefTipo.fromTerm(appl.getArgument(1),atConv), gram.i.types.LComentarios.fromTerm(appl.getArgument(2),atConv), convertATermToString(appl.getArgument(3), atConv), gram.i.types.LComentarios.fromTerm(appl.getArgument(4),atConv), gram.i.types.LComentarios.fromTerm(appl.getArgument(5),atConv), gram.i.types.Argumentos.fromTerm(appl.getArgument(6),atConv), gram.i.types.LComentarios.fromTerm(appl.getArgument(7),atConv), gram.i.types.LComentarios.fromTerm(appl.getArgument(8),atConv), gram.i.types.Instrucao.fromTerm(appl.getArgument(9),atConv), gram.i.types.LComentarios.fromTerm(appl.getArgument(10),atConv)
        );
      }
    }
    return null;
  }

  /* Visitable */
  /**
   * Returns the number of childs of the term
   *
   * @return the number of childs of the term
   */
  public int getChildCount() {
    return 11;
  }

  /**
   * Returns the child at the specified index
   *
   * @param index index of the child to return; must be
             nonnegative and less than the childCount
   * @return the child at the specified index
   * @throws IndexOutOfBoundsException if the index out of range
   */
  public tom.library.sl.Visitable getChildAt(int index) {
    switch(index) {
      case 0: return _c1;
      case 1: return _DefTipo;
      case 2: return _c2;
      case 3: return new tom.library.sl.VisitableBuiltin<String>(_Nome);
      case 4: return _c3;
      case 5: return _c4;
      case 6: return _Argumentos;
      case 7: return _c5;
      case 8: return _c6;
      case 9: return _Instrucao;
      case 10: return _c7;

      default: throw new IndexOutOfBoundsException();
    }
  }

  /**
   * Set the child at the specified index
   *
   * @param index index of the child to set; must be
             nonnegative and less than the childCount
   * @param v child to set at the specified index
   * @return the child which was just set
   * @throws IndexOutOfBoundsException if the index out of range
   */
  public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable v) {
    switch(index) {
      case 0: return make((gram.i.types.LComentarios) v, _DefTipo, _c2, getNome(), _c3, _c4, _Argumentos, _c5, _c6, _Instrucao, _c7);
      case 1: return make(_c1, (gram.i.types.DefTipo) v, _c2, getNome(), _c3, _c4, _Argumentos, _c5, _c6, _Instrucao, _c7);
      case 2: return make(_c1, _DefTipo, (gram.i.types.LComentarios) v, getNome(), _c3, _c4, _Argumentos, _c5, _c6, _Instrucao, _c7);
      case 3: return make(_c1, _DefTipo, _c2, getNome(), _c3, _c4, _Argumentos, _c5, _c6, _Instrucao, _c7);
      case 4: return make(_c1, _DefTipo, _c2, getNome(), (gram.i.types.LComentarios) v, _c4, _Argumentos, _c5, _c6, _Instrucao, _c7);
      case 5: return make(_c1, _DefTipo, _c2, getNome(), _c3, (gram.i.types.LComentarios) v, _Argumentos, _c5, _c6, _Instrucao, _c7);
      case 6: return make(_c1, _DefTipo, _c2, getNome(), _c3, _c4, (gram.i.types.Argumentos) v, _c5, _c6, _Instrucao, _c7);
      case 7: return make(_c1, _DefTipo, _c2, getNome(), _c3, _c4, _Argumentos, (gram.i.types.LComentarios) v, _c6, _Instrucao, _c7);
      case 8: return make(_c1, _DefTipo, _c2, getNome(), _c3, _c4, _Argumentos, _c5, (gram.i.types.LComentarios) v, _Instrucao, _c7);
      case 9: return make(_c1, _DefTipo, _c2, getNome(), _c3, _c4, _Argumentos, _c5, _c6, (gram.i.types.Instrucao) v, _c7);
      case 10: return make(_c1, _DefTipo, _c2, getNome(), _c3, _c4, _Argumentos, _c5, _c6, _Instrucao, (gram.i.types.LComentarios) v);

      default: throw new IndexOutOfBoundsException();
    }
  }

  /**
   * Set children to the term
   *
   * @param childs array of children to set
   * @return an array of children which just were set
   * @throws IndexOutOfBoundsException if length of "childs" is different than 11
   */
  @SuppressWarnings("unchecked")
  public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] childs) {
    if (childs.length == 11  && childs[0] instanceof gram.i.types.LComentarios && childs[1] instanceof gram.i.types.DefTipo && childs[2] instanceof gram.i.types.LComentarios && childs[3] instanceof tom.library.sl.VisitableBuiltin && childs[4] instanceof gram.i.types.LComentarios && childs[5] instanceof gram.i.types.LComentarios && childs[6] instanceof gram.i.types.Argumentos && childs[7] instanceof gram.i.types.LComentarios && childs[8] instanceof gram.i.types.LComentarios && childs[9] instanceof gram.i.types.Instrucao && childs[10] instanceof gram.i.types.LComentarios) {
      return make((gram.i.types.LComentarios) childs[0], (gram.i.types.DefTipo) childs[1], (gram.i.types.LComentarios) childs[2], ((tom.library.sl.VisitableBuiltin<String>)childs[3]).getBuiltin(), (gram.i.types.LComentarios) childs[4], (gram.i.types.LComentarios) childs[5], (gram.i.types.Argumentos) childs[6], (gram.i.types.LComentarios) childs[7], (gram.i.types.LComentarios) childs[8], (gram.i.types.Instrucao) childs[9], (gram.i.types.LComentarios) childs[10]);
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  /**
   * Returns the whole children of the term
   *
   * @return the children of the term
   */
  public tom.library.sl.Visitable[] getChildren() {
    return new tom.library.sl.Visitable[] {  _c1,  _DefTipo,  _c2,  new tom.library.sl.VisitableBuiltin<String>(_Nome),  _c3,  _c4,  _Argumentos,  _c5,  _c6,  _Instrucao,  _c7 };
  }

    /**
     * Compute a hashcode for this term.
     * (for internal use)
     *
     * @return a hash value
     */
  protected int hashFunction() {
    int a, b, c;
    /* Set up the internal state */
    a = 0x9e3779b9; /* the golden ratio; an arbitrary value */
    b = (-1264200246<<8);
    c = getArity();
    /* -------------------------------------- handle most of the key */
    /* ------------------------------------ handle the last 11 bytes */
    c += (_c1.hashCode() << 16);
    c += (_DefTipo.hashCode() << 8);
    c += (_c2.hashCode());
    b += (shared.HashFunctions.stringHashFunction(_Nome, 7) << 24);
    b += (_c3.hashCode() << 16);
    b += (_c4.hashCode() << 8);
    b += (_Argumentos.hashCode());
    a += (_c5.hashCode() << 24);
    a += (_c6.hashCode() << 16);
    a += (_Instrucao.hashCode() << 8);
    a += (_c7.hashCode());

    a -= b; a -= c; a ^= (c >> 13);
    b -= c; b -= a; b ^= (a << 8);
    c -= a; c -= b; c ^= (b >> 13);
    a -= b; a -= c; a ^= (c >> 12);
    b -= c; b -= a; b ^= (a << 16);
    c -= a; c -= b; c ^= (b >> 5);
    a -= b; a -= c; a ^= (c >> 3);
    b -= c; b -= a; b ^= (a << 10);
    c -= a; c -= b; c ^= (b >> 15);
    /* ------------------------------------------- report the result */
    return c;
  }

}
