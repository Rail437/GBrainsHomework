package sample.Meneger;

import sample.FirstWindow.FirstWindowController;
import sample.Server.MyServer;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.Timer;
import java.util.stream.Collectors;

public class ClientHandler {
    private static MyServer server;
    private static Socket socket;
    public static DataInputStream inputStream;
    public static DataOutputStream outputStream;
    public static boolean statusAuthClient = false;


    public static String name;
    private static boolean Connection = false;

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
                    if (!statusAuthClient) {
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
            }else if (messageFromClient.startsWith(ChatConstants.PER_TO_PER)) {  //Отправляем персональное сообщение
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
                        FirstWindowController.closeWindow();
                        server.broadcastMessage(name + " вошел в чат");
                        return ;
                    } else {
                        JOptionPane.showMessageDialog(null, "Ник уже используется");
                        //sendMsg("Ник уже используется");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Неверные логин/пароль");
                    //sendMsg("Неверные логин/пароль");
                }
            }
        }
    }
    public static void openConnection() throws IOException {
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
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    /*public static void closeConnection() {
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
    }*/

    public static void onClickConnect(){
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