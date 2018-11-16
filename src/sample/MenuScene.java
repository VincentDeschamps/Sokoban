package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MenuScene extends Scene {
    private Pane layout;
    private Main soko;

    MenuScene(Main soko, Pane l){
        super(l,400,400);
        this.layout = l;
        this.soko = soko;

        Button btn1 = new Button("Swap Jeu");
        this.layout.getChildren().add(btn1);

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            //Classe anonyme
            @Override
            public void handle(ActionEvent event) {
                soko.goToGame();

            }
        });



    }
}
