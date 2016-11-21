package view;/**
 * Created by Sanju on 11/19/16.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import model.Album;
import model.Photo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AlbumView{

    //The scene for this view
    private Scene scene;

    private Album album;
    //The list of albums
    private final ObservableList<Photo> albumPhotos =
            FXCollections.observableArrayList(album.photos);
    
    @FXML
    TilePane tilePane;
    @FXML
    Button deleteButton;
    @FXML
    Button addButton;
    @FXML
    Button backButton;


    //if add and delete are called, that means AlbumList was updated and should be overwritten

    public AlbumView(String fileName) throws IOException
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

        for (Photo photo : albumPhotos) {
            Node node = new ImageView(photo.getPhoto());
            Button button = new Button();
            button.setText(String.valueOf(photo.getPhotoId()));
            button.setGraphic(node);
            tilePane.getChildren().addAll(button);
        }


        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        return root;
    }
}
