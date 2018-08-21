package com.ezerskiy.ilya.com.ezerskiy.search;

import com.ezerskiy.ilya.displayForm.ListenAdditionScrolled;
import com.ezerskiy.ilya.reader.FileReader;
import com.ezerskiy.ilya.reader.Printer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Searcher implements ActionListener {
    //FormSearchIndicator formSearchIndicator = new FormSearchIndicator();
    static Printer printer = new Printer();
    static RandomAccessFile file;
    static ArrayList<Integer> positionList = new ArrayList<>();
    int currentPosition = 0;
    static FileReader fileReader = FileReader.getInstance();
    private static Searcher instance;
    static int comeBack = 0;


    @Override
    public void actionPerformed(ActionEvent e) {
        int counter = 0;
        FileReader.spinInt.addChangeListener(Searcher.spinnerListener);
        String line;
        file = Printer.getFileInstance();
        String wordSearch = String.valueOf(fileReader.searchTextField.getText());
        //int startPosition = Printer.valuePositionCursor[1];
        int startPosition = ListenAdditionScrolled.startPointer;
        int currentPosition = startPosition;
        try {
            file.seek(startPosition);
            while ((line = file.readLine()) != null) {
                currentPosition = (int) file.getFilePointer();
                String[] sentence = line.split(" ");
                for (String word : sentence) {
                    if (word.equals(wordSearch)) {
                        positionList.add(currentPosition);
                        counter++;
                    }
                }
                if (counter == 100) {
                    counter = 0;
                    break;
                }

            }
            int getPositionFromList = (Integer) fileReader.spinInt.getValue();
            int position = positionList.get(getPositionFromList - 1);
            printSearchableText(position, fileReader.listModel);


        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }

    public static void printSearchableText(int position, DefaultListModel listModel) throws IOException {
        listModel.removeAllElements();
        String line;
        file.seek(position);
        while ((line = file.readLine()) != null) {
            listModel.addElement(line);
            int filePointer = (int) file.getFilePointer();
            if (filePointer > positionList.get(99 + comeBack)) {
                break;
            }
        }
    }

    public static ChangeListener spinnerListener = e -> {
        System.out.println("Нажал");
        JSpinner js = (JSpinner) e.getSource();
        int position = positionList.get((Integer) js.getValue() - 1 + comeBack);
        try {
            printSearchableText(position, fileReader.listModel);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    };

    public static ActionListener btnBackListener = e ->
    {
        int counter = 0;
        FileReader.spinInt.setValue(1);
        String line;
        String wordSearch = String.valueOf(fileReader.searchTextField.getText());
        System.out.println("Нажата кнопка вперед");
        int startPosition = positionList.get(99 + comeBack);
        comeBack -= 100;

        int currentPosition = startPosition;
        try {
            file.seek(startPosition);
            //positionList.clear();
            while ((line = file.readLine()) != null) {
                currentPosition = (int) file.getFilePointer();
                String[] sentence = line.split(" ");
                for (String word : sentence) {
                    if (word.equals(wordSearch)) {
                        positionList.add(currentPosition);
                        System.out.println(line);
                        counter++;
                    }
                }
                if (counter == 100) {
                    counter = 0;
                    System.out.println(file.getFilePointer() + "last position");
                    break;
                }

            }
            int getPositionFromList = (Integer) fileReader.spinInt.getValue();
            int position = positionList.get(getPositionFromList - 1 + comeBack);
            printSearchableText(position, fileReader.listModel);


        } catch (IOException e1) {
            e1.printStackTrace();
        }


        System.out.println("Нажата кнопка назад");
    };

    public static ActionListener btnForwardListener = e -> {
        int counter = 0;
        FileReader.spinInt.setValue(1);
        String line;
        String wordSearch = String.valueOf(fileReader.searchTextField.getText());
        System.out.println("Нажата кнопка вперед");
        int startPosition = positionList.get(99 + comeBack);
        comeBack += 100;

        int currentPosition = startPosition;
        try {
            file.seek(startPosition);
            //positionList.clear();
            while ((line = file.readLine()) != null) {
                currentPosition = (int) file.getFilePointer();
                String[] sentence = line.split(" ");
                for (String word : sentence) {
                    if (word.equals(wordSearch)) {
                        positionList.add(currentPosition);
                        System.out.println(line);
                        counter++;
                    }
                }
                if (counter == 100) {
                    counter = 0;
                    System.out.println(file.getFilePointer() + "last position");
                    break;
                }

            }
            int getPositionFromList = (Integer) fileReader.spinInt.getValue();
            int position = positionList.get(getPositionFromList - 1 + comeBack);
            printSearchableText(position, fileReader.listModel);


        } catch (IOException e1) {
            e1.printStackTrace();
        }

    };

    public static ActionListener btnCancelListener = e -> {
        System.out.println("Нажата кнопка отмена");
        FileReader.spinInt.setValue(1);
        fileReader.listModel.removeAllElements();
        fileReader.searchTextField.setText("");
        positionList.clear();
        comeBack = 0;
     /*   for (int i = 0; i < Printer.valuePositionCursor.length; i++) {
            Printer.valuePositionCursor[i] = 0;
        } */
        try {
            file.seek(0);
            ListenAdditionScrolled.startPointer = (int) file.getFilePointer();
            printer.outputText(50000, fileReader.listModel);
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    };

    public static Searcher getInstance() {
        if ((instance == null)) {
            instance = new Searcher();
        }
        return instance;
    }
}
