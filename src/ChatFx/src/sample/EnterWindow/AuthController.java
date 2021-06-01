package sample.EnterWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import sample.Controller;

import javax.swing.*;
import java.io.IOException;


public class AuthController {

    @FXML
    private Pane LPWindow;

    @FXML
    public Button EnterButton;

    @FXML
    private TextField inpitPasswotd;

    @FXML
    private TextField inputLogin;

    @FXML
    void initialize() {
            EnterButton.setOnAction(event -> {
                String message = "/auth" + " " + inputLogin.getText() + " " + inpitPasswotd.getText();
                inputLogin.setText("");
                inpitPasswotd.setText("");
                sendMessageLP(message);
        });
    }

    @FXML
    public void closeWindow(){
        LPWindow.getScene().getWindow().hide();
    }

    private void sendMessageLP(String text) {
        try {
            Controller.outputStream.writeUTF(text);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Send error occured");
        }
    }


}

