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
    private ArrayList<Map> maps = new ArrayList<>();
    private int indexCurMap = 0;


    public ModeleConcret(){
        super();
    }

    @Override
    public void startParty() {
        nbCoups = 0;

    }

    @Override
    public ArrayList<Map> getMaps() {
        return maps;
    }

    @Override
    public void resetMaps() {
        Map tmpFirst = maps.get(0);
        maps.clear();
        maps.add(tmpFirst);
    }

    @Override
    public int getIndexCurMap() { return indexCurMap; }

    @Override
    public void setIndexCurMap(int i) { indexCurMap+=i; }

    @Override
    public void resetIndexCurMap() { indexCurMap = 0; }

    @Override
    public Map getMap() { return maps.get(indexCurMap); }

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
        if (maps.get(indexCurMap).isMovable(maps.get(indexCurMap).player, x, y)){
            nbCoups++;
            return true;
        }
        return false;
    }

    @Override
    public boolean checkVictory() {
        return getMap().checkVictory();
    }

    @Override
    public void createMap(String url) throws Exception{
        maps.clear();
        indexCurMap = 0;
        try{
            maps.add(MapParser.parseMap(url));
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
    public void changeCoups(int i) {
        nbCoups += i;
    }

}
