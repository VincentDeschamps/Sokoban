package sample;

import Game.Map;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * Class observed by views
 * Presents all datas wich views needs
 */
public class ModeleSujet extends Sujet implements Modele {
    ModeleConcret modeleConcret;
    public ObservableList<String> mapPool;
    public List<Integer> animationMenu;
    public ArrayList<char[]> mapFile = new ArrayList<>();
    public int curSelectedMap;
    public Thread rep;
    public boolean disponible = true;

    public ModeleSujet(){
        this.modeleConcret = new ModeleConcret();
        mapPool = FXCollections.observableArrayList();
    }

    @Override
    public String changeToNextMap() {
        if (!disponible) {
            stopReplay();
        }
        if (curSelectedMap != mapPool.size() -1){
            curSelectedMap ++;
            notifier();
            return mapPool.get(curSelectedMap);
        }
        return null;
    }

    @Override
    public String changeToPrevioustMap() {
        if (!disponible) {
            stopReplay();
        }
        if (curSelectedMap != 0){
            curSelectedMap--;
            notifier();
            return mapPool.get(curSelectedMap);
        }
        return null;
    }

    @Override
    public void setMapPool() {
        mapPool = LoadMapsFiles();
        notifier();
    }

    @Override
    public void mapSelected(String map) {
        for (int i = 0; i < mapPool.size(); i++) {
            if (mapPool.get(i) == map) {
                curSelectedMap = i;
            }
        }
        mapFile.clear();
        try {
            Reader fin = new InputStreamReader(new FileInputStream(new File("src"+File.separator+"tableaux"+File.separator+map)), "ISO-8859-1");
            BufferedReader reader = new BufferedReader(fin);

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Title:")) {
                    setMapName(line.split(":")[1].trim());
                }
                else if (line.contains("Author:")) {
                    setAuthorName(line.split(":")[1].trim());
                }
                else if (!line.contains("T") && !line.contains("C") && !line.contains("A") && !line.contains("D")) {
                    char[] lineArray = line.toCharArray();
                    mapFile.add(lineArray);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        notifier();
    }

    /**
     * Get the files in "src/tableaux" and put them in ObservableList maps
     * @return "ObservableList<String>" containing the files names
     */
    public ObservableList<String> LoadMapsFiles() {
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

    @Override
    public void createMap() throws Exception {
        if (!disponible) {
            stopReplay();
        }
        createMap("src" + File.separator + "tableaux" + File.separator + mapPool.get(curSelectedMap));
    }

    @Override
    public void createMap(String url) throws Exception {
        modeleConcret.createMap(url);
    }

    @Override
    public int getnbCoups() {
        return modeleConcret.getnbCoups();
    }

    @Override
    public String getMapName() {
        return modeleConcret.getMapName();
    }

    @Override
    public String getAuthorName() {
        return modeleConcret.getAuthorName();
    }

    @Override
    public void changeCoups(int i) {
        modeleConcret.changeCoups(i);
    }

    @Override
    public void startParty() {
        modeleConcret.startParty();
    }

    @Override
    public ArrayList<Map> getMaps() {
        return modeleConcret.getMaps();
    }

    @Override
    public void resetMaps() { modeleConcret.resetMaps(); }

    @Override
    public void resetIndexCurMap() { modeleConcret.resetIndexCurMap(); }

    @Override
    public Map getMap(){ return getMaps().get(getnbCoups()); }

    @Override
    public void setMapName(String newName) {
        modeleConcret.setMapName(newName);
    }

    @Override
    public void setAuthorName(String newName) {
        modeleConcret.setAuthorName(newName);
    }

    @Override
    public boolean PlayerMoves(int x, int y) {

        if (modeleConcret.PlayerMoves(x, y)) {
            notifier();
            return true;
        }

        return false;
    }

    @Override
    public boolean checkVictory() {
        return modeleConcret.checkVictory();
    }

    public void runReplay(){
        this.disponible = false;

        this.rep = new Thread(new Replay());
        this.rep.setDaemon(true);

        this.rep.start();

    }

    public void stopReplay(){
        this.rep.stop();
        this.disponible = true;
        modeleConcret.changeCoups(-modeleConcret.getnbCoups()+modeleConcret.getMaps().size()-1);
        modeleConcret.resetIndexCurMap();
        modeleConcret.changeCoups(modeleConcret.getMaps().size()-1);
        notifier();
    }

    class Replay implements Runnable{
        int index = 0;

        @Override
        public void run() {
            index = 0;
            while(index < getMaps().size()){
                changeCoups(index);
                Platform.runLater(() -> {
                    notifier();
                    index+= 1;

                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
