package sample.FirstWindow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.*;
import java.util.Timer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.Meneger.ChatConstants;
import sample.Meneger.ClientHandler;
import sample.Server.MyServer;


import static sample.FirstWindow.FirstWindowMeneger.mainWindowOpen;
import static sample.Meneger.ClientHandler.onClickConnect;
import static sample.Meneger.ClientHandler.statusAuthClient;

public class FirstWindowController {

    @FXML
    private TextField InputLogin;

    @FXML
    private TextField inputPassword;

    @FXML
    private Label ErrorLabel;

    @FXML
    private Button EnterButton;

    @FXML
    private Button RegisterButton;

    private static MyServer server;
    private static Socket socket;
    private static DataInputStream inputStream;
    public static DataOutputStream outputStream;
    private static boolean Connection = false;

    /*private void FirstConnect(){
        new Thread(() -> {
            try {
                FirstAuthentification();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        Thread checkAuth = new Thread(() -> {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!statusAuthClient) {
                        System.out.println("Не авторизованный клиент был отключен за простой.");
                        closeConnection();
                    }
                }
            }, 120*1000); //Тут задаем время через которое выполняем код выше.
        });
        checkAuth.setDaemon(true);
        checkAuth.start();
    }*/


    /*private void FirstAuthentification() throws IOException {
        System.out.println("authentification");
        while (true) {
            System.out.println("While true");
            //String message = inputStream.readUTF();
            String message = testMessage;
            if (message.startsWith(ChatConstants.AUTH_COMMAND)) {
                System.out.println("Auth_Command");
                String[] parts = message.split("\\s+");
                System.out.println("authentification First if " + message);
                ClientHandler testClient = new ClientHandler(server,socket);
                Optional<String> nick = server.getAuthService().getNickByLoginAndPass(parts[1], parts[2]);
                if (nick.isPresent()) {
                    //проверим, что такого нет
                    if (!server.isNickBusy(nick.get())) {
                        sendMsg(ChatConstants.AUTH_OK + " " + nick);
                        statusAuthClient = true;
                        ClientHandler.name = nick.get();
                        server.subscribe(testClient);
                        closeWindow();
                        server.broadcastMessage(ClientHandler.name + " вошел в чат");
                        return ;
                    } else {
                        ErrorLabel.setText( "Ник уже используется");
                        //sendMsg("Ник уже используется");
                    }
                } else {
                    ErrorLabel.setText("Неверные логин/пароль");
                    //sendMsg("Неверные логин/пароль");
                }
            }
        }
    }*/

    @FXML
    void initialize() {
        EnterButton.setOnAction(event -> {
            if(!Connection){
                onClickConnect();
                Connection = true;
            }
            if(!statusAuthClient){
                String message = "/auth" + " " + InputLogin.getText() + " " + inputPassword.getText();
                ClientHandler.sendMsg(message);
                InputLogin.setText("");
                inputPassword.setText("");
                try {
                    outputStream.writeUTF(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //ClientHandler.sendMsg(message);
                System.out.println("Send " + message);
            }
        });
    }
    public static void closeWindow(){
        System.out.println("Method closeWindow");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FirstWindowController.class.getResource("/sample/MainWindow/sample.fxml"));
        try {
            loader.load();
            mainWindowOpen = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
        stage.setResizable(false);
    }
}

