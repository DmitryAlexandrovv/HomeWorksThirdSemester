package ru.kpfu.itis.alexandrov.view.square;

import ru.kpfu.itis.alexandrov.view.form.MyForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Square extends JPanel{
    int i = 0;
    int stage = 0;
    boolean isDrawed = false;
    MyForm form = new MyForm();
    Timer timer;

    public Square(){
        super();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        switch (stage) {
            case 0:
                isDrawed = false;
                i = 0;
                break;
            case 1:
                g2d.setColor(Color.RED);
                g2d.fillRect(getWidth()/2, getHeight()/2, 50, 50);
                isDrawed = true;
                break;
            case 2:
                g2d.rotate(Math.toRadians(i), getWidth()/2 - 25, getHeight()/2 - 25);
                g2d.setColor(Color.RED);
                g2d.fillRect(getWidth()/2, getHeight()/2, 50, 50);
                isDrawed = false;
                break;
            case 3:
                i = 0;
                this.add(form);
                stage = 0;
                isDrawed = false;
                repaint();
                break;
        }
    }

    public void draw(){
        stage = 1;
        this.remove(form);
        this.repaint();
    }

    public void rotate(){
        stage = 2;
        this.remove(form);
        if(isDrawed) {
            ActionListener actionListener;
            timer = new Timer(10, e -> {
                this.repaint();
                i++;
            });
            timer.start();
        } else {
            if(timer != null){
                timer.stop();
                stage = 0;
                repaint();
            }
        }
    }

    public void displayForm(){
        stage = 3;
        this.remove(form);
        this.repaint();
    }
}
