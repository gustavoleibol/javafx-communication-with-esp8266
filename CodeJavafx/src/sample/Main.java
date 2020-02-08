package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Visao/janelaLogin.fxml"));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setTitle("CAR");
            primaryStage.setScene(new Scene(root, 900, 600));
            primaryStage.show();
        }catch(Exception e){
            e.getMessage();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
