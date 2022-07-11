package Client.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import Client.DAO.DAOArticle;
import Client.models.Article;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class PostController implements Initializable {
    @FXML
    public Label clientName;
    @FXML
    private ImageView articleIMG;
    @FXML
    public Label postTime;
    @FXML
    private Button deleteBTN;

    @FXML
    private Button editBTN;
    @FXML
    public Label postContent;
    @FXML
    private AnchorPane updatePannel;
    @FXML
    
    public Label postTitle;
    @FXML
    private Circle showProPic;
    @FXML
    public TextField updateTitleField,updateContentField,updateFilechoose;
    
    public int article_id;
   
    public void initialize(URL url, ResourceBundle resources) {
    	editBTN.setOpacity(0);
    	deleteBTN.setOpacity(0);
    }
    
    private static String[] splitToNChar(String text, int size) {
        List<String> parts = new ArrayList<>();

        int length = text.length();
        for (int i = 0; i < length; i += size) {
            parts.add(text.substring(i, Math.min(length, i + size))+"\n");
        }
        return parts.toArray(new String[0]);
    }

    public void setData(Article article) {
    	article_id=article.getId();
        clientName.setText(article.getAuthor().getFullName());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // ps.setString(3, dateFormat.format(article.getCreated_at()));
        postTime.setText(dateFormat.format(article.getCreated_at()));
        
        String content =article.getContent();       
        postContent.setText(Arrays.toString(splitToNChar(content, 33)));
        postTitle.setText(article.getTitle());
        // article img
        
        String pathIMAGEarticle = article.getImg();
        InputStream stream;
		try {
			stream = new FileInputStream(pathIMAGEarticle);
			 Image img = new Image(stream);
			 articleIMG.setImage(img);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 
		 
        //img user
       // showProPic.setStroke(Color.valueOf("#90a4ae"));
        Image image;
        if (article.getAuthor().getImage()==null) {
        if (article.getAuthor().getGender().equalsIgnoreCase("homme")) {
            image = new Image("/icons/man1.png", false);
        } else {
            image = new Image("/icons/man2.png", false);
            
        }
        showProPic.setFill(new ImagePattern(image));
        }
        else {
       	 String pathIMAGE= article.getAuthor().getImage();
       	 try {
				InputStream stream1 = new FileInputStream(pathIMAGE);
				 Image img1 = new Image(stream1);
	        	  showProPic.setFill(new ImagePattern(img1));
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
       	 
        }
        
        // delete and edit btn 
        if(article.getAuthor().getName().equalsIgnoreCase(Controller.loggedInUserInfo.getName())) {
        	editBTN.setOpacity(1);
        	deleteBTN.setOpacity(1);
        	editBTN.setDisable(true);
        	deleteBTN.setDisable(true);
        }
        
      
        
        
    }
    
    public void editArticleAction(ActionEvent event) {
        if (event.getSource().equals(editBTN)) {
        	updatePannel.toFront();
        	 Article article = DAOArticle.findOne(article_id);

             updateTitleField.setText(article.getTitle());
             updateContentField.setText(article.getContent());
             updateFilechoose.setText(article.getImg());
        	
        	}
        }
    
    public void deleteArticleAction(ActionEvent event) {
        if (event.getSource().equals(deleteBTN)) {
        	//DAOArticle.delete(_id);
        	}
        }
}
