package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.ImagePattern;


import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import sample.Model.User;
import sample.Service.UserService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class UserAccountController implements Initializable {

    private User user;
    private UserService userService;


    @FXML
    private Label NickNameLabel;

    @FXML
    private Label timeNow;

    @FXML
    private Circle userAvatar;

    @FXML
    public FlowPane flowPaneFriend;

    @FXML
    public TextField textField;

    @FXML
    public TextFlow textFlow;

    public UserAccountController (User user,UserService userService){
        this.user = user;
        this.userService = userService;
    }

    @FXML
    void sendMessageEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER) && testString(textField.getText())){
            textFlow.getChildren().add(new Text(user.getNickName() + ": " + textField.getText() + "\n"));
            System.out.println(textField.getText());
            textField.setText("");
            //TODO Сhecking a string for spaces
        }
    }

    private boolean testString(final String str){
        if (!str.trim().equals(""))
            return true;
        else
            return false;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NickNameLabel.setText(user.getNickName());
        initializeTime();
        initializeAvatar();
        try {
            initializeFriends();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void initializeTime() {
        Date now = new Date();
        String time;
        timeNow.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(now));
    }

    private void initializeAvatar() {
        Image image = new Image("/Image/kakashi.jpg", false);
        userAvatar.setFill(new ImagePattern(image));
    }

    private void initializeFriends() throws SQLException {
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
            if (!user.getNickName().equals(userService.getAll().get(i).getNickName())){
                flowPaneFriend.getChildren().add(parent);
            }

        }
    }
}



