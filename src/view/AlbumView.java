package view;/**
 * Created by Sanju on 11/19/16.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Album;
import model.Photo;
import util.UserSession;
import javafx.geometry.Insets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AlbumView {
    //The scene for this view
    private Scene scene;

    private Stage stage;

    private Album album;

    private AlbumListView albumListView;
    private PhotoView photoView;

    //The list of photos
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
    @FXML
    Button displayButton;
    @FXML
    Button captionButton;
    @FXML
    Button addTagButton;
    @FXML
    Button searchButton;
    @FXML
    TextField searchTextField;
    @FXML
    Button moveButton;
    @FXML
    Button clearButton;
    @FXML
    Button dateRangeButton;
    @FXML
    DatePicker startDate;
    @FXML
    DatePicker endDate;
    @FXML
    Label toLabel;


    //if add and delete are called, that means AlbumList was updated and should be overwritten
    public AlbumView(String fileName, Stage stage, PhotoView photoView)
            throws IOException {
        this.scene = new Scene(initializeFxmlResource(fileName));
        this.scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hideAllImageButtons();
            }
        });

        hideAllImageButtons();
        this.photoView = photoView;
        this.stage = stage;
        addClickHandlers();
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Album getAlbum() {
        return this.album;
    }

    public void setAlbumListView(AlbumListView albumListView) {
        this.albumListView = albumListView;
    }

    public Scene getScene() {
        return this.scene;
    }

    private void addClickHandlers() {
        addClickHandlerToAddButton();
        addClickHandlerToBackButton();
    }

    private void addClickHandlerToBackButton() {
        this.backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                switchToAlbumListView();
                System.out.println(UserSession.albumList);
            }
        });
    }

    private void addClickHandlerToAddButton() {
        this.addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                File file = new FileChooser().showOpenDialog(stage);
                if (file != null) {
                    Photo photo = new Photo(file);
                    album.addPhoto(photo);
                    photos.add(photo);
                    UserSession.albumList.setAlbums(albumListView.getAlbums());
                    updateTilePane(photo);
                }

            }
        });
    }

    private ImageView createImageView(Photo photo) {
        Image image = photo.getImage();
        ImageView imageView = new ImageView(image);
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 1) {
                        displayButton.setVisible(true);
                        captionButton.setVisible(true);
                        addTagButton.setVisible(true);
                        deleteButton.setVisible(true);
                        moveButton.setVisible(true);
                        startDate.setVisible(false);
                        endDate.setVisible(false);
                        dateRangeButton.setVisible(false);
                        toLabel.setVisible(false);
                        addClickHandlersToPhotoButtons(photo, imageView);
                    }
                }
            }
        });
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        return imageView;
    }

    private void switchToAlbumListView() {
        this.stage.setScene(this.albumListView.getScene());
    }

    private void updateTilePane(Photo photo) {
        ImageView imageView = createImageView(photo);
        this.tilePane.getChildren().addAll(imageView);

    }

    private void update(TilePane tile) {
        this.tilePane.getChildren().setAll(tile.getChildren());
    }

    private void addClickHandlersToPhotoButtons(Photo photo, ImageView imageView) {
        this.photoView.setPhoto(photo);
        this.displayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setScene(photoView.getScene());
                photoView.setDate();
                photoView.updateTags();
                photoView.setImage();
                photoView.setCaption();
                stage.show();
                hideAllImageButtons();
            }
        });
        this.addTagButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Tag");
                dialog.setHeaderText("Add a tag to this picture");
                dialog.setContentText("Please enter the tag:");
                dialog.showAndWait();
                String tag = dialog.getResult();
                photo.addTag(tag);
                //int index = album.getPhotos().indexOf(photo);
                //album.photos.get(index).addTag(tag);
                hideAllImageButtons();
                UserSession.albumList.setAlbums(albumListView.getAlbums());
            }
        });
        this.deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tilePane.getChildren().removeAll(imageView);
                hideAllImageButtons();
                album.removePhoto(photo);
                UserSession.albumList.setAlbums(albumListView.getAlbums());
            }
        });
        this.captionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog(photo.getCaption());
                dialog.setTitle("Caption");
                dialog.setHeaderText("Caption this picture");
                dialog.setContentText("Please enter the caption:");
                dialog.showAndWait();
                String caption = dialog.getResult();
                photo.setCaption(caption);
                hideAllImageButtons();
                UserSession.albumList.setAlbums(albumListView.getAlbums());
            }
        });

        this.searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TilePane tile = new TilePane();
                String input = searchTextField.getText();
                for (Photo photo : photos) {
                    if (photo.getTags().contains(input)) {
                        ImageView imageView = createImageView(photo);
                        tile.getChildren().add(imageView);
                    }
                }
                update(tile);
            }
        });

        this.clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TilePane tile = new TilePane();
                searchTextField.clear();
                for (Photo photo : photos) {
                    ImageView imageView = createImageView(photo);
                    tile.getChildren().add(imageView);
                }
                update(tile);
            }
        });

    }

    private void hideAllImageButtons() {
        this.displayButton.setVisible(false);
        this.captionButton.setVisible(false);
        this.addTagButton.setVisible(false);
        this.deleteButton.setVisible(false);
        this.moveButton.setVisible(false);
        this.startDate.setVisible(true);
        this.endDate.setVisible(true);
        this.dateRangeButton.setVisible(true);
        this.toLabel.setVisible(true);
    }

    private Parent initializeFxmlResource(String fileName)
            throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        return root;
    }
}