package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	
	private String finalFileName;
	
	public AlbumList(String username)
	{
		this.albums = new ArrayList<Album>();
		this.finalFileName = username + this.storeFile;
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
	
	public void writeAlbumList() 
	throws FileNotFoundException, IOException
	{
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(this.storeDir + File.separator + this.finalFileName));
		oos.writeObject(this);
	}
}