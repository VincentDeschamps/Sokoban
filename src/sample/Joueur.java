package sample;

public class Joueur implements GameObject{

    int posX;
    int posY;
    Modele m;


    Joueur(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }


    //TODO : Ajouter conditions si plusieurs caisses devant le joueur
    void moveRight(){
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
    }
}
