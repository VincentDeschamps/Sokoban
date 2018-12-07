package sample;

import Game.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;

public class MonteurGame {

    public static Scene createScene(GameVue vue){
        Pane layout = new HBox();


        for (int y = 0; y < vue.modele.map.map.size(); y++){
            for (int x = 0; x < vue.modele.map.map.get(y).size(); x++){

                if (!vue.modele.map.map.get(y).get(x).isFree()){
                    vue.map.add(new ImageView(new Image("PNG/" + vue.modele.map.map.get(y).get(x).content.img(),50,50,true, true)), x,y);
                }else if (vue.modele.map.map.get(y).get(x) instanceof CaseArrive){
                    vue.map.add(new ImageView(new Image("PNG/" + vue.modele.map.map.get(y).get(x).img(),50,50,true, true)), x,y);
                }
            }
        }

        vue.map.setBackground(new Background(vue.BGForMap));
        layout.getChildren().add(vue.map);

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
