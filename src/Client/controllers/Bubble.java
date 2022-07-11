package Client.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import Client.DAO.DAOArticle;
import Client.models.Article;
import Client.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Bubble implements Initializable {
   
	 @FXML
	    private Pane bubble;

	    @FXML
	    private Label chatText;

    public void initialize(URL url, ResourceBundle resources) {
    	
    }

    public void setData(String msg) {
    	
    	chatText.setText(msg);
       
       
    }

	

}
