package structures;

import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {

  private int upperBound;
  
  public ScapegoatTree() {
	  super();
  }

  public ScapegoatTree(BSTNode<T> root) {
		super(root);
		// TODO Auto-generated constructor stub
}


  @Override
  public void add(T t) {
	  if(t == null) {
		  throw new NullPointerException();
	  }
	  BSTNode<T> add = new BSTNode<T>(t,null,null);
	  this.root = addToSubtree(root,add);
	  upperBound++;
	  if(super.height() <= log32(upperBound)){
		  return;
	  }
	  else {
		  BSTNode<T> goat = add.getParents();
		  while(3*super.subtreeSize(add) <= 2*super.subtreeSize(goat)) {
			  goat = goat.getParents();
			  add = add.getParents();
		  }
		  BSTNode<T> goatParents = goat.getParents();
		  BinarySearchTree<T> temp = new BinarySearchTree<T>(goat);
		  temp.balance();
		  this.root = temp.root;
		  if(goatParents.getData().compareTo(temp.getRoot().getData()) > 0) {
			  goatParents.setLeft(this.root);
			  this.root = getRoot(goatParents);
		  }
		  else {
			  goatParents.setRight(this.root);
			  this.root = getRoot(goatParents);
		  }
		  }
	  }
  public int log32(int i){
	  return (int) (Math.log((double)i)/Math.log((double)3/2));
  }
  public BSTNode<T> getRoot(BSTNode<T> node){
	  if(node.getParents() == null) {
		  return node;
	  }
	  return getRoot(node.getParents());
  }
  @Override
  public boolean remove(T element) {
    if(super.remove(element)) {
    	if(2*super.size()<upperBound) {
    		super.balance();
    		upperBound = super.size();
    	}
    	return true;
    }
    return false;
  }
}
