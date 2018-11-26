package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Controller {
    Stage window;
    Modele modele;
    FacadeVues facade;

    public Controller(Stage win, FacadeVues f, Modele modele){
        window = win;
        facade = f;
        this.modele = modele;

        f.mv.goToGame.setOnAction(new GoToGame());
        f.gv.back.setOnAction(new GoToMenu());
        f.gv.btnRi.setOnAction(new MoveRight());

        window.setTitle("Home");
        Scene scene1 = MonteurMenu.createScene(facade.mv);
        window.setScene(scene1);

    }

    class GoToGame implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            window.setTitle("Game");
            Scene scene2 = MonteurGame.createScene(facade.gv);
            window.setScene(scene2);

        }
    }

    class GoToMenu implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            window.setTitle("Home");
            Scene scene1 = MonteurMenu.createScene(facade.mv);
            window.setScene(scene1);


        }
    }

    class MoveRight implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            modele.nbCoups++;
            modele.notifier();
        }
    }

}
