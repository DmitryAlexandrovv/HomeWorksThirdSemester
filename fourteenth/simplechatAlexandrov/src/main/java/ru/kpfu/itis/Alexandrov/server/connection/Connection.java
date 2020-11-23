package ru.kpfu.itis.Alexandrov.server.connection;

import com.sun.org.apache.xpath.internal.operations.String;
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
                InputStream fromClient = socket.getInputStream();
                int b;
                StringBuffer line = new StringBuffer();

                while((b = fromClient.read()) != 255){
                    line.append((char) b);
                }

                // Ответ клиенту.
                Iterator<Connection> iterator = server.connectionsIterator();
                while(iterator.hasNext()){
                    Connection connection = iterator.next();
                    if (!connection.equals(this)) {
                        OutputStream os = connection.getSocket().getOutputStream();

                        byte[] data = line.toString().getBytes();
                        byte[] bytes = new byte[data.length + 1];
                        for(int i = 0;i < data.length;i++){
                            bytes[i] = data[i];
                        }
                        bytes[data.length] = -1;

                        os.write(bytes);
                        os.flush();
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

    public Socket getSocket() {
        return socket;
    }
}