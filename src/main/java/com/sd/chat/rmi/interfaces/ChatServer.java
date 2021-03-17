package com.sd.chat.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote {
    void sendMessage(String nickName, String message) throws RemoteException;
    void clientArrived(ChatClient client) throws RemoteException;
    void clientLogout(ChatClient chatClient) throws RemoteException;
}
