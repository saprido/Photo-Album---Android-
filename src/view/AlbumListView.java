package view;

import java.io.*;

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
	private ObservableList<Album> albums =
			          FXCollections.observableArrayList();

    public boolean called = false;
		
	@FXML
	Button deleteButton;
	@FXML
	Button addButton;
	@FXML
	Button renameButton;



    //when you add an album, you need to add it to data.txt using output serializable;
	//When you click an Album from the list, send the refrence so you can access the photos in albumView.
    //if it was updated, should be overwritten in data.txt

    public void overWrite(){
        try {

            //*** This might just add the albumList as another Album list instead overriding, should do testing
            String fileData = "data.txt";
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileData));
            os.writeObject(this.albums);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public AlbumListView(String fileName, String fileData) throws IOException
	{
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileData));
		this.scene = new Scene(initializeFxmlResource(fileName));
		try {
			this.albums = (ObservableList<Album>) is.readObject();
		} catch (ClassNotFoundException e) {
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