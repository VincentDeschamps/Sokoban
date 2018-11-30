package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {
    Runnable changeGrid;
    Stage window;
    Modele modele;
    FacadeVues facade;

    public Controller(Stage win, FacadeVues f, Modele modele){
        window = win;
        facade = f;
        this.modele = modele;
        this.changeGrid = new Animate();

        f.mv.goToGame.setOnAction(new GoToGame());
        f.gv.back.setOnAction(new GoToMenu());
        f.gv.btnRi.setOnAction(new MoveRight());

        window.setTitle("Home");
        Scene scene1 = MonteurMenu.createScene(facade.mv);
        window.setScene(scene1);

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this.changeGrid, 0, 1, TimeUnit.SECONDS);
    }

    class Animate implements Runnable {
        int indiceFrame;

        Animate() {
            this.indiceFrame = 0;
        }

        public void run() {
            modele.animationMenu = modele.frameList.get(indiceFrame);
            System.out.println("indiceFrame "+ indiceFrame);
            modele.notifier();

            this.indiceFrame =  this.indiceFrame+1 < modele.frameList.size() ? this.indiceFrame+1 : 0;
        }
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
