package ru.kpfu.itis.Alexandrov.client.connection;

import ru.kpfu.itis.Alexandrov.client.SocketClient;
import ru.kpfu.itis.Alexandrov.client.Type;
import ru.kpfu.itis.Alexandrov.client.exceptions.ClientException;
import ru.kpfu.itis.Alexandrov.protocol.Message;
import ru.kpfu.itis.Alexandrov.protocol.Protocol;
import ru.kpfu.itis.Alexandrov.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MessageSender implements Runnable{
    private SocketClient socket;
    private Thread thread;

    public MessageSender(SocketClient socket){
        this.socket = socket;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            try (PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true)) {
                while (true) {
                    // Отправка данных на сервер
                    Scanner scanner = new Scanner(System.in);
                    socket.sendMessage(new Message(scanner.nextLine().getBytes(), Type.TEXT));
                }
            }
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            try {
                if (e instanceof SocketTimeoutException) {
                    throw new SocketTimeoutException();
                } else {
                    e.printStackTrace();
                }
            } catch (SocketTimeoutException ste) {
                System.out.println("Turn off the client by timeout");
            }
        }
    }
}
