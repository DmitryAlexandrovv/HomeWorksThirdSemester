package ru.kpfu.itis;

import ru.kpfu.itis.protocol.Protocol;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Throwable {

        System.out.println("Starting server...");
        System.out.println("Starting to listen port " + Protocol.PORT);

        try{
            ServerSocket serverSocket = new ServerSocket(Protocol.PORT);
            serverSocket.setSoTimeout(20000);
            while (true) {
                // Подключение к порту. По сути, начало работы сервера.
                Socket server = serverSocket.accept();
                // Получение данных от клиента.
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(server.getInputStream()));
                String line = fromClient.readLine();
                // Ответ клиенту.
                PrintWriter toClient = new PrintWriter(server.getOutputStream(), true);
                toClient.println(getData(line));
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
                System.out.println("Turn off the server by timeout");
            }
        }
    }

    public static String getData(String url){
        try {
            URL api = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)api.openConnection();
            int code = connection.getResponseCode();
            if(code == 200){
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()));

                String inputLine;

                while ((inputLine = br.readLine()) != null)

                return inputLine;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
