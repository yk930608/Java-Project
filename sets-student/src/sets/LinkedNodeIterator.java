package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
	public Iterator<E> Iterator;
	public LinkedNode<E> Head;
	public LinkedNode<E> Trav;
	public int size;
  // Constructors
  public LinkedNodeIterator(LinkedNode<E> head) {
    this.Head = head;
    this.Trav= head;
  }

  @Override
  public boolean hasNext() {
    // TODO (3)
    return this.Trav != null;
  }

  @Override
  public E next() throws NoSuchElementException{
    if(!hasNext()) {
    throw new NoSuchElementException();
    }
    if(Trav.getData() != null) {
		E toReturn = Trav.getData();
		Trav = Trav.getNext();
		return toReturn;
	}
	return null;
    }

  @Override
  public void remove() {
    // Nothing to change for this method
    throw new UnsupportedOperationException();
  }
}
