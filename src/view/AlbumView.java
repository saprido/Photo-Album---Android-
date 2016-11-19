package view;/**
 * Created by Sanju on 11/19/16.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import model.Album;
import model.Photo;

import java.io.File;
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


        String path = "/home/ubuntu/eclipse with liferay/Desktop/imagetest/";

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (final File file : listOfFiles) {
            ImageView imageView = new ImageView(file);
            tilePane.getChildren().addAll(new Button().setGraphic(imageView));
        }


        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        return root;
    }
}
