package com.sd.chat.controllers;

import com.sd.chat.Navigator;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SigninViewController {
    @FXML
    TextField nickNameTextField;

    @FXML
    void onEnterClick() {
        String textValue = nickNameTextField.getText();
        if(textValue != null && textValue.length() > 2) {
            try {
                Navigator.setNickName(textValue);
                Pane root = Navigator.loadView("chat_view.fxml");
                var scene = new Scene(root);
                Navigator.navigate(scene, "Chat");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error occurred");
        alert.setHeaderText("Nick name must be at least 2 characters");
        alert.showAndWait();
    }
}
