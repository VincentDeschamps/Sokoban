package sample;

import Game.Map;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Objects which will be observed by observers.
 */
public abstract class Sujet {

    protected Sujet(){
        subscribers = new ArrayList<>();
    }

    public ArrayList<Observer> subscribers;

    /**
     * Ads a new observer to subscribe and to notify for updates
     * @param ob Observer to subscribe
     */
    public void subscribe(Observer ob){
        subscribers.add(ob);
    }

    /**
     * Noitifies all subscribed observers
     */
    public void notifier(){
        for (Observer ob : subscribers){
            ob.actualiser();
        }

    }

    /**
     * Changes the current map by the next map
     * @return Name of the next map's fileName
     */
    public abstract String changeToNextMap();

    /**
     * Changes the current map by the previous map
     * @return Name of the previous map's fileName
     */
    public abstract String changeToPrevioustMap();

    /**
     * Fullfills the list of maps' fileNames
     */
    public abstract void setMapPool();

    /**
     * Loads the map concerned by the url and modifies the model with datas obtained in the concerned file
     * @param map String name of the file
     */
    public abstract void mapSelected(String map);

    /**
     * @return int the index of the selected mapFile inside the list of files
     */
    public abstract int getMapSelectedIndex();

    /**
     * Creates the instance of the map
     * @throws Exception if the selected file dosn't exists anymore
     */
    public abstract void createMap() throws Exception;

    /**
     * Starts the party
     */
    public abstract void startParty();

    /**
     * Modifies datas and stops everything wich it needs to be stopped
     * before sending the user's to the MenuScene
     */
    public abstract void GoToMenu();

    /**
     * Undo the last movement
     */
    public abstract void Undo();

    /**
     * Redo the next movement if a undo has been made
     */
    public abstract void Redo();

    /**
     * Resets the party
     */
    public abstract void Reset();

    /**
     * Makes the player moving
     * @param x horizontal movement
     * @param y vertical movement
     * @return true if the movement has been authorized, false if it is not
     */
    public abstract boolean PlayerMoves(int x, int y);

    /**
     * Check if the player win
     * @return true if the player win
     */
    public abstract boolean checkVictory();

    /**
     * Realize an animation which shows all player's movement
     */
    public abstract void Replay();

    /**
     * Returns moves number
     * @return 
     */
    public abstract int getnbCoups();

    public abstract String getMapName();

    public abstract String getAuthorName();

    public abstract ObservableList<String> getMapFiles();

    public abstract Map getMap();
}
