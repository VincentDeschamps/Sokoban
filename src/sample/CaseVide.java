package sample;

public class CaseVide implements GameObject {

    int posX;
    int posY;

    CaseVide(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public int getPosX() {
        return this.posX;
    }

    @Override
    public int getPosY() {
        return this.posY;
    }

    @Override
    public void ActualiserCoordonnees(int x, int y) {
        this.posX += x;
        this.posY += y;
    }
}
