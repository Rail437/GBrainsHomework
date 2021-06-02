package sample;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.Timer;
import java.util.stream.Collectors;

import static sample.MyServer.*;

public class ClientHandler {
    public static MyServer server;
    public static Socket socket;
    public static DataInputStream inputStream;
    public static DataOutputStream outputStream;
    public static boolean Connection = false;


    public static String name;

    public String getName() {
        return name;
    }

    public ClientHandler(MyServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
            this.name = "";

            new Thread(() -> {
                try {
                    authentification();
                    readMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }

            }).start();
            Thread checkAuth = new Thread(() -> {
                    Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (statusAuthClient == false) {
                        System.out.println("Не авторизованный клиент был отключен за простой.");
                        closeConnection();
                    }
                }
            }, 120*1000); //Тут задаем время через которое выполняем код выше.
            });
            checkAuth.setDaemon(true);
            checkAuth.start();
        } catch (IOException ex) {
            System.out.println("Проблема при создании клиента");
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            String messageFromClient = inputStream.readUTF();
            System.out.println("от " + name + ": " + messageFromClient);
            if (messageFromClient.equals(ChatConstants.STOP_WORD)) {
                return;
            } else if (messageFromClient.startsWith(ChatConstants.SEND_TO_LIST)) {
                String[] splittedStr = messageFromClient.split("\\s+");
                List<String> nicknames = new ArrayList<>();
                for (int i = 1; i < splittedStr.length - 1; i++) {
                    nicknames.add(splittedStr[i]);
                }
            } else if (messageFromClient.startsWith(ChatConstants.CLIENTS_LIST)) {
                server.broadcastClients();
            } else if (messageFromClient.startsWith(ChatConstants.SEND_REFRESH_LIST)) {
                server.broadcastClientsList();
            } else if (messageFromClient.startsWith(ChatConstants.PER_TO_PER)) {  //Отправляем персональное сообщение
                String splitPersonNick = Arrays.stream(messageFromClient.split("\\s+"))
                        .skip(1).limit(1).collect(Collectors.joining());
                String splitMessage = Arrays.stream(messageFromClient.split("\\s+"))
                        .skip(2).collect(Collectors.joining());
                server.messageToPers("Privat chat ["+ name + " & " + splitPersonNick + "] "
                        + splitMessage,splitPersonNick, name);
            } else {
                server.broadcastMessage("[" + name + "]: " + messageFromClient);
            }

        }
    }

    // /auth login pass
    private void authentification() throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            if (message.startsWith(ChatConstants.REGISTER)) {
                String[] Registration = message.split("\\s+");
                server.addUsers(Registration[1], Registration[2], Registration[3]);
            }
            if (message.startsWith(ChatConstants.AUTH_COMMAND)) {
                String[] parts = message.split("\\s+");
                Optional<String> nick = server.getAuthService().getNickByLoginAndPass(parts[1], parts[2]);
                if (nick.isPresent()) {
                    //проверим, что такого нет
                    if (!server.isNickBusy(nick.get())) {
                        sendMsg(ChatConstants.AUTH_OK + " " + nick);
                        statusAuthClient = true;
                        name = nick.get();
                        server.subscribe(this);
                        server.broadcastMessage(name + " вошел в чат");
                        //AuthController.closeWindow();
                        return ;
                    } else {
                        //JOptionPane.showMessageDialog(null, "Ник уже используется");
                        sendMsg("Ник уже используется");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Неверные логин/пароль");
                    sendMsg("Неверные логин/пароль");
                }
            }
        }
    }

    public static void sendMsg(String message) {
        try {
            outputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void refreshList(String message) {
        try {
            outputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(name + " вышел из чата");
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
}
