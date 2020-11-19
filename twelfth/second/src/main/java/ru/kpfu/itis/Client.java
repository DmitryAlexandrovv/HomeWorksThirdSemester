package ru.kpfu.itis;

import jdk.management.resource.internal.inst.ServerSocketChannelImplRMHooks;
import ru.kpfu.itis.protocol.Protocol;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Client {
    public static void main(String[] args) throws Throwable {

        String url = "https://api.covid19api.com/";

        System.out.println("Starting client...");
        try (Socket socket = new Socket(InetAddress.getLocalHost(), Protocol.PORT)) {
            socket.setSoTimeout(10000);
            try (PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true)) {
                // Отправка данных на сервер
                toServer.println(url);
                // Ответ сервера
                BufferedReader fromServer;
                fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = fromServer.readLine();
                System.out.println(line);
                fromServer.close();
            }
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
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
