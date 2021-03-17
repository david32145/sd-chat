package com.sd.chat;

import com.sd.chat.rmi.interfaces.ChatClient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;

public class Navigator {
    private static Stage stage;
    private static String nickName;

    public static void setStage(Stage stage) {
        Navigator.stage = stage;
    }

    public static void navigate(Scene scene, String title) {
        stage.close();
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public static  <T> T loadView(String name) throws IOException {
        var fxmlLoader = new FXMLLoader();
        String fxmlDocPath = "./src/main/java/com/sd/chat/ui/" + name;
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
        return fxmlLoader.load(fxmlStream);
    }

    public static void setNickName(String nickName) {
        Navigator.nickName = nickName;
    }

    public static String getNickName() {
        return Navigator.nickName;
    }

    public static void setCloseButton(ChatClient chatClient) {
        stage.setOnCloseRequest((event) -> {
            try {
                chatClient.logout();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            System.exit(0);
        });
    }
}
