package com.sd.chat.rmi.server;

import com.sd.chat.rmi.interfaces.ChatClient;
import com.sd.chat.rmi.interfaces.ChatServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
    private List<ChatClient> clients;
    private StringBuilder messages;

    public ChatServerImpl(int port) throws RemoteException {
        super(port);
        this.clients = new ArrayList<>();
        this.messages = new StringBuilder("");
    }

    @Override
    public void sendMessage(String nickName, String message) throws RemoteException {
        broadcastMessage(nickName, message);
    }

    @Override
    public void clientArrived(ChatClient client) throws RemoteException {
        this.clients.add(client);
        client.onAllMessage(this.messages.toString());
        broadcastMessage("@Server", "new client arrived " + client.getNickName());
    }

    @Override
    public void clientLogout(ChatClient chatClient) throws RemoteException {
        List<ChatClient> newClients = new ArrayList<>();
        for (ChatClient client : clients) {
            if (!client.getNickName().equals(chatClient.getNickName())) {
                newClients.add(client);
            }
        }
        this.clients = newClients;
        broadcastMessage("@Server", chatClient.getNickName() + " left");
    }

    private void broadcastMessage(String nickName, String message) throws RemoteException {
        this.messages.append(nickName + ": " + message).append("\n");
        for (ChatClient client : clients) {
            client.onMessage(nickName, message);
        }
    }
}
