
package maqv.msp.types.instrucoes;



public final class ConsInstrucoes extends maqv.msp.types.instrucoes.Instrucoes implements tom.library.sl.Visitable  {
  
  private static String symbolName = "ConsInstrucoes";


  private ConsInstrucoes() {}
  private int hashCode;
  private static ConsInstrucoes gomProto = new ConsInstrucoes();
    private maqv.msp.types.Instrucao _HeadInstrucoes;
  private maqv.msp.types.Instrucoes _TailInstrucoes;

  /**
   * Constructor that builds a term rooted by ConsInstrucoes
   *
   * @return a term rooted by ConsInstrucoes
   */

  public static ConsInstrucoes make(maqv.msp.types.Instrucao _HeadInstrucoes, maqv.msp.types.Instrucoes _TailInstrucoes) {

    // use the proto as a model
    gomProto.initHashCode( _HeadInstrucoes,  _TailInstrucoes);
    return (ConsInstrucoes) factory.build(gomProto);

  }

  /**
   * Initializes attributes and hashcode of the class
   *
   * @param  _HeadInstrucoes
   * @param _TailInstrucoes
   * @param hashCode hashCode of ConsInstrucoes
   */
  private void init(maqv.msp.types.Instrucao _HeadInstrucoes, maqv.msp.types.Instrucoes _TailInstrucoes, int hashCode) {
    this._HeadInstrucoes = _HeadInstrucoes;
    this._TailInstrucoes = _TailInstrucoes;

    this.hashCode = hashCode;
  }

  /**
   * Initializes attributes and hashcode of the class
   *
   * @param  _HeadInstrucoes
   * @param _TailInstrucoes
   */
  private void initHashCode(maqv.msp.types.Instrucao _HeadInstrucoes, maqv.msp.types.Instrucoes _TailInstrucoes) {
    this._HeadInstrucoes = _HeadInstrucoes;
    this._TailInstrucoes = _TailInstrucoes;

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
    return "ConsInstrucoes";
  }

  /**
   * Returns the arity of the symbol
   *
   * @return the arity of the symbol
   */
  private int getArity() {
    return 2;
  }

  /**
   * Copy the object and returns the copy
   *
   * @return a clone of the SharedObject
   */
  public shared.SharedObject duplicate() {
    ConsInstrucoes clone = new ConsInstrucoes();
    clone.init( _HeadInstrucoes,  _TailInstrucoes, hashCode);
    return clone;
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
    maqv.msp.mspAbstractType ao = (maqv.msp.mspAbstractType) o;
    /* return 0 for equality */
    if (ao == this) { return 0; }
    /* compare the symbols */
    int symbCmp = this.symbolName().compareTo(ao.symbolName());
    if (symbCmp != 0) { return symbCmp; }
    /* compare the childs */
    ConsInstrucoes tco = (ConsInstrucoes) ao;
    int _HeadInstrucoesCmp = (this._HeadInstrucoes).compareToLPO(tco._HeadInstrucoes);
    if(_HeadInstrucoesCmp != 0) {
      return _HeadInstrucoesCmp;
    }

    int _TailInstrucoesCmp = (this._TailInstrucoes).compareToLPO(tco._TailInstrucoes);
    if(_TailInstrucoesCmp != 0) {
      return _TailInstrucoesCmp;
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
    maqv.msp.mspAbstractType ao = (maqv.msp.mspAbstractType) o;
    /* return 0 for equality */
    if (ao == this) { return 0; }
    /* use the hash values to discriminate */

    if(hashCode != ao.hashCode()) { return (hashCode < ao.hashCode())?-1:1; }

    /* If not, compare the symbols : back to the normal order */
    int symbCmp = this.symbolName().compareTo(ao.symbolName());
    if (symbCmp != 0) { return symbCmp; }
    /* last resort: compare the childs */
    ConsInstrucoes tco = (ConsInstrucoes) ao;
    int _HeadInstrucoesCmp = (this._HeadInstrucoes).compareTo(tco._HeadInstrucoes);
    if(_HeadInstrucoesCmp != 0) {
      return _HeadInstrucoesCmp;
    }

    int _TailInstrucoesCmp = (this._TailInstrucoes).compareTo(tco._TailInstrucoes);
    if(_TailInstrucoesCmp != 0) {
      return _TailInstrucoesCmp;
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
   * @return true if obj is a ConsInstrucoes and its members are equal, else false
   */
  public final boolean equivalent(shared.SharedObject obj) {
    if(obj instanceof ConsInstrucoes) {

      ConsInstrucoes peer = (ConsInstrucoes) obj;
      return _HeadInstrucoes==peer._HeadInstrucoes && _TailInstrucoes==peer._TailInstrucoes && true;
    }
    return false;
  }


   //Instrucoes interface
  /**
   * Returns true if the term is rooted by the symbol ConsInstrucoes
   *
   * @return true, because this is rooted by ConsInstrucoes
   */
  @Override
  public boolean isConsInstrucoes() {
    return true;
  }
  
  /**
   * Returns the attribute maqv.msp.types.Instrucao
   *
   * @return the attribute maqv.msp.types.Instrucao
   */
  @Override
  public maqv.msp.types.Instrucao getHeadInstrucoes() {
    return _HeadInstrucoes;
  }

  /**
   * Sets and returns the attribute maqv.msp.types.Instrucoes
   *
   * @param set_arg the argument to set
   * @return the attribute maqv.msp.types.Instrucao which just has been set
   */
  @Override
  public maqv.msp.types.Instrucoes setHeadInstrucoes(maqv.msp.types.Instrucao set_arg) {
    return make(set_arg, _TailInstrucoes);
  }
  
  /**
   * Returns the attribute maqv.msp.types.Instrucoes
   *
   * @return the attribute maqv.msp.types.Instrucoes
   */
  @Override
  public maqv.msp.types.Instrucoes getTailInstrucoes() {
    return _TailInstrucoes;
  }

  /**
   * Sets and returns the attribute maqv.msp.types.Instrucoes
   *
   * @param set_arg the argument to set
   * @return the attribute maqv.msp.types.Instrucoes which just has been set
   */
  @Override
  public maqv.msp.types.Instrucoes setTailInstrucoes(maqv.msp.types.Instrucoes set_arg) {
    return make(_HeadInstrucoes, set_arg);
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
      new aterm.ATerm[] {getHeadInstrucoes().toATerm(), getTailInstrucoes().toATerm()});
  }

  /**
   * Apply a conversion on the ATerm contained in the String and returns a maqv.msp.types.Instrucoes from it
   *
   * @param trm ATerm to convert into a Gom term
   * @param atConv ATerm Converter used to convert the ATerm
   * @return the Gom term
   */
  public static maqv.msp.types.Instrucoes fromTerm(aterm.ATerm trm, tom.library.utils.ATermConverter atConv) {
    trm = atConv.convert(trm);
    if(trm instanceof aterm.ATermAppl) {
      aterm.ATermAppl appl = (aterm.ATermAppl) trm;
      if(symbolName.equals(appl.getName()) && !appl.getAFun().isQuoted()) {
        return make(
maqv.msp.types.Instrucao.fromTerm(appl.getArgument(0),atConv), maqv.msp.types.Instrucoes.fromTerm(appl.getArgument(1),atConv)
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
    return 2;
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
      case 0: return _HeadInstrucoes;
      case 1: return _TailInstrucoes;

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
      case 0: return make((maqv.msp.types.Instrucao) v, _TailInstrucoes);
      case 1: return make(_HeadInstrucoes, (maqv.msp.types.Instrucoes) v);

      default: throw new IndexOutOfBoundsException();
    }
  }

  /**
   * Set children to the term
   *
   * @param childs array of children to set
   * @return an array of children which just were set
   * @throws IndexOutOfBoundsException if length of "childs" is different than 2
   */
  @SuppressWarnings("unchecked")
  public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] childs) {
    if (childs.length == 2  && childs[0] instanceof maqv.msp.types.Instrucao && childs[1] instanceof maqv.msp.types.Instrucoes) {
      return make((maqv.msp.types.Instrucao) childs[0], (maqv.msp.types.Instrucoes) childs[1]);
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
    return new tom.library.sl.Visitable[] {  _HeadInstrucoes,  _TailInstrucoes };
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
    b = (-1577090557<<8);
    c = getArity();
    /* -------------------------------------- handle most of the key */
    /* ------------------------------------ handle the last 11 bytes */
    a += (_HeadInstrucoes.hashCode() << 8);
    a += (_TailInstrucoes.hashCode());

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
