package br.com.matrix;

import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class App {
    public static void main(String[] args) throws FontFormatException, IOException {
        JFrame frame = new JFrame("Matrix");
        frame.add(new Matrix());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}