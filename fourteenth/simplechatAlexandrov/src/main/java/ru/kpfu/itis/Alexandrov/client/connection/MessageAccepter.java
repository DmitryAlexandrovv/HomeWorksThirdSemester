package ru.kpfu.itis.Alexandrov.client.connection;

import ru.kpfu.itis.Alexandrov.client.SocketClient;
import ru.kpfu.itis.Alexandrov.client.Type;
import ru.kpfu.itis.Alexandrov.client.exceptions.ClientException;
import ru.kpfu.itis.Alexandrov.protocol.Message;

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
                Message message = socket.getMessage();
                if(message.getType().equals(Type.TEXT)){
                    System.out.println(new String(message.getData()));
                }
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
