package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.UserSession;

/**
 * 
 * @author syedmahmood
 *
 * Stores the albums for a specific user. 
 * 
 */

public class AlbumList implements Serializable
{
	private List<Album> albums;
	
	public static final String storeDir = "dat";
	public static final String storeFile = "albums.dat";
	private static final long serialVersionUID = 1L;
	
	private String finalFileName;
	
	public AlbumList(String username)
	{
		this.albums = new ArrayList<Album>();
		this.finalFileName = username + this.storeFile;
	}
	
	public void setAlbums(List<Album> albums)
	{
		this.albums = albums;
	}
	
	public List<Album> getAlbums()
	{
		return this.albums;
	}
	
	public void addAlbum(Album album)
	{
		this.albums.add(album);
	}
	
	public void deleteAlbum(Album album)
	{
		this.albums.remove(album);
	}
	
	@Override
	public String toString()
	{
		return this.albums.toString();
	}
	
	public static AlbumList createAlbumListFromFile() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(UserSession.username + storeFile));
				return (AlbumList) ois.readObject(); 
	}
	
	public void writeAlbumList() 
	throws FileNotFoundException, IOException
	{
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(this.finalFileName));
		oos.writeObject(this);
	}
}