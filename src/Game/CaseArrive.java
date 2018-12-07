package Game;

import javafx.scene.image.Image;

public class CaseArrive extends Case {

    public final String img(){return "EndPoint_Blue.png";}

    public CaseArrive(int x, int y){
        super(x,y);
    }

    public CaseArrive(int x, int y, GameObject g){
        super(x, y, g);
    }

}
