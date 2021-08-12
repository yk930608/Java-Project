package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import structures.Queue;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 */
public class LevelOrderIterator extends FileIterator<File> {
	private Queue<File> queue = new Queue<File>();
  /**
   * Instantiate a new LevelOrderIterator, rooted at the rootNode.
   * @param rootNode : node of the root.
   * @throws FileNotFoundException if the rootNode does not exist.
   */
  public LevelOrderIterator(File rootNode) throws FileNotFoundException {
    // TODO 1
	  if(rootNode.exists() == false) {
		  throw new FileNotFoundException("file not Found");
	  }
	  queue = new Queue<File>();
	  queue.enqueue(rootNode);
  }

  @Override
  public boolean hasNext() {
    if(queue.size() == 0) {
    return false;
    }
    else {
    	return true;
    }
  }

  @Override
  public File next() throws NoSuchElementException {
    if(queue.size() == 0) {
    	throw new NoSuchElementException("No Nuch Element");
    }
    if(queue.peek().isDirectory()) {
    	File[] resort = queue.peek().listFiles();
    	Arrays.sort(resort);
    	for(int i = 0; i < resort.length; i++) {
    		queue.enqueue(resort[i]);
    	}
    }
    return queue.dequeue();
  }

  @Override
  public void remove() {
    // Leave this one alone.
    throw new UnsupportedOperationException();
  }

}
