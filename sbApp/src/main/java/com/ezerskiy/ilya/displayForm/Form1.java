package com.ezerskiy.ilya.displayForm;

import com.ezerskiy.ilya.reader.FileSystemReader;

import javax.swing.*;
import java.awt.*;

public class Form1 extends JFrame {
    //JFileChooser fileopen;
    JMenuItem m12;
    static JFrame frame;

    public Form1() throws HeadlessException {
        //Creating the Frame
         frame = new JFrame("my app");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        mb.add(m1);
        JMenuItem m11 = new JMenuItem("Load logs from");
        m1.add(m11);
        m12 = new JMenuItem("Open file from file system");
        m1.add(m12);

       //
       // frame.add(fileopen);


        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, mb);


        m11.addActionListener(new ReceiverFrame());
        m12.addActionListener(new FileSystemReader());

    }

    public static void main(String[] args) {
        Form1 form1 = new Form1();
        frame.setVisible(true);
    }
}
