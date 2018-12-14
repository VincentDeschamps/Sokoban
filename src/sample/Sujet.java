package sample;

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
}
