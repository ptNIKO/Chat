package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

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
