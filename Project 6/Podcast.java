/**
 * This class encapsulates the data required to represent a podcast in a collection
 * of MediaItems. It derives from MediaItem.
 * In addition to its superclass attributes, the attributes of a podcast are: 
 *     String: its description, String: its website, String: its date, 
 *     true if it is a video podcast, false otherwise.
 **/
 public class Podcast extends MediaItem {
 
   private String description;
   private String website;
   private String date;
   private boolean isVideo;

   
   public Podcast(String title, String author, String genre, String description,
                  String website, String date, boolean isVideo){
    super(title, author, genre);
    this.description = description;
    this.website = website;
    this.date = date;
    this.isVideo = isVideo;
   }
   

   public String getDescription(){
    return description;}
  
   public String getWebsite(){
    return website;}
  
   public String getDate(){
    return date;}
   
   public boolean isVideo(){
    return isVideo;}

  @Override
  public String toString(){
   return "Podcast: "+super.toString()+", "+description+", "+website+", "+date+", "+isVideo;
     
  }
  
}