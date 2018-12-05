package Game;

public abstract class GameObject {
    private int x, y;

    protected GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getPosX(){return x;}
    public int getPosY(){return y;}
    //public abstract void ActualiserCoordonnees(int x, int y);

    public void setX(int i){x = i;}

    public void setY(int i){y = i;}
}
