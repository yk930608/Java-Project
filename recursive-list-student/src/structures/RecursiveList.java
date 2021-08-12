package structures;

import java.util.Iterator;

public class RecursiveList<T extends Comparable<T>> implements ListInterface<T>{
	
	private int size = 0;
	private LLNode<T> head;
	private LLNode<T> tail;
	private LLNode<T> trav;
	private LLNode<T> perv;
	

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator<T>(head);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public ListInterface<T> insertFirst(T elem) throws NullPointerException{
		// TODO Auto-generated method stub
		if(elem == null) {
			throw new NullPointerException();
		}
		else {
			if(head == null) {
				head = new LLNode<T>(elem);
				tail = head;
				resetTrav();
				size++;
			}
			else {
				LLNode<T> temp = new LLNode<T>(elem,head);
				head = temp;
				resetTrav();
				size++;
			}
		}
		return this;
	}

	@Override
	public ListInterface<T> insertLast(T elem) throws NullPointerException {
		// TODO Auto-generated method stub
		if(elem == null) {
			throw new NullPointerException();
		}
		else {
			if(head == null) {
				head = new LLNode<T>(elem);
				tail = head;
				resetTrav();
				size++;
			}
			else {
				LLNode<T> temp = new LLNode<T>(elem);
				tail.setLink(temp);
				tail=temp;
				resetTrav();
				size++;
			}
		}
		return this;
	}

	@Override
	public ListInterface<T> insertAt(int index, T elem) throws IndexOutOfBoundsException,
	NullPointerException{
		if(elem == null) {
			throw new NullPointerException();
		}
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if(index == size) {
			insertLast(elem);
		}
		else if(index == 0) {
			insertFirst(elem);
		}
		else {
			getTrav(index-1);
			LLNode<T> temp = new LLNode<T>(elem,trav.getLink());
			trav.setLink(temp);
			resetTrav();
			size++;
		}
		return this;
	}
	
	public void getTrav(int count) {//recursive method to get one node before insert 
		if(count == 0) {//help method
			return;
		}
		else {
			trav = trav.getLink();
			getTrav(count - 1);
		}
	}//1 2 3 4 5
	
	public void resetTrav() {//help method
		trav = head;
		perv = null;
	}
	@Override
	public T removeFirst() throws IllegalStateException{
		if(size == 0) {
			throw new IllegalStateException();
		}
		else {
			T toReturn = head.getData();
			head = head.getLink();
			resetTrav();
			size--;
			return toReturn;
		}
		// TODO Auto-generated method stub
	}

	@Override
	public T removeLast() throws IllegalStateException{
		if(size == 0) {
			throw new IllegalStateException();
		}
		else {
			T toReturn = tail.getData();
			if(head.getLink() == null) {
				head = null;
				tail = null;
				resetTrav();
				size--;
				return toReturn;
			}
			else {
				getTrav(size-2); 
				trav.setLink(null);
				tail = trav;
				size--;
				resetTrav();
				return toReturn;
		}
		}
		// TODO Auto-generated method stub
	}

	@Override
	public T removeAt(int i) throws IndexOutOfBoundsException{
		if(i >= size || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		if(i == size-1) {
			return removeLast();
		}
		else if(i == 0) {
			return removeFirst();
		}
		else {
			getTrav(i-1);
			T toReturn = trav.getLink().getData();
			trav.setLink(trav.getLink().getLink());
			size--;
			resetTrav();
			return toReturn;
		}
	}

	@Override
	public T getFirst() throws IllegalStateException{
		if(size == 0) {
			throw new IllegalStateException();
		}
		return head.getData();
	}

	@Override
	public T getLast() throws IllegalStateException{
		if(size == 0) {
			throw new IllegalStateException();
		}
		return tail.getData();
	}

	@Override
	public T get(int i) throws IndexOutOfBoundsException{
		if(i > size || i < 0) {
			throw new IndexOutOfBoundsException("Index invaild");
		}
		if(i == size) {
			return getLast();
		}
		else if(i == 0) {
			return getFirst();
		}
		else {
			if(i == 1) {
				return head.getLink().getData();
			}
			else {
				getTrav(i);
				T toReturn = trav.getData();
				resetTrav();
				return toReturn;
			}
		}
	}
	
	public void elemGetTrav(T elem) {
		if(!(trav.getData().equals(elem))&&trav.getLink() != null) {
			perv = trav;
			trav = trav.getLink();
			elemGetTrav(elem);
		}
		else {
			return;
		}
	}
	@Override
	public boolean remove(T elem) throws NullPointerException{
		if(elem == null) {
			throw new NullPointerException();
		}
		if(size == 0) {
			return false;
		}
		elemGetTrav(elem);
		if(trav.getLink()==null && !(trav.getData().equals(elem))) {
			resetTrav();
			return false;
		}
		else {
			if(trav.equals(tail)) {
				perv.setLink(null);
				resetTrav();
				size--;
				return true;
			}
			if(trav.equals(head)) {
				removeFirst();
				return true;
			}
			else {
				perv.setLink(trav.getLink());
				resetTrav();
				size--;
				return true;
			}
		}
	}
	
	public int getIndex(T elem) throws NullPointerException{//help recursive method
		if(elem == null) {
			throw new NullPointerException();
		}
		if(!(trav.getData().equals(elem))&&trav.getLink() != null) {
			trav = trav.getLink();
			return 1 + getIndex(elem);
		}
		else {
			return 0;
		}
	}
	@Override
	public int indexOf(T elem) throws NullPointerException{
		if(elem == null) {
			throw new NullPointerException();
		}
		// TODO Auto-generated method stub
		int toReturn = getIndex(elem);
		if(trav.getData().equals(elem)) {
			resetTrav();
			return toReturn;
		}
		else {
			resetTrav();
			return -1;
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
	}
	class LLNode<T>{
		private LLNode<T> link;
		private T data;
		
		public LLNode(T data) {
			this.data = data;
			this.link = null;
		}
		
		public LLNode(T data, LLNode<T> link) {
			this.data = data;
			this.link = link;
		}
		
		public String toString() {
			return this.data.toString();
		}
		public void setLink(LLNode<T> link) {
			this.link = link;
		}
		public LLNode<T> getLink(){
			return this.link;
		}
		public T getData(){
			return this.data;
		}
	}
