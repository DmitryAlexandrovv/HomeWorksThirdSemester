package ru.kpfu.itis.alexandrov.view.menu;

import ru.kpfu.itis.alexandrov.view.events.ExitAction;
import ru.kpfu.itis.alexandrov.view.modal.Modal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    JMenuBar menu;

    public Menu(JFrame frame, Color color, Dimension dimension){
        menu = new JMenuBar();
        menu.setLayout(new FlowLayout(FlowLayout.LEFT));
        menu.setBackground(color);
        menu.setMaximumSize(dimension);
    }

    public static JMenu createFileMenu()
    {
        JMenu file = new JMenu("Файл");
        JMenuItem open = new JMenuItem("Открыть",
                new ImageIcon("images/open.png"));
        JMenuItem exit = new JMenuItem(new ExitAction());
        // Добавление к пункту меню изображения
        exit.setIcon(new ImageIcon("images/exit.png"));
        file.add(open);
        file.addSeparator();
        file.add(exit);

        open.addActionListener(arg0 -> System.out.println ("ActionListener.actionPerformed : open"));
        return file;
    }

    public static JMenu createAboutMenu(JFrame frame){
        JMenu about = new JMenu("About");

        JMenuItem aboutUs = new JMenuItem("About Us");

        about.add(aboutUs);

        JDialog modal = new Modal(frame).getModal();

        aboutUs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modal.setVisible(true);
            }
        });

        return about;
    }

    public static JMenu createViewMenu()
    {
        JMenu viewMenu = new JMenu("Вид");
        JCheckBoxMenuItem line  = new JCheckBoxMenuItem("Линейка");
        JCheckBoxMenuItem grid  = new JCheckBoxMenuItem("Сетка");
        JCheckBoxMenuItem navig = new JCheckBoxMenuItem("Навигация");
        JRadioButtonMenuItem one = new JRadioButtonMenuItem("Одна страница");
        JRadioButtonMenuItem two = new JRadioButtonMenuItem("Две страницы");
        ButtonGroup bg = new ButtonGroup();
        bg.add(one);
        bg.add(two);
        viewMenu.add(line);
        viewMenu.add(grid);
        viewMenu.add(navig);
        viewMenu.add( new JSeparator());
        viewMenu.add(one);
        viewMenu.add(two);
        return viewMenu;
    }

    public JMenuBar getMenu(){
        return this.menu;
    }
}
