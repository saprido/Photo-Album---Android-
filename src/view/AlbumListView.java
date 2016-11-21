package view;

import java.io.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	private ObservableList<Album> albums =
			          FXCollections.observableArrayList();

    public boolean called = false;
		
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
    public void addClickHandlerToAddButton(EventHandler<ActionEvent> eventHandler)
    {
        this.addButton.setOnAction(eventHandler);
    }

    public void addClickHandlerToDeleteButton(EventHandler<ActionEvent> eventHandler)
    {
        this.addButton.setOnAction(eventHandler);
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