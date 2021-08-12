import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

/**This class encapsulates a list of media items in a user's collection.
*  The list is implemented as either an ArrayList or array of type MediaItem.
*  Media items are either a book (electronic format), movie, podcast, or song. 
*  Each type of media item is represented by an instance of the Book, Movie, Podcast, or Song class.
*  These classes are subclasses of MediaItem. The list stores media items as 
*  references of type MediaItem.   
**/
public class MediaList {

   /*
    * Class instance variable declarations section.
    * You are required to declare at least one instance variable called itemList.
    * That variable should be either an array or an ArrayList of type MediaItem. 
    * This variable is the primary data structure that stores all of the MediaItem objects 
    * in the library.
    * You may declare other instance variables if you wish. Points will be 
    * deducted if you declare local variables here. A variable is local if
    * it is used in only one method.
   */
   private ArrayList<MediaItem> itemList;

   public MediaList(){
   itemList = new ArrayList<MediaItem>();}
   

   public void addItem(MediaItem newItem){
   itemList.add(newItem);
      
   }
   
   public boolean containsItem(String targetTitle, String targetAuthor){
      boolean result = false;
      for(int i = 0;i < itemList.size();i++){
      if(itemList.get(i).getTitle().equalsIgnoreCase(targetTitle)&&
         itemList.get(i).getAuthor().equalsIgnoreCase(targetAuthor)){
         result = true;
         break;
         }
         }
         return result;
   }
   

   public boolean removeItem(String targetTitle, String targetAuthor){
      boolean result = false;
      for(int i = 0;i < itemList.size();i++){
      if(itemList.get(i).getTitle().equalsIgnoreCase(targetTitle)&&
         itemList.get(i).getAuthor().equalsIgnoreCase(targetAuthor)){
         itemList.remove(i);
         result = true;
         break;}
         }
      return result;
      }
   

   public String[] getItemListAsStringArray(){
      int length = 0;
      for(int i = 0;i < itemList.size();i++)
      if(!(itemList.get(i).toString().equals(null))&&!(itemList.get(i).toString().equals(""))){
          length++;}
      if(length>0){
      String [] output = new String[length];
      int index = 0;
      for(MediaItem item: itemList){
        if(!(item.toString().equals(null))&&!(item.toString().equals(""))){
        output[index]=item.toString();
        index++;}
        }
        return output;}
        else return empty();
   }

 
   public String[] getAllItemsByTitle(String targetTitle){
      int length = 0;
      for(int i = 0;i < itemList.size();i++)
      if(!(itemList.get(i).getTitle().equals(null))&&!(itemList.get(i).getTitle().equals(""))&&
         itemList.get(i).getTitle().equalsIgnoreCase(targetTitle)){
          length++;}
      if(length>0){
      String [] output = new String[length];
      int index = 0;
      for(MediaItem item: itemList){
        if(!(item.getTitle().equals(null))&&!(item.getTitle().equals(""))&&
         item.getTitle().equalsIgnoreCase(targetTitle)){
        output[index]=item.toString();
        index++;}
        }
        return output;}
        else return empty();
   }
   

   public String[] getAllItemsByAuthor(String targetAuthor){
      int length = 0;
      for(int i = 0;i < itemList.size();i++)
      if(!(itemList.get(i).getAuthor().equals(null))&&!(itemList.get(i).getAuthor().equals(""))
         &&itemList.get(i).getAuthor().equals(targetAuthor)){
          length++;}
      if(length>0){
      String [] output = new String[length];
      int index = 0;
      for(MediaItem item: itemList){
        if(!(item.getAuthor().equals(null))&&!(item.getAuthor().equals(""))
         &&item.getAuthor().equals(targetAuthor)){
        output[index]=item.toString();
        index++;}
        }
        return output;}
        else return empty();
      

   }
   

   public String[] getSortedListOfAuthors(){
      int length = 0;
      for(int i = 0;i < itemList.size();i++)
      if(!(itemList.get(i).getAuthor().equals(null))){
          length++;}
      if(length>0){
      String [] output = new String[length];
      int index = 0;
      for(MediaItem item: itemList){
        if(!(item.getAuthor().equals(null))){
        output[index]=item.getAuthor();
        index++;}
        }
        Arrays.sort(output);
        return output;}
        else return empty();
   }
   

   public int getNumItems(){
      return itemList.size();
   }
    

   public boolean isEmpty(){
      if(itemList.size() == 0){
      return true;}
      else{
      return false;}
   }
  
   /****** Private, "helper" method section. You may define any private 
           methods you want below.  This is not a requirement of this project. 
           These methods perform tasks that make the code above simler and easier to 
           develop, troubleshoot, and understand.
    ******/
    private String [] empty(){
      return new String[0];
 }
 }