package com.test.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Application extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel label;
    private JFrame frame;

    public static int getCount(String fName, String text) throws IOException {
        FileInputStream fstream = new FileInputStream(fName);
        BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
        String readLine = "";
        System.out.println(fName);
        int count = 0;
        while ((readLine = in.readLine()) != null) {
            String[] words = readLine.split(" ");
                for (String item : words) {

                    if (item.equalsIgnoreCase(text)) {
                    count=count+1;
                    }     
                }   
            }
        return count;
    }

    public Application() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("OOP Assignment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        BaseFileChooser fp1 = new BaseFileChooser("Select a file", "Search!");
        fp1.setMode(BaseFileChooser.MODE_SAVE);
        fp1.addFileTypeFilter(".txt", "Text Files");

        BaseFileChooser fp2 = new BaseFileChooser("Select a file", "Search!");
        fp2.setMode(BaseFileChooser.MODE_SAVE);
        fp2.addFileTypeFilter(".txt", "Text Files");

        label = new JLabel("Enter Text to be searched for");
        JTextField tf1 = new JTextField("Enter your text here");
        tf1.setSize(150,20);
        
        JLabel ans1 = new JLabel();
        ans1.setSize(300,20);
        JLabel ans2 = new JLabel();
        ans2.setSize(300,20);

        JButton submit = new JButton("Get Count");
        submit.setSize(300, 20);

        submit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                String f1 = fp1.getSelectedFilePath();
                String f2 = fp2.getSelectedFilePath();
                String search = tf1.getText();
                int c1=0;
                int c2=0;
                try {
                    c1 = getCount(f1, search);
                    c2 = getCount(f2, search);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ans1.setText("The count for first is "+c1);
                ans2.setText("The count for second is "+c2);
            }
        });

        add(fp1);
        add(fp2);
        add(label);
        add(tf1);
        add(submit);
        add(ans1);
        add(ans2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 500);
        setLocationRelativeTo(null); 

    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Application().setVisible(true);
            }
        });
    }
}