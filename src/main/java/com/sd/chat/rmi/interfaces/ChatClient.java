package com.sd.chat.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    void onMessage(String nickName, String message) throws RemoteException;
    void sendMessage(String message) throws RemoteException;
    String getNickName() throws RemoteException;
    void logout() throws RemoteException;
    void onAllMessage(String messages) throws RemoteException;
}
