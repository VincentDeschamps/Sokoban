package sample;

import javafx.scene.image.Image;

public class Joueur implements GameObject{

    int posX;
    int posY;
    Image img;
    Modele m;


    Joueur(int posX, int posY, Image img){
        this.posX = posX;
        this.posY = posY;
        this.img = img;
    }

    @Override
    public int getPosX(){
        return this.posX;
    }

    @Override
    public int getPosY(){
        return this.posY;
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

    void ActualiserCoordonnees(int x, int y){

    }
}
