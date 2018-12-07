package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameVue extends Vue implements Observer {

    Modele modele;

    GridPane plateau = new GridPane();

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

    public GameVue(Modele modele){
        this.modele = modele;
        modele.subscribe(this);
        nbCoups = new Label(modele.nbCoups+"");
        nom = new Label("Map " + modele.mapName + " de " + modele.authorName);
        btnRep = new Button("Replay");
        btnRes = new Button("Reset");
        btnUnd = new Button("Undo");
        btnRed = new Button("Redo");
        btnUp = new Button("/\\");
        btnDo = new Button("\\/");
        btnRi = new Button("->");
        btnLe = new Button("<-");
        back = new Button("back");

        plateau.add(new Label("First"), 0,0);
        plateau.add(new Label("First"), 0,1);
        plateau.add(new Label("First"), 0,2);
        plateau.add(new Label("First"), 1,0);
        plateau.add(new Label("First"), 1,1);
        plateau.add(new Label("First"), 1,2);
        plateau.add(new Label("First"), 2,0);
        plateau.add(new Label("First"), 2,1);
        plateau.add(new Label("First"), 2,2);
    }


    @Override
    public void actualiser() {
        nbCoups.setText(modele.nbCoups+"");
        nom.setText("Map " + modele.mapName + " de " + modele.authorName);

    }
}
