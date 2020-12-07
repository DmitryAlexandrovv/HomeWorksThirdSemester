package ru.kpfu.itis.alexandrov.view.form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyForm extends JPanel {
    private JTextField textField = new JTextField("Please enter yout name");
    private JButton doneBtn = new JButton("Done");

    public MyForm() {
        super();
        this.add(textField);
        this.add(doneBtn);

        doneBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.out.println(textField.getText()); // Or do whatever you like
            }
        });
    }

    // rest of your form
}
