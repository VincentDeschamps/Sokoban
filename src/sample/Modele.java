package sample;

import java.util.ArrayList;
import java.util.List;

public class Modele {

    public ArrayList<Observer> subscribers;
    public int nbCoups;
    public String mapName = "Ta mère";
    public String authorName = "En chemise";
    public int score = 12;

    public Modele(){
        subscribers = new ArrayList<Observer>();
        nbCoups = 0;
    }

    public void subscribe(Observer ob){
        subscribers.add(ob);
    }

    public void notifier(){
        for (Observer ob : subscribers){
            ob.actualiser();
        }
    }

}
