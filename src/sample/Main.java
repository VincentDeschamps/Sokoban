package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

public class Main extends Application {
    public Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sokorico");

        window = primaryStage;

        ModeleSujet m = new ModeleSujet();
        FacadeVues facade = new FacadeVues(new GameVue(m),new MenuVue(m));

        Controller ctrl = new Controller(window, facade, m);

        window.show();
    }

}
