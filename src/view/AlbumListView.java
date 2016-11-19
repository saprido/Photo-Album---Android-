package view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import model.Album;

public class AlbumListView
{
	//The scene for this view
	private Scene scene;
	
	//The list of albums
	private final ObservableList<Album> albums = 
			          FXCollections.observableArrayList();
		
	@FXML
	Button deleteButton;
	@FXML
	Button addButton;
	@FXML
	Button renameButton;
	
	public AlbumListView(String fileName) throws IOException
	{
		this.scene = new Scene(initializeFxmlResource(fileName));
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