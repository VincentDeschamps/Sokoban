package Game;

import javafx.scene.image.Image;

public class Joueur extends GameObject{

    public final String img() {return "Character4.png";}


    public Joueur(int posX, int posY){
        super(posX,posY);
    }


    //TODO : Ajouter conditions si plusieurs caisses devant le joueur
    /*void moveRight(){
       // if (this.posX >= map.longueur)
            this.posX++;
    }

    void moveLeft(){
        if (this.posX > 0)
            this.posX--;
    }

    void moveUp(){
        if (this.posY < 0)
            this.posY++;
    }

    void moveDown(){
       // if (this.posY <= map.hauteur)
            this.posY--;
    }*/


}
