package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

  /**
   * QueueBasedBreadthFirstSearcher.
   * @param searchProblem : search problem
   */
  public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
    super(searchProblem);
  }

  @Override
  public List<T> findSolution() {
    Queue<T> Queue = new LinkedList<T>();
    List<T> States = new ArrayList<T>();
    List<T> Predecessors = new ArrayList<T>();
    Queue.add(searchProblem.getInitialState());
    T prev;
    while(Queue.isEmpty() == false) {
    	prev = Queue.peek();
    	if(searchProblem.isGoal(prev)) {
    		Queue.remove();
    		States.add(prev);
    		Predecessors.add(prev);
    	}
    	else {
    		Queue.remove();
    		States.add(prev);
    	}
    	if(searchProblem.getSuccessors(prev).size()>0) {
    		List<T> Childs = searchProblem.getSuccessors(prev);
    		Predecessors.add(prev);
    		for(T data:Childs) {
    			if(States.contains(data) == false) {
    				Queue.add(data);
    			}
    		}
    	}
    }
    for(T data:Predecessors) {
    	if(Predecessors.indexOf(data) != 0) {
    	T cur = data;
    	T perv = Predecessors.get(Predecessors.indexOf(cur)-1);
    	if(searchProblem.getSuccessors(perv).contains(cur) == false) {
    		Predecessors.add(perv);
    	}
    }
    }
    return Predecessors;
  }
}
