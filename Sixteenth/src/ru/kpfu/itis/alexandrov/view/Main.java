package ru.kpfu.itis.alexandrov.view;

import ru.kpfu.itis.alexandrov.view.content.Content;
import ru.kpfu.itis.alexandrov.view.menu.Menu;
import ru.kpfu.itis.alexandrov.view.sidebar.Sidebar;
import ru.kpfu.itis.alexandrov.view.square.Square;
import ru.kpfu.itis.alexandrov.view.status.Status;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    public void start() {
        JFrame frame = new JFrame("Homework");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = frame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JMenuBar menu = new Menu(frame, Color.RED, new Dimension(2000, 60)).getMenu();

        menu.add(Menu.createFileMenu());
        menu.add(Menu.createViewMenu());
        menu.add(Menu.createAboutMenu(frame));

        frame.setJMenuBar(menu);

        Square content = new Square();

        content.setBackground(Color.BLUE);

        JPanel contentContainer = new JPanel();
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.X_AXIS));
        contentContainer.add(content);

        container.add(contentContainer);

        Status status = new Status(Color.magenta, new Dimension(2000, 200));
        container.add(status.getContainer());

        Sidebar sidebar = new Sidebar(Color.PINK, new Dimension(4000, 2000), status.getStatus(), content);

        contentContainer.add(sidebar.getSidebar());

        frame.setSize(new Dimension(1000, 1000));
        frame.setVisible(true);
    }

    public static JButton createMenuItem(String name, ArrayList<String> items){
        JPopupMenu popupMenu = new JPopupMenu();

        Iterator<String> iterator = items.iterator();

        while(iterator.hasNext()){
            String item = (String) iterator.next();
            JMenuItem menuItem = new JMenuItem(item);
            popupMenu.add(menuItem);
        }

        JButton btnMenu = new JButton(name);
        btnMenu.addActionListener(e -> popupMenu.show(btnMenu, 0, -popupMenu.getPreferredSize().height));

        return btnMenu;
    }
}
