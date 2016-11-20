package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import util.FileHandler;
import view.AdminView;
import view.AlbumView;
import view.LoginView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application 
{
	private Stage primaryStage;
	
	private static String loginViewFileName = "LoginView.fxml";
	private static String albumViewFileName = "AlbumView.fxml";
	private static String adminViewFileName = "AdminView.fxml";
	private LoginView loginView;
	private AlbumView albumView; 
	private AdminView adminView;
	
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			this.primaryStage = primaryStage;
			
			this.loginView = new LoginView(loginViewFileName);
			this.albumView = new AlbumView(albumViewFileName);
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
					switchToAdminView();
				}
			}
		});
	}
	
	public void switchToAdminView()
	{
		this.primaryStage.setScene(this.adminView.getScene());
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
