package Game;


public class Caisse extends GameObject{

    public final String img() { return "Crate_Brown.png";}

    public Caisse(int posX, int posY){
        super(posX,posY);
    }

    public Caisse(Caisse c){
        super(c.getPosX(), c.getPosY());
    }
}
