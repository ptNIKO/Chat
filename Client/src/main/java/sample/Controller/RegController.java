package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.model.User;
import sample.service.UserService;

import java.io.IOException;


public class RegController {

    private User user;
    private UserService userService;

    public RegController(){
        user = new User();
        userService = new UserService();
    }

    @FXML
    private TextField loginTextField;

    @FXML
    private Button ButtonRegister;

    @FXML
    private Label ButtonBack;

    @FXML
    private TextField nickTextField;

    @FXML
    private Label lblTest;

    @FXML
    private PasswordField passwordPasField;

    @FXML
    private ImageView closeButton;



    @FXML
    void initialize () throws IOException {
        ButtonBack.setOnMouseClicked(event -> {

            Parent tableViewParent = null;
            try {
                tableViewParent = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene tableViewScene = new Scene (tableViewParent);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();

        });

        ButtonBack.setOnMouseEntered(event -> {
            ButtonBack.setFont(Font.font("Century Gothic", 20));

        });

        ButtonBack.setOnMouseExited(event -> {
            ButtonBack.setFont(Font.font("Century Gothic", 17));

        });


        closeButton.setOnMouseClicked(event -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        });

        loginTextField.setFocusTraversable(false);

    }

    @FXML
    void handlerReg(ActionEvent event) {
        user.setLogin(loginTextField.getText().toString());
        user.setPassword(passwordPasField.getText().toString());
        user.setNickName(nickTextField.getText().toString());
        userService.AddUser(user);
    }


}
