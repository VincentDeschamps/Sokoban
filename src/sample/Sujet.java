package sample;

import javafx.collections.ObservableList;

import java.util.ArrayList;

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

    public abstract String changeToNextMap();

    public abstract String changeToPrevioustMap();

    public abstract void setMapPool();

    /**
     * Loads the map concerned by the url and modifies the model with datas obtained in the concerned file
     * @param map String name of the file
     */
    public abstract void mapSelected(String map);

    public abstract void createMap() throws Exception;

    public abstract void startParty();
}
