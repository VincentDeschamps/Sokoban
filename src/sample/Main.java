package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

public class Main extends Application {
    public Stage window;
    public ArrayList<ScheduledExecutorService> exe = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sokorico");

        window = primaryStage;

        Modele m = new Modele();
        FacadeVues facade = new FacadeVues(new GameVue(m),new MenuVue(m));

        Controller ctrl = new Controller(window, facade, m);

        window.show();
    }

    @Override
    public void stop()
    {
        Platform.exit();
        for( ScheduledExecutorService sched : exe )
        {
            sched.shutdown();
        }
    }



}
