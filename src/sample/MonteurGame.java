package sample;

import Game.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;

public class MonteurGame {

    public static Scene createScene(GameVue vue){
        Pane layout = new HBox();

        createMap(vue);


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


    public static void createMap(GameVue vue){
        Map map = vue.modele.getMap();

        vue.map.getChildren().clear();
        for (int y = 0; y < map.map.size(); y++){
            for (int x = 0; x < map.map.get(y).size(); x++){

                if (!map.map.get(y).get(x).isFree()){
                    vue.map.add(new ImageView(new Image("PNG/" + map.map.get(y).get(x).content.img(),50,50,true, true)), x,y);
                }else if (map.map.get(y).get(x) instanceof CaseArrive){
                    vue.map.add(new ImageView(new Image("PNG/" + map.map.get(y).get(x).img(),50,50,true, true)), x,y);
                }
            }
        }

    }
}
