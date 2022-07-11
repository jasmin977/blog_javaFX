package Client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Client.DAO.DAOArticle;
import Client.DAO.DAOUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class User implements Initializable {

   
    @FXML
    private Label userName;

    @FXML
    private GridPane userContainer;

    @FXML
    private Circle showProPic;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ArrayList<Client.models.User> users = DAOUser.find();
        int row = 0;
        for (Client.models.User user : users) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/client/view/user.fxml"));
            try {
                AnchorPane vb = fxmlLoader.load();
                UserController uc = fxmlLoader.getController();
                uc.setData(user);
                userContainer.add(vb, 0, row++);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
