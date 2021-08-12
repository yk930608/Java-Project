package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * The spaces in an 8-puzzle are indexed as follows:
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

  /**
   * Creates a new instance of the 8 puzzle with the given starting values.
   * The values are indexed as described above, and should contain exactly the
   * nine integers from 0 to 8.
   * 
   * @param startingValues
   *            the starting values, 0 -- 8
   * @throws IllegalArgumentException
   *             if startingValues is invalid
   */
	List<Integer> Initial; 
  public EightPuzzle(List<Integer> startingValues) {
	  this.Initial = startingValues;
  }

  @Override
  public List<Integer> getInitialState() {
    // TODO
    return Initial;
  }

  @Override
  public List<List<Integer>> getSuccessors(List<Integer> currentState) {
    List<List<Integer>> toReturn = new ArrayList<>();
    List<Integer> add;
    switch(currentState.indexOf(0)) {
    case 0:
    	add = currentState;
    	Collections.swap(add, 0, 1);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 0, 3);
    	toReturn.add(add);
    	break;
    case 1:
    	add = currentState;
    	Collections.swap(add, 1, 0);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 1, 2);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 1, 4);
    	toReturn.add(add);
    	break;
    case 2:
    	add = currentState;
    	Collections.swap(add, 2, 1);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 2, 5);
    	toReturn.add(add);
    	break;
    case 3:
    	add = currentState;
    	Collections.swap(add, 3, 0);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 3, 4);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 3, 6);
    	toReturn.add(add);
    	break;
    case 4:
    	add = currentState;
    	Collections.swap(add, 4, 1);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 4, 3);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 4, 5);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 4, 7);
    	toReturn.add(add);
    	break;
    case 5:
    	add = currentState;
    	Collections.swap(add, 5, 2);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 5, 4);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 5, 8);
    	toReturn.add(add);
    	break;
    case 6:
    	add = currentState;
    	Collections.swap(add, 6, 3);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 6, 7);
    	toReturn.add(add);
    	break;
    case 7:
    	add = currentState;
    	Collections.swap(add, 7, 4);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 7, 6);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 7, 8);
    	toReturn.add(add);
    	break;
    case 8:
    	add = currentState;
    	Collections.swap(add, 8, 5);
    	toReturn.add(add);
    	add = currentState;
    	Collections.swap(add, 8, 7);
    	toReturn.add(add);
    	break;
    }
    return toReturn;
  }


  @Override
  public boolean isGoal(List<Integer> state) {
	if(state == null) {
		throw new NullPointerException();
	}
	if(state.size() < 1) {
    	return false;
    }
	for(Integer num: state) {
		if(num != state.indexOf(num)+1) {
			return false;
		}
		if(state.get(state.size()-1) != 0) {
			return false;
		}
	}
    return true;
  }

  /**
   * supporting man method.
   */
  public static void main(String[] args) {
    EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
        4, 0, 6, 7, 5, 8 }));

    List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
    for (List<Integer> l : r) {
      System.out.println(l);
    }
  }
}
