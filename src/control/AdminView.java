/*
  @author Sanjana Dodley
  @author Syed Mahmood
*/

package control;

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
import javafx.scene.control.TextInputDialog;
import model.AlbumList;
import util.FileHandler;
import util.UserSession;

/*
    The controller class for the AdminView, which is the state of the Scene when as user inputs "admin" at Login time.
 */
public class AdminView {
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
    @FXML
    Button logoutButton;

    /*Constructor*/
    public AdminView(String fileName) {
        try {
            populateListIfNecessary();
            this.scene = new Scene(initializeFxmlResource(fileName));
            this.usernameListView.setItems(this.users);
            this.addButton.setVisible(false);
            this.usernameTextField.setVisible(false);
            addClickHandlers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        *  @return Scene of this scene
     */
    public Scene getScene() {
        return this.scene;
    }

    /*
        * adds clickHandlers to the LogoutButton
     */
    public void addClickHandlerToLogoutButton(EventHandler<ActionEvent> handler) {
        this.logoutButton.setOnAction(handler);
    }

    /*
        All ClickHandlers for this View, including add user and deleteUser
     */
    private void addClickHandlers() {
        this.addUserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("New User");
                dialog.setHeaderText("Username");
                dialog.setContentText("Please enter the username");
                dialog.showAndWait();
                String username = dialog.getResult();
                if (username == null) return;
                users.add(username);
                addButton.setVisible(false);
                usernameTextField.setVisible(false);
                usernameTextField.setText("");
                usernameListView.setItems(users);
                try {
                    FileHandler.addUsername(username);
                    FileHandler.createUserFile(username);
                    UserSession.albumList = new AlbumList(username);
                    UserSession.albumList.writeAlbumList();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        this.deleteUserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String username = usernameListView.getSelectionModel().getSelectedItem();
                users.remove(username);
                usernameListView.setItems(users);
                try {
                    FileHandler.deleteUsername(username);
                    FileHandler.deleteUserFile(username);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void populateListIfNecessary() {
        FileHandler.addUserNames(this.users);
    }

    private Parent initializeFxmlResource(String fileName)
            throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        return root;
    }
}