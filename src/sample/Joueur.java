package sample;

import javafx.scene.image.Image;

public class Joueur implements GameObject{

    int posX;
    int posY;
    static Image img;
    Modele m;


    Joueur(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public int getPosX(){
        return this.posX;
    }

    @Override
    public int getPosY(){
        return this.posY;
    }

    @Override
    public void ActualiserCoordonnees(int x, int y){
        this.posX += x;
        this.posY += y;
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
