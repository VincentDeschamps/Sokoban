package Game;

import java.util.ArrayList;

public class Map {

    public ArrayList<ArrayList<Case>> map;

    public Joueur player;

    public Map(){
        this.map = null;
    }

    public Map(ArrayList<ArrayList<Case>> map, Joueur j){
        this.map = map;
        this.player = j;
    }

    public void ActualiserGameObject(GameObject go){
        map.get(go.getPosX()).get(go.getPosY()).addContent(go);
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

    /**
     *
     * @param  j   Joueur que l'on veut deplacer
     * @param  x   -1 ou 1 selon si on veux aller a gauche ou a droite (-1 pour gauche et 1 pour droite)
     * @param  y   -1 ou 1 selon si on veux aller en haut ou en bas (-1 pour haut et 1 pour bas)
     * @result     Verifie si le deplacement est valide et deplace la caisse pousse ainsi que le joueur
     */
    /*
    void MovePlayer(Joueur j, int x, int y){
        GameObject caseCible = map.get(j.getPosX() + x).get(j.getPosY() + y).content;
        if (caseCible instanceof CaseVide || caseCible instanceof CaseArrive){
            caseCible.ActualiserCoordonnees(caseCible.getPosX()+x, caseCible.getPosY()+y);
            ActualiserGameObject(caseCible);
            j.ActualiserCoordonnees(x,y);
            ActualiserGameObject(j);
        }
        else if (caseCible instanceof Caisse){
            if(isMovable(caseCible,x,y)){
                if (caseCible instanceof CaseArrive){
                   // ((CaseArrive) caseCible).setContainsCrate(true);
                }
                //TODO Gerer les non deplacements des CaseArrive et ne pas les supprimer en mettant une caisse ou joueur dessus
                caseCible.ActualiserCoordonnees(caseCible.getPosX()+x, caseCible.getPosY()+y);
                ActualiserGameObject(caseCible);
                j.ActualiserCoordonnees(x,y);
                ActualiserGameObject(j);
            }
        }

        else if (caseCible instanceof Mur){}

    }*/
}
