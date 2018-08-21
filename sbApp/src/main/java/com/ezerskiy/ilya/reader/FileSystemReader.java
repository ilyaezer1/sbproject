package com.ezerskiy.ilya.reader;

import com.ezerskiy.ilya.displayForm.ListenAdditionScrolled;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileSystemReader implements ActionListener {
    JFileChooser fileopen;
    FileReader fileReader;
    Printer printer = new Printer();
    public File file;

    public FileSystemReader() {

        fileopen = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fileReader = new FileReader();

        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            file = fileopen.getSelectedFile();

            //  fileReader.scrollV.getViewport().addChangeListener(new ListenAdditionScrolled(fileReader.scrollV, fileReader.listModel));

            try {
                printer.printFile(file.getPath(), System.out, Charset.defaultCharset(), 5000, fileReader.listModel);
                fileReader.scrollV.getViewport().addChangeListener(new ListenAdditionScrolled(fileReader.scrollV, fileReader.listModel, file.getPath()));

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            //fileReader.readMappedFile();


        }

    }
}
