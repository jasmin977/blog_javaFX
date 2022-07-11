package Client.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import Client.DAO.DAOArticle;
import Client.DAO.DAOUser;
import Client.models.Article;
import Client.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateArticle implements Initializable {
	  @FXML
	    private Button postBTN;

	    @FXML
	    private Button exitBTN;

	    @FXML
	    private ComboBox<?> picker;

	    @FXML
	    private TextField contentField,typeField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	 public void add() {
	        if (!contentField.getText().equalsIgnoreCase("")&& !typeField.getText().equalsIgnoreCase(""))
	                {	  String img = null;
	        	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        	Date date = new Date();
	        	System.out.println(dateFormat.format(date));
	        	 
	                    Article article = new Article(typeField.getText(), contentField.getText(),date,
	                    		Controller.loggedInUserInfo,img);
	                    
	                  
	                    DAOArticle.add(article);
	                    System.out.println("article added");
	                   // success.setOpacity(1);
	                   
	                 
	        } 
	    }

}
