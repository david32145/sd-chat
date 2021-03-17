package com.sd.chat.rmi.client;

import com.sd.chat.rmi.interfaces.ChatClient;
import com.sd.chat.rmi.interfaces.ChatServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
    @FunctionalInterface
    public static interface MessageCallback {
        void arrived(String nickName, String message);
    }

    private String name;
    private ChatServer chatServer;
    private MessageCallback messageCallback;

    public ChatClientImpl(String name, ChatServer chatServer, MessageCallback messageCallback) throws RemoteException {
        super();
        this.name = name;
        this.chatServer = chatServer;
        this.messageCallback = messageCallback;
    }

    @Override
    public void onMessage(String nickName, String message) throws RemoteException {
        messageCallback.arrived(nickName, message);
    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        this.chatServer.sendMessage(getNickName(), message);
    }

    @Override
    public String getNickName() throws RemoteException {
        return name;
    }

    @Override
    public void logout() throws RemoteException {
        chatServer.clientLogout(this);
    }

    @Override
    public void onAllMessage(String messages) throws RemoteException {
        this.messageCallback.arrived(null, messages);
    }
}
