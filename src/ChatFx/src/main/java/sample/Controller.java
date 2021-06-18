package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Server.ClientHandler;
import sample.Server.ChatConstants;

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
    private static DataOutputStream outputStream;
    private static boolean OnOffConnect = false;

    @FXML
    private TextArea messages;
    @FXML
    private TextArea nickList;
    @FXML
    private TextField inputText;


    @FXML
    public void openConnection() throws IOException {
        socket = new Socket(ChatConstants.HOST, ChatConstants.PORT);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());

        ClientHandler.executorService.execute(() -> {
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
                    this.messages.appendText(strFromServer);
                    this.messages.appendText("\n");
                }
                //read
                while (true) {

                    String strFromServer = inputStream.readUTF();
                    if (strFromServer.equals(ChatConstants.STOP_WORD)) {
                        break;
                    }
                    this.messages.appendText(strFromServer);
                    this.messages.appendText("\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
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
                !OnOffConnect) {
                openConnection();                      //Тут подключение к серверу.
                inputText.setText("");
                OnOffConnect = true;
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
}
