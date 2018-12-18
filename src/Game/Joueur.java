package Game;

public class Joueur extends GameObject{

    //character state is the direction he's looking at (4: bottom, 7: top, 1: left, 2: right)
    int state = 4;

    public final String img() {return "Character"+state+".png";}

    public Joueur(int posX, int posY){
        super(posX,posY);
    }

    public void setState(int state) {
        this.state = state;
    }
}
