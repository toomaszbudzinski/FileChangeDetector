package sample;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static javax.swing.JOptionPane.showMessageDialog;

public class Main {

    static JLabel emptyLabel;

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        JFrame jFrame = new JFrame("Monitor changes on the file");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        emptyLabel = new JLabel("Monitor changes on the file",null,JLabel.CENTER);
        emptyLabel.setSize(270,90);
        jFrame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        jFrame.setSize(270,90);
        jFrame.setVisible(true);
        jFrame.setAlwaysOnTop(true);

        //showMessageDialog(null, "The program has been activated");

        TimerTask task = new FileWatcher(new File("!!!!FILE LOCATION")) {
            @Override
            protected void onChange(File file) {
                showMessageDialog(null, "The file has been changed!");
            }
            @Override
            protected void onCreate(File file){
                emptyLabel.setText(convertToMultiline("Last modification: " + sdf.format(file.lastModified()) + "\n\rNamed: " + file.getName()));
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,new Date(),100000);
    }

    public static String convertToMultiline(String orig){
        return "<html>" + orig.replaceAll("\n", "<br>" + "</html>");
    }
}

