package com.sd.chat.clients;

import com.sd.chat.Navigator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class ClientApp1 extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Navigator.setStage(stage);
        Pane root = Navigator.loadView("signin_view.fxml");
        Scene scene = new Scene(root);
        Navigator.navigate(scene, "Sign In");
    }

    public static void main(String[] args) {
        launch();
    }

}