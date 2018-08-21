package com.ezerskiy.ilya.displayForm;

import com.ezerskiy.ilya.reader.FileReader;
import com.ezerskiy.ilya.reader.Printer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.util.ArrayList;

public class ListenAdditionScrolled implements ChangeListener {
    JScrollPane ScrollV;
    JList list = new JList();
    DefaultListModel listModel;
    Printer printer = new Printer();
    String pathToFile;
    FileReader fileReader;
    int seek;
    public static int startPointer = 0;

    public ListenAdditionScrolled(JScrollPane scrollPane, DefaultListModel modelList, String path) {
        listModel = modelList;
        this.ScrollV = scrollPane;
        this.pathToFile = path;
        fileReader = FileReader.getInstance();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int extent = ScrollV.getVerticalScrollBar().getModel().getExtent();
        if ("".equals(fileReader.searchTextField.getText())) {
            if ((ScrollV.getVerticalScrollBar().getValue() + extent) == ScrollV.getVerticalScrollBar().getMaximum()
                    && ScrollV.getVerticalScrollBar().getValue() != 0) {
                try {
                    listModel.removeAllElements();
                    startPointer = (int) fileReader.file.getFilePointer();
                    printer.outputText(50000, listModel);
                    ScrollV.getVerticalScrollBar().setValue(1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        try {
            int check = (int) fileReader.file.getFilePointer();
            String check1 = FileReader.searchTextField.getText();
            if ("".equals(fileReader.searchTextField.getText())) {
                if ((ScrollV.getVerticalScrollBar().getValue()) == 0 && fileReader.file.getFilePointer() > 60000) {
                    // System.out.println(fileReader.file.getFilePointer() + " сейчас");
                    listModel.removeAllElements();

                    printer.outputTextDown(listModel);
                    ScrollV.getVerticalScrollBar().setValue(ScrollV.getVerticalScrollBar().getMaximum() - extent - 1);
                    startPointer = (int) fileReader.file.getFilePointer();
                    System.out.println(startPointer + " startPointer ");
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }

}
