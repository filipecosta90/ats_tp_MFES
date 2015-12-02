
package gram.i.types.declaracoes;



public abstract class ListaDecl extends gram.i.types.Declaracoes implements java.util.Collection<gram.i.types.Declaracoes>  {


  /**
   * Returns the number of arguments of the variadic operator
   *
   * @return the number of arguments of the variadic operator
   */
  @Override
  public int length() {
    if(this instanceof gram.i.types.declaracoes.ConsListaDecl) {
      gram.i.types.Declaracoes tl = this.getTailListaDecl();
      if (tl instanceof ListaDecl) {
        return 1+((ListaDecl)tl).length();
      } else {
        return 2;
      }
    } else {
      return 0;
    }
  }

  public static gram.i.types.Declaracoes fromArray(gram.i.types.Declaracoes[] array) {
    gram.i.types.Declaracoes res = gram.i.types.declaracoes.EmptyListaDecl.make();
    for(int i = array.length; i>0;) {
      res = gram.i.types.declaracoes.ConsListaDecl.make(array[--i],res);
    }
    return res;
  }

  /**
   * Inverses the term if it is a list
   *
   * @return the inverted term if it is a list, otherwise the term itself
   */
  @Override
  public gram.i.types.Declaracoes reverse() {
    if(this instanceof gram.i.types.declaracoes.ConsListaDecl) {
      gram.i.types.Declaracoes cur = this;
      gram.i.types.Declaracoes rev = gram.i.types.declaracoes.EmptyListaDecl.make();
      while(cur instanceof gram.i.types.declaracoes.ConsListaDecl) {
        rev = gram.i.types.declaracoes.ConsListaDecl.make(cur.getHeadListaDecl(),rev);
        cur = cur.getTailListaDecl();
      }

      if(!(cur instanceof gram.i.types.declaracoes.EmptyListaDecl)) {
        rev = gram.i.types.declaracoes.ConsListaDecl.make(cur,rev);
      }

      return rev;
    } else {
      return this;
    }
  }

  /**
   * Appends an element
   *
   * @param element element which has to be added
   * @return the term with the added element
   */
  public gram.i.types.Declaracoes append(gram.i.types.Declaracoes element) {
    if(this instanceof gram.i.types.declaracoes.ConsListaDecl) {
      gram.i.types.Declaracoes tl = this.getTailListaDecl();
      if (tl instanceof ListaDecl) {
        return gram.i.types.declaracoes.ConsListaDecl.make(this.getHeadListaDecl(),((ListaDecl)tl).append(element));
      } else {

        return gram.i.types.declaracoes.ConsListaDecl.make(this.getHeadListaDecl(),gram.i.types.declaracoes.ConsListaDecl.make(tl,element));

      }
    } else {
      return gram.i.types.declaracoes.ConsListaDecl.make(element,this);
    }
  }

  /**
   * Appends a string representation of this term to the buffer given as argument.
   *
   * @param buffer the buffer to which a string represention of this term is appended.
   */
  @Override
  public void toStringBuilder(java.lang.StringBuilder buffer) {
    buffer.append("ListaDecl(");
    if(this instanceof gram.i.types.declaracoes.ConsListaDecl) {
      gram.i.types.Declaracoes cur = this;
      while(cur instanceof gram.i.types.declaracoes.ConsListaDecl) {
        gram.i.types.Declaracoes elem = cur.getHeadListaDecl();
        cur = cur.getTailListaDecl();
        elem.toStringBuilder(buffer);

        if(cur instanceof gram.i.types.declaracoes.ConsListaDecl) {
          buffer.append(",");
        }
      }
      if(!(cur instanceof gram.i.types.declaracoes.EmptyListaDecl)) {
        buffer.append(",");
        cur.toStringBuilder(buffer);
      }
    }
    buffer.append(")");
  }

  /**
   * Returns an ATerm representation of this term.
   *
   * @return an ATerm representation of this term.
   */
  public aterm.ATerm toATerm() {
    aterm.ATerm res = atermFactory.makeList();
    if(this instanceof gram.i.types.declaracoes.ConsListaDecl) {
      gram.i.types.Declaracoes tail = this.getTailListaDecl();
      res = atermFactory.makeList(getHeadListaDecl().toATerm(),(aterm.ATermList)tail.toATerm());
    }
    return res;
  }

  /**
   * Apply a conversion on the ATerm contained in the String and returns a gram.i.types.Declaracoes from it
   *
   * @param trm ATerm to convert into a Gom term
   * @param atConv ATerm Converter used to convert the ATerm
   * @return the Gom term
   */
  public static gram.i.types.Declaracoes fromTerm(aterm.ATerm trm, tom.library.utils.ATermConverter atConv) {
    trm = atConv.convert(trm);
    if(trm instanceof aterm.ATermAppl) {
      aterm.ATermAppl appl = (aterm.ATermAppl) trm;
      if("ListaDecl".equals(appl.getName())) {
        gram.i.types.Declaracoes res = gram.i.types.declaracoes.EmptyListaDecl.make();

        aterm.ATerm array[] = appl.getArgumentArray();
        for(int i = array.length-1; i>=0; --i) {
          gram.i.types.Declaracoes elem = gram.i.types.Declaracoes.fromTerm(array[i],atConv);
          res = gram.i.types.declaracoes.ConsListaDecl.make(elem,res);
        }
        return res;
      }
    }

    if(trm instanceof aterm.ATermList) {
      aterm.ATermList list = (aterm.ATermList) trm;
      gram.i.types.Declaracoes res = gram.i.types.declaracoes.EmptyListaDecl.make();
      try {
        while(!list.isEmpty()) {
          gram.i.types.Declaracoes elem = gram.i.types.Declaracoes.fromTerm(list.getFirst(),atConv);
          res = gram.i.types.declaracoes.ConsListaDecl.make(elem,res);
          list = list.getNext();
        }
      } catch(IllegalArgumentException e) {
        // returns null when the fromATerm call failed
        return null;
      }
      return res.reverse();
    }

    return null;
  }

  /*
   * Checks if the Collection contains all elements of the parameter Collection
   *
   * @param c the Collection of elements to check
   * @return true if the Collection contains all elements of the parameter, otherwise false
   */
  public boolean containsAll(java.util.Collection c) {
    java.util.Iterator it = c.iterator();
    while(it.hasNext()) {
      if(!this.contains(it.next())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if gram.i.types.Declaracoes contains a specified object
   *
   * @param o object whose presence is tested
   * @return true if gram.i.types.Declaracoes contains the object, otherwise false
   */
  public boolean contains(Object o) {
    gram.i.types.Declaracoes cur = this;
    if(o==null) { return false; }
    if(cur instanceof gram.i.types.declaracoes.ConsListaDecl) {
      while(cur instanceof gram.i.types.declaracoes.ConsListaDecl) {
        if( o.equals(cur.getHeadListaDecl()) ) {
          return true;
        }
        cur = cur.getTailListaDecl();
      }
      if(!(cur instanceof gram.i.types.declaracoes.EmptyListaDecl)) {
        if( o.equals(cur) ) {
          return true;
        }
      }
    }
    return false;
  }

  //public boolean equals(Object o) { return this == o; }

  //public int hashCode() { return hashCode(); }

  /**
   * Checks the emptiness
   *
   * @return true if empty, otherwise false
   */
  public boolean isEmpty() { return isEmptyListaDecl() ; }

  public java.util.Iterator<gram.i.types.Declaracoes> iterator() {
    return new java.util.Iterator<gram.i.types.Declaracoes>() {
      gram.i.types.Declaracoes list = ListaDecl.this;

      public boolean hasNext() {
        return list!=null && !list.isEmptyListaDecl();
      }

      public gram.i.types.Declaracoes next() {
        if(list.isEmptyListaDecl()) {
          throw new java.util.NoSuchElementException();
        }
        if(list.isConsListaDecl()) {
          gram.i.types.Declaracoes head = list.getHeadListaDecl();
          list = list.getTailListaDecl();
          return head;
        } else {
          // we are in this case only if domain=codomain
          // thus, the cast is safe
          Object res = list;
          list = null;
          return (gram.i.types.Declaracoes)res;
        }
      }

      public void remove() {
        throw new UnsupportedOperationException("Not yet implemented");
      }
    };

  }

  public boolean add(gram.i.types.Declaracoes o) {
    throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
  }

  public boolean addAll(java.util.Collection<? extends gram.i.types.Declaracoes> c) {
    throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
  }

  public boolean remove(Object o) {
    throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
  }

  public void clear() {
    throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
  }

  public boolean removeAll(java.util.Collection c) {
    throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
  }

  public boolean retainAll(java.util.Collection c) {
    throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
  }

  /**
   * Returns the size of the collection
   *
   * @return the size of the collection
   */
  public int size() { return length(); }

  /**
   * Returns an array containing the elements of the collection
   *
   * @return an array of elements
   */
  public Object[] toArray() {
    int size = this.length();
    Object[] array = new Object[size];
    int i=0;
    if(this instanceof gram.i.types.declaracoes.ConsListaDecl) {
      gram.i.types.Declaracoes cur = this;
      while(cur instanceof gram.i.types.declaracoes.ConsListaDecl) {
        gram.i.types.Declaracoes elem = cur.getHeadListaDecl();
        array[i] = elem;
        cur = cur.getTailListaDecl();
        i++;
      }
      if(!(cur instanceof gram.i.types.declaracoes.EmptyListaDecl)) {
        array[i] = cur;
      }
    }
    return array;
  }

  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] array) {
    int size = this.length();
    if (array.length < size) {
      array = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size);
    } else if (array.length > size) {
      array[size] = null;
    }
    int i=0;
    if(this instanceof gram.i.types.declaracoes.ConsListaDecl) {
      gram.i.types.Declaracoes cur = this;
      while(cur instanceof gram.i.types.declaracoes.ConsListaDecl) {
        gram.i.types.Declaracoes elem = cur.getHeadListaDecl();
        array[i] = (T)elem;
        cur = cur.getTailListaDecl();
        i++;
      }
      if(!(cur instanceof gram.i.types.declaracoes.EmptyListaDecl)) {
        array[i] = (T)cur;
      }
    }
    return array;
  }

  /*
   * to get a Collection for an immutable list
   */
  public java.util.Collection<gram.i.types.Declaracoes> getCollection() {
    return new CollectionListaDecl(this);
  }

  public java.util.Collection<gram.i.types.Declaracoes> getCollectionListaDecl() {
    return new CollectionListaDecl(this);
  }

  /************************************************************
   * private static class
   ************************************************************/
  private static class CollectionListaDecl implements java.util.Collection<gram.i.types.Declaracoes> {
    private ListaDecl list;

    public ListaDecl getDeclaracoes() {
      return list;
    }

    public CollectionListaDecl(ListaDecl list) {
      this.list = list;
    }

    /**
     * generic
     */
  public boolean addAll(java.util.Collection<? extends gram.i.types.Declaracoes> c) {
    boolean modified = false;
    java.util.Iterator<? extends gram.i.types.Declaracoes> it = c.iterator();
    while(it.hasNext()) {
      modified = modified || add(it.next());
    }
    return modified;
  }

  /**
   * Checks if the collection contains an element
   *
   * @param o element whose presence has to be checked
   * @return true if the element is found, otherwise false
   */
  public boolean contains(Object o) {
    return getDeclaracoes().contains(o);
  }

  /**
   * Checks if the collection contains elements given as parameter
   *
   * @param c elements whose presence has to be checked
   * @return true all the elements are found, otherwise false
   */
  public boolean containsAll(java.util.Collection<?> c) {
    return getDeclaracoes().containsAll(c);
  }

  /**
   * Checks if an object is equal
   *
   * @param o object which is compared
   * @return true if objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    return getDeclaracoes().equals(o);
  }

  /**
   * Returns the hashCode
   *
   * @return the hashCode
   */
  @Override
  public int hashCode() {
    return getDeclaracoes().hashCode();
  }

  /**
   * Returns an iterator over the elements in the collection
   *
   * @return an iterator over the elements in the collection
   */
  public java.util.Iterator<gram.i.types.Declaracoes> iterator() {
    return getDeclaracoes().iterator();
  }

  /**
   * Return the size of the collection
   *
   * @return the size of the collection
   */
  public int size() {
    return getDeclaracoes().size();
  }

  /**
   * Returns an array containing all of the elements in this collection.
   *
   * @return an array of elements
   */
  public Object[] toArray() {
    return getDeclaracoes().toArray();
  }

  /**
   * Returns an array containing all of the elements in this collection.
   *
   * @param array array which will contain the result
   * @return an array of elements
   */
  public <T> T[] toArray(T[] array) {
    return getDeclaracoes().toArray(array);
  }

/*
  public <T> T[] toArray(T[] array) {
    int size = getDeclaracoes().length();
    if (array.length < size) {
      array = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size);
    } else if (array.length > size) {
      array[size] = null;
    }
    int i=0;
    for(java.util.Iterator it=iterator() ; it.hasNext() ; i++) {
        array[i] = (T)it.next();
    }
    return array;
  }
*/
    /**
     * Collection
     */

    /**
     * Adds an element to the collection
     *
     * @param o element to add to the collection
     * @return true if it is a success
     */
    public boolean add(gram.i.types.Declaracoes o) {
      list = (ListaDecl)gram.i.types.declaracoes.ConsListaDecl.make(o,list);
      return true;
    }

    /**
     * Removes all of the elements from this collection
     */
    public void clear() {
      list = (ListaDecl)gram.i.types.declaracoes.EmptyListaDecl.make();
    }

    /**
     * Tests the emptiness of the collection
     *
     * @return true if the collection is empty
     */
    public boolean isEmpty() {
      return list.isEmptyListaDecl();
    }

    public boolean remove(Object o) {
      throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean removeAll(java.util.Collection<?> c) {
      throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean retainAll(java.util.Collection<?> c) {
      throw new UnsupportedOperationException("Not yet implemented");
    }

  }


}
