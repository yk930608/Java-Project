package stack;

/**
 * A {@link LinkedStack} is a generic stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 */
public class LinkedStack<T> {
	LLNode <T> head;
	int size;

  /**
   * Remove and return the top element on this stack.
   * If stack is empty, return null (instead of throw exception)
   */
  public T pop() {
	  if(isEmpty()) {
		  return null;
	  }
	T toReturn = head.info;
    head = head.link;
    size--;
    return toReturn;
  }

  /**
   * Return the top element of this stack (do not remove the top element).
   * If stack is empty, return null (instead of throw exception)
   */
  public T top() {
    if(isEmpty()) {
    	return null;
    }
    return head.info;
  }

  /**
   * Return true if the stack is empty and false otherwise.
   */
  public boolean isEmpty() {
    return (head == null);
  }

  /**
   * Return the number of elements in this stack.
   */
  public int size() {
    return size;
  }

  /**
   * Pushes a new element to the top of this stack.
   */
  public void push(T elem) {
	LLNode <T> newNode = new LLNode <T> (elem);
	newNode.link = head;
	head = newNode;
	size++;
  }

}
