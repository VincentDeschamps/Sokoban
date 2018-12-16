package sample;

import Game.Map;

import java.util.ArrayList;

public interface Modele {


    /**
     * Creates the instance of a Map described by the file
     * @params url String : url of the file which describes the map
     */
    void createMap(String url) throws Exception;

    /**
     *
     * @return "int" the number of moves which the player made
     */
    int getnbCoups();

    /**
     *
     * @return "String" the name of the current selected map
     */
    String getMapName();

    /**
     *
     * @return "String" the name of the author of the current selected map
     */
    String getAuthorName();

    /**
     * Increases the number of moves made by the player
     */
    void changeCoups(int i);

    /**
     * Begins the party and initialize the nomber of moves
     */
    void startParty();

    /**
     *
     * @return "Maps" the ArrayList of all the states of the map since the start of the game
     */
    ArrayList<Map> getMaps();

    /**
     * Reset the list of map copies
     */
    void resetMaps();

    /**
     * Reset indexCurMap to 0
     */
    void resetIndexCurMap();

    /**
     *
     * @return the currently diplayed map in the map list
     */
    Map getMap();

    /**
     * Sets the name of the current selected map. Called when a new map has been selected.
     * @param newName String name of the new map
     */
    void setMapName(String newName);

    /**
     * Sets the name of the author of the current selected map. Called when a new map has been selected.
     * @param newName String name of the author of the new map
     */
    void setAuthorName(String newName);

    /**
     * Moves the player's character int the direction of the vector (x,y)
     * @param x int value of the horizontal movement ( 1 for going to the right or -1 for going to the left)
     * @param y int value of the vertical movement ( 1 for going down or -1 for going up)
     * @return "boolean" true if the movement has been accepted or false if it has not
     */
    boolean PlayerMoves(int x, int y);

    boolean checkVictory();
}
