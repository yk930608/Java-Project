package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game.
 */
public class LinkedListGame {

  // TODO: declare data members as necessary
	public boolean Over;
	public LLIntegerNode CondidateHead,CondidateTail; 
	public int numguess;
	public LLIntegerNode PeriorHead,PeriorTail;
	public int guess;

   /********************************************************
   * NOTE: for this project you must use linked lists
   * implemented by yourself. You are NOT ALLOWED to use
   * Java arrays of any type, or any class in the java.util
   * package (such as ArrayList).
   *******************************************************/

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but DO NOT remove any provided method, and do NOT add
   * new files (as they will be ignored by the autograder).
   *******************************************************/

  // LinkedListGame constructor method
  public LinkedListGame() {
	  CondidateHead = new LLIntegerNode(9999,null);
	  LLIntegerNode nextAdd = null;
	  for(int i = 9998; i >= 1000; i--) {
		  nextAdd = new LLIntegerNode(i,null);
		  nextAdd.setLink(CondidateHead);
		  CondidateHead = nextAdd;		  
	  }
	  PeriorHead = null;
	  PeriorTail = null;
	  Over = false;
	  guess = 1000;
	  numguess = 0;
  }

  /** Resets data members and game state so we can play again.
   * 
   */
  public void reset() {
	  CondidateHead = new LLIntegerNode(9999,null);
	  LLIntegerNode nextAdd = null;
	  for(int i = 9998; i >= 1000; i--) {
		  nextAdd = new LLIntegerNode(i,null);
		  nextAdd.setLink(CondidateHead);
		  CondidateHead = nextAdd;		  
	  }
	  PeriorHead = null;
	  PeriorTail = null;
	  Over = false;
	  guess = 1000;
	  numguess = 0;
  }

  /** Returns true if n is a prior guess; false otherwise.
   * 
   */
  public boolean isPriorGuess(int n) {
    boolean toReturn = false;
    LLIntegerNode Node = PeriorHead;
	  while(Node != null) {
    	if(Node.getInfo() == n) {
    		toReturn = true;
    		break;
    	}
    	Node = Node.getLink();
    }
    return toReturn;
  }

  /** Returns the number of guesses so far.
   * 
   */
  public int numGuesses() {
    // TODO

    return numguess;
  }

  /**
   * Returns the number of matches between integers a and b.
   * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
   * The return value must be between 0 and 4.
   * 
   * <p>A match is the same digit at the same location. For example:
   *   1234 and 4321 have 0 match;
   *   1234 and 1114 have 2 matches (1 and 4);
   *   1000 and 9000 have 3 matches (three 0's).
   */
  public static int numMatches(int a, int b) {

    // TODO
	int toReturn = 0;
	  if(a == b) {
	    toReturn = 4;
	    return toReturn;
	  }
	  else{
	    int fdoa = a/1000;
	    int fdob = b/1000;
	    int sdoa = (a % 1000)/100;
	    int sdob = (b % 1000)/100;
	    int tdoa = (a % 100)/10;
	    int tdob = (b % 100)/10;
	    int rdoa = a % 10;
	    int rdob = b % 10;
	      
	    if(fdoa == fdob) {
	      toReturn++;
	    }
	    if(sdoa == sdob) {
	      toReturn++;
	    }
	    if(tdoa == tdob) {
	      toReturn++;
	    }
	    if(rdoa == rdob) {
	      toReturn++;
	    }
	  }
	    return toReturn;	  
  }

  /**
   * Returns true if the game is over; false otherwise.
   * The game is over if the number has been correctly guessed
   * or if no candidate is left.
   */
  public boolean isOver() {
    // TODO
    return Over;
  }

  /**
   * Returns the guess number and adds it to the list of prior guesses.
   * The insertion should occur at the end of the prior guesses list,
   * so that the order of the nodes follow the order of prior guesses.
   */
  public int getGuess() {
	if(PeriorHead == null) {
		PeriorHead = new LLIntegerNode(guess,null);
	}
	else {
		LLIntegerNode nextguess = new LLIntegerNode (guess,null);
		LLIntegerNode trav = PeriorHead;
		while(trav.getLink()!= null) {
			trav = trav.getLink();
		}
		trav.setLink(nextguess);
		
	}
	numguess ++;
    return guess;
  }

  /**
   * Updates guess based on the number of matches of the previous guess.
   * If nmatches is 4, the previous guess is correct and the game is over.
   * Check project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if no candidate 
   * is left (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
	  LLIntegerNode tempHead = null;
	  LLIntegerNode tempTail = null;
	  LLIntegerNode trav = CondidateHead;
	  LLIntegerNode tempstore = null;
	  if(nmatches == 4) {
		  Over = true;
		  return true;
	  }
	  else{
		  while(trav != null) {
		  if(numMatches(guess,trav.getInfo()) == nmatches) {
			 tempstore = new LLIntegerNode(trav.getInfo(),null);
			 tempHead = tempstore;
			 tempTail = tempstore;
			 tempHead.setLink(tempTail);
			 trav = null;
		  }
		  else {
			  trav = trav.getLink();
		  }
		  }
			  trav = CondidateHead;
			  while(trav != null) {
				  if(numMatches(guess,trav.getInfo()) == nmatches && tempHead.getInfo() != trav.getInfo()) {
					  tempstore = new LLIntegerNode(trav.getInfo(),null);
					  tempTail.setLink(tempstore);
					  tempTail = tempstore;
					  trav = trav.getLink();
				  }
				  else {
					  trav = trav.getLink();
				  }
			  }
		  }
		CondidateHead = tempHead;
		if(CondidateHead == null) {
			return false;
		}
		guess = CondidateHead.getInfo();
		return true;
  }

  /**
   *  Returns the head of the prior guesses list.
   *  Returns null if there hasn't been any prior guess
   */
  public LLIntegerNode priorGuesses() {
		return PeriorHead;
  }

  /**
   * Returns the list of prior guesses as a String. For example,
   * if the prior guesses are 1000, 2111, 3222, in that order,
   * the returned string should be "1000, 2111, 3222", in the same order,
   * with every two numbers separated by a comma and space, except the
   * last number (which should not be followed by either comma or space).
   *
   * <p>Returns an empty string if here hasn't been any prior guess
   */
  public String priorGuessesString() {
	String toReturn;
	  if(numguess == 0) {
		toReturn = "";
		  return toReturn;
	}
	  else{
		toReturn = Integer.toString(PeriorHead.getInfo()); 
		LLIntegerNode Node = PeriorHead.getLink();
		while(Node != null) {
		toReturn = toReturn + ", " + Integer.toString(Node.getInfo());
		Node = Node.getLink();
	}
  }
	return toReturn;
  }
}
