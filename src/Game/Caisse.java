package Game;

import javafx.scene.image.Image;

public class Caisse extends GameObject{

    public final String img() { return "Crate_Beige.png";}

    public Caisse(int posX, int posY){
        super(posX,posY);
    }
}
