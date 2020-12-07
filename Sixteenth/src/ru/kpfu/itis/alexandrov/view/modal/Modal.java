package ru.kpfu.itis.alexandrov.view.modal;

import javax.swing.*;

public class Modal {
    JDialog modal;

    public Modal(JFrame frame){
        modal = new JDialog(frame, "About Us", true);
        modal.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        modal.setSize(700, 900);
    }

    public JDialog getModal(){
        return modal;
    }
}
