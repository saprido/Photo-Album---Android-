/*
  @author Sanjana Dodley
  @author Syed Mahmood
*/


package control;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Album;
import util.FileHandler;
import util.UserSession;
/*
    Shows the list of albums correlated to a specific User
 */

public class AlbumListView {
    //The scene for this view
    private Scene scene;

    //The list of albums
    private List<Album> albums = new ArrayList<Album>();
    private ObservableList<Album> albumsOL =
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
    ListView<Album> albumListView;

    public AlbumListView(String fileName, AlbumView albumView, Stage primaryStage) throws IOException {
        this.scene = new Scene(initializeFxmlResource(fileName));
        this.albumTextField.setVisible(false);
        this.addAlbumButton.setVisible(false);

        //this.albums = UserSession.loadedAlbumList.getAlbums();
        this.albumsOL.addAll(this.albums);
        this.albumListView.setItems(albumsOL);

        this.albumView = albumView;
        this.stage = primaryStage;
        this.albumView.setAlbumListView(this);

        addClickHandlers();
    }


    /* @returns the class's albums*/
    public List<Album> getAlbums() {
        return this.albums;
    }

    private void addClickHandlers() {
        addClickHandlerToAddButton();
        addClickHandlerToRenameButton();
        addClickHandlerToListViewCells();
        addClickHandlerToDeleteButton();
    }

    /* @returns the class's Scene*/
    public Scene getScene() {
        return this.scene;
    }

    /* Click Handlers for the AddAlbum Button*/
    private void addClickHandlerToAddButton() {
        this.addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Album");
                dialog.setHeaderText("Add Album");
                dialog.setContentText("Please enter the album name:");
                dialog.showAndWait();

                String result = dialog.getResult();
                if (result == null) return;
                String albumName = result;
                //UI changes
                if (albumName.equals("")) return;
                Album album = new Album(result);
                albums.add(album);
                albumsOL.add(album);
                UserSession.albumList.setAlbums(albums);
                albumListView.setItems(albumsOL);
            }
        });
    }

    /* Click Handlers for the delet an Album Button*/
    private void addClickHandlerToDeleteButton() {
        this.deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Album album = (Album) albumListView.getSelectionModel().getSelectedItem();
                albums.remove(album);
                albumsOL.remove(album);
                UserSession.albumList.setAlbums(albums);
                albumListView.setItems(albumsOL);
            }
        });
    }

    /* Click Handlers for the rename an album Button*/
    private void addClickHandlerToRenameButton() {
        this.renameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Album album = (Album) albumListView.getSelectionModel().getSelectedItem();
                TextInputDialog dialog = new TextInputDialog(album.getName());
                dialog.setTitle("Album");
                dialog.setHeaderText("Rename Album");
                dialog.setContentText("Please enter the album name:");
                dialog.showAndWait();

                String result = dialog.getResult();
                if (result == null) return;
                String albumName = result;
                //UI changes
                if (albumName.equals("")) return;
                album.setName(albumName);
                UserSession.albumList.setAlbums(albums);
                //albumListView.setItems(albums);
            }
        });
    }

    /* Click Handlers to pop up the corresponding albumView for a specific album*/
    private void addClickHandlerToListViewCells() {
        this.albumListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                //Double click
                if (click.getClickCount() == 2) {
                    Album album = (Album) albumListView.getSelectionModel().getSelectedItem();
                    albumView.setAlbum(album);
                    System.out.println(album.getName());
                    switchToAlbumView();
                }
            }
        });
    }

    /* Switches to albumView*/
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