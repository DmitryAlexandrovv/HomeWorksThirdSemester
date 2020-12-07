package ru.kpfu.itis.alexandrov.view.content;

import ru.kpfu.itis.alexandrov.view.square.Square;

import javax.swing.*;
import java.awt.*;

public class Content extends JPanel{
    JPanel content;

    public Content(Color color) {
        content = new JPanel();
        content.setBackground(color);

    }

    public JPanel getContent(){
        return this.content;
    }
}
