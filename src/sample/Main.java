package sample;

import javafx.application.Application;
import javafx.stage.Stage;

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

        Modele m = new Modele();
        FacadeVues facade = new FacadeVues(new GameVue(m),new MenuVue(m));

        Controller ctrl = new Controller(window, facade, m);

        window.show();
    }



}
