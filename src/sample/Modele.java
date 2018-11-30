package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Modele {

    public ArrayList<Observer> subscribers;
    public int nbCoups;
    public String mapName = "Ta mère";
    public String authorName = "En chemise";
    public int score = 12;
    public List<Integer> animationMenu;
    public ArrayList<List<Integer>> frameList;
    public String title = "SOKOBAN";
    public String buttonText = "JOUER !";

    public Modele(){
        subscribers = new ArrayList<Observer>();
        nbCoups = 0;

        frameList = new ArrayList<>();
        frameList.add(Arrays.asList(6,1,25));
        frameList.add(Arrays.asList(5,1,50));
        frameList.add(Arrays.asList(6,1,75));
        frameList.add(Arrays.asList(5,1,100));
        frameList.add(Arrays.asList(4,1,100));
        frameList.add(Arrays.asList(2,1,100));
        frameList.add(Arrays.asList(3,2,100));
        frameList.add(Arrays.asList(3,3,100));
        frameList.add(Arrays.asList(3,4,100));
        frameList.add(Arrays.asList(3,5,100));
        frameList.add(Arrays.asList(2,6,100));
        frameList.add(Arrays.asList(7,6,100));
        frameList.add(Arrays.asList(8,6,100));
        frameList.add(Arrays.asList(9,6,75));
        frameList.add(Arrays.asList(8,6,50));
        frameList.add(Arrays.asList(9,6,25));
        frameList.add(Arrays.asList(9,6,1));
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
