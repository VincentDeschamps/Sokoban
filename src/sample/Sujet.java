package sample;

import java.util.ArrayList;

public abstract class Sujet {

    protected Sujet(){
        subscribers = new ArrayList<>();
    }

    public ArrayList<Observer> subscribers;

    public void subscribe(Observer ob){
        subscribers.add(ob);
    }

    public void notifier(){
        for (Observer ob : subscribers){
            ob.actualiser();
        }

    }
}
