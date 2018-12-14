package sample;

import Game.Caisse;
import Game.CaseArrive;
import Game.Map;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 * Vue of the Game part of the app. Where the user can play to the game.
 */
public class GameVue implements Observer {

    ModeleSujet modele;

    GridPane map;

    Label nbCoups;
    Label nom;
    Button btnRep;
    Button btnRes;
    Button btnUnd;
    Button btnRed;
    Button back;
    Button nextMap;
    Button previousMap;
    BackgroundImage BGForMap;
    boolean isVisible;

    public GameVue(ModeleSujet modele){
        this.modele = modele;
        modele.subscribe(this);
        nbCoups = new Label(modele.getnbCoups()+"");
        nbCoups.setFont(Font.font("Webdings", 60));
        nom = new Label("Map " + modele.getMapName() + " de " + modele.getAuthorName());
        btnRep = new Button("Replay");
        btnRes = new Button("Reset");
        btnUnd = new Button("Undo");
        btnRed = new Button("Redo");
        back = new Button("back");
        previousMap = new Button("Previous");
        nextMap = new Button("Next");
        map = new GridPane();
        map.setAlignment(Pos.CENTER);
        map.setMinWidth(1200);
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
            createMap();
            if (modele.curSelectedMap == 0) {
                previousMap.setDisable(true);
                nextMap.setDisable(false);
            }
            else if (modele.curSelectedMap == modele.mapPool.size() - 1){
                nextMap.setDisable(true);
                previousMap.setDisable(false);
            } else {
                previousMap.setDisable(false);
                nextMap.setDisable(false);
                System.out.println("ici");

            }
        }

    }


    /**
     * Creates the map's view to show from the current map on the modele
     */
    public void createMap(){
        Map map = modele.getMap();

        this.map.getChildren().clear();
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
                    MenuVue.addImageGridpane(this.map, "PNG/" + map.map.get(y).get(x).content.img(), sizeBlock, x, y);
                }else if (map.map.get(y).get(x) instanceof CaseArrive){
                    MenuVue.addImageGridpane(this.map, "PNG/" + map.map.get(y).get(x).img(), sizeBlock/3, x, y);
                }
            }
        }

    }
}
