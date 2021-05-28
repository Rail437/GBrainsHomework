package sample.EnterWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import sample.ChatConstants;
import sample.ClientHandler;
import sample.Controller;
import sample.MyServer;

import javax.swing.*;
import javax.xml.bind.Unmarshaller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

import static sample.ClientHandler.*;
import static sample.Controller.*;
import static sample.MyServer.*;


public class LPWController  {

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
                if(statusAuthClient == true){
                    closeWindow();
                }
        });
    }

    @FXML
    private void closeWindow(){
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

