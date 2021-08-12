import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.*;
import java.util.*;


public class StickWaterFireGameTest {

   StickWaterFireGame game;

   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
      game = new StickWaterFireGame(1234);
   }

   /** isValidInput() returns correct value on valid input. **/
   @Test public void isValidInputTest1() {
      Assert.assertEquals("isValidInput() returns correct value on valid input", true, game.isValidInput("S"));
      Assert.assertEquals("isValidInput() returns correct value on valid input", true, game.isValidInput("W"));
      Assert.assertEquals("isValidInput() returns correct value on valid input", true, game.isValidInput("F"));
   }
   
   /** isValidInput() returns correct value on invalid input. **/
   @Test public void isValidInputTest2() {
      Assert.assertEquals("isValidInput() returns correct value on invalid input", false, game.isValidInput("Y"));
      Assert.assertEquals("isValidInput() returns correct value on invalid input", false, game.isValidInput("M"));
      Assert.assertEquals("isValidInput() returns correct value on invalid input", false, game.isValidInput("C"));
      Assert.assertEquals("isValidInput() returns correct value on invalid input", false, game.isValidInput("A"));
   }
   
   /** getRandomChoice() returns correct value. **/
   @Test public void getRandomChoiceTest1() {
      String returnValue = "";
      int fCount = 0, sCount = 0, wCount = 0;
     /* Note that you do not need to understand how this test works. This is some
      * fancy stuff to get around the fact that this method is private. You
      * should not try to do this outside of testing, as it defeats the purpose of
      * things being private */
      try{
         for(int i = 0; i < 10000; i++){
            Method getRandomChoiceMethod = StickWaterFireGame.class.getDeclaredMethod("getRandomChoice");
            getRandomChoiceMethod.setAccessible(true);
            returnValue = (String)getRandomChoiceMethod.invoke(game);
         }
      } catch (Exception e){}
      Assert.assertEquals("getRandomChoice gives a valid response", true, returnValue.equals("F") || returnValue.equals("W") || returnValue.equals("S"));
   }
   
   /** getRandomChoice() returns correct values. **/
   @Test public void getRandomChoiceTest2() {
      String returnValue = "";
      int fCount = 0, sCount = 0, wCount = 0;
     /* Note that you do not need to understand how this test works. This is some
      * fancy stuff to get around the fact that this method is private. You
      * should not try to do this outside of testing, as it defeats the purpose of
      * things being private */
      try{
         for(int i = 0; i < 10000; i++){
            Method getRandomChoiceMethod = StickWaterFireGame.class.getDeclaredMethod("getRandomChoice");
            getRandomChoiceMethod.setAccessible(true);
            returnValue = (String)getRandomChoiceMethod.invoke(game);
            if(returnValue.equals("F")){
               fCount++;
            }
            else if (returnValue.equals("S")){
               sCount++;
            }
            else if (returnValue.equals("W")){
               wCount++;
            }
         }
      } catch (Exception e){}
      Assert.assertEquals("All answers are 'F', 'S', or 'W'", 10000, fCount+sCount+wCount);
   }
   
   /** Tests if the constructor initializes rand correctly in seeded case **/
   @Test public void constructorTest1() {
      Random rand = null;
      try{
         /* Note that you do not need to understand how this test works. This is some
          * fancy stuff to get around the fact that these variables are private. You
          * should not try to do this outside of testing, as it defeats the purpose of
          * things being private */
         Field randField = StickWaterFireGame.class.getDeclaredField("rand");
         randField.setAccessible(true);
         rand = (Random)randField.get(game);
      } catch (Exception e){}
      Assert.assertEquals("Constructor initializes rand correctly in seeded case", 0.6465821602909256, rand.nextDouble(), 0.0);
   }
   
   /** Tests if the constructor initializes playerScore correctly **/
   @Test public void constructorTest2() {
      int playerScore = 0;
      try{
         /* Note that you do not need to understand how this test works. This is some
          * fancy stuff to get around the fact that these variables are private. You
          * should not try to do this outside of testing, as it defeats the purpose of
          * things being private */
         Field scoreField = StickWaterFireGame.class.getDeclaredField("playerScore");
         scoreField.setAccessible(true);
         playerScore = (int)scoreField.get(game);
      } catch (Exception e){}
      Assert.assertEquals("Constructor initializes playerScore correctly", 0, playerScore);
   }
   
   /** Tests if the constructor initializes computerScore correctly **/
   @Test public void constructorTest3() {
      int computerScore = 0;
      try{
         /* Note that you do not need to understand how this test works. This is some
          * fancy stuff to get around the fact that these variables are private. You
          * should not try to do this outside of testing, as it defeats the purpose of
          * things being private */
         Field scoreField = StickWaterFireGame.class.getDeclaredField("computerScore");
         scoreField.setAccessible(true);
         computerScore = (int)scoreField.get(game);
      } catch (Exception e){}
      Assert.assertEquals("Constructor initializes computerScore correctly", 0, computerScore);
   }
   
   /** Tests if the constructor initializes rounds correctly **/
   @Test public void constructorTest4() {
      int rounds = 0;
      try{
         /* Note that you do not need to understand how this test works. This is some
          * fancy stuff to get around the fact that these variables are private. You
          * should not try to do this outside of testing, as it defeats the purpose of
          * things being private */
         Field roundsField = StickWaterFireGame.class.getDeclaredField("rounds");
         roundsField.setAccessible(true);
         rounds = (int)roundsField.get(game);
      } catch (Exception e){}
      Assert.assertEquals("Constructor initializes rounds correctly", 0, rounds);
   }
   
   /** Tests if the constructor initializes playerScore correctly **/
   @Test public void constructorTest5() {
      String computerChoice = "";
      try{
         /* Note that you do not need to understand how this test works. This is some
          * fancy stuff to get around the fact that these variables are private. You
          * should not try to do this outside of testing, as it defeats the purpose of
          * things being private */
         Field choiceField = StickWaterFireGame.class.getDeclaredField("computerChoice");
         choiceField.setAccessible(true);
         computerChoice = (String)choiceField.get(game);
      } catch (Exception e){}
      Assert.assertEquals("Constructor initializes computerChoice correctly", "", computerChoice);
   }
   
   /** Tests playRound to ensure scores get updated correctly **/
   @Test public void playRoundTest1() {
      game.playRound("S");
      int pScore1 = 0, pScore2 = 0, pScore3 = 0;
      int cScore1 = 0, cScore2 = 0, cScore3 = 0;
      /* Note that you do not need to understand how this test works. This is some
       * fancy stuff to get around the fact that these variables are private. You
       * should not try to do this outside of testing, as it defeats the purpose of
       * things being private */
      try{
         Field playerField = StickWaterFireGame.class.getDeclaredField("playerScore");
         playerField.setAccessible(true);
         pScore1 = (int)playerField.get(game);
         
         Field computerField = StickWaterFireGame.class.getDeclaredField("computerScore");
         computerField.setAccessible(true);
         cScore1 = (int)computerField.get(game);
      } catch (Exception e){}
      game = new StickWaterFireGame(1234);
      game.playRound("W");
      try{
         Field playerField = StickWaterFireGame.class.getDeclaredField("playerScore");
         playerField.setAccessible(true);
         pScore2 = (int)playerField.get(game);
         
         Field computerField = StickWaterFireGame.class.getDeclaredField("computerScore");
         computerField.setAccessible(true);
         cScore2 = (int)computerField.get(game);
      } catch (Exception e){}
      game = new StickWaterFireGame(1234);
      game.playRound("F");
      try{
         Field playerField = StickWaterFireGame.class.getDeclaredField("playerScore");
         playerField.setAccessible(true);
         pScore3 = (int)playerField.get(game);
         
         Field computerField = StickWaterFireGame.class.getDeclaredField("computerScore");
         computerField.setAccessible(true);
         cScore3 = (int)computerField.get(game);
      } catch (Exception e){}
      boolean valid1 = (pScore1 == 1 && cScore1 == 0) || (pScore1 == 0 && cScore1 == 1) || (pScore1 == 0 && cScore1 == 0);
      boolean valid2 = ((pScore2 == 1 && cScore2 == 0) || (pScore2 == 0 && cScore2 == 1) || (pScore2 == 0 && cScore2 == 0)) && (pScore2 != pScore1 || cScore2 != cScore1);
      boolean valid3 = ((pScore3 == 1 && cScore3 == 0) || (pScore3 == 0 && cScore3 == 1) || (pScore3 == 0 && cScore3 == 0)) && (pScore3 != pScore1 || cScore3 != cScore1) && (pScore3 != pScore2 || cScore3 != cScore2);
      Assert.assertEquals("playRound updates scores correctly", true, valid1 && valid2 && valid3);
   }
   
   /** Tests if computerWinning() returns correctly **/
   @Test public void computerWinningTest1() {
      try{
         /* Note that you do not need to understand how this test works. This is some
          * fancy stuff to get around the fact that these variables are private. You
          * should not try to do this outside of testing, as it defeats the purpose of
          * things being private */
         Field scoreField = StickWaterFireGame.class.getDeclaredField("computerScore");
         scoreField.setAccessible(true);
         scoreField.set(game, 1);
      } catch (Exception e){}
      Assert.assertEquals("computerWinning() correctly identifies when it is winning", true, game.computerWinning());
   }
   
   /** Tests if playerWinning() returns correctly **/
   @Test public void playerWinningTest1() {
      try{
         /* Note that you do not need to understand how this test works. This is some
          * fancy stuff to get around the fact that these variables are private. You
          * should not try to do this outside of testing, as it defeats the purpose of
          * things being private */
         Field scoreField = StickWaterFireGame.class.getDeclaredField("playerScore");
         scoreField.setAccessible(true);
         scoreField.set(game, 1);
      } catch (Exception e){}
      Assert.assertEquals("playerWinning() correctly identifies when it is winning", true, game.playerWinning());
   }
   
   /** Tests if isTie() returns correctly **/
   @Test public void isTieTest1() {
      Assert.assertEquals("isTie() correctly identifies when it is a tie", true, game.isTie());
   }
   
   /** Tests if getScoreReportStr() returns correctly **/
   @Test public void getScoreReportStrTest1() {
      try{
         Field cScoreField = StickWaterFireGame.class.getDeclaredField("computerScore");
         cScoreField.setAccessible(true);
         cScoreField.set(game, 3);
         
         Field pScoreField = StickWaterFireGame.class.getDeclaredField("playerScore");
         pScoreField.setAccessible(true);
         pScoreField.set(game, 2);
      } catch (Exception e){}
      Assert.assertEquals("getScoreReportStr() includes correct scores", true, game.getScoreReportStr().indexOf("2") != -1 && game.getScoreReportStr().indexOf("3") != -1);
   }
}
