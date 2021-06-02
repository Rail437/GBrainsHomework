package sample.MainWindow;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/FirstWindow/FirstWindow.fxml"));//getResource("sample.fxml"));
        primaryStage.setTitle("ChatFX");
        primaryStage.setScene(new Scene(root, 400, 400 ));
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
                Controller.ending();
                event.consume();
            }
        });
    }



    public static void main(String[] args) {
        launch(args);
    }
}
