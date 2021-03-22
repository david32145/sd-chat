package com.sd.chat.controllers;

import com.sd.chat.Config;
import com.sd.chat.Navigator;
import com.sd.chat.rmi.client.ChatClientImpl;
import com.sd.chat.rmi.interfaces.ChatClient;
import com.sd.chat.rmi.interfaces.ChatServer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    @FXML
    TextArea chatTextArea;

    @FXML
    Label chatLabel;

    @FXML
    TextField messageTextField;

    StringBuilder messages = new StringBuilder("");

    ChatClient chatClient;

    @FXML
    void onSendClick() throws RemoteException {
        String text = messageTextField.getText();
        if (text != null && text.length() > 2) {
            chatClient.sendMessage(text);
            messageTextField.clear();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error occurred");
        alert.setHeaderText("Message must be at least 3 characters");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chatLabel.setText("Chat Messages - " + Navigator.getNickName());
        chatTextArea.setEditable(false);
        try {
            Registry registry = LocateRegistry.getRegistry(Config.RMI_PORT);
            ChatServer chatServer = (ChatServer) registry.lookup(Config.REMOTE_KEY);
            ChatClient chatClient = new ChatClientImpl(Navigator.getNickName(), chatServer, this::onMessage);
            chatServer.clientArrived(chatClient);
            this.chatClient = chatClient;
            Navigator.setCloseButton(chatClient);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private void onMessage(String nickName, String message) {
        if(nickName == null) {
            this.messages.append(message);
        } else {
            this.messages
                    .append(nickName + ": " + message)
                    .append("\n");
        }
        this.chatTextArea.setText(messages.toString());
    }
}
