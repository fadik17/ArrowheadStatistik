package com.wang.myDB;


import java.util.List;

/**
 * Define the model
 * @author wang
 *
 */

public class Library {

	/**
	 * Data field
	 */
	private AlbumDAO albumDAO;
	private List<Album> list;
	/**
	 * Constructor
	 * @param albumDAO
	 */
	public Library(AlbumDAO albumDAO){
		
		this.albumDAO = albumDAO;
		/**
		 * Connect to the database & show all albums+artists
		 */
		try {
			albumDAO.connect();
			albumDAO.findAll();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * Add a new album + artists
	 * @param title
	 * @param score
	 * @param genre
	 * @param year
	 * @param names
	 * @param nations
	 */
	public void addNewAlbum(String title,String score,String genre, String year, String[] names,  String[] nations) {
		
		Thread t = new Thread() {
			
			public void run(){
				Album album = new Album();
				album.setTitle(title);
				album.setScore(score);
				album.setGenre(genre);
				album.setReleased(year);
				
				albumDAO.insertArtist(names, nations);
				albumDAO.insertAlbum(album);
			}
			
		};
		
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	};
	
	/**
	 * Search albums + artists according to the search data & value
	 * @param searchType
	 * @param value
	 * @return
	 */
	public List<Album> search(AlbumSearchType searchType, String value){
		
		Thread t = new Thread(){
		
		
			public void run(){
				 list = albumDAO.findAlbumByProperty(searchType, value);
				 
			}
		};
		
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
		
		
		
	}
	/**
	 * Display all the albums+artists
	 * @return
	 */
	public List<Album> showAll(){
		Thread t = new Thread(){
			
			public void run(){
				
				list = albumDAO.findAll();
			}
			
		};
		
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * Close the connection to database
	 */
	public void close(){
		try {
			albumDAO.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	 
}
