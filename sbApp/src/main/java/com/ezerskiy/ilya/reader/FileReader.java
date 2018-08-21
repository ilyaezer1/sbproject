package com.ezerskiy.ilya.reader;

import com.ezerskiy.ilya.com.ezerskiy.search.FormSearchIndicator;
import com.ezerskiy.ilya.com.ezerskiy.search.Searcher;
import com.ezerskiy.ilya.displayForm.ListenAdditionScrolled;
import com.ezerskiy.ilya.displayForm.ReceiverFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.*;

import java.nio.charset.Charset;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class FileReader implements ActionListener {

    public static JFrame frameReader = new JFrame("Printer");
    ReceiverFrame receiverFrame = new ReceiverFrame();
    public JScrollPane scrollV;
    public final DefaultListModel listModel = new DefaultListModel();
    final JList list = new JList(listModel);
    //ListenAdditionScrolled listenAdditionScrolled = new ListenAdditionScrolled(scrollV, listModel);
    Printer printer = new Printer();
    public RandomAccessFile file = Printer.getFileInstance();
    private static FileReader instance;
    static public JSpinner spinInt;
    //Searcher searcher = Searcher.getInstance();
    public static JButton btnBack;
    public static JButton btnForward;
    public static JTextField searchTextField;
    public static JButton btnCancel;
    public static JTextField pagTextField;


    public FileReader() {
        frameReader.setSize(500, 500);
        JPanel contents = new JPanel();
        contents.setLayout(new BorderLayout());

        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        mb.add(m1);
        JMenuItem m11 = new JMenuItem("ReadFile");
        m1.add(m11);
        contents.add(mb, BorderLayout.NORTH);
        scrollV = new JScrollPane(list);
        contents.add(scrollV, BorderLayout.CENTER);


        SpinnerModel spinnerNumbers = new SpinnerNumberModel(1, 1, 100, 1);
        spinInt = new JSpinner(spinnerNumbers);
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinInt.getEditor();
        editor.getTextField().setEditable(false);


        JLabel label = new JLabel("Поиск строки :");
        searchTextField = new JTextField();
        btnBack = new JButton("Back");
        btnForward = new JButton("Forward");
        JButton btnFind = new JButton("Найти");
        btnCancel = new JButton("Отменить");



        JPanel searchPanel = new JPanel();
        GroupLayout layout = new GroupLayout(searchPanel);
        searchPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        // Создание горизонтальной группы
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(searchTextField)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(LEADING)
                                        .addComponent(btnBack)
                                        .addComponent(spinInt))
                                .addGroup(layout.createParallelGroup(LEADING)
                                        .addComponent(btnForward))))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(btnFind)
                        .addComponent(btnCancel))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, btnFind, btnCancel);

        // Создание вертикальной группы
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(label)
                        .addComponent(searchTextField)
                        .addComponent(btnFind))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(BASELINE)
                                        .addComponent(btnBack)
                                        .addComponent(btnForward))
                                .addGroup(layout.createParallelGroup(BASELINE)
                                        .addComponent(spinInt)))
                        .addComponent(btnCancel))
        );

        contents.add(searchPanel, BorderLayout.SOUTH);




        frameReader.setContentPane(contents);
        frameReader.setVisible(true);

        m11.addActionListener(this);
        btnFind.addActionListener(new Searcher());
        btnBack.addActionListener(Searcher.btnBackListener);
        btnForward.addActionListener(Searcher.btnForwardListener);
        btnCancel.addActionListener(Searcher.btnCancelListener);


    }


    public void actionPerformed(ActionEvent e) {
        try {


            //printFile(receiverFrame.destination, System.out, Charset.defaultCharset(), 50000);
            printer.outputText(50000, listModel);
            ListenAdditionScrolled listenAdditionScrolled = new ListenAdditionScrolled(scrollV, listModel, new ReceiverFrame().destination);
            scrollV.getViewport().addChangeListener(listenAdditionScrolled);
            //printer.outputText(5000, listModel);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }


    public void printFile(String filePath, Appendable out, Charset charsetint, int seek) throws IOException {
        ListenAdditionScrolled listenAdditionScrolled = new ListenAdditionScrolled(scrollV, listModel, new ReceiverFrame().destination);
        scrollV.getViewport().addChangeListener(listenAdditionScrolled);
        file = new RandomAccessFile(filePath, "r");
        String line;
        while ((line = file.readLine()) != null) {
            listModel.addElement(line);
            if (file.getFilePointer() > 50000) {
                break;
            }
        }

        //System.out.println(listModel.getSize());

    }

    public static FileReader getInstance() {
        if ((instance == null) || (!frameReader.isVisible())) {
            instance = new FileReader();
        }
        return instance;
    }


}
