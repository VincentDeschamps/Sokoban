package sample;

import Game.Map;

import java.util.ArrayList;

public class ModeleConcret implements Modele{

    private String mapName = "";
    private String authorName = "";
    private ArrayList<Map> maps = new ArrayList<>();
    private int indexCurMap = 0;


    public ModeleConcret(){
        super();
    }

    @Override
    public void startParty() {
        indexCurMap = 0;

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
        Map map = getMap();
        Map next = new Map(map);
        if (next.isMovable(next.player, x, y)){
            indexCurMap++;
            next.player.setState(x == -1 ? 1 : x == 1 ? 2 : y == 1 ? 4 : 7);
            while (getMaps().size() > getnbCoups()) {
                getMaps().remove(getMaps().size() - 1);
            }
            maps.add(next);
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
        return indexCurMap;
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
        indexCurMap = i;
    }

}
