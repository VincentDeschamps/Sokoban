package sample;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MonteurAside {
    AsideVue vue;

    MonteurAside(AsideVue v){
        vue = v;
    }

    public Pane createPane(){
        VBox layout = new VBox();
        layout.getChildren().addAll(
                vue.nbCoups,
                vue.nom,
                vue.btnRep,
                vue.btnRes,
                new HBox(vue.btnUnd, vue.btnRed),
                vue.btnUp,
                new HBox(vue.btnLe, vue.btnDo, vue.btnRi),
                vue.back
        );

        return layout;
    }
}
