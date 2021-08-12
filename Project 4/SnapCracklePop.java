//TODOa complete this javadoc comment
/**
* [ComputerScience 121]
* @author [Kai Ye]
* @version 1.0
*/

//TODO1: declare the SnapCracklePop class
public class SnapCracklePop {
 
   //TODOb Complete Comments
   /**
   *SnapCracklePop Game
   Users of three. Each choose from Snap Crackle and pop and assign a number.
   when the round number is multiple number of the word, user need to input his word.
   when the round number is multiple number of two or three word. user need input the 
   combination of the word. Such as "snappop".
   
   */
   
   //TODO2 Declare private instance variables 
   private int Snap;/*for store SnapValue*/
   private int Crackle;/*for store crackleValue*/
   private int Pop;/*for store PopValue*/
   //to store Snap, Crackle, and Pop values
   
   
   //TODOc complete comments
   /**
   * the constructor assigns the input of each user and the user input for
   each user assign word.
   * @param [param name] [what the param represents]
   * @param [param name] [what the param represents]
   * @param [param name] [what the param represents]
   */
    
   public SnapCracklePop(int Snap, int Crackle, int Pop){/*store all the value from userInput*/
      this.Snap = Snap;
      this.Crackle = Crackle;
      this.Pop = Pop;}
     
   /* playRound() is a helper method for playGame(). 
   *  It takes an int parameter representing the 
   *  current round of play, and returns the 
   *  String result for that specific round only.
   */
   public String playRound(int round){/*playRound from the loop blow*/
      String output = "";
      if(round % Snap == 0){
      output += "snap";}
      if(round % Crackle == 0){
      output += "crackle";}
      if(round % Pop == 0){
      output += "pop";}
      if(output.equals("")){
      return round + "";}
      else{
      return output;}}
      
      
   
   //TODO4 implement the playRound method 
   
   
   //TODOd complete comments
   /**
   
   * [Method Description]
   * @param [param name] [What the parameter represents]
   * @return [What the method returns]
   */ 
   
   /* playGame() takes a single parameter representing the rounds and returns
   *  a String representing the result of the entire game. The helper method
   *  playRound() may be useful here, so you may want to complete it first.
   */
   public String playGame(int round){/*main play method with call out playRound method*/
      String output ="";
      for(int i = 1; i <= round; i++){
         output += "round " + i + ": " + playRound(i) + "\n";
         }
         return output;
       }
      
   
   public int getSnap(){/*call out this method for get the value of Snap*/
      return Snap;}
      
   public int getCrackle(){/*call out this method for get the value of Crackle*/
      return Crackle;}
   
   public int getPop(){/*call out this method for get the value of Pop*/
      return Pop;}
  }