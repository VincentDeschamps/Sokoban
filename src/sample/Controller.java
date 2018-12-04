package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Executors;

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

        //charge into ObservableList<String> modele.mapPool the maps in src/tableaux/
        this.modele.mapPool = getMaps();
        this.modele.notifier();
        //puts a controller on the comboBox (when a map will be selected, it will be displayed)
        f.mv.choixTableau.setOnAction(new LoadMap());
    }

    //get the files in src/tableaux
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

    //when a file is selected in the combo box, read it and put it in modele.mapFile
    //format of mapFile : ArrayList<char[]>, each char[] correspond to one row
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

    class GoToGame implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            facade.mv.executorService.shutdown();
            window.setTitle("Game");
            Scene scene2 = MonteurGame.createScene(facade.gv);
            window.setScene(scene2);
        }
    }

    class GoToMenu implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            facade.mv.executorService = Executors.newSingleThreadScheduledExecutor();
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
