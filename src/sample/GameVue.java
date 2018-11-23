package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameVue extends Vue{

    ImageView img = new ImageView(new Image("https://lasueur.com/wp-content/uploads/2018/06/Pokemon.jpg"));



    Label nbCoups = new Label("0");
    Label nom = new Label("Map de xxxxx");
    Button btnRep = new Button("Replay");
    Button btnRes = new Button("Reset");
    Button btnUnd = new Button("Undo");
    Button btnRed = new Button("Redo");
    Button btnUp = new Button("/\\");
    Button btnDo = new Button("\\/");
    Button btnRi = new Button("->");
    Button btnLe = new Button("<-");
    Button back = new Button("back");

}
