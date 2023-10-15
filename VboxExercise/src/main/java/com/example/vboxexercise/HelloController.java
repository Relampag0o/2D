package com.example.vboxexercise;


import com.opencsv.CSVWriter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelloController {

    @FXML
    public void initialize() {
        dPicker.setConverter(new StringConverter<>() {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        listView.getItems().addAll("Sports", "Music", "Traveling", "Films", "Books");
    }

    @FXML
    private TextField nameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField phoneText;

    @FXML
    private DatePicker dPicker;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ListView<String> listView;

    public void saveInfo() {

        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("contacts.csv", true))) {
            String[] details = {nameText.getText(), emailText.getText(), phoneText.getText(),
                    dPicker.getValue().toString(), listView.getItems().toString()};
            csvWriter.writeNext(details);
            nameText.setText("");
            emailText.setText("");
            phoneText.setText("");
            dPicker.setValue(null);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cancelOperation() {
        Platform.exit();
                
    }
}