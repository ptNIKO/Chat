package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Model.User;
import sample.Service.UserService;


import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;

import java.io.IOException;

public class LogInController {

    private User user;
    private UserService userService;

    private double xOffset;
    private double yOffset;

    public LogInController(){
        user = new User();
        userService = new UserService();
    }

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblErrors;

    @FXML
    private Button reg;

    @FXML
    private Button next;


    @FXML
    private Pane borderPanel;

    @FXML
    private ImageView closeButton;

    @FXML
    void initialize () throws IOException {

        reg.setOnMouseClicked(event -> {
            Parent tableViewParent = null;
            try {
                tableViewParent = FXMLLoader.load(getClass().getResource("/View/Reg.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene tableViewScene = new Scene (tableViewParent);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();

        });

        borderPanel.setOnMousePressed(event -> {
            xOffset =  Main.getStageObj().getX() - event.getScreenX();
            yOffset =  Main.getStageObj().getY() - event.getScreenY();
        });

        borderPanel.setOnMouseDragged(event -> {
            Main.getStageObj().setX(event.getScreenX() + xOffset);
            Main.getStageObj().setY(event.getScreenY() + yOffset);
        });

        closeButton.setOnMouseClicked(event -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        });

        //Фокус курсора при запуске
        txtPassword.setFocusTraversable(false);
        txtUsername.setFocusTraversable(false);
    }

    @FXML
    void handlerLogin(ActionEvent event){
        user = userService.GetUser(txtUsername.getText().toString(),txtPassword.getText().toString());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UserAccount.fxml"));
        Parent parent = null;
        try {
            parent = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        UserAccountController userAccountController = (UserAccountController) loader.getController();
        userAccountController.setUser(user);
        stage.setTitle("OMG");
        stage.setScene(new Scene(parent));
        stage.show();

    }

}
