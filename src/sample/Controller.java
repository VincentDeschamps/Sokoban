package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.*;
import java.util.Comparator;

/**
 * Controller
 */
public class Controller {
    private Stage window;
    private Sujet modele;
    private FacadeVues facade;

    public Controller(Stage win, FacadeVues f, Sujet modele){
        window = win;
        facade = f;
        this.modele = modele;

        f.mv.goToGame.setOnAction(new GoToGame());
        f.gv.back.setOnAction(new GoToMenu());
        f.gv.nextMap.setOnAction(new GoToNextMap());
        f.gv.previousMap.setOnAction(new GoToPreviousMap());
        f.gv.btnUnd.setOnAction(new Undo());
        f.gv.btnRed.setOnAction(new Redo());
        f.gv.btnRes.setOnAction(new Reset());
        f.gv.btnRep.setOnAction(new Replay());

        window.setTitle("Home");
        Scene scene1 = MonteurMenu.createScene(facade.mv);
        window.setScene(scene1);

        //charge into ObservableList<String> modele.mapPool the maps in src/tableaux/
        this.modele.setMapPool();
        //puts a controller on the comboBox (when a map will be selected, it will be displayed)
        f.mv.choixTableau.setOnAction(new LoadMap());

        f.gv.map.setOnKeyPressed(new PlayerInputs());

    }



    /**
     * Called when a file is selected in the ComboBox, read it,
     * transform its format to ArrayList<char[]> (each char[] correspond to one row)
     * and put it in "modele.mapFile"
     */
    class LoadMap implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            facade.mv.goToGame.setDisable(false);

            String map = facade.mv.choixTableau.getValue().toString();
            modele.mapSelected(map);

        }
    }

    /**
     * Called when the user clicks on the "PLAY!" button on the menu
     * Opens up the game scene
     */
    class GoToGame implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            facade.mv.changeGrid.pause();
            window.setTitle("Game");
            try{
                modele.createMap();
                Scene scene2 = MonteurGame.createScene(facade.gv);
                window.setScene(scene2);
                facade.gv.isVisible = true;
                facade.gv.map.requestFocus();
                modele.startParty();
                facade.gv.actualiser();

            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    /**
     * Called when the user clicks on the "BACK" button on the game scene
     * Gets the user back to the menu scene
     */
    class GoToMenu implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            modele.GoToMenu();
            facade.mv.changeGrid.release();
            window.setTitle("Home");
            Scene scene1 = MonteurMenu.createScene(facade.mv);
            window.setScene(scene1);
            facade.gv.isVisible = false;
        }
    }

    /**
     * Called when the user clicks on the "undo" button on the game scene
     * Cancel the last move of the player
     */
    class Undo implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            modele.Undo();
            facade.gv.map.requestFocus();
        }
    }

    class Redo implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            modele.Redo();
            facade.gv.map.requestFocus();
        }
    }

    class Reset implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            modele.Reset();
            facade.gv.map.requestFocus();
        }
    }

    /**
     * Gets the user's inputs and modifies the model if necessary
     */
    class PlayerInputs implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {

            switch (event.getCode()){
                case R:
                    new Reset().handle(new ActionEvent());
                    break;
                case Y:
                    new Redo().handle(new ActionEvent());
                    break;
                case Z:
                    new Undo().handle(new ActionEvent());
                    break;
                case UP:
                    modele.PlayerMoves(0,-1);
                    break;
                case DOWN:
                    modele.PlayerMoves(0,1);
                    break;
                case LEFT:
                    modele.PlayerMoves(-1,0);
                    break;
                case RIGHT:
                    modele.PlayerMoves(1,0);
                    break;
            }

            if (modele.checkVictory()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wonderful!");
                alert.setHeaderText(null);
                alert.setContentText("You won this map!");

                alert.showAndWait();
            }
            event.consume();
        }
    }

    /**
     * Sets the selected map with the next map on the modele.mapPool list
     */
    class GoToNextMap implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            String mapName = modele.changeToNextMap();
            if (mapName != null){
                modele.mapSelected(mapName);
                new GoToGame().handle(event);
            }
        }
    }

    /**
     * Sets the selected map with the previous map on the modele.mapPool list
     */
    class GoToPreviousMap implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            String mapName = modele.changeToPrevioustMap();
            if (mapName != null){
                modele.mapSelected(mapName);
                new GoToGame().handle(event);
            }
        }
    }

    class Replay implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            modele.Replay();
            facade.gv.map.requestFocus();
        }
    }

}
