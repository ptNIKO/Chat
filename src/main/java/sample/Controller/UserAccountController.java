package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.ImagePattern;


import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Model.User;
import sample.Service.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class UserAccountController {

    private User user;
    private UserService userService = new UserService();;


        /*
        public UserAccountController(User user,UserService userService){
            this.user = user;
            this.userService = userService;

            Нельзя передавать в конструктор не знаю почему я читал много статей
        }*/


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
    private Text addFriendButton;

    @FXML
    public FlowPane flowPaneFriend;

    @FXML
    public void initialize() throws IOException, SQLException {

        Date now = new Date();
        String time;
        timeNow.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(now));

        Image image = new Image("/Image/kakashi.jpg", false);
        userAvatar.setFill(new ImagePattern(image));

        addFriendButton.setOnMouseClicked(event -> {
            // Open friend list from DB ss
        });


        for (int i = 0; i < userService.getAll().size(); i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CustomBlockFriend.fxml"));
            Parent parent = null;
            try {
                parent = (Parent) loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            CustomBlockFriendController customBlockFriendController = (CustomBlockFriendController) loader.getController();
            customBlockFriendController.setName(userService.getAll().get(i).getNickName());
            flowPaneFriend.getChildren().add(parent);
        }

    }

}






