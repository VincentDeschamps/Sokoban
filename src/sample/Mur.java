package sample;

import javafx.scene.image.Image;

public class Mur implements GameObject{

    int posX;
    int posY;
    Image img;
    Modele m;

    Mur(int posX, int posY, Image img){
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
}
