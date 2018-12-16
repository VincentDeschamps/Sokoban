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
    private ModeleSujet modele;
    private FacadeVues facade;

    public Controller(Stage win, FacadeVues f, ModeleSujet modele){
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

    /**
     * Loads the map concerned by the url and modifies the model with datas obtained in the concerned file
     * @param url String url of the file
     */
    public void mapLoader(String url){
        modele.mapFile.clear();
        try {
            Reader fin = new InputStreamReader(new FileInputStream(new File("src"+File.separator+"tableaux"+File.separator+url)), "ISO-8859-1");
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

        System.out.println(url);
        modele.notifier();
    }

    /**
     * Called when the user clicks on the "PLAY!" button on the menu
     * Opens up the game scene
     */
    class GoToGame implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (!modele.disponible) {
                modele.stopReplay();
            }
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
            if (!modele.disponible) {
                modele.stopReplay();
            }
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
            if (!modele.disponible) {
                modele.stopReplay();
            }
            if (modele.disponible &&  (facade.gv.modele.getnbCoups()-1>=0)) {
                facade.gv.modele.changeCoups(modele.getnbCoups()-1);
                facade.gv.actualiser();
            }
            facade.gv.map.requestFocus();
        }
    }

    class Redo implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            if (!modele.disponible) {
                modele.stopReplay();
            }
            if (facade.gv.modele.getnbCoups()+1 < facade.gv.modele.getMaps().size()) {
                facade.gv.modele.changeCoups(modele.getnbCoups()+1);
                facade.gv.actualiser();
            }
            facade.gv.map.requestFocus();
        }
    }

    class Reset implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            if (!modele.disponible) {
                modele.stopReplay();
            }
            System.out.println("click reset");
            facade.gv.modele.resetIndexCurMap();
            facade.gv.modele.resetMaps();
            facade.gv.modele.startParty();
            facade.gv.actualiser();
            facade.gv.map.requestFocus();
        }
    }

    /**
     * Gets the user's inputs and modifies the model if necessary
     */
    class PlayerInputs implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            if (!modele.disponible) {
                modele.stopReplay();
            }
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
            if (!modele.disponible) {
                modele.stopReplay();
            }
            if (modele.curSelectedMap != modele.mapPool.size() -1){
                modele.curSelectedMap ++;
                modele.notifier();
                String mapName = modele.mapPool.get(modele.curSelectedMap);
                mapLoader(mapName);

                EventHandler<ActionEvent> gotogame = new GoToGame();
                gotogame.handle(event);
            }
        }
    }

    /**
     * Sets the selected map with the previous map on the modele.mapPool list
     */
    class GoToPreviousMap implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            if (!modele.disponible) {
                modele.stopReplay();
            }
            if (modele.disponible && modele.curSelectedMap != 0){
                modele.curSelectedMap --;
                modele.notifier();
                String mapName = modele.mapPool.get(modele.curSelectedMap);
                mapLoader(mapName);

                EventHandler<ActionEvent> gotogame = new GoToGame();
                gotogame.handle(event);
            }
        }
    }

    class Replay implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            if (!modele.disponible) {
                modele.stopReplay();
            }
            modele.runReplay();
            facade.gv.map.requestFocus();
        }
    }

}
