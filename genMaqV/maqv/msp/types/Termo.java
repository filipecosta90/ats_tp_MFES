
package maqv.msp.types;


public abstract class Termo extends maqv.msp.mspAbstractType  {
  /**
   * Sole constructor.  (For invocation by subclass
   * constructors, typically implicit.)
   */
  protected Termo() {}



  /**
   * Returns true if the term is rooted by the symbol I
   *
   * @return true if the term is rooted by the symbol I
   */
  public boolean isI() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol S
   *
   * @return true if the term is rooted by the symbol S
   */
  public boolean isS() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol B
   *
   * @return true if the term is rooted by the symbol B
   */
  public boolean isB() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol F
   *
   * @return true if the term is rooted by the symbol F
   */
  public boolean isF() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Vazio
   *
   * @return true if the term is rooted by the symbol Vazio
   */
  public boolean isVazio() {
    return false;
  }

  /**
   * Returns the subterm corresponding to the slot i
   *
   * @return the subterm corresponding to the slot i
   */
  public int geti() {
    throw new UnsupportedOperationException("This Termo has no i");
  }

  /**
   * Returns a new term where the subterm corresponding to the slot i
   * is replaced by the term given in argument.
   * Note that there is no side-effect: a new term is returned and the original term is left unchanged
   *
   * @param _arg the value of the new subterm
   * @return a new term where the subterm corresponding to the slot i is replaced by _arg
   */
  public Termo seti(int _arg) {
    throw new UnsupportedOperationException("This Termo has no i");
  }

  /**
   * Returns the subterm corresponding to the slot b
   *
   * @return the subterm corresponding to the slot b
   */
  public maqv.msp.types.Boool getb() {
    throw new UnsupportedOperationException("This Termo has no b");
  }

  /**
   * Returns a new term where the subterm corresponding to the slot b
   * is replaced by the term given in argument.
   * Note that there is no side-effect: a new term is returned and the original term is left unchanged
   *
   * @param _arg the value of the new subterm
   * @return a new term where the subterm corresponding to the slot b is replaced by _arg
   */
  public Termo setb(maqv.msp.types.Boool _arg) {
    throw new UnsupportedOperationException("This Termo has no b");
  }

  /**
   * Returns the subterm corresponding to the slot f
   *
   * @return the subterm corresponding to the slot f
   */
  public int getf() {
    throw new UnsupportedOperationException("This Termo has no f");
  }

  /**
   * Returns a new term where the subterm corresponding to the slot f
   * is replaced by the term given in argument.
   * Note that there is no side-effect: a new term is returned and the original term is left unchanged
   *
   * @param _arg the value of the new subterm
   * @return a new term where the subterm corresponding to the slot f is replaced by _arg
   */
  public Termo setf(int _arg) {
    throw new UnsupportedOperationException("This Termo has no f");
  }

  /**
   * Returns the subterm corresponding to the slot id
   *
   * @return the subterm corresponding to the slot id
   */
  public String getid() {
    throw new UnsupportedOperationException("This Termo has no id");
  }

  /**
   * Returns a new term where the subterm corresponding to the slot id
   * is replaced by the term given in argument.
   * Note that there is no side-effect: a new term is returned and the original term is left unchanged
   *
   * @param _arg the value of the new subterm
   * @return a new term where the subterm corresponding to the slot id is replaced by _arg
   */
  public Termo setid(String _arg) {
    throw new UnsupportedOperationException("This Termo has no id");
  }

  protected static tom.library.utils.IdConverter idConv = new tom.library.utils.IdConverter();

  /**
   * Returns an ATerm representation of this term.
   *
   * @return null to indicate to sub-classes that they have to work
   */
  public aterm.ATerm toATerm() {
    // returns null to indicate sub-classes that they have to work
    return null;
  }

  /**
   * Returns a maqv.msp.types.Termo from an ATerm without any conversion
   *
   * @param trm ATerm to handle to retrieve a Gom term
   * @return the term from the ATerm
   */
  public static maqv.msp.types.Termo fromTerm(aterm.ATerm trm) {
    return fromTerm(trm,idConv);
  }

  /**
   * Returns a maqv.msp.types.Termo from a String without any conversion
   *
   * @param s String containing the ATerm
   * @return the term from the String
   */
  public static maqv.msp.types.Termo fromString(String s) {
    return fromTerm(atermFactory.parse(s),idConv);
  }

  /**
   * Returns a maqv.msp.types.Termo from a Stream without any conversion
   *
   * @param stream stream containing the ATerm
   * @return the term from the Stream
   * @throws java.io.IOException if a problem occurs with the stream
   */
  public static maqv.msp.types.Termo fromStream(java.io.InputStream stream) throws java.io.IOException {
    return fromTerm(atermFactory.readFromFile(stream),idConv);
  }

  /**
   * Apply a conversion on the ATerm and returns a maqv.msp.types.Termo
   *
   * @param trm ATerm to convert into a Gom term
   * @param atConv ATermConverter used to convert the ATerm
   * @return the Gom term
   * @throws IllegalArgumentException
   */
  public static maqv.msp.types.Termo fromTerm(aterm.ATerm trm, tom.library.utils.ATermConverter atConv) {
    aterm.ATerm convertedTerm = atConv.convert(trm);
    maqv.msp.types.Termo tmp;
    java.util.ArrayList<maqv.msp.types.Termo> results = new java.util.ArrayList<maqv.msp.types.Termo>();

    tmp = maqv.msp.types.termo.I.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = maqv.msp.types.termo.S.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = maqv.msp.types.termo.B.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = maqv.msp.types.termo.F.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = maqv.msp.types.termo.Vazio.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    switch(results.size()) {
      case 0:
        throw new IllegalArgumentException(trm + " is not a Termo");
      case 1:
        return results.get(0);
      default:
        java.util.logging.Logger.getLogger("Termo").log(java.util.logging.Level.WARNING,"There were many possibilities ({0}) in {1} but the first one was chosen: {2}",new Object[] {results.toString(), "maqv.msp.types.Termo", results.get(0).toString()});
        return results.get(0);
    }
  }

  /**
   * Apply a conversion on the ATerm contained in the String and returns a maqv.msp.types.Termo from it
   *
   * @param s String containing the ATerm
   * @param atConv ATerm Converter used to convert the ATerm
   * @return the Gom term
   */
  public static maqv.msp.types.Termo fromString(String s, tom.library.utils.ATermConverter atConv) {
    return fromTerm(atermFactory.parse(s),atConv);
  }

  /**
   * Apply a conversion on the ATerm contained in the Stream and returns a maqv.msp.types.Termo from it
   *
   * @param stream stream containing the ATerm
   * @param atConv ATerm Converter used to convert the ATerm
   * @return the Gom term
   */
  public static maqv.msp.types.Termo fromStream(java.io.InputStream stream, tom.library.utils.ATermConverter atConv) throws java.io.IOException {
    return fromTerm(atermFactory.readFromFile(stream),atConv);
  }

  /**
   * Returns the length of the list
   *
   * @return the length of the list
   * @throws IllegalArgumentException if the term is not a list
   */
  public int length() {
    throw new IllegalArgumentException(
      "This "+this.getClass().getName()+" is not a list");
  }

  /**
   * Returns an inverted term
   *
   * @return the inverted list
   * @throws IllegalArgumentException if the term is not a list
   */
  public maqv.msp.types.Termo reverse() {
    throw new IllegalArgumentException(
      "This "+this.getClass().getName()+" is not a list");
  }
  
}
