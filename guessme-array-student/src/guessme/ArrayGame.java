package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game.
 */
public class ArrayGame {

  // stores the next number to guess
  private int guess;
  public int [] Condidate;
  public boolean [] TorF;
  private int index;
  private boolean Over;

  // TODO: declare additional data members, such as arrays that store
  // prior guesses, eliminated candidates etc.

  // NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
  // You MAY NOT use any Collection type (such as ArrayList) provided by Java.

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but DO NOT remove any provided method, otherwise your
   * code will fail the JUnit tests.
   * Also DO NOT create any new Java files, as they will
   * be ignored by the autograder!
   *******************************************************/

  // ArrayGame constructor method
  public ArrayGame() {
    Condidate = new int [18];
    index = 0;
    guess = 1000;
    Over = false;
    TorF = new boolean[9000];
  }

  /**
   *  Resets data members and game state so we can play again.
   */
  public void reset() {
    Condidate = new int [18];
    index = 0;
    guess = 1000;
    Over = false;
    TorF = new boolean[9000];
  }

  /**
   *  Returns true if n is a prior guess; false otherwise.
   */
  public boolean isPriorGuess(int n) {
    boolean toReturn = false;
    for(int i = 0; i < index ; i ++) {
      if(n == Condidate [i]) {
        toReturn = true;
        break;
      }
    }
    return toReturn;
  }

  /**
   *  Returns the number of guesses so far.
   */
  public int numGuesses() {
    return index;
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
  public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
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
   * or if all candidates have been eliminated.
   */
  public boolean isOver() {
    // TODO
    return Over;
  }

  /**
   *  Returns the guess number and adds it to the list of prior guesses.
   */
  public int getGuess() {
    Condidate[index] = guess;
    index++;
    return guess;
    }

  /**
   * Updates guess based on the number of matches of the previous guess.
   * If nmatches is 4, the previous guess is correct and the game is over.
   * Check project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if all candidates
   * have been eliminated (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
      if(nmatches == 4) {
        Over = true;
        return true;
      }
      else {
      for(int i = 0; i < TorF.length; i++) {
          if(numMatches(guess,i+1000) !=nmatches) {
            TorF[i] = true;
          }
        }
      }
       for(int i = 0; i < TorF.length; i++) {
           if(!TorF[i]) {
             guess = i + 1000;
             return true;
           }
          }
        return false;
      }
  /**
   * Returns the list of guesses so far as an integer array.
   * The size of the array must be the number of prior guesses.
   * Returns null if there has been no prior guess
   */
  public int[] priorGuesses() {
    if(index > 0) {
      int [] returnArray = new int [index];
      for(int i = 0; i < returnArray.length; i++) {
        returnArray [i] = Condidate[i];
      }
      return returnArray;
    }
    return null;
  }
}
