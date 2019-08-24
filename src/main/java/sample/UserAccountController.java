package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;


import javafx.scene.shape.Circle;
import sample.Model.User;
import sample.Service.UserService;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;

public class UserAccountController {

    private User user;
    private UserService userService;


    /*
        public UserAccountController(User user,UserService userService){
            this.user = user;
            this.userService = userService;
        }
     */

    public void setUser(User user) {
        this.user = user;
        NickNameLabel.setText(user.getNickName());
    }


    @FXML
    private Label NickNameLabel;

    @FXML
    private Label timeNow;

    @FXML
    private Circle userAvatar;

    @FXML
    private Label addFriendButton;

    @FXML
    public void initialize() throws IOException{

        Date now = new Date();
        String time ;
        timeNow.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(now));

        Image image = new Image("/Image/kakashi.jpg",false);
        userAvatar.setFill(new ImagePattern(image));

        addFriendButton.setOnMouseClicked(event -> {
            // Open friend list from DB ss
        });
    }

    public void TestMethod (){


    }


}


