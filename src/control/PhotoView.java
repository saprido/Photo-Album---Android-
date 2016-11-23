/**
 * Created by Sanju on 11/20/16.
 */

package control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Photo;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class PhotoView
{
	Photo photo;
	
	Scene scene;
    @FXML
    ImageView imageView;
    @FXML
    Label titleLabel;
    @FXML
    Label nameLabel;
    @FXML
    Label dateLabel;
    @FXML
    Label captionLabel;
    @FXML
    ListView<String> tagsListView;
    @FXML
    Button deleteTagButton;
    
    public PhotoView(String filename)
    {
    	try
    	{
			this.scene = new Scene(initializeFxmlResource(filename));
			this.deleteTagButton.setVisible(false);
			this.tagsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent click) {
	                if (click.getClickCount() == 1 && photo.getTags().size() > 0) {
	                    deleteTagButton.setVisible(true);
	                }
	            }
	        });
			this.deleteTagButton.setOnAction(new EventHandler<ActionEvent>() 
	        {
	            @Override
	            public void handle(ActionEvent event) 
	            {
	            	String tag = tagsListView.getSelectionModel().getSelectedItem();
	            	if (tag == null) return;
	            	photo.deleteTag(tag);
	            	updateTags();
	            	deleteTagButton.setVisible(false);
	            }
	        });
		} 
    	catch (IOException e)
    	{
			e.printStackTrace();
		}
    }

    public Scene getScene()
    {
    	return this.scene;
    }
    
    public void setPhoto(Photo photo)
    {
    	this.photo = photo;
    }
    
    public void setImage()
    {
    	this.imageView.setImage(this.photo.getImage());
    }
    
    public void setCaption()
    {
    	this.captionLabel.setText(this.photo.getCaption());
    }
    
    public void setDate()
    {
    	this.dateLabel.setText(this.photo.getDate().toString());
    }
    
    public void updateTags()
    {
    	this.tagsListView.setItems(this.photo.getTags());
    }

    private Parent initializeFxmlResource(String fileName)
    throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        return root;
    }
}