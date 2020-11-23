package ru.kpfu.itis.Alexandrov.client;

import ru.kpfu.itis.Alexandrov.client.connection.MessageAccepter;
import ru.kpfu.itis.Alexandrov.client.connection.MessageSender;
import ru.kpfu.itis.Alexandrov.client.exceptions.ClientException;
import ru.kpfu.itis.Alexandrov.protocol.Protocol;

import java.io.*;
import java.net.*;

public class AppClient {
    public AppClient() throws IOException {
        init();
    }

    public void init() throws IOException {
        try {
            SocketClient socket = new SocketClient(InetAddress.getLocalHost(), Protocol.PORT);
            socket.connect();
            MessageSender sender = new MessageSender(socket);
            MessageAccepter accepter = new MessageAccepter(socket);
            while (true) {

            }
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Throwable {
        AppClient client = new AppClient();
    }
}
