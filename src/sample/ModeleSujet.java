package sample;

import Game.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ModeleSujet extends Sujet implements Modele {
    ModeleConcret modeleConcret;
    public ObservableList<String> mapPool;
    public List<Integer> animationMenu;
    public ArrayList<char[]> mapFile = new ArrayList<>();


    public ModeleSujet(){
        this.modeleConcret = new ModeleConcret();
        mapPool = FXCollections.observableArrayList();
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
    public void addCoup() {
        modeleConcret.addCoup();
    }

    @Override
    public void startParty() {
        modeleConcret.startParty();
    }

    @Override
    public Map getMap() {
        return modeleConcret.getMap();
    }

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
        if (modeleConcret.PlayerMoves(x,y)) {
            notifier();
            return true;
        }
        System.out.println("loose");
        return false;
    }


}
