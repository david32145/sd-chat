package com.sd.chat;

import com.sd.chat.rmi.interfaces.ChatServer;
import com.sd.chat.rmi.server.ChatServerImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerApp {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(Config.RMI_PORT);
        ChatServer chatServer = new ChatServerImpl(Config.RMI_PORT);
        registry.bind(Config.REMOTE_KEY, chatServer);
        System.out.println("RMI server is running");
    }
}
