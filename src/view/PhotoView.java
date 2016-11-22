package view;/**
 * Created by Sanju on 11/20/16.
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Photo;

import java.io.IOException;

public class PhotoView
{
	Scene scene;
    @FXML
    ImageView photo;
    @FXML
    Label titleLabel;
    @FXML
    Label nameLabel;
    @FXML
    Label dateLabel;

    Button backButton;
    
    public PhotoView(String filename)
    {
    	try 
    	{
			this.scene = new Scene(initializeFxmlResource(filename));
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

    private Parent initializeFxmlResource(String fileName)
    throws IOException
    {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        return root;
    }
}