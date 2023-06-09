package com.mihov;

import com.mihov.parser.Parser;
import com.mihov.parser.Printer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
                String lin = Parser.parse(handViewerLink.getText());
                try {
                    Printer.appendFileToLinOutput(lin, appendToFileRadioButton.isSelected());
                } catch (IOException ex) {
                    System.out.println("Exception occurred!");
                }
            }
        });
        deleteExportFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Printer.clearFileContents();
                } catch (IOException ex) {
                    System.out.println("Exception occured.");
                }
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
        ui.setTitle("Lin");
        ui.setSize(500, 200);
        ui.setVisible(true);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
