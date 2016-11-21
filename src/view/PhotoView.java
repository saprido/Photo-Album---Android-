package view;/**
 * Created by Sanju on 11/20/16.
 */

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import model.Photo;

import java.io.IOException;

public class PhotoView{

    @FXML
    ImageView photo;
    @FXML
    Label titleLabel;
    @FXML
    Label nameLabel;
    @FXML
    Label dateLabel;

    Button backButton;

    private Parent initializeFxmlResource(String fileName)
            throws IOException
    {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        return root;
    }


}
