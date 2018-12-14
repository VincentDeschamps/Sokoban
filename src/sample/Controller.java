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

import java.io.*;
import java.util.Comparator;
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
        f.gv.nextMap.setOnAction(new GoToNextMap());
        f.gv.previousMap.setOnAction(new GoToPreviousMap());

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
        maps.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

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
            facade.mv.goToGame.setDisable(false);

            String map = facade.mv.choixTableau.getValue().toString();
            for (int i = 0; i < facade.mv.modele.mapPool.size(); i++) {
                if (facade.mv.modele.mapPool.get(i) == facade.mv.choixTableau.getValue()) {
                    facade.mv.modele.curSelectedMap = i;
                }
            }
            mapLoader(map);
        }
    }

    public void mapLoader(String map){
        modele.mapFile.clear();
        try {
            Reader fin = new InputStreamReader(new FileInputStream(new File("src"+File.separator+"tableaux"+File.separator+map)), "ISO-8859-1");
            BufferedReader reader = new BufferedReader(fin);

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Title:")) {
                    modele.setMapName(line.split(":")[1].trim());
                }
                else if (line.contains("Author:")) {
                    modele.setAuthorName(line.split(":")[1].trim());
                }
                else if (!line.contains("T") && !line.contains("C") && !line.contains("A") && !line.contains("D")) {
                    char[] lineArray = line.toCharArray();
                    modele.mapFile.add(lineArray);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(map);
        modele.notifier();
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
                modele.createMap("src" + File.separator + "tableaux" + File.separator + modele.mapPool.get(modele.curSelectedMap));
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
            facade.mv.changeGrid.release();
            window.setTitle("Home");
            Scene scene1 = MonteurMenu.createScene(facade.mv);
            window.setScene(scene1);
            facade.gv.isVisible = false;
        }
    }

    class MoveRight implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            modele.addCoup();
            modele.notifier();
        }
    }

    /**
     * Gets the user's inputs
     */
    class PlayerInputs implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()){
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


            event.consume();
        }
    }

    class GoToNextMap implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            if (modele.curSelectedMap != modele.mapPool.size() -1){
                modele.curSelectedMap ++;
                String mapName = modele.mapPool.get(modele.curSelectedMap);
                mapLoader(mapName);

                EventHandler<ActionEvent> gotogame = new GoToGame();
                gotogame.handle(event);
            }
        }
    }

    class GoToPreviousMap implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            if (modele.curSelectedMap != 0){
                modele.curSelectedMap --;
                String mapName = modele.mapPool.get(modele.curSelectedMap);
                mapLoader(mapName);

                EventHandler<ActionEvent> gotogame = new GoToGame();
                gotogame.handle(event);
            }
        }
    }

}
