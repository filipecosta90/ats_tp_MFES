
package gram.i.types.instrucao;



public final class While extends gram.i.types.Instrucao implements tom.library.sl.Visitable  {
  
  private static String symbolName = "While";


  private While() {}
  private int hashCode;
  private static While gomProto = new While();
    private gram.i.types.LComentarios _c1;
  private gram.i.types.LComentarios _c2;
  private gram.i.types.LComentarios _c3;
  private gram.i.types.Expressao _Condicao;
  private gram.i.types.LComentarios _c4;
  private gram.i.types.LComentarios _c5;
  private gram.i.types.Instrucao _Instrucao;
  private gram.i.types.LComentarios _c6;

  /**
   * Constructor that builds a term rooted by While
   *
   * @return a term rooted by While
   */

  public static While make(gram.i.types.LComentarios _c1, gram.i.types.LComentarios _c2, gram.i.types.LComentarios _c3, gram.i.types.Expressao _Condicao, gram.i.types.LComentarios _c4, gram.i.types.LComentarios _c5, gram.i.types.Instrucao _Instrucao, gram.i.types.LComentarios _c6) {

    // use the proto as a model
    gomProto.initHashCode( _c1,  _c2,  _c3,  _Condicao,  _c4,  _c5,  _Instrucao,  _c6);
    return (While) factory.build(gomProto);

  }

  /**
   * Initializes attributes and hashcode of the class
   *
   * @param  _c1
   * @param _c2
   * @param _c3
   * @param _Condicao
   * @param _c4
   * @param _c5
   * @param _Instrucao
   * @param _c6
   * @param hashCode hashCode of While
   */
  private void init(gram.i.types.LComentarios _c1, gram.i.types.LComentarios _c2, gram.i.types.LComentarios _c3, gram.i.types.Expressao _Condicao, gram.i.types.LComentarios _c4, gram.i.types.LComentarios _c5, gram.i.types.Instrucao _Instrucao, gram.i.types.LComentarios _c6, int hashCode) {
    this._c1 = _c1;
    this._c2 = _c2;
    this._c3 = _c3;
    this._Condicao = _Condicao;
    this._c4 = _c4;
    this._c5 = _c5;
    this._Instrucao = _Instrucao;
    this._c6 = _c6;

    this.hashCode = hashCode;
  }

  /**
   * Initializes attributes and hashcode of the class
   *
   * @param  _c1
   * @param _c2
   * @param _c3
   * @param _Condicao
   * @param _c4
   * @param _c5
   * @param _Instrucao
   * @param _c6
   */
  private void initHashCode(gram.i.types.LComentarios _c1, gram.i.types.LComentarios _c2, gram.i.types.LComentarios _c3, gram.i.types.Expressao _Condicao, gram.i.types.LComentarios _c4, gram.i.types.LComentarios _c5, gram.i.types.Instrucao _Instrucao, gram.i.types.LComentarios _c6) {
    this._c1 = _c1;
    this._c2 = _c2;
    this._c3 = _c3;
    this._Condicao = _Condicao;
    this._c4 = _c4;
    this._c5 = _c5;
    this._Instrucao = _Instrucao;
    this._c6 = _c6;

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
    return "While";
  }

  /**
   * Returns the arity of the symbol
   *
   * @return the arity of the symbol
   */
  private int getArity() {
    return 8;
  }

  /**
   * Copy the object and returns the copy
   *
   * @return a clone of the SharedObject
   */
  public shared.SharedObject duplicate() {
    While clone = new While();
    clone.init( _c1,  _c2,  _c3,  _Condicao,  _c4,  _c5,  _Instrucao,  _c6, hashCode);
    return clone;
  }
  
  /**
   * Appends a string representation of this term to the buffer given as argument.
   *
   * @param buffer the buffer to which a string represention of this term is appended.
   */
  @Override
  public void toStringBuilder(java.lang.StringBuilder buffer) {
    buffer.append("While(");
    _c1.toStringBuilder(buffer);
buffer.append(",");
    _c2.toStringBuilder(buffer);
buffer.append(",");
    _c3.toStringBuilder(buffer);
buffer.append(",");
    _Condicao.toStringBuilder(buffer);
buffer.append(",");
    _c4.toStringBuilder(buffer);
buffer.append(",");
    _c5.toStringBuilder(buffer);
buffer.append(",");
    _Instrucao.toStringBuilder(buffer);
buffer.append(",");
    _c6.toStringBuilder(buffer);

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
    While tco = (While) ao;
    int _c1Cmp = (this._c1).compareToLPO(tco._c1);
    if(_c1Cmp != 0) {
      return _c1Cmp;
    }

    int _c2Cmp = (this._c2).compareToLPO(tco._c2);
    if(_c2Cmp != 0) {
      return _c2Cmp;
    }

    int _c3Cmp = (this._c3).compareToLPO(tco._c3);
    if(_c3Cmp != 0) {
      return _c3Cmp;
    }

    int _CondicaoCmp = (this._Condicao).compareToLPO(tco._Condicao);
    if(_CondicaoCmp != 0) {
      return _CondicaoCmp;
    }

    int _c4Cmp = (this._c4).compareToLPO(tco._c4);
    if(_c4Cmp != 0) {
      return _c4Cmp;
    }

    int _c5Cmp = (this._c5).compareToLPO(tco._c5);
    if(_c5Cmp != 0) {
      return _c5Cmp;
    }

    int _InstrucaoCmp = (this._Instrucao).compareToLPO(tco._Instrucao);
    if(_InstrucaoCmp != 0) {
      return _InstrucaoCmp;
    }

    int _c6Cmp = (this._c6).compareToLPO(tco._c6);
    if(_c6Cmp != 0) {
      return _c6Cmp;
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
    While tco = (While) ao;
    int _c1Cmp = (this._c1).compareTo(tco._c1);
    if(_c1Cmp != 0) {
      return _c1Cmp;
    }

    int _c2Cmp = (this._c2).compareTo(tco._c2);
    if(_c2Cmp != 0) {
      return _c2Cmp;
    }

    int _c3Cmp = (this._c3).compareTo(tco._c3);
    if(_c3Cmp != 0) {
      return _c3Cmp;
    }

    int _CondicaoCmp = (this._Condicao).compareTo(tco._Condicao);
    if(_CondicaoCmp != 0) {
      return _CondicaoCmp;
    }

    int _c4Cmp = (this._c4).compareTo(tco._c4);
    if(_c4Cmp != 0) {
      return _c4Cmp;
    }

    int _c5Cmp = (this._c5).compareTo(tco._c5);
    if(_c5Cmp != 0) {
      return _c5Cmp;
    }

    int _InstrucaoCmp = (this._Instrucao).compareTo(tco._Instrucao);
    if(_InstrucaoCmp != 0) {
      return _InstrucaoCmp;
    }

    int _c6Cmp = (this._c6).compareTo(tco._c6);
    if(_c6Cmp != 0) {
      return _c6Cmp;
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
   * @return true if obj is a While and its members are equal, else false
   */
  public final boolean equivalent(shared.SharedObject obj) {
    if(obj instanceof While) {

      While peer = (While) obj;
      return _c1==peer._c1 && _c2==peer._c2 && _c3==peer._c3 && _Condicao==peer._Condicao && _c4==peer._c4 && _c5==peer._c5 && _Instrucao==peer._Instrucao && _c6==peer._c6 && true;
    }
    return false;
  }


   //Instrucao interface
  /**
   * Returns true if the term is rooted by the symbol While
   *
   * @return true, because this is rooted by While
   */
  @Override
  public boolean isWhile() {
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
    return make(set_arg, _c2, _c3, _Condicao, _c4, _c5, _Instrucao, _c6);
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
    return make(_c1, set_arg, _c3, _Condicao, _c4, _c5, _Instrucao, _c6);
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
    return make(_c1, _c2, set_arg, _Condicao, _c4, _c5, _Instrucao, _c6);
  }
  
  /**
   * Returns the attribute gram.i.types.Expressao
   *
   * @return the attribute gram.i.types.Expressao
   */
  @Override
  public gram.i.types.Expressao getCondicao() {
    return _Condicao;
  }

  /**
   * Sets and returns the attribute gram.i.types.Instrucao
   *
   * @param set_arg the argument to set
   * @return the attribute gram.i.types.Expressao which just has been set
   */
  @Override
  public gram.i.types.Instrucao setCondicao(gram.i.types.Expressao set_arg) {
    return make(_c1, _c2, _c3, set_arg, _c4, _c5, _Instrucao, _c6);
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
    return make(_c1, _c2, _c3, _Condicao, set_arg, _c5, _Instrucao, _c6);
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
    return make(_c1, _c2, _c3, _Condicao, _c4, set_arg, _Instrucao, _c6);
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
    return make(_c1, _c2, _c3, _Condicao, _c4, _c5, set_arg, _c6);
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
    return make(_c1, _c2, _c3, _Condicao, _c4, _c5, _Instrucao, set_arg);
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
      new aterm.ATerm[] {getc1().toATerm(), getc2().toATerm(), getc3().toATerm(), getCondicao().toATerm(), getc4().toATerm(), getc5().toATerm(), getInstrucao().toATerm(), getc6().toATerm()});
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
gram.i.types.LComentarios.fromTerm(appl.getArgument(0),atConv), gram.i.types.LComentarios.fromTerm(appl.getArgument(1),atConv), gram.i.types.LComentarios.fromTerm(appl.getArgument(2),atConv), gram.i.types.Expressao.fromTerm(appl.getArgument(3),atConv), gram.i.types.LComentarios.fromTerm(appl.getArgument(4),atConv), gram.i.types.LComentarios.fromTerm(appl.getArgument(5),atConv), gram.i.types.Instrucao.fromTerm(appl.getArgument(6),atConv), gram.i.types.LComentarios.fromTerm(appl.getArgument(7),atConv)
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
    return 8;
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
      case 1: return _c2;
      case 2: return _c3;
      case 3: return _Condicao;
      case 4: return _c4;
      case 5: return _c5;
      case 6: return _Instrucao;
      case 7: return _c6;

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
      case 0: return make((gram.i.types.LComentarios) v, _c2, _c3, _Condicao, _c4, _c5, _Instrucao, _c6);
      case 1: return make(_c1, (gram.i.types.LComentarios) v, _c3, _Condicao, _c4, _c5, _Instrucao, _c6);
      case 2: return make(_c1, _c2, (gram.i.types.LComentarios) v, _Condicao, _c4, _c5, _Instrucao, _c6);
      case 3: return make(_c1, _c2, _c3, (gram.i.types.Expressao) v, _c4, _c5, _Instrucao, _c6);
      case 4: return make(_c1, _c2, _c3, _Condicao, (gram.i.types.LComentarios) v, _c5, _Instrucao, _c6);
      case 5: return make(_c1, _c2, _c3, _Condicao, _c4, (gram.i.types.LComentarios) v, _Instrucao, _c6);
      case 6: return make(_c1, _c2, _c3, _Condicao, _c4, _c5, (gram.i.types.Instrucao) v, _c6);
      case 7: return make(_c1, _c2, _c3, _Condicao, _c4, _c5, _Instrucao, (gram.i.types.LComentarios) v);

      default: throw new IndexOutOfBoundsException();
    }
  }

  /**
   * Set children to the term
   *
   * @param childs array of children to set
   * @return an array of children which just were set
   * @throws IndexOutOfBoundsException if length of "childs" is different than 8
   */
  @SuppressWarnings("unchecked")
  public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] childs) {
    if (childs.length == 8  && childs[0] instanceof gram.i.types.LComentarios && childs[1] instanceof gram.i.types.LComentarios && childs[2] instanceof gram.i.types.LComentarios && childs[3] instanceof gram.i.types.Expressao && childs[4] instanceof gram.i.types.LComentarios && childs[5] instanceof gram.i.types.LComentarios && childs[6] instanceof gram.i.types.Instrucao && childs[7] instanceof gram.i.types.LComentarios) {
      return make((gram.i.types.LComentarios) childs[0], (gram.i.types.LComentarios) childs[1], (gram.i.types.LComentarios) childs[2], (gram.i.types.Expressao) childs[3], (gram.i.types.LComentarios) childs[4], (gram.i.types.LComentarios) childs[5], (gram.i.types.Instrucao) childs[6], (gram.i.types.LComentarios) childs[7]);
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
    return new tom.library.sl.Visitable[] {  _c1,  _c2,  _c3,  _Condicao,  _c4,  _c5,  _Instrucao,  _c6 };
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
    b = (1653057342<<8);
    c = getArity();
    /* -------------------------------------- handle most of the key */
    /* ------------------------------------ handle the last 11 bytes */
    b += (_c1.hashCode() << 24);
    b += (_c2.hashCode() << 16);
    b += (_c3.hashCode() << 8);
    b += (_Condicao.hashCode());
    a += (_c4.hashCode() << 24);
    a += (_c5.hashCode() << 16);
    a += (_Instrucao.hashCode() << 8);
    a += (_c6.hashCode());

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
