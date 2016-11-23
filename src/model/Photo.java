package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

public class Photo implements Serializable
{
    private List<String> tags;
    private String caption = "";
    private transient Image image;
    private LocalDate date;
    private int photoId;
    private Album album;
    private File file;

    public Photo(File file)
    {
    	this.tags = new ArrayList<String>();
    	this.file = file;
    	long date = getFile().lastModified();
    	//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    	//this.date = sdf.format(date);
        this.date = LocalDate.now();
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
    
    public void setCaption(String caption){
    	this.caption = caption;
    }
    
    public String getCaption(){
    	return this.caption;
    }

    public LocalDate getDate() {
        return date;
    }

    public Image getImage() {
        return this.image;
    }
    
    public File getFile(){
    	return this.file;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public ObservableList<String> getTags()
    {
    	ObservableList<String> obTags = FXCollections.observableArrayList();
    	obTags.addAll(this.tags);
    	return obTags;
    }
    
    public int getPhotoId(){
        return this.photoId;
    }
    
    @Override
    public String toString(){
    	return "Caption: " + this.caption + "Date: " + this.date + "Tags: " + this.tags;
    }
}