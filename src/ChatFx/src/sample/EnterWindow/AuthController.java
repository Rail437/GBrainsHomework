package sample.EnterWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import sample.MainWindow.Controller;
import sample.Meneger.ClientHandler;


public class AuthController {

    @FXML
    private Pane LPWindow;

    @FXML
    public Button EnterButton;

    @FXML
    private TextField inpitPasswotd;

    @FXML
    private TextField inputLogin;

    @FXML
    void initialize() {
            EnterButton.setOnAction(event -> {
                String message = "/auth" + " " + inputLogin.getText() + " " + inpitPasswotd.getText();
                inputLogin.setText("");
                inpitPasswotd.setText("");
                ClientHandler.sendMsg(message);
        });
    }

    @FXML
    public void closeWindow(){
        Controller.openLPW = false;
        LPWindow.getScene().getWindow().hide();
    }
}

