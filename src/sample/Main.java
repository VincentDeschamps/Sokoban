package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public Stage window;
    private Scene scene1, scene2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sokorico");

        window = primaryStage;

        StackPane layout1 = new StackPane();
        Pane layout2 = new HBox();

        scene1 = new MenuScene(this, layout1);

        scene2 = new GameScene(this, layout2);


        goToMenu();
        window.show();
    }

    public void goToGame() {
        window.setTitle("Game");
        window.setScene(scene2);
    }

    public void goToMenu(){
        window.setTitle("Menu");
        window.setScene(scene1);
    }




}
