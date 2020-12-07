package ru.kpfu.itis.alexandrov.view.sidebar;

import ru.kpfu.itis.alexandrov.view.events.MouseAction;
import ru.kpfu.itis.alexandrov.view.form.MyForm;
import ru.kpfu.itis.alexandrov.view.square.Square;
import ru.kpfu.itis.alexandrov.view.type.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class Sidebar{
    public JPanel buttonsPanel;
    private GridBagLayout gbl;
    private GridBagConstraints c;
    private ArrayList<JButton> sidebarItems;
    private JLabel status;
    Square square;

    public Sidebar(Color color, Dimension dimension, JLabel status, Square square){
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(color);
        buttonsPanel.setMaximumSize(dimension);
        gbl = new GridBagLayout();
        c = new GridBagConstraints();

        this.status = status;

        sidebarItems = new ArrayList<>();

        this.square = square;

        setLayout();

        setItems();
        addItems(sidebarItems);
    }

    private void setItems(){
        sidebarItems.add(createBtnsPanelItem("Draw", "I draw a square", Type.DRAW));
        sidebarItems.add(createBtnsPanelItem("Rotate", "I rotate the square", Type.ROTATE));
        sidebarItems.add(createBtnsPanelItem("Display form", "Displaying the form", Type.DISPLAY_FORM));
    }

    private void addItems(ArrayList<JButton> items){
        Iterator<JButton> iterator = items.iterator();

        while(iterator.hasNext()){
            JButton item = iterator.next();
            gbl.setConstraints(item, c);
            buttonsPanel.add(item);
        }
    }

    private JButton createBtnsPanelItem(String name, String description, Type type){
        JButton item = new JButton(name);
        switch (type){
            case DRAW:
                item.addActionListener(e -> {
                    square.draw();
                });
                break;
            case ROTATE:
                item.addActionListener(e -> {
                    square.rotate();
                });
                break;
            case DISPLAY_FORM:
                item.addActionListener(e -> {
                    square.displayForm();
                });
                break;
        }
        item.addMouseListener(new MouseAction(status, description, type));
        return item;
    }

    public JPanel getSidebar(){
        return this.buttonsPanel;
    }

    public GridBagConstraints getConstraints(){
        return c;
    }

    private void setLayout(){
        buttonsPanel.setLayout(gbl);

        c.anchor = GridBagConstraints.NORTH;
        c.fill   = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(40, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
    }
}
