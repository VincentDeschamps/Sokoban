package sample;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MonteurGame {

    public static Scene createScene(GameVue vue){
        Pane layout = new HBox();

        layout.getChildren().add(vue.img);

        VBox layout2 = new VBox();
        layout2.getChildren().addAll(
                vue.nbCoups,
                vue.nom,
                vue.btnRep,
                vue.btnRes,
                new HBox(vue.btnUnd, vue.btnRed),
                vue.btnUp,
                new HBox(vue.btnLe, vue.btnDo, vue.btnRi),
                vue.back
        );
        layout.getChildren().add(layout2);
/*


        this.layout.getChildren().add(img);

        MonteurAside monteurA = new MonteurAside(new AsideVue());

        this.layout.getChildren().add(monteurA.createPane());

        Button btn2 = new Button("Swap Menu");

        layout.getChildren().add(btn2);
*/
        return new Scene(layout, 1500, 720);
    }
}
