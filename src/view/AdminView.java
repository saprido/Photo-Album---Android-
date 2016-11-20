package view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import util.FileHandler;

public class AdminView 
{
	//The scene for this view
	Scene scene;
	
	//The list of songs
	private final ObservableList<String> users = FXCollections.observableArrayList();
	
	@FXML
	Button addUserButton;
	@FXML
	Button deleteUserButton;
	@FXML
	ListView<String> usernameListView;
	@FXML
	Button addButton;
	@FXML
	TextField usernameTextField;
	
	public AdminView(String fileName)
	{
		try 
		{
			populateListIfNecessary();
			this.scene = new Scene(initializeFxmlResource(fileName));
			this.usernameListView.setItems(this.users);
			this.addButton.setVisible(false);
			this.usernameTextField.setVisible(false);
			addClickHandlers();
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
	
	private void addClickHandlers()
	{
		this.addUserButton.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) 
			{
				addButton.setVisible(true);
				usernameTextField.setVisible(true);
			}
		});
		this.addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) 
			{
				users.add(usernameTextField.getText());
				addButton.setVisible(false);
				usernameTextField.setVisible(false);
				usernameTextField.setText("");
				usernameListView.setItems(users);
				//TODO: create a new file for the user (this will store the AlbumList)
				//TODO: write the username to the usernames.txt file
			}
		});
		this.deleteUserButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) 
			{
				//Remove from users the selected username
				//set listview as items again so that we can see the update
				//TODO: delete the file for the user (the ones that stores the AlbumList)
				//TODO: remove the username from the usernames.txt file, just rewrite usernames.txt file
			}
		});
	}
	
	private void populateListIfNecessary()
	{
		FileHandler.addUserNames(this.users);
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