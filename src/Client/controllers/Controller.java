package Client.controllers;

import Server.ClientHandler;
import Server.Server;
import Client.models.User;
// import animatefx.animation.FadeIn;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Client.DAO.DAOUser;

public class Controller implements Initializable {
    @FXML
    public Pane pnSignIn;
    @FXML
    public Pane pnSignUp;
    @FXML
    public Button btnSignUp;
    @FXML
    public Button getStarted;
    @FXML
    public ImageView btnBack;
    @FXML
    public TextField regName;
    @FXML
    public TextField regPass;
    @FXML
    public TextField regEmail;
    @FXML
    public TextField regFirstName;
    @FXML
    public TextField regPhoneNo;
    @FXML
    public RadioButton male;
    @FXML
    public RadioButton female;
    @FXML
    public Label controlRegLabel;
    @FXML
    public Label success;
    @FXML
    public Label goBack;
    @FXML
    public TextField userName;
    @FXML
    public TextField passWord;
    @FXML
    public Label loginNotifier;
    @FXML
    public Label nameExists;
    @FXML
    public Label checkEmail;
    public static String username, password, gender;
    public static ArrayList<User> loggedInUser = new ArrayList<>();
    public static User loggedInUserInfo;
    public static ArrayList<User> users = new ArrayList<User>();

    public void registration() {
        if (!regName.getText().equalsIgnoreCase("")
                && !regPass.getText().equalsIgnoreCase("")
                && !regEmail.getText().equalsIgnoreCase("")
                && !regFirstName.getText().equalsIgnoreCase("")
                && !regPhoneNo.getText().equalsIgnoreCase("")
                && (male.isSelected() || female.isSelected())) {
            if (checkUser(regName.getText())) {
                if (checkEmail(regEmail.getText())) {
                    String gender;
                    String imgpath;

                    if (male.isSelected()) {
                        gender = "homme";
                        imgpath="C:\\Users\\bayas\\Desktop\\java_projects\\blog_project\\src\\icons\\man1.png";
                    } else {
                        gender = "femme";
                        imgpath="C:\\Users\\bayas\\Desktop\\java_projects\\blog_project\\src\\icons\\man2.png";

                    }
                    User newUser = new User(regName.getText(), regFirstName.getText(), regPass.getText(),
                            regEmail.getText(), gender, regPhoneNo.getText(),imgpath);
                    
                    users.add(newUser);
                    DAOUser.add(newUser);
                    goBack.setOpacity(1);
                    success.setOpacity(1);
                    makeDefault();
                    if (controlRegLabel.getOpacity() == 1) {
                        controlRegLabel.setOpacity(0);
                    }
                    if (nameExists.getOpacity() == 1) {
                        nameExists.setOpacity(0);
                    }
                } else {
                    checkEmail.setOpacity(1);
                    setOpacity(nameExists, goBack, controlRegLabel, success);
                }
            } else {
                nameExists.setOpacity(1);
                setOpacity(success, goBack, controlRegLabel, checkEmail);
            }
        } else {
            controlRegLabel.setOpacity(1);
            setOpacity(success, goBack, nameExists, checkEmail);
        }
    }

    private void setOpacity(Label a, Label b, Label c, Label d) {
        if (a.getOpacity() == 1 || b.getOpacity() == 1 || c.getOpacity() == 1 || d.getOpacity() == 1) {
            a.setOpacity(0);
            b.setOpacity(0);
            c.setOpacity(0);
            d.setOpacity(0);
        }
    }

    private void setOpacity(Label controlRegLabel, Label checkEmail, Label nameExists) {
        controlRegLabel.setOpacity(0);
        checkEmail.setOpacity(0);
        nameExists.setOpacity(0);
    }

    private boolean checkUser(String username) {
        User user = DAOUser.findOne(username);
        if (user != null) {
            return false;
        }
        return true;
    }

    private boolean checkEmail(String email) {
        User user = DAOUser.findOneByEmail(email);
        if (user != null) {
            return false;
        }
        return true;
    }

    private void makeDefault() {
        regName.setText("");
        regPass.setText("");
        regEmail.setText("");
        regFirstName.setText("");
        regPhoneNo.setText("");
        male.setSelected(true);
        setOpacity(controlRegLabel, checkEmail, nameExists);
    }

    public void login() {
        username = userName.getText();
        password = passWord.getText();
        boolean login = false;
       loggedInUserInfo = DAOUser.findOne(username, password);
      //  loggedInUserInfo = DAOUser.findOne("dali", "123456");
        if (loggedInUserInfo != null) {
            login = true;
            loggedInUser.add(loggedInUserInfo);
            System.out.println(loggedInUserInfo.getFullName());
            gender = loggedInUserInfo.getGender();
        }
        if (login) {
            changeWindow();
        } else {
            loginNotifier.setOpacity(1);
        }
    }

    public void changeWindow() {
        try {
            Stage stage = (Stage) userName.getScene().getWindow();
         
            Parent root = FXMLLoader.load(getClass().getResource("/client/view/Home.fxml"));

            stage.setScene(new Scene(root, 1100, 625));
            stage.setTitle(username + "");
            stage.setOnCloseRequest(event -> {
                System.exit(0);
            });
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource().equals(btnSignUp)) {
            // new FadeIn(pnSignUp).play();
        	 pnSignUp.setOpacity(1);
        	 pnSignIn.setOpacity(0);
        	 pnSignIn.toBack();
            pnSignUp.toFront();
            
        }
        if (event.getSource().equals(getStarted)) {
        	pnSignUp.toBack();
            pnSignIn.toFront();
            pnSignUp.setOpacity(0);
            pnSignIn.setOpacity(1);
        }
        loginNotifier.setOpacity(0);
        userName.setText("");
        passWord.setText("");
    }

    @FXML
    private void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnBack) {
            // new FadeIn(pnSignIn).play();
        	   pnSignIn.setOpacity(1);
           pnSignUp.setOpacity(0);
           pnSignUp.toBack();
            pnSignIn.toFront();
        }
        regName.setText("");
        regPass.setText("");
        regEmail.setText("");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		  pnSignUp.setOpacity(0);
          pnSignUp.toBack();
		
	}
    
  
    
}