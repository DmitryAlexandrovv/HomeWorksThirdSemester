package ru.kpfu.itis.Alexandrov.server.connection;

import ru.kpfu.itis.Alexandrov.client.SocketClient;
import ru.kpfu.itis.Alexandrov.client.exceptions.ClientException;
import ru.kpfu.itis.Alexandrov.protocol.Message;
import ru.kpfu.itis.Alexandrov.server.Server;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Iterator;

public class Connection implements Runnable {
    private Socket socket;
    private Thread thread;
    private Server server;

    public Connection(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        try{
            while (true) {
                // Получение данных от клиента.
                Message response = Message.readMessage(socket.getInputStream());

                // Ответ клиенту.
                Iterator<Connection> iterator = server.connectionsIterator();
                while(iterator.hasNext()){
                    Connection connection = iterator.next();
                    if (!connection.equals(this)) {
                        sendMessage(response, connection.getSocket());
                    }
                }
            }
        } catch (IOException e) {
            try {
                if (e instanceof SocketTimeoutException) {
                    throw new SocketTimeoutException();
                } else {
                    e.printStackTrace();
                }
            } catch (SocketTimeoutException ste) {
                System.out.println("Turn off the server by timeout");
            }
        }
    }

    public void sendMessage(Message message, Socket socket) {
        try {
            socket.getOutputStream().write(Message.getBytes(message));
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }
}