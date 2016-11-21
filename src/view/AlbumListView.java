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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Album;
import util.FileHandler;

public class AlbumListView {
    //The scene for this view
    private Scene scene;

    //The list of albums
    private ObservableList<Album> albums =
            FXCollections.observableArrayList();

    private AlbumView albumView;

    public boolean called = false;

    private Stage stage;

    @FXML
    Button deleteButton;
    @FXML
    Button addButton;
    @FXML
    Button renameButton;
    @FXML
    TextField albumTextField;
    @FXML
    Button addAlbumButton;
    @FXML
    ListView albumListView;

    public AlbumListView(String fileName, AlbumView albumView, Stage primaryStage) throws IOException {
        this.scene = new Scene(initializeFxmlResource(fileName));
        this.albumTextField.setVisible(false);
        this.addAlbumButton.setVisible(false);

        this.albumView = albumView;
        this.stage = primaryStage;
        this.albumView.setAlbumListView(this);

        addClickHandlers();
    }

    private void addClickHandlers() {
        addClickHandlerToAddButtons();
        addClickHandlerToListViewCells();
    }

    public void addClickHandlerToDeleteButton(EventHandler<ActionEvent> eventHandler) {
        this.addButton.setOnAction(eventHandler);
    }


    public Scene getScene() {
        return this.scene;
    }

    private void addClickHandlerToAddButtons() {
        this.addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                addButton.setVisible(false);
                albumTextField.setVisible(true);
                addAlbumButton.setVisible(true);
            }
        });
        this.addAlbumButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String albumName = albumTextField.getText();
                //UI changes
                albumListView.setItems(albums);
                addButton.setVisible(true);
                albumTextField.setVisible(false);
                addAlbumButton.setVisible(false);
                if (albumName.equals("")) return;
                Album album = new Album(albumTextField.getText());
                albums.add(album);
                albumTextField.setText("");
            }
        });
    }

    private void addClickHandlerToListViewCells() {
        this.albumListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                //Double click
                if (click.getClickCount() == 2) {
                    Album album = (Album) albumListView.getSelectionModel().getSelectedItem();
                    albumView.setAlbum(album);
                    switchToAlbumView();
                }
            }
        });
    }

    private void switchToAlbumView() {
        this.stage.setScene(this.albumView.getScene());
    }

    private Parent initializeFxmlResource(String fileName)
            throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        return root;
    }
}