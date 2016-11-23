package model;

/*
  @author Sanjana Dodley
  @author Syed Mahmood
*/


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* Album represents a collection of photos*/
public class Album implements Serializable {

    public String name;

    public List<Photo> photos;

    public Album(String name) {
        this.photos = new ArrayList<Photo>();
        this.name = name;
    }

    public void addPhoto(Photo photo){
        this.photos.add(photo);
    }
    
    @Override
    public String toString()
    {
    	return this.name + "Photos: " + this.photos;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
    	return this.name;
    }
    
    public List<Photo> getPhotos()
    {
    	return this.photos;
    }
    
    public void removePhoto(Photo photo)
    {
    	this.photos.remove(photo);
    }
}
