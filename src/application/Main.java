package application;
	
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import control.AdminView;
import control.AlbumListView;
import control.AlbumView;
import control.LoginView;
import control.PhotoView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.AlbumList;
import util.FileHandler;
import util.UserSession;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Main extends Application implements Serializable
{
	private Stage primaryStage;

    String filePath = "data.txt";

	private static String loginViewFileName = "/view/LoginView.fxml";
	private static String albumListViewFileName = "/view/AlbumListView.fxml";
    private static String albumViewFileName = "/view/AlbumView.fxml";
    private static String adminViewFileName = "/view/AdminView.fxml";
    private static String photoViewFileName = "/view/PhotoView.fxml";
	private LoginView loginView;
	private AlbumListView albumListView;
    private AlbumView albumView; //TODO: initialize + add click handlers
    private AdminView adminView;
    private PhotoView photoView;
	
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			this.primaryStage = primaryStage;
			
			this.loginView = new LoginView(loginViewFileName);
			
			addClickHandlerForLoginView();
			this.adminView = new AdminView(adminViewFileName);
			addClickHandlerForAdminView();
			
			this.primaryStage.setScene(this.loginView.getScene());
			this.primaryStage.show();
			
			this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

		        @Override
		        	public void handle(WindowEvent event) 
		        	{
			        	Alert alert = new Alert(AlertType.CONFIRMATION);
			        	alert.setTitle("Save");
			        	alert.setHeaderText("Save your changes");
			        	alert.setContentText("Would you like to save your changes?");
	
			        	ButtonType yesButton = new ButtonType("Yes");
			        	ButtonType noButton = new ButtonType("No");
			        	alert.getButtonTypes().setAll(yesButton, noButton);
			        	Optional<ButtonType> result = alert.showAndWait();
			        	if (result.get() == yesButton)
			        	{
			        		try 
			        		{
								UserSession.albumList.writeAlbumList();
							} 
			        		catch (FileNotFoundException e)
			        		{
								e.printStackTrace();
							} 
			        		catch (IOException e) 
			        		{
								e.printStackTrace();
							}
			        	}
		        	}
				});
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
					UserSession.albumList = new AlbumList(UserSession.username);
					try {
						UserSession.loadedAlbumList = AlbumList.createAlbumListFromFile();
						System.out.println(UserSession.loadedAlbumList);
					} catch (ClassNotFoundException | IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					photoView = new PhotoView(photoViewFileName);
					
					try {
						albumView = new AlbumView(albumViewFileName, primaryStage, photoView);
						albumListView = new AlbumListView(albumListViewFileName, albumView, primaryStage);
					}
					catch (IOException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
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
	
	public void addClickHandlerForAdminView() 
	{
		//Adds a handler for the back button in the add song view
		this.adminView.addClickHandlerToLogoutButton(new EventHandler<ActionEvent>() {
			@Override
			public void handle( ActionEvent e) 
			{
				switchToView(loginView.getScene());
				loginView.setUsername("");
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