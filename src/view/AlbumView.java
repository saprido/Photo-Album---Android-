package view;/**
 * Created by Sanju on 11/19/16.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Album;
import model.Photo;

import javafx.geometry.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AlbumView{

    //The scene for this view
    private Scene scene;
    
    private Stage stage;

    private Album album;

    private AlbumListView albumListView;

    //The list of albums
    private final ObservableList<Photo> photos =
            FXCollections.observableArrayList();

    @FXML
    TilePane tilePane;
    @FXML
    Button deleteButton;
    @FXML
    Button addButton;
    @FXML
    Button backButton;

    //if add and delete are called, that means AlbumList was updated and should be overwritten

    public AlbumView(String fileName, Stage stage) throws IOException
    {
        this.scene = new Scene(initializeFxmlResource(fileName));
        this.stage = stage;
        addClickHandlers();
    }

    public void setAlbum(Album album)
    {
        this.album = album;
    }
    
    public Album getAlbum()
    {
    	return this.album;
    }
    
    public void setAlbumListView(AlbumListView albumListView)
    {
    	this.albumListView = albumListView;
    }

    public Scene getScene()
    {
        return this.scene;
    }
    
    private void addClickHandlers()
    {
    	addClickHandlerToAddButton();
    	addClickHandlerToBackButton();
    }
    
    private void addClickHandlerToBackButton()
    {
    	this.backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                switchToAlbumListView();
            }
        });
    }
    
    private void addClickHandlerToAddButton()
    {
    	this.addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	File file = new FileChooser().showOpenDialog(stage);
                if (file != null) 
                {
                	Photo photo = new Photo(file);
                    album.addPhoto(photo);
                    photos.add(photo);
                    updateTilePane(photo);
                }
               
            }
        });
    }

    private ImageView createImageView(Image image) 
    {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
		imageView.setFitWidth(100);
        return imageView;
    }
    
    private void switchToAlbumListView()
    {
    	this.stage.setScene(this.albumListView.getScene());
    }
    
    private void updateTilePane(Photo photo)
    {
    	ImageView imageView = createImageView(photo.getImage());
        this.tilePane.getChildren().addAll(imageView);
    	/*
    	if(this.photos != null) {
            for (Photo photo : this.photos) {
            	ImageView imageView = createImageView(photo.getImage());
                this.tilePane.getChildren().addAll(imageView);
            }
        }*/
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