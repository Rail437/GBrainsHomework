package sample;

import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static sample.ClientHandler.sendMsg;

public class MyServer {
    public static boolean statusAuthClient = false;
    /**
     * Непосредственно сервер */

    private List<ClientHandler> clients;
    private AuthService authService;

    public MyServer() {

        try (ServerSocket server = new ServerSocket(ChatConstants.PORT)) {
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
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
        clients.forEach(client -> sendMsg(message));
    /*for (ClientHandler client : clients) {
        client.sendMsg(message);
    }*/
    }

    public synchronized void broadcastMessageToClients(String message, List<String> nicknames) {
        clients.stream()
                .filter(c -> nicknames.contains(c.getName()))
                .forEach(c -> sendMsg(message));

    /*for (ClientHandler client : clients) {
        if (!nicknames.contains(client.getName())) {
          continue;
        }
        client.sendMsg(message);
    }*/
    }
    public synchronized void messageToPers(String message,  String nickname){
        for (ClientHandler client : clients) {
            if(nickname.contains(client.getName())){
                sendMsg(message);
            }
        }
    }

    public synchronized void broadcastClients() {
        String clientsMessage = ChatConstants.CLIENTS_LIST +
                " " +
                clients.stream()
                        .map(ClientHandler::getName)
                        .collect(Collectors.joining(" "));
        // /client nick1 nick2 nick3
        clients.forEach(c-> sendMsg(clientsMessage));
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
    public void addUsers(String nick, String login, String password) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        if(checkNicks(nick)){
            sendMsg("Такой ник занят");
            return;
        }else {
            dbHandler.signUpUser(nick,login,password);
            checkNicks(nick);
        }
    }

    public void changeLogin(ClientHandler clientHandler, String login, String changedLogin) {
        if( login.equals(clientHandler.getName())){
            DatabaseHandler db = new DatabaseHandler();
            db.changeNickname(login, changedLogin);
            clientHandler.setName(changedLogin);
            return;
        }
        sendMsg("Не верно указан ваш логин. \n" +
                "Для смены логина\n введите сообщение в формате \n" +
                "/change login вашлогин новыйлогин");
    }
}
