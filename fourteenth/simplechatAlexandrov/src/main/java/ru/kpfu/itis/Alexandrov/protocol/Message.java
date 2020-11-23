package ru.kpfu.itis.Alexandrov.protocol;

import ru.kpfu.itis.Alexandrov.client.Type;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class Message {
    protected final byte[] data;
    protected final Type type;

    public Message(Type type, byte[] data) {
        this.type = type;
        this.data = data;
    }

    public static byte[] getBytes(Message message){
        int rawMessageLength = 4 + 4 + message.getData().length;

        byte[] rawMessage = new byte[rawMessageLength];

        int j = 0;
        byte[] type = getTypeBytes(message.getType());
        for(int i = 0;i < type.length;i++){
            rawMessage[j++] = type[i];
        }

        byte[] length = ByteBuffer.allocate(4).putInt(message.getData().length).array();
        for(int i = 0;i < length.length;i++){
            rawMessage[j++] = length[i];
        }

        byte[] body = ByteBuffer.allocate(message.getData().length).put(message.getData()).array();
        for(int i = 0;i < body.length;i++){
            rawMessage[j++] = body[i];
        }
        return rawMessage;
    }

    private static byte[] getTypeBytes(Type type){
        if(type.equals(Type.TEXT)){
            return ByteBuffer.allocate(4).put(Type.TEXT.toString().getBytes()).array();
        }
        return null;
    }

    public static Message createMessage(Type type, byte[] data){
        return new Message(type, data);
    }

    public static Message readMessage(InputStream inputStream) throws IOException {
        byte[] type = new byte[4];
        byte[] length = new byte[4];
        byte[] body = new byte[50];

        inputStream.read(type, 0, 4);
        inputStream.read(length, 0, 4);
        int messageLenght = ByteBuffer.wrap(length, 0 ,4).getInt();

        inputStream.read(body, 0, messageLenght);
        ByteBuffer.wrap(body, 0, messageLenght);

        return new Message(Type.valueOf(new String("TEXT")), body);
    }

    private byte[] sendText(byte[] data){
        byte[] bytes = new byte[data.length + 1];
        for(int i = 0;i < data.length;i++){
            bytes[i] = data[i];
        }
        bytes[data.length] = -1;
        return bytes;
    }

    public byte[] getData(){
        return data;
    }

    public Type getType(){
        return type;
    }
}
