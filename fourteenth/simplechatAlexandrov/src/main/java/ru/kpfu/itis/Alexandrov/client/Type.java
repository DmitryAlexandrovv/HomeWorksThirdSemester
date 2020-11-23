package ru.kpfu.itis.Alexandrov.client;

public enum Type {
    TEXT("TEXT"),
    FILE("FILE");

    private String type;

    Type(String type) {
        this.type = type;
    }
}
