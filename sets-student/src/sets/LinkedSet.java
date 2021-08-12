package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */

/********************************************************
 * NOTE: Before you start, check the Set interface in
 * Set.java for detailed description of each method.
 *******************************************************/

/********************************************************
 * NOTE: for this project you must use linked lists
 * implemented by yourself. You are NOT ALLOWED to use
 * Java arrays of any type, or any Collection-based class 
 * such as ArrayList, Vector etc. You will receive a 0
 * if you use any of them.
 *******************************************************/ 

/********************************************************
 * NOTE: you are allowed to add new methods if necessary,
 * but do NOT add new files (as they will be ignored).
 *******************************************************/

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head = null;
  Iterator<E> Iteartor = this.Iteartor;
  private Set<E> set;

  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }
  
  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
    int toReturn = 0;
    for(E data: this) {
    	toReturn++;
    }
    return toReturn;
  }

  @Override
  public boolean isEmpty() {
    // TODO (2)
    return head == null;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) throws NullPointerException{
	  if(o == null) {
		  throw new NullPointerException();
	  }
    LinkedNode<E> trav = head;
    while(trav != null) {
    	if(trav.getData().equals(o)) {
    		return true;
    	}
    	trav = trav.getNext();
    }
    return false;
  }

  @Override
  public boolean isSubset(Set<E> that) throws NullPointerException{
	  if(that == null) {
		  throw new NullPointerException();
	  }
	  if(this.size() > that.size()) {
    	return false;
    }
	  for(E data: this) {
	    	if(that.contains(data) == false){
	    		return false;
	    	}
	    }
    return true;
  }

  @Override
  public boolean isSuperset(Set<E> that) throws NullPointerException{
	  if(that == null) {
		  throw new NullPointerException();
	  }
	  if(this.size() < that.size()) {
	    	return false;
	    }
	  for(E data: that) {
	    	if(this.contains(data) == false){
	    		return false;
	    	}
	    }
	  return true;
	  }

  @Override
  public Set<E> adjoin(E e) throws NullPointerException{
	  if(e == null) {
		  throw new NullPointerException();
	  }
	  if(head == null) {
		  head = new LinkedNode<E>(e,null);
		  set = new LinkedSet<E>(head);
		  return set;
	  }
	  else{
		  LinkedNode<E> temp = new LinkedNode<E>(e,head);
		  set = new LinkedSet<E>(temp);
		  return set;
	  }
  }

  @Override
  public Set<E> union(Set<E> that) throws NullPointerException{
	  if(that == null) {
		  throw new NullPointerException();
	  }
	  if(this.isSubset(that)) {
		  return that;
	  }
	  if(this.isSuperset(that)) {
		  return this;
	  }
	  LinkedSet<E> toReturn = new LinkedSet<E>(head);
	  for(E data: that) {
		  if(this.contains(data) == false) {
			  toReturn = (LinkedSet<E>) toReturn.adjoin(data);
		  }
	  }
    return toReturn;
  }

  @Override
  public Set<E> intersect(Set<E> that) throws NullPointerException{
	  if(that == null) {
		  throw new NullPointerException();
	  }
	  if(this.isSubset(that)) {
		  return this;
	  }
	  if(that.isSubset(this)) {
		  return that;
	  }
	  LinkedSet<E> toReturn = new LinkedSet<E>(); 
	  for(E data:that) {
		  if(this.contains(data)) {
			  toReturn = (LinkedSet<E>) toReturn.adjoin(data);
		  }
	  }
    return toReturn;
  }

  @Override
  public Set<E> subtract(Set<E> that) throws NullPointerException{
	  if(that == null) {
		  throw new NullPointerException();
	  }
    LinkedSet<E> toReturn = new LinkedSet<E>(this.head);
    for(E data: that) {
    	if(toReturn.contains(data)) {
    		toReturn = (LinkedSet<E>) toReturn.remove(data);
    	}
    }
    return toReturn;
  }

  @Override
  public Set<E> remove(E e) throws NullPointerException{
	  if(e == null) {
		  throw new NullPointerException();
	  }
	  LinkedSet<E> toReturn = new LinkedSet<E>();
	  for(E data: this) {
		  if(data.equals(e)==false) {
			  toReturn = (LinkedSet<E>) toReturn.adjoin(data);
		  }
	  }
	  return toReturn;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (! (o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>)o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
  public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}
