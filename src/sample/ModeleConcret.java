package sample;

import Game.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ModeleConcret implements Modele{

    public int nbCoups;
    public String mapName = "";
    public String authorName = "";
    public int score = 12;



    public Map map;

    public ModeleConcret(){
        super();
        nbCoups = 0;
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
    public void createMap(String url) throws Exception{
        try{
            map = MapParser.parseMap(url);
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public int getnbCoups() {
        return score;
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
