package ru.kpfu.itis.Alexandrov.client;

import ru.kpfu.itis.Alexandrov.client.exceptions.ClientException;
import ru.kpfu.itis.Alexandrov.protocol.Message;

public interface Client {
    void connect() throws ClientException;
    void sendMessage(Message message) throws ClientException;
    Message getMessage() throws ClientException;
}
