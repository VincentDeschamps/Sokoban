package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Executors;

import static javafx.scene.input.KeyCode.*;

/**
 * Controller
 */
public class Controller {
    Stage window;
    ModeleSujet modele;
    FacadeVues facade;

    public Controller(Stage win, FacadeVues f, ModeleSujet modele){
        window = win;
        facade = f;
        this.modele = modele;

        f.mv.goToGame.setOnAction(new GoToGame());
        f.gv.back.setOnAction(new GoToMenu());
        f.gv.btnRi.setOnAction(new MoveRight());

        window.setTitle("Home");
        Scene scene1 = MonteurMenu.createScene(facade.mv);
        window.setScene(scene1);

        //charge into ObservableList<String> modele.mapPool the maps in src/tableaux/
        this.modele.mapPool = getMaps();
        this.modele.notifier();
        //puts a controller on the comboBox (when a map will be selected, it will be displayed)
        f.mv.choixTableau.setOnAction(new LoadMap());

        f.gv.map.setOnKeyPressed(new PlayerInputs());
    }

    /**
     * Get the files in "src/tableaux" and put them in ObservableList maps
     * @return "ObservableList<String>" containing the files names
     */
    public ObservableList<String> getMaps() {
        ObservableList<String> maps =  FXCollections.observableArrayList();

        File directory = new File("src"+File.separator+"tableaux");

        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            maps.add(file.getName());
        }
        return maps;
    }

    /**
     * Called when a file is selected in the ComboBox, read it,
     * transform its format to ArrayList<char[]> (each char[] correspond to one row)
     * and put it in "modele.mapFile"
     */
    class LoadMap implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            modele.mapFile.clear();
            String map = facade.mv.choixTableau.getValue().toString();

            try (BufferedReader reader = new BufferedReader(new FileReader(new File("src"+File.separator+"tableaux"+File.separator+map)))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    char[] lineArray = line.toCharArray();
                    modele.mapFile.add(lineArray);
                    System.out.println(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(map);
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
                modele.createMap("src" + File.separator + "tableaux" + File.separator + facade.mv.choixTableau.getValue().toString());
                Scene scene2 = MonteurGame.createScene(facade.gv);
                window.setScene(scene2);
                facade.gv.map.requestFocus();
            } catch (Exception e){
                System.out.print("error");
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
            facade.mv.changeGrid.release();
            window.setTitle("Home");
            Scene scene1 = MonteurMenu.createScene(facade.mv);
            window.setScene(scene1);
        }
    }

    class MoveRight implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            modele.addCoup();
            modele.notifier();
        }
    }

    class PlayerInputs implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()){
                case UP:
                    System.out.println("Up");
                    break;
                case DOWN:
                    System.out.println("Down");
                    break;
                case LEFT:
                    System.out.println("Left");
                    break;
                case RIGHT:
                    System.out.println("Right");
                    break;
            }


            event.consume();
        }
    }

}
