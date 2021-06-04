package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.stream.*;


public class Controller {
    private static Socket socket;
    private static DataInputStream inputStream;
    public static DataOutputStream outputStream;
    private static boolean openLPW = false;
    private static boolean Connection = false;

    @FXML
    private TextArea messeges;
    @FXML
    private TextArea nickList;
    @FXML
    private TextField inputText;
    @FXML
    private Button connect;



    @FXML
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
                    if (strFromServer.startsWith(ChatConstants.SEND_REFRESH_LIST)){
                        this.nickList.clear();
                        String str = Arrays.stream(strFromServer.split("\\s+")).skip(1).map((s -> s + "\n"))
                        .collect(Collectors.joining());
                        this.nickList.appendText(str);
                        continue;
                    }
                    this.messeges.appendText(strFromServer);
                    this.messeges.appendText("\n");
                }
                //read
                while (true) {

                    String strFromServer = inputStream.readUTF();
                    if (strFromServer.equals(ChatConstants.STOP_WORD)) {
                        break;
                    }
                    this.messeges.appendText(strFromServer);
                    this.messeges.appendText("\n");
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
            if (inputText.getText().equals(ChatConstants.CONNECT) &&
                !Connection) {
                openConnection();                      //Тут подключение к серверу.
                inputText.setText("");
                //OnOffConnect = true;
                Connection = true;
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

    private void onClickConnect(){
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
    private void sendMessage() {
        if (!inputText.getText().trim().isEmpty()) {
            try {
                outputStream.writeUTF(inputText.getText());
                inputText.setText("");
                inputText.fontProperty();
            } catch (IOException e) {
                e.printStackTrace();
                inputText.setText("");
                JOptionPane.showMessageDialog(null, "Send error occured");
            }
        }
    }

    public static void sendMessage(String text) {
        try {
            outputStream.writeUTF(text);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Send error occured");
        }
    }

    protected static void ending() {
        try {
            outputStream.writeUTF(ChatConstants.STOP_WORD);
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshList(ActionEvent event) {
        try {
            outputStream.writeUTF(ChatConstants.SEND_REFRESH_LIST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*private void onClickConnect(javafx.event.ActionEvent event) {
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
*/
    @FXML
    void initialize(){
        connect.setOnAction(event -> {
            if(!openLPW) {
                onClickConnect();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/EnterWindow/EnterWindow.fxml"));

                try {
                    loader.load();
                    openLPW = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                stage.setResizable(false);
            }
        });
    }
}
