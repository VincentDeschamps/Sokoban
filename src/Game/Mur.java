package Game;

import javafx.scene.image.Image;

public class Mur extends GameObject{

    public final String img(){return "Wall_Black.png";}

    public Mur(int posX, int posY){
        super(posX,posY);
    }
}
