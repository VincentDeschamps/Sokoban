package sample;

import java.util.ArrayList;

public class Map {

    ArrayList<ArrayList<GameObject>> map;

    Joueur player;

    Map(){
        this.map = null;
    }

    Map(ArrayList<ArrayList<GameObject>> map, Joueur j){
        this.map = map;
        this.player = j;
    }

    void ActualiserGameObject(GameObject go){
        map.get(go.getPosX()).set(go.getPosY(), go);
    }

    /**
     *
     * @param  go  GameObject que l'on veut deplacer
     * @param  x   -1 ou 1 selon si on veux aller a gauche ou a droite (-1 pour gauche et 1 pour droite)
     * @param  y   -1 ou 1 selon si on veux aller en haut ou en bas (-1 pour haut et 1 pour bas)
     * @return      Un booleen indiquant si le deplacement est valide ou non
     */
    boolean isMovable(GameObject go, int x, int y){
        GameObject caseCible = map.get(go.getPosX() + x).get(go.getPosY() + y);
        if(caseCible instanceof Mur || caseCible instanceof Caisse){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     *
     * @param  j   Joueur que l'on veut deplacer
     * @param  x   -1 ou 1 selon si on veux aller a gauche ou a droite (-1 pour gauche et 1 pour droite)
     * @param  y   -1 ou 1 selon si on veux aller en haut ou en bas (-1 pour haut et 1 pour bas)
     * @result     Verifie si le deplacement est valide et deplace la caisse pousse ainsi que le joueur
     */
    void MovePlayer(Joueur j, int x, int y){
        GameObject caseCible = map.get(j.getPosX() + x).get(j.getPosY() + y);
        if (caseCible instanceof CaseVide || caseCible instanceof CaseArrive){
            caseCible.ActualiserCoordonnees(caseCible.getPosX()+x, caseCible.getPosY()+y);
            ActualiserGameObject(caseCible);
            j.ActualiserCoordonnees(x,y);
            ActualiserGameObject(j);
        }
        else if (caseCible instanceof Caisse){
            if(isMovable(caseCible,x,y)){
                if (caseCible instanceof CaseArrive){
                    ((CaseArrive) caseCible).setContainsCrate(true);
                }
                //TODO Gerer les non deplacements des CaseArrive et ne pas les supprimer en mettant une caisse ou joueur dessus
                caseCible.ActualiserCoordonnees(caseCible.getPosX()+x, caseCible.getPosY()+y);
                ActualiserGameObject(caseCible);
                j.ActualiserCoordonnees(x,y);
                ActualiserGameObject(j);
            }
        }

        else if (caseCible instanceof Mur){}

    }
}
