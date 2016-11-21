package view;/**
 * Created by Sanju on 11/19/16.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
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

    public AlbumView(String fileName) throws IOException
    {
        this.scene = new Scene(initializeFxmlResource(fileName));
    }

    public void setAlbum(Album album)
    {
        this.album = album;
    }

    private ImageView createImageView(final File imageFile) {
        // Possibility:
        ImageView imageView = null;
        try {
            final Image image = new Image(new FileInputStream(imageFile), 150, 0, true,
                    true);
            imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {

                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){

                        if(mouseEvent.getClickCount() == 2){
                            try {
                                BorderPane borderPane = new BorderPane();
                                ImageView imageView = new ImageView();
                                Image image = new Image(new FileInputStream(imageFile));
                                imageView.setImage(image);
                                imageView.setStyle("-fx-background-color: BLACK");
                                imageView.setFitHeight(scene.getHeight() - 10);
                                imageView.setPreserveRatio(true);
                                imageView.setSmooth(true);
                                imageView.setCache(true);
                                borderPane.setCenter(imageView);
                                borderPane.setStyle("-fx-background-color: BLACK");
                                Stage newStage = new Stage();
                                newStage.setWidth(scene.getWidth());
                                newStage.setHeight(scene.getHeight());
                                newStage.setTitle(imageFile.getName());
                                Scene scene = new Scene(borderPane, Color.BLACK);
                                newStage.setScene(scene);
                                newStage.show();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            });
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return imageView;
    }

    public Scene getScene()
    {
        return this.scene;
    }

    private Parent initializeFxmlResource(String fileName)
            throws IOException
    {

        if(this.photos != null) {
            for (Photo photo : this.photos) {
                Node node = new ImageView(photo.getPhoto());
                Button button = new Button();
                button.setText(String.valueOf(photo.getPhotoId()));
                button.setGraphic(node);
                tilePane.getChildren().addAll(button);
            }
        }


        // Possibility:
//        String path = "/home/ubuntu/eclipse with liferay/Desktop/imagetest/";
//
//        File folder = new File(path);
//        File[] listOfFiles = folder.listFiles();
//
//        for (final File file : listOfFiles) {
//            ImageView imageView;
//            imageView = createImageView(file);
//            tilePane.getChildren().addAll(imageView);
//        }
//


        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        return root;
    }


}
