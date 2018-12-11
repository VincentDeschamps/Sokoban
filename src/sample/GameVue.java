package sample;

import Game.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.awt.font.ImageGraphicAttribute;

public class GameVue extends Vue implements Observer {

    ModeleSujet modele;

    GridPane map;

    Label nbCoups;
    Label nom;
    Button btnRep;
    Button btnRes;
    Button btnUnd;
    Button btnRed;
    Button btnUp;
    Button btnDo;
    Button btnRi;
    Button btnLe;
    Button back;
    BackgroundImage BGForMap;
    boolean isVisible;

    public GameVue(ModeleSujet modele){
        this.modele = modele;
        modele.subscribe(this);
        nbCoups = new Label(modele.getnbCoups()+"");
        nom = new Label("Map " + modele.getMapName() + " de " + modele.getAuthorName());
        btnRep = new Button("Replay");
        btnRes = new Button("Reset");
        btnUnd = new Button("Undo");
        btnRed = new Button("Redo");
        btnUp = new Button("/\\");
        btnDo = new Button("\\/");
        btnRi = new Button("->");
        btnLe = new Button("<-");
        back = new Button("back");
        map = new GridPane();
        BGForMap = new BackgroundImage(
                new Image("PNG/GroundGravel_Grass.png",50,50,true,true),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

    }


    @Override
    public void actualiser() {
        if (isVisible){
            nbCoups.setText(modele.getnbCoups()+"");
            nom.setText("Map " + modele.getMapName() + " de " + modele.getAuthorName());
            MonteurGame.createMap(this);
        }

    }
}
