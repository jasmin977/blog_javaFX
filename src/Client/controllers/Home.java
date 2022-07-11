package Client.controllers;

import static Client.controllers.Controller.users;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import Client.DAO.DAOArticle;
import Client.DAO.DAOTopic;
import Client.DAO.DAOUser;
import Client.models.*;
import Client.models.User;
import Client.controllers.Controller;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Home extends Thread implements Initializable {

    @FXML
    private Label clientName;
    @FXML
    private VBox chats;
////profile pane
    @FXML
    public Label fullName;
    @FXML
    public Label email;
    @FXML
    public Label phoneNo,chatText;
    @FXML
    public Label gender;
    @FXML
    public Pane profile,homePanel,bubble;
    @FXML
    public Button profileBtn,HomeBtn,exitBTN,chatBtn;
    @FXML
    public TextField fileChoosePath,serchUser,typeField,contentField,chooseimg;

    @FXML
    private AnchorPane createpanel;
    @FXML
    private AnchorPane updatePannel;
    @FXML
    public ImageView proImage;
    //////////////chat panel
    @FXML
    public Button sendBTN,addTopicBtn;
    @FXML
    public Pane chatPanel;
    @FXML
    public TextField msgField,updateTitleField,updateContentField,updateFilechoose;
    @FXML
    public TextArea topicInput;
    
    /////////////////////////////
    

    @FXML
    private Button add_btn,postBTN,chooseimgBTNupdate,upfateBTN,exitbtnUpdate;    
    @FXML
    private Label loggedinEmail;

    @FXML
    private GridPane postsContainer;
    
    @FXML
    private GridPane userContainer;
    
    @FXML
    private ListView<HBox> topicList;

    @FXML
    private Circle showProPic;

    private FileChooser fileChooser;
    private File filePath;
    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    
    ArrayList<AnchorPane> usersList = new ArrayList<>(); 
    
    
    ArrayList<Client.models.User> userss = DAOUser.find();
    public void connectSocket() {
        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println(cmd);
                StringBuilder fulmsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fulmsg.append(tokens[i]);
                }
                System.out.println(fulmsg);
                if (cmd.equalsIgnoreCase(Controller.username + ":")) {
                    continue;
                } else if (fulmsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
                
                Label l =new Label(msg);
                l.setStyle("-fx-text-fill: #000; ");
                
                l.setFont(new Font(20));
                Pane bubble= new Pane(l);
                l.setAlignment(Pos.CENTER);
                l.setPadding(new Insets(10));
              
                bubble.setStyle("-fx-background-color: gray ; -fx-background-radius: 10;");
                bubble.setPadding(new Insets(10));
         			   HBox chatline= new HBox(bubble); 
         			   
         			   chatline.setAlignment(Pos.CENTER_LEFT);
         			   
         			   
         			  Platform.runLater(new Runnable(){
         				  @Override public void run() {			
         				 chats.getChildren().add(chatline);
         				  }
         				});
         			  
         			  
         			   
         			   
	                
             // msgRoom.appendText(msg + "\n");
            }
            reader.close();
            writer.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleSendEvent(MouseEvent event) {
        send();
        for (User user : userss) {
            System.out.println(user.getName());
        }
    }

    public void send() {
        String msg = msgField.getText();
        
       Label l =new Label(msg);
       l.setStyle("-fx-text-fill: #fff; ");
       
       l.setFont(new Font(20));
       Pane bubble= new Pane(l);
       l.setAlignment(Pos.CENTER);
       l.setPadding(new Insets(10));
     
       bubble.setStyle("-fx-background-color: #2d5297 ; -fx-background-radius: 10;");
       bubble.setPadding(new Insets(10));
			   HBox chatline= new HBox(bubble); 
			   
			   chatline.setAlignment(Pos.CENTER_RIGHT);
			   chats.getChildren().add(chatline);
               writer.println(Controller.username + ": " + msg);
		
	        msgField.setText("");
	        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
	            System.exit(0);
	        }
			
    }
    public void sendMessageByKey(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            send();
        }
    }

    
    //////////////////////////////////////////////////////////////////////////////////
    public void searchUserAction(ActionEvent event) {
        if (event.getSource().equals(serchUser)) {
        	String query = serchUser.getText();
        	if(query.equals("")) {
        		renderUserList(DAOUser.find());
        	}else {
        		System.out.println("search 1 : "+ query);
        		renderUserList(DAOUser.find(query));
        	}
        
        } 
    }
    
    public void handleHomeBtn(ActionEvent event) {
        if (event.getSource().equals(HomeBtn)) {
           
        	homePanel.toFront();
            profile.toBack();
            profile.setOpacity(0);
         
           
        } 
    }
    
    public void handleChatBtn(ActionEvent event) {
        if (event.getSource().equals(chatBtn)) {
            chatPanel.toFront();
        } 
    }
    
    
    public void setProfile() {
        User user = DAOUser.findOne(Controller.username);

        fullName.setText(user.getFullName());
        fullName.setOpacity(1);
        email.setText(user.getEmail());
        email.setOpacity(1);
        phoneNo.setText(user.getPhoneNo());
        gender.setText(user.getGender());
    }
    
    public void setArticle(int id) {
        Article article = DAOArticle.findOne(id);

        updateTitleField.setText(article.getTitle());
        updateContentField.setText(article.getContent());
        updateFilechoose.setText(article.getImg());
    }
    
    public void updateArticleAction(ActionEvent event) {
        if (event.getSource().equals(upfateBTN)) {
        	
        	  if (!updateTitleField.getText().equalsIgnoreCase("")&& !updateContentField.getText().equalsIgnoreCase("")
        			  && !updateFilechoose.getText().equalsIgnoreCase(""))
              {
        		 
      	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      	Date date = new Date();
      	System.out.println(dateFormat.format(date));
      	 
                  Article article = new Article(updateTitleField.getText(), updateContentField.getText(),date,
                  		Controller.loggedInUserInfo,updateFilechoose.getText());
                  
                
                  DAOArticle.update(article, Controller.loggedInUserInfo.getName());
                  System.out.println("article updated");
                  updateTitleField.setText("");
                  updateContentField.setText("");
                  updateFilechoose.setText("");
                  updatePannel.toBack();
                  getAticles();
                 // success.setOpacity(1);
                 
               
      } 
           
        } 
    }
    
    public void deleteActionUpdate(ActionEvent event) {
        if (event.getSource().equals(exitbtnUpdate)) {
        	
        	updateTitleField.setText("");
        	updateContentField.setText("");
        	 updateFilechoose.setText("");
        } 
    }
    
    public void addArticleAction(ActionEvent event) {
        if (event.getSource().equals(postBTN)) {
        	
        	  if (!contentField.getText().equalsIgnoreCase("")&& !typeField.getText().equalsIgnoreCase(""))
              {
        		  String img = null;
      	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      	Date date = new Date();
      	System.out.println(dateFormat.format(date));
      	 
                  Article article = new Article(typeField.getText(), contentField.getText(),date,
                  		Controller.loggedInUserInfo,chooseimg.getText());
                  
                
                  DAOArticle.add(article);
                  System.out.println("article added");
                  contentField.setText("");
                  typeField.setText("");
                  chooseimg.setText("");
                  homePanel.toFront();
                  getAticles();
                 // success.setOpacity(1);
                 
               
      } 
           
        } 
    }
    
    
    
    public void deleteAction(ActionEvent event) {
        if (event.getSource().equals(exitBTN)) {
        	
                  contentField.setText("");
                  typeField.setText("");
    
        } 
    }
    
    
    public void handleProfileBtn(ActionEvent event) {
        if (event.getSource().equals(profileBtn)) {
           
         //   FadeTransition ft = new FadeTransition(Duration.millis(3000), profile);
         
            profile.toFront();
            profile.setOpacity(1);
            createpanel.setOpacity(0);
            createpanel.toBack();
          
          setProfile();
           
        } 
    }
 // Changing profile pic

    public boolean saveControl = false;
    
    public void chooseImageButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
         filePath = fileChooser.showOpenDialog(stage);
         fileChoosePath.setText(filePath.getPath());
         saveControl = true;
        
    }
    public void chooseImageButtonUpdate(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser2 = new FileChooser();
        fileChooser2.setTitle("Open Image");
        File filePath2 = fileChooser2.showOpenDialog(stage);
        updateFilechoose.setText(filePath2.getPath());
        
    }
    public void chooseImageButtonAdd(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser3  = new FileChooser();
        fileChooser3.setTitle("Open Image");
       File filePath3 = fileChooser3.showOpenDialog(stage);
        chooseimg.setText(filePath3.getPath());
       
    }
    public void saveImage() {
        if (saveControl) {
            try {
            	DAOUser.updateImage(filePath.getPath(),Controller.loggedInUserInfo.getName());
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                proImage.setImage(image);
                showProPic.setFill(new ImagePattern(image));
                saveControl = false;
                fileChoosePath.setText("");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    
    
	public void handleAddBtn(ActionEvent event) {
        if (event.getSource().equals(add_btn)) {
        	
        	
        	createpanel.setOpacity(1);
        	createpanel.toFront();
        	
        }}
	
	
	public void renderTopicList() throws FileNotFoundException {
		ArrayList<Topic> list = DAOTopic.find();
		for(Topic topic : list) {
			System.out.println(topic.getImg());
			VBox container = new VBox();
			Label name = new Label(topic.getName());
			Label topicNumber = new Label("10");
			container.getChildren().addAll(name,topicNumber);
			InputStream stream = new FileInputStream(topic.getImg());
			 Image img = new Image(stream);
			 Circle topicIcon = new Circle(20);
       	  	topicIcon.setFill(new ImagePattern(img));
			HBox warpper = new HBox();
			warpper.getChildren().addAll(topicIcon, container);
			topicList.getItems().add(warpper);
		}
		topicList.setStyle("-fx-background-color: transparent");
	}
	
	public void getAticles() {
		System.out.println("testing ");
		 ArrayList<Article> articles = DAOArticle.find();
	        int row = 0;
	        int colum=0;
	        for (Article article : articles) {
	            FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(getClass().getResource("/client/view/article.fxml"));
	            try {
	                AnchorPane vb = fxmlLoader.load();
	               
	                PostController pc = fxmlLoader.getController();
	                pc.setData(article);
	                if(colum==2) {
	                	colum=0;
	                	row++;
	                }
	                postsContainer.add(vb, colum++, row);
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	}
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
     //  ArrayList<Article> articles = DAOArticle.find();
    	connectSocket();
       ArrayList<Client.models.User> users = DAOUser.find();
       setProfile();

       profile.toBack();
       profile.setOpacity(0);
      // createpanel.toBack();
     
      try {
		renderTopicList();
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
        
        
        //my info 
        
       
        showProPic.setStroke(Color.valueOf("#90a4ae"));
        Image image;
        if (Controller.loggedInUserInfo.getImage()==null) {
        if (Controller.loggedInUserInfo.getGender().equalsIgnoreCase("homme")) {
            image = new Image("/icons/man1.png", false);
        } else {
            image = new Image("/icons/man2.png", false);
            //proImage.setImage(image);
        }
        showProPic.setFill(new ImagePattern(image));
        
        }
        else {
       	 String pathIMAGE= Controller.loggedInUserInfo.getImage();
       	 try {
				InputStream stream = new FileInputStream(pathIMAGE);
				 Image img = new Image(stream);
	        	  showProPic.setFill(new ImagePattern(img));
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
       	 
        }
        
        clientName.setText(Controller.loggedInUserInfo.getName());
        loggedinEmail.setText(Controller.loggedInUserInfo.getEmail());
        System.out.print(Controller.loggedInUserInfo.toString());

        getAticles();
        renderUserList(users);    
    }

    public void renderUserList(ArrayList<User> users) {
    	
    	for(AnchorPane vb: usersList) {
    		userContainer.getChildren().remove(vb);
    	}
    	
    	 // list of users
        Platform.runLater(new Runnable(){
			  @Override public void run() {	
			        int row2 = 0;

    	for (Client.models.User user : users) {
    		System.out.println(user.getName());

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/client/view/user.fxml"));
            try {
                AnchorPane vb = fxmlLoader.load();
                usersList.add(vb);
                UserController pc = (UserController) fxmlLoader.getController();
                pc.setData(user);
                userContainer.add(vb, 0, row2++);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    	}
			  };});     
    }
    

   
    
  
      
    
}
