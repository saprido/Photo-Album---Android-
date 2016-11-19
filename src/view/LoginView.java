//Author: Syed Mahmood
//Author: Sanjana Dodley

package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginView
{
	//The scene for this view
	private Scene scene;
	
	@FXML
	TextField usernameTextField;
	@FXML
	Button loginButton;
	
	public LoginView(String fileName) throws IOException
	{
		this.scene = new Scene(initializeFxmlResource(fileName));
	}
	
	public Scene getScene()
	{
		return this.scene;
	}
	
	public String getUsername()
	{
		return this.usernameTextField.getText();
	}
	
	public void addClickHandlerToLoginButton(EventHandler<ActionEvent> eventHandler)
	{
		this.loginButton.setOnAction(eventHandler);
	}

	private Parent initializeFxmlResource(String fileName)
	throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
		loader.setController(this);
		Parent root = (Parent) loader.load();
		return root;
	}
}
