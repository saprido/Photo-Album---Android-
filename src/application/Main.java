package application;

import java.io.IOException;
import java.io.Serializable;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import util.FileHandler;
import util.UserSession;
import view.AdminView;
import view.AlbumListView;
import view.AlbumView;
import view.LoginView;
import javafx.scene.Scene;

public class Main extends Application implements Serializable
{
    private Stage primaryStage;

    String filePath = "data.txt";

    private static String loginViewFileName = "LoginView.fxml";
    private static String albumListViewFileName = "AlbumListView.fxml";
    private static String albumViewFileName = "AlbumView.fxml";
    private static String adminViewFileName = "AdminView.fxml";
    private LoginView loginView;
    private AlbumListView albumListView;
    private AlbumView albumView; //TODO: initialize + add click handlers
    private AdminView adminView;

    @Override
    public void start(Stage primaryStage)
    {
        try
        {

            this.primaryStage = primaryStage;

            this.loginView = new LoginView(loginViewFileName);
            this.albumView = new AlbumView(albumViewFileName);
            this.albumListView = new AlbumListView(albumListViewFileName, albumView, primaryStage);
            this.adminView = new AdminView(adminViewFileName);

            addClickHandlerForLoginView();

            this.primaryStage.setScene(this.loginView.getScene());
            this.primaryStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addClickHandlerForLoginView()
    {
        //Adds a handler for the back button in the add song view
        this.loginView.addClickHandlerToLoginButton( new EventHandler<ActionEvent>() {
            @Override
            public void handle( ActionEvent e)
            {
                if (loginView.getUsername().equals("admin"))
                {
                    switchToView(adminView.getScene());
                }
                else
                {
                    UserSession.username = loginView.getUsername();
                    //TODO: populate album list with user specific albums
                    try {
                        if (userExists())
                        {
                            switchToView(albumListView.getScene());
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    //TODO: error message if username doesn't exist
                }
            }
        });
    }

    public void switchToView(Scene scene)
    {
        this.primaryStage.setScene(scene);
    }

    private boolean userExists() throws IOException
    {
        return FileHandler.doesUserNameExist(UserSession.username);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}