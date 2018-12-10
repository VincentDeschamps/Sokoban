package sample;

import Game.Map;

public interface Modele {
    void createMap(String url) throws Exception;

    int getnbCoups();

    String getMapName();

    String getAuthorName();

    void addCoup();

    void startParty();

    Map getMap();

    void setMapName(String newName);

    void setAuthorName(String newName);

}
