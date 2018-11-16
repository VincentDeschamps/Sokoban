package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    private Main soko;
    private Pane layout;

    public GameScene(Main soko, Pane l) {
        super(l, 400,400);
        this.layout = l;
        this.soko = soko;

        Button btn2 = new Button("Swap Menu");

        layout.getChildren().add(btn2);

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            //Classe anonyme
            @Override
            public void handle(ActionEvent event) {
                soko.goToMenu();

            }
        });
    }
}
