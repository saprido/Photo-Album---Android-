package application;
	
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.Album;
import model.AlbumList;
import util.FileHandler;
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
	private LoginView loginView;
	private AlbumListView albumListView;
    private AlbumView albumView;

    public Main() throws IOException {
    }

    @Override
	public void start(Stage primaryStage) 
	{
		try 
		{

			this.primaryStage = primaryStage;
			
			this.loginView = new LoginView(loginViewFileName);
			this.albumListView = new AlbumListView(albumListViewFileName);
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
					if (FileHandler.doesUserNameExist(loginView.getUsername()))
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



    public void addClickHandlerForAlbumListView()
    {
        //Adds a handler for the back button in the add song view
        this.albumListView.addClickHandlerToAddButton( new EventHandler<ActionEvent>() {
            @Override
            public void handle( ActionEvent e)
            {
                //add textinput dialog box


            }
        });

        this.albumListView.addClickHandlerToDeleteButton(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //get selected
                //delete selected from list

            }
        });
    }


    public void addClickHandlerForAlbumView(){

        this.albumView.addClickHandlerToAddButton( new EventHandler<ActionEvent>() {
            @Override
            public void handle( ActionEvent e)
            {


            }
        });


        this.albumView.addClickHandlerToDeleteButton(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });


        this.albumView.addClickHandlerToBackButton(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switchToAlbumListView();
            }
        });


    }


    public void switchToAlbumView()
    {
        this.primaryStage.setScene(this.albumView.getScene());
    }
	
	public void switchToAlbumListView()
	{
		this.primaryStage.setScene(this.albumListView.getScene());
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}