package com.ezerskiy.ilya.reader;

import com.ezerskiy.ilya.displayForm.ReceiverFrame;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

public class Printer {
    final DefaultListModel listModel = new DefaultListModel();

    RandomAccessFile file;
   static RandomAccessFile instance;
   static FileReader fileReader = FileReader.getInstance();
    public int[] valuePositionCursor = new int[3];

    public Printer() {
        {
            file = getFileInstance();
        }

    }




    public void printFile(String filePath, Appendable out, Charset charsetint, int seek, DefaultListModel listModel) throws IOException {
        String line;
        int seekFlePointer;
        seekFlePointer = (int) file.getFilePointer();
        //  file.seek(seek);
        seek = seek + seek;
        System.out.println(seek);
        while ((line = file.readLine()) != null) {
            listModel.addElement(line);

            if (file.getFilePointer() > seek) {
                break;
            }
        }

    }


    public void printOneString(String filePath, DefaultListModel listModel, int seek) throws FileNotFoundException {
        String line;
        try {
            //seek = seek + (int) file.getFilePointer();
            //  file.seek(seek);
            line = file.readLine();
            listModel.addElement(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void outputText(int positionCursor, DefaultListModel listModel) throws IOException {
        String line;

        for (int i = 0; i < valuePositionCursor.length; i++) {
            if (i < valuePositionCursor.length - 1) {
                valuePositionCursor[i] = valuePositionCursor[i + 1];
            }
        }
        positionCursor = positionCursor + (int) file.getFilePointer();
        while ((line = file.readLine()) != null) {
            listModel.addElement(line);
            int seekFilePointer = (int) file.getFilePointer();
            if (file.getFilePointer() > Math.abs(positionCursor)) {
                valuePositionCursor[2] = (int) file.getFilePointer();

                for (int aValuePositionCursor : valuePositionCursor) {
                    System.out.println(aValuePositionCursor);
                }
                break;
            }
        }
    }

    public void outputTextDown( DefaultListModel listModel) throws IOException {
        String line;
        file.seek(valuePositionCursor[0]);

        while ((line = file.readLine()) != null) {

            listModel.addElement(line);
            if (file.getFilePointer() > valuePositionCursor[1]) {
                valuePositionCursor[2] = (int) file.getFilePointer();
                if(valuePositionCursor[0] != 0 ){
                    valuePositionCursor[1] = valuePositionCursor[0];
                    valuePositionCursor[0] -= 50000;
                }else {
                    valuePositionCursor[1] = 0;
                }
                if(valuePositionCursor[0] < 1000){
                    valuePositionCursor[0] = 0;
                }
               /* for (int aValuePositionCursor : valuePositionCursor) {
                    System.out.println(aValuePositionCursor);
                } */
                break;
            }
        }
    }





public static RandomAccessFile getFileInstance() {
        if (instance == null || !FileReader.frameReader.isVisible()) {
            try {
                instance = new RandomAccessFile(new ReceiverFrame().destination, "r");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;

}



}
