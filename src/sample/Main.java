package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

        FacadeVues facade = new FacadeVues(new GameVue(),new MenuVue());
        Controller ctrl = new Controller(window, facade);

        window.show();
    }



}
