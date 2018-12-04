package sample;

import javafx.scene.image.Image;

public class CaseArrive implements GameObject {

    int posX;
    int posY;
    Image img;
    boolean containsCrate;

    CaseArrive(int posX, int posY, Image img,boolean containsCrate){
        this.posX = posX;
        this.posY = posY;
        this.img = img;
        this.containsCrate = containsCrate;
    }

    @Override
    public int getPosX() {
        return this.posX;
    }

    @Override
    public int getPosY() {
        return this.posY;
    }

    void setContainsCrate(boolean status){
        this.containsCrate = status;
    }

    @Override
    public void ActualiserCoordonnees(int x, int y) {
        this.posX += x;
        this.posY += y;
    }

    public boolean contientUneCaisse(){
        return containsCrate;
    }
}
