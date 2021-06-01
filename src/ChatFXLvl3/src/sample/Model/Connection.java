package sample.Model;

import sample.Controller.ChatConstants;
import sample.Controller.ControllerMainWindow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Connection {
    private static Socket socket;
    private static DataInputStream inputStream;
    public static DataOutputStream outputStream;
    public static boolean Connection = false;

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
                    if (strFromServer.startsWith(ChatConstants.SEND_REFRESH_LIST)){
                        ControllerMainWindow.nickList.clear();
                        String str = Arrays.stream(strFromServer.split("\\s+")).skip(1).map((s -> s + "\n"))
                                .collect(Collectors.joining());
                        ControllerMainWindow.nickList.appendText(str);
                        continue;
                    }
                    ControllerMainWindow.messegPane.appendText(strFromServer);
                    ControllerMainWindow.messegPane.appendText("\n");
                }
                //read
                while (true) {

                    String strFromServer = inputStream.readUTF();
                    if (strFromServer.equals(ChatConstants.STOP_WORD)) {
                        break;
                    }
                    ControllerMainWindow.messegPane.appendText(strFromServer);
                    ControllerMainWindow.messegPane.appendText("\n");
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

    public static void ending() {
        try {
            outputStream.writeUTF(ChatConstants.STOP_WORD);
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
