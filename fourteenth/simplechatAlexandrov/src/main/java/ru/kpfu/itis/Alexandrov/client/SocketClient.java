package ru.kpfu.itis.Alexandrov.client;

import ru.kpfu.itis.Alexandrov.client.exceptions.ClientException;
import ru.kpfu.itis.Alexandrov.protocol.Message;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient implements Client{
    protected final InetAddress address;
    protected final int port;
    protected Socket socket;

    public SocketClient(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    @Override
    public void connect() throws ClientException {
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            throw new ClientException("Can't connect", e);
        }
    }

    @Override
    public void sendMessage(Message message) throws ClientException{
        try {
            socket.getOutputStream().write(Message.getBytes(message));
            socket.getOutputStream().flush();
        } catch (IOException e) {
            throw new ClientException("Can't connect", e);
        }
    }

//    @Override
//    public Message getMessage() throws ClientException {
//        try {
//            InputStream fromServer = socket.getInputStream();
//            int b;
//            StringBuffer line = new StringBuffer();
//            while((b = fromServer.read()) != 255){
//                line.append((char) b);
//            }
//            return new Message(line.toString().getBytes(), Type.TEXT);
//        } catch (IOException e) {
//            throw new ClientException("Can't connect", e);
//        }
//    }
}
