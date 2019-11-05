package sample.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private static Stage primaryStageObj;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStageObj = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/Login.fxml"));
            //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/UserAccount.fxml"));
        primaryStage.setTitle("Hippo");
        primaryStage.setScene(new Scene(root, 948, 583));
        primaryStage.setMinWidth(948);
        primaryStage.setMinHeight(583);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }

    public static Stage getStageObj (){
        return primaryStageObj;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
