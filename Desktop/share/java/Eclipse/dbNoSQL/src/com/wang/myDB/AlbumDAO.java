package com.wang.myDB;


import java.util.List;

import org.bson.Document;
/**
 * Extention of DAO
 * @author wang
 *
 */
public interface AlbumDAO extends DAO{

	public void insertAlbum(Album album);
	public List<Document> insertArtist(String[] names, String[] nations);
	public List<Album> findAlbumByProperty(AlbumSearchType searchType, Object value);
	public List<Album> findAll(); // Generic
	
	
	
}
