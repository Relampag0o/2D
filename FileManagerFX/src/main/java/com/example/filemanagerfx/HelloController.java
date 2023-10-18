package com.example.filemanagerfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Scanner;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private BorderPane bP;

    @FXML
    private TextArea textFile;
    private File f;

    public void onClick() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        f = fileChooser.showOpenDialog(bP.getScene().getWindow());
        Scanner sc = new Scanner(f);
        sc.useDelimiter("\n");

        try {
            while (sc.hasNextLine()) {
                textFile.appendText(sc.nextLine() + '\n');
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error...");
        }
    }

    public void saveFile() {
        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f));
            bfw.write(textFile.getText());
            bfw.close();
        } catch (Exception e) {
            System.out.println("There was an error writing the file..");
        }


    }


    public void clearText() {
        textFile.setText("");
    }
}