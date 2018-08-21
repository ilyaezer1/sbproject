package com.ezerskiy.ilya.com.ezerskiy.search;

import com.ezerskiy.ilya.reader.FileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.File;

public class FormSearchIndicator implements ActionListener {
    public static JFrame frameSearch = new JFrame("Search");
   public static JTextField searchField = new JTextField(15);

    @Override
    public void actionPerformed(ActionEvent e) {
       /* frameSearch.setSize(200,120);
        frameSearch.setResizable(false);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel searchLabel = new JLabel("Enter word for search:");


        panel.add(searchLabel, BorderLayout.NORTH);
        panel.add(searchField, BorderLayout.CENTER);


        JButton searchButton = new JButton("Search");
        panel.add(searchButton, BorderLayout.SOUTH);


        frameSearch.setContentPane(panel);

        searchButton.addActionListener(new Searcher()); */
        //FileReader.spinInt.addChangeListener(Searcher.spinnerListener);
        //FileReader.btnBack.addActionListener(Searcher.btnBackListener);
        frameSearch.setVisible(true);

        //FileReader.spinInt.addChangeListener(Searcher.spinnerListener);




    }
}
