
package gram.i.types.expressao;



public final class IncAntes extends gram.i.types.Expressao implements tom.library.sl.Visitable  {
  
  private static String symbolName = "IncAntes";


  private IncAntes() {}
  private int hashCode;
  private static IncAntes gomProto = new IncAntes();
    private gram.i.types.OpInc _OpInc;
  private String _Id;

  /**
   * Constructor that builds a term rooted by IncAntes
   *
   * @return a term rooted by IncAntes
   */

  public static IncAntes make(gram.i.types.OpInc _OpInc, String _Id) {

    // use the proto as a model
    gomProto.initHashCode( _OpInc,  _Id);
    return (IncAntes) factory.build(gomProto);

  }

  /**
   * Initializes attributes and hashcode of the class
   *
   * @param  _OpInc
   * @param _Id
   * @param hashCode hashCode of IncAntes
   */
  private void init(gram.i.types.OpInc _OpInc, String _Id, int hashCode) {
    this._OpInc = _OpInc;
    this._Id = _Id.intern();

    this.hashCode = hashCode;
  }

  /**
   * Initializes attributes and hashcode of the class
   *
   * @param  _OpInc
   * @param _Id
   */
  private void initHashCode(gram.i.types.OpInc _OpInc, String _Id) {
    this._OpInc = _OpInc;
    this._Id = _Id.intern();

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
    return "IncAntes";
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
    IncAntes clone = new IncAntes();
    clone.init( _OpInc,  _Id, hashCode);
    return clone;
  }
  
  /**
   * Appends a string representation of this term to the buffer given as argument.
   *
   * @param buffer the buffer to which a string represention of this term is appended.
   */
  @Override
  public void toStringBuilder(java.lang.StringBuilder buffer) {
    buffer.append("IncAntes(");
    _OpInc.toStringBuilder(buffer);
buffer.append(",");
    buffer.append('"');
            for (int i = 0; i < _Id.length(); i++) {
              char c = _Id.charAt(i);
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
    IncAntes tco = (IncAntes) ao;
    int _OpIncCmp = (this._OpInc).compareToLPO(tco._OpInc);
    if(_OpIncCmp != 0) {
      return _OpIncCmp;
    }

    int _IdCmp = (this._Id).compareTo(tco._Id);
    if(_IdCmp != 0) {
      return _IdCmp;
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
    IncAntes tco = (IncAntes) ao;
    int _OpIncCmp = (this._OpInc).compareTo(tco._OpInc);
    if(_OpIncCmp != 0) {
      return _OpIncCmp;
    }

    int _IdCmp = (this._Id).compareTo(tco._Id);
    if(_IdCmp != 0) {
      return _IdCmp;
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
   * @return true if obj is a IncAntes and its members are equal, else false
   */
  public final boolean equivalent(shared.SharedObject obj) {
    if(obj instanceof IncAntes) {

      IncAntes peer = (IncAntes) obj;
      return _OpInc==peer._OpInc && _Id==peer._Id && true;
    }
    return false;
  }


   //Expressao interface
  /**
   * Returns true if the term is rooted by the symbol IncAntes
   *
   * @return true, because this is rooted by IncAntes
   */
  @Override
  public boolean isIncAntes() {
    return true;
  }
  
  /**
   * Returns the attribute gram.i.types.OpInc
   *
   * @return the attribute gram.i.types.OpInc
   */
  @Override
  public gram.i.types.OpInc getOpInc() {
    return _OpInc;
  }

  /**
   * Sets and returns the attribute gram.i.types.Expressao
   *
   * @param set_arg the argument to set
   * @return the attribute gram.i.types.OpInc which just has been set
   */
  @Override
  public gram.i.types.Expressao setOpInc(gram.i.types.OpInc set_arg) {
    return make(set_arg, _Id);
  }
  
  /**
   * Returns the attribute String
   *
   * @return the attribute String
   */
  @Override
  public String getId() {
    return _Id;
  }

  /**
   * Sets and returns the attribute gram.i.types.Expressao
   *
   * @param set_arg the argument to set
   * @return the attribute String which just has been set
   */
  @Override
  public gram.i.types.Expressao setId(String set_arg) {
    return make(_OpInc, set_arg);
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
      new aterm.ATerm[] {getOpInc().toATerm(), (aterm.ATerm) atermFactory.makeAppl(atermFactory.makeAFun(getId() ,0 , true))});
  }

  /**
   * Apply a conversion on the ATerm contained in the String and returns a gram.i.types.Expressao from it
   *
   * @param trm ATerm to convert into a Gom term
   * @param atConv ATerm Converter used to convert the ATerm
   * @return the Gom term
   */
  public static gram.i.types.Expressao fromTerm(aterm.ATerm trm, tom.library.utils.ATermConverter atConv) {
    trm = atConv.convert(trm);
    if(trm instanceof aterm.ATermAppl) {
      aterm.ATermAppl appl = (aterm.ATermAppl) trm;
      if(symbolName.equals(appl.getName()) && !appl.getAFun().isQuoted()) {
        return make(
gram.i.types.OpInc.fromTerm(appl.getArgument(0),atConv), convertATermToString(appl.getArgument(1), atConv)
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
      case 0: return _OpInc;
      case 1: return new tom.library.sl.VisitableBuiltin<String>(_Id);

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
      case 0: return make((gram.i.types.OpInc) v, getId());
      case 1: return make(_OpInc, getId());

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
    if (childs.length == 2  && childs[0] instanceof gram.i.types.OpInc && childs[1] instanceof tom.library.sl.VisitableBuiltin) {
      return make((gram.i.types.OpInc) childs[0], ((tom.library.sl.VisitableBuiltin<String>)childs[1]).getBuiltin());
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
    return new tom.library.sl.Visitable[] {  _OpInc,  new tom.library.sl.VisitableBuiltin<String>(_Id) };
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
    b = (1466426365<<8);
    c = getArity();
    /* -------------------------------------- handle most of the key */
    /* ------------------------------------ handle the last 11 bytes */
    a += (_OpInc.hashCode() << 8);
    a += (shared.HashFunctions.stringHashFunction(_Id, 0));

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
