package ru.kpfu.itis.Alexandrov.client.connection;

import ru.kpfu.itis.Alexandrov.client.SocketClient;
import ru.kpfu.itis.Alexandrov.client.Type;
import ru.kpfu.itis.Alexandrov.client.exceptions.ClientException;
import ru.kpfu.itis.Alexandrov.protocol.Message;

import java.io.IOException;

public class MessageAccepter implements Runnable{
    private SocketClient socket;
    private Thread thread;

    public MessageAccepter(SocketClient socket){
        this.socket = socket;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Ответ сервера
                Message message = Message.readMessage(socket.getInputStream());
                if(message.getType().equals(Type.TEXT)){
                    System.out.println(new String(message.getData()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
