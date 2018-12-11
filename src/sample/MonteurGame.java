package sample;

import Game.*;
import javafx.geometry.Pos;
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

        HBox buttonUR = new HBox(vue.btnUnd, vue.btnRed);
        buttonUR.setAlignment(Pos.TOP_CENTER);

        GridPane buttonArrows = new GridPane();
        buttonArrows.add(vue.btnLe, 0, 1);
        buttonArrows.add(vue.btnDo, 1, 1);
        buttonArrows.add(vue.btnRi, 2, 1);
        buttonArrows.add(vue.btnUp, 1, 0);
        buttonArrows.setAlignment(Pos.TOP_CENTER);

        VBox layout2 = new VBox();
        layout2.getChildren().addAll(
                vue.nbCoups,
                vue.nom,
                vue.btnRep,
                vue.btnRes,
                buttonUR,
                buttonArrows,
                vue.back
        );
        layout2.setMinWidth(300);
        layout2.setAlignment(Pos.TOP_CENTER);
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
        int height = map.map.size();
        int width = 0;
        for (int y = 0; y < map.map.size(); y++) {
            if (width == 0 || map.map.get(y).size()>width){
                width = map.map.get(y).size();
            }
        }

        for (int y = 0; y < map.map.size(); y++){
            for (int x = 0; x < map.map.get(y).size(); x++){

                int heightBlock = 700/height;
                int widthBlock = 1200/width;
                int sizeBlock = Math.min(heightBlock, widthBlock) > 100 ? 100 : Math.min(heightBlock, widthBlock);

                if (!map.map.get(y).get(x).isFree()){
                    if (map.map.get(y).get(x).content instanceof Caisse){
                        sizeBlock *= 0.9;
                    }
                    MenuVue.addImageGridpane(vue.map, "PNG/" + map.map.get(y).get(x).content.img(), sizeBlock, x, y);
                }else if (map.map.get(y).get(x) instanceof CaseArrive){
                    MenuVue.addImageGridpane(vue.map, "PNG/" + map.map.get(y).get(x).img(), sizeBlock/3, x, y);
                }
            }
        }

    }
}
