package structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
  protected BSTNode<T> root;
  
  public BinarySearchTree() {
	  this.root = null;
  }
  
  public BinarySearchTree(BSTNode<T> root) {
	  this.root = root;
  }
  
  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return subtreeSize(root);
  }

  protected int subtreeSize(BSTNode<T> node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + subtreeSize(node.getLeft()) + subtreeSize(node.getRight());
    }
  }

  public boolean contains(T t) {
    if(this.subtreeSize(root) == 0) {
    return false;
    }
    T temp = recursiveTo(t,root);
    if(temp == null) {
    		return false;
    }
    return true;
  }
  public T recursiveTo(T elem,BSTNode<T> node) {
	 if(node == null) {
			 return null; 
	 }
	 if(elem.compareTo(node.getData()) < 0) {
		 return recursiveTo(elem,node.getLeft());
	 }
	 if(elem.compareTo(node.getData()) > 0) {
		 return recursiveTo(elem,node.getRight());
	 }
	 if(elem.compareTo(node.getData()) == 0) {
		 return node.getData();
	 }
	return null;
  }

  /**
   * remove the data from the tree.
   */
  public boolean remove(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    boolean result = contains(t);
    if (result) {
      root = removeFromSubtree(root, t);
    }
    return result;
  }

  private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
    // node must not be null
    int result = t.compareTo(node.getData());
    if (result < 0) {
      node.setLeft(removeFromSubtree(node.getLeft(), t));
      return node;
    } else if (result > 0) {
      node.setRight(removeFromSubtree(node.getRight(), t));
      return node;
    } else { // result == 0
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else { // neither child is null
        T predecessorValue = getHighestValue(node.getLeft());
        node.setLeft(removeRightmost(node.getLeft()));
        node.setData(predecessorValue);
        return node;
      }
    }
  }

  private T getHighestValue(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getData();
    } else {
      return getHighestValue(node.getRight());
    }
  }

  private BSTNode<T> removeRightmost(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getLeft();
    } else {
      node.setRight(removeRightmost(node.getRight()));
      return node;
    }
  }

  public T get(T t) {
    if(t.equals(recursiveTo(t,root))){
    	return t;
    }
    return null;
  }


  /**
   * add data into the tree.
   */
  public void add(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    root = addToSubtree(root, new BSTNode<T>(t, null, null));
  }

  protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
    if (node == null) {
      return toAdd;
    }
    int result = toAdd.getData().compareTo(node.getData());
    if (result <= 0) {
      node.setLeft(addToSubtree(node.getLeft(), toAdd));
      node.getLeft().setParents(node);
    } else {
      node.setRight(addToSubtree(node.getRight(), toAdd));
      node.getRight().setParents(node);
    }
    return node;
  }

  @Override
  public T getMinimum() {
	if(root == null) {
    return null;
	}
	BSTNode<T> trav = root;
	while(trav.getLeft() != null) {
		trav = trav.getLeft();
	}
	return trav.getData();
  }


  @Override
  public T getMaximum() {
	  if(root == null) {
		  return null;
	  }
	  BSTNode<T> trav = root;
	  while(trav.getRight() != null) {
		  trav = trav.getRight();
	  }
	  return trav.getData();
  }


  @Override
  public int height() {
	  if(root == null) {
		  return -1;
	  }
	  return getHeight(root);
  }
  private int getHeight(BSTNode<T> passin) {
	  if(passin == null) {
		  return -1;
	  }
		  return 1+ Math.max(getHeight(passin.getLeft()),getHeight(passin.getRight()));
	  }

  public Iterator<T> preorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    preoderTraverse(queue,root);
    return queue.iterator();
  }


  private void preoderTraverse(Queue<T> queue, BSTNode<T> node) {
	// TODO Auto-generated method stub
	  if(node != null) {
		  queue.add(node.getData());
		  preoderTraverse(queue,node.getLeft());
		  preoderTraverse(queue,node.getRight());
	  }
}

/**
   * in-order traversal.
   */
  public Iterator<T> inorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    inorderTraverse(queue, root);
    return queue.iterator();
  }


  private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      inorderTraverse(queue, node.getLeft());
      queue.add(node.getData());
      inorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> postorderIterator() {
	  Queue<T> queue = new LinkedList<T>();
	  postorderTraverse(queue, root);
	  return queue.iterator();
  }


  private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
	  if (node != null) {
	      postorderTraverse(queue, node.getLeft());
	      postorderTraverse(queue, node.getRight());
	      queue.add(node.getData());
	    }
	
}

@Override
  public boolean equals(BSTInterface<T> other) {
	if(this.size() != other.size()) {
		return false;
	}
    return equalsHelper(other.getRoot(),this.root);
  }


  private boolean equalsHelper(BSTNode<T> thisNode, BSTNode<T> thatNode) {
	if(thisNode == null && thatNode == null) {
		return true;
	}
	if(!(thisNode.getData().equals(thatNode.getData()))) {
	return false;
	}
	return equalsHelper(thisNode.getLeft(),thatNode.getLeft())&&
			equalsHelper(thatNode.getRight(),thatNode.getRight());
  }

@Override
  public boolean sameValues(BSTInterface<T> other) throws NullPointerException {
	if(other == null) {
		throw new NullPointerException();
	}
	ArrayList<T> thisArray = toArrayList(this.root, new ArrayList<T>());
	ArrayList<T> otherArray = toArrayList(other.getRoot(), new ArrayList<T>());
	Collections.sort(thisArray);
	Collections.sort(otherArray);
	if(this.size() > 0 && other.size() == 0) {
		return false;
	}
	if(this.size() == 0 && other.size() != 0) {
		return false;
	}
	for(int i = 0; i < thisArray.size(); i++) {
		if(!(thisArray.get(i).equals(otherArray.get(i)))) {
			return false;
		}
	}
	return true;
}
  public Queue<BSTNode<T>> toQueue(BSTNode<T> passin, Queue<BSTNode<T>> queue) {
	  if(passin == null) {
		  return queue;
	  }
	  toQueue(passin.getLeft(),queue);
	  queue.add(passin);
	  return toQueue(passin.getRight(),queue);
  }
  
  public ArrayList<T> toArrayList(BSTNode<T> passin, ArrayList<T> array) {
	  if(passin == null) {
		  return array;
	  }
	  toArrayList(passin.getLeft(),array);
	  array.add(passin.getData());
	  return toArrayList(passin.getRight(),array);
  }



  @Override
  public boolean isBalanced() {
	  boolean test1 = (size() >= Math.pow(2, height()));
	  boolean test2 = (size() <= Math.pow(2,height()+1));
	  return test1 && test2;
  }

  @Override
  @SuppressWarnings("unchecked")

  public void balance() {
    // TODO
	  ArrayList<T> forSort = toArrayList(root,new ArrayList<T>());
	  Collections.sort(forSort);
	  root = ArrayListToBST(forSort,0,forSort.size()-1);
  }


  private BSTNode<T> ArrayListToBST(ArrayList<T> forSort, int i, int j) {
	if(i>j) {
		return null;
	}
	int parentIndex = (i+j)/2;
	BSTNode<T> toReturn = 
			new BSTNode<T>(forSort.get(parentIndex),ArrayListToBST(forSort,i,parentIndex-1),ArrayListToBST(forSort,parentIndex+1,j));
	return toReturn;	
}

@Override
  public BSTNode<T> getRoot() {
    // DO NOT MODIFY
    return root;
  }

  /**
   * toDotFormat.
   * @param root root of tree.
   * @return type T.
   */
  public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
    // header
    int count = 0;
    String dot = "digraph G { \n";
    dot += "graph [ordering=\"out\"]; \n";
    // iterative traversal
    Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
    queue.add(root);
    BSTNode<T> cursor;
    while (!queue.isEmpty()) {
      cursor = queue.remove();
      if (cursor.getLeft() != null) {
        // add edge from cursor to left child
        dot += cursor.getData().toString() + " -> "
            + cursor.getLeft().getData().toString() + ";\n";
        queue.add(cursor.getLeft());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count
            + ";\n";
        count++;
      }
      if (cursor.getRight() != null) {
        // add edge from cursor to right child
        dot += cursor.getData().toString() + " -> "
            + cursor.getRight().getData().toString() + ";\n";
        queue.add(cursor.getRight());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count
            + ";\n";
        count++;
      }

    }
    dot += "};";
    return dot;
  }

  /**
   * main method.
   * @param args arguments.
   */
  public static void main(String[] args) {
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      BSTInterface<String> tree = new BinarySearchTree<String>();
      for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
        tree.add(s);
      }
      Iterator<String> iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.preorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.postorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();

      System.out.println(tree.remove(r));

      iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
    }

    BSTInterface<String> tree = new BinarySearchTree<String>();
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      tree.add(r);
    }
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
    tree.balance();
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
  }
}