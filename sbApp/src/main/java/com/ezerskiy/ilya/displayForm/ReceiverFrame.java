package com.ezerskiy.ilya.displayForm;

import com.ezerskiy.ilya.reader.FileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


public class ReceiverFrame extends Frame implements ActionListener
{
    private TextField tf;
     Button button;
    private JFrame frame1 = new JFrame("my app");
    private String url;
    public String destination = "D:\\file.log";

    public ReceiverFrame()
    {
        //Creating the Frame

        frame1.setSize(400, 400);
        frame1.setLocationRelativeTo(null);
        frame1.setLayout(new GridLayout(3,1));
        JPanel contents = new JPanel();

        SpinnerModel sm = new SpinnerNumberModel(0, 0, 10, 1); //default value,lower bound,upper bound,increment by
        JSpinner spinner = new JSpinner(sm);


        Label label = new Label("Count of servers");
        add(label);

        contents.add(label);
        contents.add(spinner);

        tf = new TextField(10);
        contents.add(tf);

        button = new Button("Load logs");
        contents.add(button);

        frame1.setContentPane(contents);
        button.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        frame1.setVisible(true);
        if (e.getSource() == button)
        {
            url = tf.getText();
            // качаем файл с помощью NIO
            try {
                downloadFileFromURL(url, destination);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            frame1.dispose();
            //button.addActionListener(new FileReader());
            button.addActionListener(FileReader.getInstance());

        }



    }
    // качаем файл с помощью Stream
    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }

    public static void downloadFileFromURL(String urlString, String destination) throws IOException {
        URL website = new URL(urlString);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(destination);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

}