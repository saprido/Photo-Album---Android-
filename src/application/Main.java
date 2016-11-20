package application;
	
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import util.FileHandler;
import view.AlbumView;
import view.AlbumView;
import view.LoginView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application implements Serializable
{
	private Stage primaryStage;
	
	private FileHandler fileHandler = new FileHandler("usernames.txt");


    String filePath = "data.txt";

	private static String loginViewFileName = "LoginView.fxml";
	private static String albumListViewFileName = "AlbumListView.fxml";
    private static String albumViewFileName = "AlbumView.fxml";
	private LoginView loginView;
	//private AlbumListView albumListView;
    private AlbumView albumView;
	
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{

			this.primaryStage = primaryStage;
			
			this.loginView = new LoginView(loginViewFileName);
			this.albumListView = new AlbumListView(albumListViewFileName, filePath);
            this.albumView = new AlbumView(albumViewFileName);
			
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
				try {
					if (fileHandler.doesUserNameExist(loginView.getUsername()))
					{
						switchToAlbumView();
					} 
					else 
					{
						//TODO: error message
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void switchToAlbumView()
	{
		this.primaryStage.setScene(this.albumView.getScene());
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}