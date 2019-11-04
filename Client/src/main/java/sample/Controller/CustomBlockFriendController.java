package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

import java.io.IOException;

public class CustomBlockFriendController {

    private String name;

    public void setName(String name) {
        this.nameLabel.setText(name);
    }

    @FXML
    private Text nameLabel;

    @FXML
    public void initialize(){


    }

}
