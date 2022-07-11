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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class UserController implements Initializable {
   
    @FXML
    public Label userName;
    @FXML
    private Circle showProPic;
   
    @FXML
    public Label postTitle;

    public void initialize(URL url, ResourceBundle resources) {
    	
    }

    public void setData(User user) {
    	 showProPic.setStroke(Color.valueOf("#90a4ae"));
         Image image;
         if (user.getImage()==null) {
         if (user.getGender().equalsIgnoreCase("homme")) {
             image = new Image("/icons/man1.png", false);
         } else {
             image = new Image("/icons/man2.png", false);
             
         }
         showProPic.setFill(new ImagePattern(image));
         }
         else {
        	 String pathIMAGE= user.getImage();
        	 try {
				InputStream stream = new FileInputStream(pathIMAGE);
				 Image img = new Image(stream);
	        	  showProPic.setFill(new ImagePattern(img));
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
        	 
         }
        userName.setText(user.getName());
       
       
    }

	

}
