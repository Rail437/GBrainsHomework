package sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Model.Connection;

import javax.swing.*;
import java.io.IOException;

import static sample.Model.Connection.ending;
import static sample.Model.Connection.openConnection;

public class ControllerMainWindow {
    @FXML
    public static TextArea messegPane;
    @FXML
    public static TextArea nickList;
    @FXML
    private TextField inputText;
    @FXML
    private Button connect;

    @FXML
    private void onClickButton(javafx.event.ActionEvent event) {
        try {
            if (inputText.getText().equals(ChatConstants.CONNECT) &&
                    !Connection.Connection) {
                openConnection();                      //Тут подключение к серверу.
                inputText.setText("");
                Connection.Connection = true;
            }
            if (inputText.getText().equals(ChatConstants.DISCONNECT)) {
                ending();                              //Тут отключаемся
                inputText.setText("");
            }
        } catch (IOException e){
            e.printStackTrace();
            inputText.setText("");
        }
        if (!inputText.getText().trim().isEmpty()) {
            sendMessage();
        }
    }

    @FXML
    private void onClickConnect(javafx.event.ActionEvent event){
        if(!Connection.Connection){
            try {
                openConnection();
                Connection.Connection = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void sendMessage() {
        if (!inputText.getText().trim().isEmpty()) {
            try {
                Connection.outputStream.writeUTF(inputText.getText());
                inputText.setText("");
                inputText.fontProperty();
            } catch (IOException e) {
                e.printStackTrace();
                inputText.setText("");
                JOptionPane.showMessageDialog(null, "Send error occured");
            }
        }
    }
}
