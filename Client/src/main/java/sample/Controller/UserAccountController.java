package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;


import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import sample.model.User;
import sample.service.UserService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserAccountController implements Initializable {

    private User user;
    private UserService userService;

    private double xOffset;
    private double yOffset;

    @FXML
    private Label NickNameLabel;

    @FXML
    private Circle userAvatar;

    @FXML
    public FlowPane flowPaneFriend;

    @FXML
    public TextField textField;

    @FXML
    public TextFlow textFlow;

    @FXML
    private Pane windowMove;

    @FXML
    public Circle closeButton;

    @FXML
    public Circle maximazeButton;

    @FXML
    public Circle minimizeButton;

    @FXML
    public Button joinChatButton;


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
        WindowMovement();
        NickNameLabel.setText(user.getNickName());
        initializeAvatar();
        try {
            initializeFriends();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private boolean windowSizeChecker = true;

    private void WindowMovement() {

        windowMove.setOnMousePressed(event -> {
            xOffset =  Main.getStageObj().getX() - event.getScreenX();
            yOffset =  Main.getStageObj().getY() - event.getScreenY();
        });

        windowMove.setOnMouseDragged(event -> {
            Main.getStageObj().setX(event.getScreenX() + xOffset);
            Main.getStageObj().setY(event.getScreenY() + yOffset);
        });

        closeButton.setOnMouseClicked(event -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        });

        maximazeButton.setOnMouseClicked(event -> {
            Stage stage = (Stage) maximazeButton.getScene().getWindow();
            if (windowSizeChecker){
                stage.setMaximized(true);
                windowSizeChecker = false;
            } else {
                stage.setMaximized(false);
                windowSizeChecker = true;
            }

        });

        minimizeButton.setOnMouseClicked(event -> {
            Stage stage = (Stage) minimizeButton.getScene().getWindow();
            stage.setIconified(true);
        });

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



