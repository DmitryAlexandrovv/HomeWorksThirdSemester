package ru.kpfu.itis.Alexandrov.protocol;

import ru.kpfu.itis.Alexandrov.client.Type;

public class Message {
    protected final byte[] data;
    private final Type type;

    public Message(byte[] data, Type type) {
        this.data = data;
        this.type = type;
    }

    private byte[] sendText(byte[] data){
        byte[] bytes = new byte[data.length + 1];
        for(int i = 0;i < data.length;i++){
            bytes[i] = data[i];
        }
        bytes[data.length] = -1;
        return bytes;
    }

    public byte[] getBytes(Message message){
        if(message.getType() == Type.TEXT){
            return message.sendText(message.getData());
        }
        return message.getData();
    }

    public byte[] getData(){
        return data;
    }

    public Type getType(){
        return type;
    }
}
