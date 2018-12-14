package sample;

import Game.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ModeleConcret implements Modele{

    private int nbCoups = 0;
    private String mapName = "";
    private String authorName = "";
    private Map map;

    public ModeleConcret(){
        super();
    }

    @Override
    public void startParty() {
        nbCoups = 0;

    }

    @Override
    public Map getMap() {
        return map;
    }

    @Override
    public void setMapName(String newName) {
        this.mapName = newName;
    }

    @Override
    public void setAuthorName(String newName) {
        this.authorName = newName;
    }

    @Override
    public boolean PlayerMoves(int x, int y) {
        if (map.isMovable(map.player, x, y)){
            nbCoups++;
            return true;
        }
        return false;
    }

    @Override
    public boolean checkVictory() {
        return map.checkVictory();
    }

    @Override
    public void createMap(String url) throws Exception{
        try{
            map = MapParser.parseMap(url);
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public int getnbCoups() {
        return nbCoups;
    }

    @Override
    public String getMapName() {
        return mapName;
    }

    @Override
    public String getAuthorName() {
        return authorName;
    }

    @Override
    public void addCoup() {
        nbCoups++;
    }

}
