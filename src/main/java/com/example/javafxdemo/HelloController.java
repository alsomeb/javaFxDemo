package com.example.javafxdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class HelloController {
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label sendMessage;

    @FXML
    private Button sendButton;

    @FXML
    protected void onSendButtonClick() {
        if (isFormInvalid()) {
            sendMessage.setText("Du måste fylla i alla fält!");
            sendMessage.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        } else {
            sendMessage.setText("Tack så mycket, vi hör av oss!");
            sendMessage.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");

            sendButton.setText("Skickat");
            sendButton.setStyle("-fx-text-fill: green;");
            sendButton.setDisable(true);

            String firstName = firstNameTextField.getText().trim();
            String lastName = lastNameTextField.getText().trim();
            String emailAdr = emailTextField.getText().trim();
            LocalDate date = datePicker.getValue();

            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(emailAdr);
            System.out.println(date);

            writeToFile(firstName, lastName, emailAdr, date);

        }
    }

    private void writeToFile(String firstName, String lastName, String email, LocalDate date) {
        Path saveFile = Path.of("src/main/resources/form.txt");

        try(BufferedWriter writer = Files.newBufferedWriter(saveFile)) {
            writer.write(firstName + "\n" + lastName + "\n" + email + "\n" + date);
            System.out.println("Written customer to file successfully!");
        } catch (IOException e) {
            System.out.println("Error writing customer to file!");
            e.printStackTrace();
        }
    }

    private boolean isFormInvalid() {
        return firstNameTextField.getText().isBlank() || lastNameTextField.getText().isBlank()
                || emailTextField.getText().isBlank() || datePicker.getValue() == null;
    }

}