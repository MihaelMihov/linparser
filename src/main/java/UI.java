package main.java;

import parser.Parser;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {
    private JPanel panel;
    private JTextField handViewerLink;
    private JButton exportAsLinButton;
    private JTextField txtName2;
    private JButton deleteExportFileButton;
    private JRadioButton appendToFileRadioButton;

    public UI() {
        exportAsLinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Parser.parse(handViewerLink.getText());
            }
        });
        deleteExportFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        appendToFileRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        // Listen for changes in the text
        handViewerLink.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                activateButton();
            }

            public void removeUpdate(DocumentEvent e) {
                activateButton();
            }

            public void insertUpdate(DocumentEvent e) {
                activateButton();
            }

            public void activateButton() {
                exportAsLinButton.setEnabled(!handViewerLink.getText().isEmpty());
            }
        });
    }

    public static void main(String[] args) {


        UI ui = new UI();
        ui.setContentPane(ui.panel);
        ui.setTitle("Lin parser");
        ui.setSize(500, 200);
        ui.setVisible(true);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
