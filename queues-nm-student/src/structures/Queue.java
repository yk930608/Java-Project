package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {
  
  @SuppressWarnings("hiding")
class Node<T> {
    public T data;
    public Node<T> next;
    
    public Node(T data) { 
      this.data = data;
    }
    
    public Node(T data, Node<T> next) {
      this.data = data; 
      this.next = next;
    }
  }
  Node<T> head;
  Node<T> tail;
  private int size;

  public Queue() {
	  head = null;
	  tail = null;
	  size = 0;
  }

  public Queue(Queue<T> other) {
    Node<T> passin = other.head;
    for(int i = 0; i < other.size;i++){
    	this.enqueue(passin.data);
    	passin = passin.next;
    }
  }

  @Override
  public boolean isEmpty() {
    // TODO 3
    return size == 0;
  }

  @Override
  public int size() {
    // TODO 4
    return size;
  }

  @Override
  public void enqueue(T element) {
    Node<T> newNode = new Node<T>(element);
	  if(head == null) {
    	head = newNode;
    	tail = newNode;
    	size++;
    }
    else {
    	tail.next = newNode;
    	tail = tail.next;
    	size++;
    }
  }

  @Override
  public T dequeue() throws NoSuchElementException {
    if(size == 0) {
    	throw new NoSuchElementException("underflow");
    }
    	T toReturn = head.data;
    	if(head == null) {
    		tail = null;
    	}
    	head = head.next;
    	size--;
    	return toReturn;
    }

  @Override
  public T peek() throws NoSuchElementException {
    if(size == 0) {
    	throw new NoSuchElementException("underflow");
    }
    return head.data;
  }


  @SuppressWarnings("unchecked")
@Override
  public UnboundedQueueInterface<T> reversed() {
    T[] temp = (T[]) new Object [this.size];
    Queue<T> tempQueue = new Queue<T>(this);
    Queue<T> toReturn = new Queue<T>();
    for(int i = 0; i < temp.length; i++) {
    	temp[i] = tempQueue.dequeue(); 
    }
    for(int i = temp.length-1; i >= 0; i--) {
    	toReturn.enqueue(temp[i]);
    }
    return toReturn;
  }
}


