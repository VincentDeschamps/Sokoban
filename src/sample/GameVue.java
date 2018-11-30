package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameVue extends Vue implements Observer {

    Modele modele;

    ImageView img = new ImageView(new Image("https://lasueur.com/wp-content/uploads/2018/06/Pokemon.jpg"));

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
    }


    @Override
    public void actualiser() {
        nbCoups.setText(modele.nbCoups+"");
        nom.setText("Map " + modele.mapName + " de " + modele.authorName);

    }
}
