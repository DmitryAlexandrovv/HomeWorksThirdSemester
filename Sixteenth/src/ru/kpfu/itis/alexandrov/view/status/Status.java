package ru.kpfu.itis.alexandrov.view.status;

import javax.swing.*;
import java.awt.*;

public class Status {
    JLabel status;
    JPanel statusContainer;

    public Status(Color color, Dimension dimension){
        statusContainer = new JPanel();
        statusContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        statusContainer.setMaximumSize(dimension);
        statusContainer.setBackground(color);
        status = new JLabel();
        statusContainer.add(status);
    }

    public JPanel getContainer(){
        return statusContainer;
    }

    public JLabel getStatus(){
        return status;
    }
}
