package com.wang.myDB;

public class Album {

	//private double uniqueID;
	private String title;
	private String genre;
	private String score;
	private String released;
	private String artists;
	
	/**
	 * Default constructor 1
	 */
	public Album() {}

	/**
	 * Constructor 2
	 * @param title
	 * @param score
	 * @param genre
	 * @param released
	 */
	public Album(String title, String score, String genre, String released) {
		this.title = title;
		this.score = score;
		this.genre = genre;
		this.released = released;	
	}
	/**
	 * Constructor 3
	 * @param title
	 * @param score
	 * @param genre
	 * @param released
	 * @param artists
	 */
	public Album(String title, String score, String genre, String released,String artists){
	
		this.title = title;
		this.score = score;
		this.genre = genre;
		this.released = released;
		this.artists = artists;
		
	}
	
	/*public Album(double uniqueID, String title, String score, String genre, String released,String artists){
		this.uniqueID = uniqueID;
		this.title = title;
		this.score = score;
		this.genre = genre;
		this.released = released;
		this.artists = artists;
		
	}*/
	/*private List<String> artists;
	// skapa klass Artist och ändra typen till Artist istället för String. 
	//I så fall behöver du inte metoderna nedan: getArtist och setAuthors--> 
	//de ska istället vara i Artist klassen
	

	public String getArtists() {
		
		String result = "";
		for(String artist:artists)
			result += artist + ",";
		return result.substring(0, result.length() - 1);	// Discard the last ","
	}
	
	public void setArtists(String artists){
		this.artists = Arrays.asList(artists.split(","));
		
	}*/
	
	/*public double getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(double uniqueID) {
		this.uniqueID = uniqueID;
	}*/
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getReleased() {
		return released;
	}
	public void setReleased(String releasedYear) {
		this.released = releasedYear;
	}
	
	
	

	

	@Override
	public String toString() {
		return "Album [Title=" + title  + ";  Score=" + score +";  Released=" + released + ";  Genre="
				+ genre + ";  Artist=" + artists + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((released == null)?0:title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		if (artists == null) {
			if (other.artists != null)
				return false;
		} else if (!artists.equals(other.artists))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (released != other.released)
			return false;
		return true;
	}
	
	
	
	 
	
}
