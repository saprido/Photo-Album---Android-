package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Album implements Serializable {

    public List<Photo> photos;

    public Album(){
        this.photos = new ArrayList<Photo>();
    }

    public void addPhoto(Photo photo){
        this.photos.add(photo);
    }



}
