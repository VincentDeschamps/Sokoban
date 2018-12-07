package sample;

import Game.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Modele {

    public ArrayList<Observer> subscribers;
    public int nbCoups;
    public String mapName = "Ta mère";
    public String authorName = "En chemise";
    public int score = 12;


    public List<Integer> animationMenu;
    public String title = "SOKOBAN";
    public String buttonText = "JOUER !";
    public ObservableList<String> mapPool;
    public String labelChoix = "Choisir un tableau : ";
    public ArrayList<char[]> mapFile = new ArrayList<>();

    public Map map;

    public Modele(){
        subscribers = new ArrayList<>();
        nbCoups = 0;
        mapPool = FXCollections.observableArrayList();
    }

    public void subscribe(Observer ob){
        subscribers.add(ob);
    }

    public void notifier(){
        for (Observer ob : subscribers){
            ob.actualiser();
        }

    }

    public void createMap(String url) throws Exception{
        try{
            map = MapParser.parseMap(url);
        } catch (Exception e){
            throw e;
        }
    }

}
