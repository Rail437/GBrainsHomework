package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/MainWindow.fxml"));
        primaryStage.setTitle("ChatFX");
        primaryStage.setScene(new Scene(root, 450, 400 ));
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
                sample.Model.Connection.ending();
                event.consume();
            }
        });
    }



    public static void main(String[] args) {
        launch(args);
    }
}
