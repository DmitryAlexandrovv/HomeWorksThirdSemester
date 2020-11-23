package ru.kpfu.itis.Alexandrov.server;

import ru.kpfu.itis.Alexandrov.protocol.Protocol;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ru.kpfu.itis.Alexandrov.server.connection.Connection;

public class Server {
    private List<Connection> connections;

    public Server() throws IOException {
        connections = new ArrayList<>();
        init();
    }

    public void init() throws IOException {
        ServerSocket s1 = new ServerSocket(Protocol.PORT);
        while (true) {
            Socket client = s1.accept();
            connections.add(new Connection(this, client));
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }

    public Iterator<Connection> connectionsIterator(){
        return connections.iterator();
    }
}
