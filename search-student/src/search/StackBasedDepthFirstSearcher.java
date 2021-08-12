package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {

  /**
   * StackBasedDepthFirstSearcher.
   * @param searchProblem : search problem
   */
  public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
    super(searchProblem);
  }

  @Override
  public List<T> findSolution() {
    Stack<T> toReturn = new Stack<T>();
    T initial = searchProblem.getInitialState();
    visited.add(initial);
    toReturn.push(initial);
    while(toReturn.isEmpty() == false) {
    	T neighbor = getNextNeighobor(toReturn.peek());
    	if(neighbor == null) {
    		toReturn.pop();
    	}
    	if(searchProblem.isGoal(neighbor)) {
    		toReturn.push(neighbor);
    		return toReturn;
    	}
    	else {
    		visited.add(neighbor);
    		toReturn.push(neighbor);
    	}
    }
    return toReturn;
  }

private T getNextNeighobor(T t) {
	List<T> Successors = searchProblem.getSuccessors(t);
	for(T data: Successors) {
		if(visited.contains(data)==false) {
			return data;
		}
	}
	return null;
}
}
