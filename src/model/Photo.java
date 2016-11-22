package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

public class Photo
{
    private ObservableList<String> tags;
    private Image image;
    private Date date;
    private int photoId;
    private Album album;
    private File file;

    public Photo(File file)
    {
    	this.tags = FXCollections.observableArrayList();
    	this.file = file;
        try 
        {
			this.image = SwingFXUtils.toFXImage(ImageIO.read(file), null);
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}
    }

    public void addTag(String tag){
        this.tags.add(tag);
    }
    
    public void deleteTag(String tag){
    	this.tags.remove(tag);
    }

    public Date getDate() {
        return date;
    }

    public Image getImage() {
        return this.image;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public ObservableList<String> getTags()
    {
    	return this.tags;
    }
    
    public int getPhotoId(){
        return this.photoId;
    }
}