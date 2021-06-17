package src;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


public class EchoClient extends JFrame {

    private Socket socket;

    private JTextArea chatArea;

    private JTextField inputField;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public EchoClient() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initGUI();
    }

    private void openConnection() throws IOException {
        socket = new Socket(src.EchoAppConstants.HOST, src.EchoAppConstants.PORT);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                //auth
                while (true) {
                    String strFromServer = inputStream.readUTF();
                    if (strFromServer.equals(src.EchoAppConstants.AUTH_OK)) {
                        break;
                    }
                    chatArea.append(strFromServer);
                    chatArea.append("\n");
                }
                //read
                while (true) {
                    String strFromServer = inputStream.readUTF();
                    if (strFromServer.equals(src.EchoAppConstants.STOP_WORD)) {
                        break;
                    }
                    chatArea.append(strFromServer);
                    chatArea.append("\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    public void closeConnection() {
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

    public void initGUI() {
        setBounds(600, 300, 500, 500);
        setTitle("Клиент");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Message area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        //down pannel
        JPanel panel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        // inputField.setBounds(100, 100, 150, 30);
        panel.add(inputField, BorderLayout.CENTER);

        JButton sendButton = new JButton("Send");
        panel.add(sendButton, BorderLayout.EAST);

        add(panel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    outputStream.writeUTF(src.EchoAppConstants.STOP_WORD);
                    closeConnection();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }finally {
                    System.exit(0);         // Добавил закрытие при нажатии на крестик.
                                                // До этого не выходило, если зупущен был только клиент
                }
            }
        });

        setVisible(true);

    }

    private void sendMessage() {
        if (!inputField.getText().trim().isEmpty()) {
            if(inputField.getText().equals(src.EchoAppConstants.CONNECT)){
                try {
                    openConnection();                 //Тут добавил подключение к серверу.
                } catch (IOException e) {             //На случай если мы запустили клиента раньше чем серверю
                    e.printStackTrace();
                }
            }
            try {
                outputStream.writeUTF(inputField.getText());
                inputField.setText("");
                inputField.grabFocus();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Send error occured");
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(EchoClient::new);
    }

}