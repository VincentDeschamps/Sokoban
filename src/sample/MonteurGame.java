package sample;

import Game.Caisse;
import Game.CaseArrive;
import Game.Map;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Game Scene Builder
 */
public class MonteurGame {

    /**
     * Builds the Game Scene with all elements in the parameters
     * @param vue GameVue containing all javafx elems to put on the scene
     * @return "Scene" to show in the app
     */
    public static Scene createScene(GameVue vue){
        Pane layout = new HBox();

        vue.createMap();

        vue.map.setBackground(new Background(vue.BGForMap));
        layout.getChildren().add(vue.map);

        HBox buttonUR = new HBox(vue.btnUnd, vue.btnRed);
        buttonUR.setAlignment(Pos.TOP_CENTER);

        VBox layout2 = new VBox();
        layout2.getChildren().addAll(
                vue.nbCoups,
                vue.nom,
                vue.btnRep,
                vue.btnRes,
                buttonUR,
                vue.back,
                new HBox(vue.previousMap, vue.nextMap)
        );
        layout2.setMinWidth(300);
        layout2.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().add(layout2);
        return new Scene(layout, 1500, 720);
    }

}
