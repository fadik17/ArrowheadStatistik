package com.wang.myDB;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;


/**
 * Implementation of AlbumDAO with concrete methods
 * @author wang
 *
 */
public class DerbyAlbumDAO implements AlbumDAO{

	/**
	 * Data fields
	 */
	private MongoDatabase db;
	private MongoClient mongoClient;
	private FindIterable<Document> iterable;
	private List<Document> artists;
	
	
	public List<Document> getArtists() {
		return artists;
	}

	public void setArtists(List<Document> artists) {
		this.artists = artists;
	}

	String database = "myDB";
	String server= "localhost";
	int port = 27017;
	
	
	@Override
	public void connect() throws Exception {
			
		try {
			
			mongoClient = new MongoClient(server, port);
			db = mongoClient.getDatabase(database);
				
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			
	}

	@Override
	public void close() throws Exception {
		mongoClient.close();
		
	}
	
	@Override
	public void insertAlbum(Album album) {
		Document albumToAdd = new Document();
		try{
			
			db.getCollection("album").insertOne(albumToAdd.append("title", album.getTitle())
					.append("score", album.getScore())
					.append("released", album.getReleased())
					.append("genre", album.getGenre())
					.append("artist", new Document().append("name", this.getArtists()).append("nation", this.getArtists())));
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Document> insertArtist(String[] names, String[] nations) {
		
		
		for (int i = 0; i < names.length; i ++) {
		try {
			
			artists.add(new Document().append("name", names[i]).append("nation", nations[i]));
			System.out.print(artists);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	  }
		this.setArtists(artists);
		return artists;
	}
	

	@Override
	public List<Album> findAlbumByProperty(AlbumSearchType searchType,
			Object value) {
		
		List<Album> albums = new ArrayList<>();
		// Generic methods
		String whereClause = "";
		String valueClause = "";
		
		switch(searchType){
		
		
		case ARTIST:
			whereClause = "artist.name";
			valueClause = value.toString();
			break;
		case SCORE:
			whereClause = "score";
			valueClause = value.toString();
			break;
		case TITLE:
			whereClause = "title";
			valueClause = value.toString();
			break;
		case GENRE:
			whereClause = "genre";
			valueClause = value.toString();
			break;
		default:
			System.out.println("Unknow search type");
			break;
		
		}
		
		try{
			
			
			FindIterable<Document> iterable = db.getCollection("album").find(new Document(whereClause, valueClause));
			iterable.forEach(new Block<Document>(){

				@Override
				public void apply(final Document document) {
					
					String title = document.getString("title");
					System.out.println(title);
					String genre = document.getString("genre");
					System.out.println(genre);
					String score =  document.getString("score");
					System.out.println(score);
					String released = document.getString("released");
					System.out.println(released);
					String artists =  document.get("artist").toString();
					System.out.println(artists);
					
					albums.add(new Album(title,score,genre,released,artists));
				}
				
			});
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return albums;
	}

	@Override
	public List<Album> findAll() {
		List<Album> albums = new ArrayList<Album>();
		try {
			
			iterable = db.getCollection("album").find();
			iterable.forEach(new Block<Document>() {

				
				@Override
				public void apply(final Document document) {
					
					
					String title = document.getString("title");
					System.out.println(title);
					String genre = document.getString("genre");
					System.out.println(genre);
					String score =  document.getString("score");
					System.out.println(score);
					String released = document.getString("released");
					System.out.println(released);
					String artists =  document.get("artist").toString();
					System.out.println(artists);
					
					albums.add(new Album(title,score,genre,released,artists));
				}
					
			});
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return albums;
		
	}

}
