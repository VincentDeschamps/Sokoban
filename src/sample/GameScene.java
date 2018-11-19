package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    private Main soko;
    private Pane layout;

    public GameScene(Main soko, Pane l) {
        super(l, 1500,720);
        this.layout = l;
        this.soko = soko;

        ImageView img = new ImageView(new Image("https://lasueur.com/wp-content/uploads/2018/06/Pokemon.jpg"));

        this.layout.getChildren().add(img);

        MonteurAside monteurA = new MonteurAside(new AsideVue());

        this.layout.getChildren().add(monteurA.createPane());

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
