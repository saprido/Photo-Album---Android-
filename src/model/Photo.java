package model;

import javafx.scene.image.Image;

import java.util.Date;
import java.util.List;

public class Photo{

    private List<String> tags;
    private Image photoImage;
    private Date date;
    private int photoId;
    private Album album;

    public Photo(Image photoImage, Date date, Album album){
        this.photoImage = photoImage;
        this.date = date;
        this.album = album;
        this.photoId = album.photos.size()+1;
        album.photos.add(this);
    }

    //utlize google api with this
    public void addTag(String tag){
        this.tags.add(tag);
    }

    public Date getDate() {
        return date;
    }

    public Image getPhoto() {
        return photoImage;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public int getPhotoId(){
        return this.photoId;
    }
}
