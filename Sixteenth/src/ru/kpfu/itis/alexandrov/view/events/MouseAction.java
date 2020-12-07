package ru.kpfu.itis.alexandrov.view.events;

import ru.kpfu.itis.alexandrov.view.square.Square;
import ru.kpfu.itis.alexandrov.view.type.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.IdentityScope;

public class MouseAction implements MouseListener {
    JLabel status;
    String description;
    Type type;
    Square square;

    public MouseAction(JLabel status, String description, Type type){
        this.status = status;
        this.description = description;
        this.type = type;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        status.setText(description);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        status.setText(description);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        status.setText(description);
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
