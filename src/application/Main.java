package application;
	
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Album;
import util.FileHandler;
import util.UserSession;
import view.AdminView;
import view.AlbumListView;
import view.AlbumView;
import view.AlbumView;
import view.LoginView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

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
    private AlbumView albumView;
    private AdminView adminView;
	
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{

			this.primaryStage = primaryStage;
			
			this.loginView = new LoginView(loginViewFileName);
			this.albumListView = new AlbumListView(albumListViewFileName);
            //this.albumView = new AlbumView(albumViewFileName);
			this.adminView = new AdminView(adminViewFileName);
			
			addClickHandlerForLoginView();
            addClickHandlerForAlbumListView();
			
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
					switchToAdminView();
				} 
				else 
				{
					UserSession.username = loginView.getUsername();
					//TODO: populate album list with user specific albums
					try {
						if (userExists())
						{
							switchToAlbumListView();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//TODO: error message if username doesn't exist
				}
			}
		});
	}


	public void addClickHandlerForAlbumListView(){
        this.albumListView.addClickHandlerToAddButton(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Album album = new Album("name");
                albumListView.getAlbumList().addAlbum(album);

            }
        });

        this.albumListView.addClickHandlerToDeleteButton(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                albumListView.deleteSelectedAlbumFromAlbumList();
            }
        });
    }
	
	public void switchToAlbumListView()
	{
		this.primaryStage.setScene(this.albumListView.getScene());
	}
	
	public void switchToAdminView()
	{
		this.primaryStage.setScene(this.adminView.getScene());
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