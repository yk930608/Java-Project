package structures;

/**
 * A node in a BST.
 * Note that BSTNode MUST implement BSTNodeInterface; removing this will resulit
 * in your program failing to compile for the autograder.
 * 
 * @author liberato
 *
 * @param <T> : generic type.
 */
public class BSTNode<T extends Comparable<T>> implements BSTNodeInterface<T> {
  private T data;
  private BSTNode<T> left;
  private BSTNode<T> right;
  private BSTNode<T> parents;

  public BSTNode(T data, BSTNode<T> left, BSTNode<T> right,BSTNode<T> parents) {
    this.data = data;
    this.left = left;
    this.right = right;
    this.parents = parents;
  }
  
  public BSTNode(T data, BSTNode<T> left, BSTNode<T> right) {
	    this.data = data;
	    this.left = left;
	    this.right = right;
  }


  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public BSTNode<T> getLeft() {
    return left;
  }

  public void setLeft(BSTNode<T> left) {
    this.left = left;
  }

  public BSTNode<T> getRight() {
    return right;
  }

  public BSTNode<T> getParents(){
	  return parents;
  }
  
  public void setParents(BSTNode<T> parents){
	  this.parents = parents;
  }
  public void setRight(BSTNode<T> right) {
    this.right = right;
  }

}