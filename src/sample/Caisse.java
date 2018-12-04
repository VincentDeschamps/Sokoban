package sample;

import javafx.scene.image.Image;

public class Caisse implements GameObject{

    int posX;
    int posY;
    static Image img;
    Modele m;

    Caisse(int posX, int posY){
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

    void move(int posX, int posY){
        this.posX += posX;
        this.posY += posY;
    }
}
