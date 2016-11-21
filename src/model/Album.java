package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    	return this.name + "\n" + "Naseer " + "\n" + "Hello world";
    }

    @Override
    public String toString()
    {
        return this.name + "\n" + "Naseer " + "\n" + "Hello world";
    }

    public void setName(String s){
        this.name = s;
    }





}
