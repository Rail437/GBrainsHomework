package sample.FirstWindow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.ChatConstants;

import javax.swing.*;

public class FirstWindowController {
    private static Socket socket;
    private static DataInputStream inputStream;
    public static DataOutputStream outputStream;
    private static boolean openLPW = false;
    private static boolean Connection = false;

    @FXML
    private Button enterButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label ErrorLabel;

    @FXML
    private TextField loginInput;

    @FXML
    private TextField passwordInput;

    /*@FXML
    public void openConnection() throws IOException {
        socket = new Socket(ChatConstants.HOST, ChatConstants.PORT);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                //auth
                while (true) {
                    String strFromServer = inputStream.readUTF();
                    if (strFromServer.equals(ChatConstants.AUTH_OK)) {
                        break;
                    }
                }
                //read
                while (true) {

                    String strFromServer = inputStream.readUTF();
                    if (strFromServer.equals(ChatConstants.STOP_WORD)) {
                        break;
                    }

                    this.ErrorLabel.setText("");
                    this.ErrorLabel.setText(strFromServer);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    public static void closeConnection() {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClickButton(javafx.event.ActionEvent event) {
        try {
            if (!Connection) {
                openConnection();                      //Тут подключение к серверу.
                Connection = true;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        if (!(loginInput.getText().trim().isEmpty() &&
                passwordInput.getText().trim().isEmpty())) {
            //sendMessage();
        }
    }

    @FXML
    private void onClickConnect(javafx.event.ActionEvent event){
        if(!Connection){
            try {
                openConnection();
                Connection = true;
                //OnOffConnect = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Вот здесь обработка логина и пароля из полей.
     */
    /*private void sendMessage() {
        if (!(loginInput.getText().trim().isEmpty() &&
                passwordInput.getText().trim().isEmpty())) {
            try {
                outputStream.writeUTF("/auth "+ loginInput.getText() + " " + passwordInput);
                loginInput.setText("");
                passwordInput.setText("");
                inputText.fontProperty();
            } catch (IOException e) {
                e.printStackTrace();
                inputText.setText("");
                JOptionPane.showMessageDialog(null, "Send error occured");
            }
        }
    }*/
}
