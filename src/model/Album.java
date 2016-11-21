package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Album implements Serializable {

    public String name;

    public List<Photo> photos;

    public Album(String s){
        this.photos = new ArrayList<Photo>();
        this.name = s;
    }

    public void addPhoto(Photo photo){
        this.photos.add(photo);
    }

    public void setName(String s){
        this.name = s;
    }





}
