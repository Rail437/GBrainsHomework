package sample.Server;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import sample.*;
import sample.DataBase.AuthService;
import sample.DataBase.BaseAuthService;
import sample.DataBase.DatabaseHandler;
import sample.SaveMessages.SavingMessages;

import java.util.List;

import java.util.stream.Collectors;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class MyServer {
    public static boolean statusAuthClient = false;
    private static final Logger LOGGER = LogManager.getLogger(MyServer.class); //Что ему не нравится????
    private List<ClientHandler> clients;
    private AuthService authService;
    public MyServer() {

        try (ServerSocket server = new ServerSocket(ChatConstants.PORT)) {
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                LOGGER.warn("Сервер ожидает подключения");
                System.out.println("SOP: Сервер ожидает подключения");
                Socket socket = server.accept();
                LOGGER.warn("Клиент подключился");
                System.out.println("SOP: Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized boolean isNickBusy(String nick) {
        return clients.stream().anyMatch(client -> client.getName().equals(nick));
   /* for (ClientHandler client : clients) {
        if (client.getName().equals(nick)) {
            return true;
        }
    }
    return false;*/
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
        for (ClientHandler client : clients){
            System.out.println(client.getName());
        }
        broadcastClients();
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastClients();
    }

    /**
     * Отправляет сообщение всем пользователям
     * @param message
     */
    public synchronized void broadcastMessage(String message) {
        SavingMessages saver = new SavingMessages();
        clients.forEach(client -> {
            client.sendMsg(message);
            saver.SaveMessagesToText(client.getName(), message);
        });

    /*for (ClientHandler client : clients) {
        client.sendMsg(message);
    }*/
    }

    public synchronized void messageToPers(String message, String nickname, ClientHandler clientHandler){
        SavingMessages save = new SavingMessages();
        for (ClientHandler client : clients) {
            if(nickname.contains(client.getName())){
                client.sendMsg(message);
                save.SaveMessagesToText(nickname, message);
            }
        }
        clientHandler.sendMsg(message);
        save.SaveMessagesToText(clientHandler.getName(), message);
    }

    public synchronized void broadcastClients() {
        String clientsMessage = ChatConstants.CLIENTS_LIST +
                " " +
                clients.stream()
                        .map(ClientHandler::getName)
                        .collect(Collectors.joining(" "));
        // /client nick1 nick2 nick3
        clients.forEach(c-> c.sendMsg(clientsMessage));
    }
    public synchronized void broadcastClientsList() {
        String clientsMessage = ChatConstants.SEND_REFRESH_LIST + " " +
                clients.stream()
                        .map(ClientHandler::getName)
                        .collect(Collectors.joining(" "));
        clients.forEach(c-> c.refreshList(clientsMessage));
    }
    private Boolean checkNicks(String nick2){
        DatabaseHandler db = new DatabaseHandler();
        List nicks = db.getNicks();

        for (Object nick : nicks){
            if(nick2.equals(nick)){
                return true;
            }
        }
        return false;
    }
    private Boolean checkLogin(String login){
        DatabaseHandler db = new DatabaseHandler();
        List logins = db.getNicks();

        for (Object log : logins){
            if(login.equals(log)){
                return true;
            }
        }
        return false;
    }

    public void addUsers(ClientHandler client,String nick, String login, String password) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        if(checkNicks(nick)){
            client.sendMsg("Такой ник занят");
            return;
        }
        if(checkLogin(login)){
            client.sendMsg("Такой логин занят");
            return;
        }
        if (!checkLogin(login) && !checkNicks(nick)){
            dbHandler.signUpUser(client,nick,login,password);
            checkNicks(nick);
        }
    }

    public void changeLogin(ClientHandler clientHandler, String login, String changedLogin) {
        if( login.equals(clientHandler.getName())){
            DatabaseHandler db = new DatabaseHandler();
            db.changeNickname(clientHandler,login, changedLogin);
            clientHandler.setName(changedLogin);
            return;
        }
        clientHandler.sendMsg("Не верно указан ваш логин. \n" +
                "Для смены логина\n введите сообщение в формате \n" +
                "/change login вашлогин новыйлогин");
    }
}

