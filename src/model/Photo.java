package model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

public class Photo{

    private List<String> tags;
    private Image image;
    private Date date;
    private int photoId;
    private Album album;

    public Photo(File file)
    {
        try 
        {
			this.image = SwingFXUtils.toFXImage(ImageIO.read(file), null);
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}
    }

    //utlize google api with this
    public void addTag(String tag){
        this.tags.add(tag);
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
    public int getPhotoId(){
        return this.photoId;
    }
}
