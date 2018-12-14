package Game;

import java.util.ArrayList;

public class Map {

    public ArrayList<ArrayList<Case>> map;
    public ArrayList<CaseArrive> goals = new ArrayList<>();

    public Joueur player;

    public Map(ArrayList<ArrayList<Case>> map, Joueur j){
        this.map = map;
        this.player = j;
        for (ArrayList<Case> line: this.map) {
            for (Case c: line){
                if (c instanceof CaseArrive){
                goals.add((CaseArrive)c);
                }
            }
        }
    }

    public Map(Map map){
        this.map = new ArrayList();
        for (int i = 0; i<map.map.size(); i++){
            ArrayList<Case> ligne = new ArrayList<>();
            for (int j = 0; j<map.map.get(i).size(); j++){
                if (map.map.get(i).get(j) instanceof CaseArrive){
                    if (map.map.get(i).get(j).content instanceof Caisse){
                        ligne.add(new CaseArrive(map.map.get(i).get(j).getX(), map.map.get(i).get(j).getY(), new Caisse((Caisse) map.map.get(i).get(j).content)));
                    }
                    else{
                        ligne.add(new CaseArrive(map.map.get(i).get(j).getX(), map.map.get(i).get(j).getY(), map.map.get(i).get(j).content));
                    }
                    goals.add((CaseArrive)ligne.get(j));
                }
                else {
                    if (map.map.get(i).get(j).content instanceof Caisse){
                        ligne.add(new Case(map.map.get(i).get(j).getX(), map.map.get(i).get(j).getY(), new Caisse((Caisse) map.map.get(i).get(j).content)));
                    }
                    else {
                        ligne.add(new Case(map.map.get(i).get(j).getX(), map.map.get(i).get(j).getY(), map.map.get(i).get(j).content));
                    }
                }
            }
            this.map.add(ligne);
        }
        this.player = new Joueur(map.player.getPosX(), map.player.getPosY());
    }

    public ArrayList<ArrayList<Case>> getMap(){
        return this.map;
    }

    public void setMap(ArrayList<ArrayList<Case>> map){
        this.map = map;
    }

    public Joueur getPlayer(){
        return this.player;
    }

    public void setPlayer(Joueur j){
        this.player = j;
    }

    /**
     *
     * @param  go  GameObject que l'on veut deplacer
     * @param  x   -1 ou 1 selon si on veux aller a gauche ou a droite (-1 pour gauche et 1 pour droite)
     * @param  y   -1 ou 1 selon si on veux aller en haut ou en bas (-1 pour haut et 1 pour bas)
     * @return      Un booleen indiquant si le deplacement est valide ou non etg, s'il l'est, l'applique
     */
    public boolean isMovable(GameObject go, int x, int y){
        if (go.getPosY()+y >= map.size() | go.getPosY()+y < 0){
            return false;
        }
        if (map.get(go.getPosY()+y).size() <= go.getPosX()+x | go.getPosX()+x < 0){
            return false;
        }


        Case nextCase = map.get(go.getPosY()+y).get(go.getPosX()+x);
        if(nextCase.isFree() || (!(go instanceof Caisse) && nextCase.content instanceof Caisse && isMovable(nextCase.content, x, y))){
            map.get(go.getPosY()).get(go.getPosX()).removeContent();
            nextCase.addContent(go);
            go.setX(go.getPosX()+x);
            go.setY(go.getPosY()+y);
            return true;
        }
        return false;
    }

    public boolean checkVictory(){
        for (CaseArrive c: goals) {
            if (c.isFree() || !(c.content instanceof Caisse)) return false;
        }
        return true;
    }
}
