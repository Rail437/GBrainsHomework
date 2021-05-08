package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextArea mainTextArea;

    @FXML
    private TextField inputText;

    @FXML
    private void clickButton(ActionEvent event){
        String text = inputText.getText();
        mainTextArea.appendText(text + "\n");
        inputText.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
