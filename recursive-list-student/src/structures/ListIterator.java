package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListIterator <T> implements Iterator<T> {
	
	private LLNode<T> head;
	private LLNode<T> curr;
	
	public ListIterator (LLNode<T> head) {
		this.head = head;
		this.curr = head;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return this.curr != null;
	}

	@Override
	public T next() throws NullPointerException{
		// TODO Auto-generated method stub
		if(!hasNext()) {
			throw new NullPointerException();
		}
		if(curr.getData() != null) {
			T toReturn = curr.getData();
			curr = curr.getLink();
			return toReturn;
		}
		return null;
	}
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
