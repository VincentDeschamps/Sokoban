package sample;

import Game.Caisse;
import Game.CaseArrive;
import Game.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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

        VBox nbCoupMap = new VBox();
        nbCoupMap.setAlignment(Pos.TOP_CENTER);
        nbCoupMap.getChildren().addAll(vue.nbCoups, vue.nom);
        nbCoupMap.setPadding(new Insets(10,0,10,0));

        HBox buttonUR = new HBox(vue.btnUnd, vue.btnRed);
        buttonUR.setAlignment(Pos.TOP_CENTER);
        vue.btnRed.getStyleClass().add("rich-blue");
        vue.btnUnd.getStyleClass().add("rich-blue");
        buttonUR.setPadding(new Insets(10,0,10,0));

        VBox repRes = new VBox();
        repRes.getChildren().addAll(vue.btnRep, vue.btnRes);
        repRes.setAlignment(Pos.TOP_CENTER);
        repRes.setPadding(new Insets(10,0,0,0));

        VBox gameCommands = new VBox();
        gameCommands.getChildren().addAll(vue.gameCommandLabel, repRes, buttonUR);
        gameCommands.setAlignment(Pos.TOP_CENTER);

        gameCommands.setPadding(new Insets(10,20,0,20));


        HBox buttonPN = new HBox(vue.previousMap, vue.nextMap);
        buttonPN.setAlignment(Pos.TOP_CENTER);
        vue.previousMap.getStyleClass().add("rich-blue");
        vue.nextMap.getStyleClass().add("rich-blue");
        buttonPN.setPadding(new Insets(10,0,10,0));

        VBox navigationCommands = new VBox();
        navigationCommands.getChildren().addAll(vue.navigationCommandLabel, buttonPN, vue.back);
        navigationCommands.setAlignment(Pos.TOP_CENTER);

        navigationCommands.setPadding(new Insets(10,20,10,20));

        vue.btnRep.getStyleClass().add("rich-blue");
        vue.btnRes.getStyleClass().add("rich-blue");
        vue.back.getStyleClass().add("rich-blue");

        VBox layout2 = new VBox();
        layout2.getChildren().addAll(
                nbCoupMap,
                gameCommands,
                navigationCommands
        );
        layout2.setMinWidth(300);
        layout2.setAlignment(Pos.TOP_CENTER);

        //Set the background
        layout.setBackground(new Background(vue.BGForPanel));

        layout.getChildren().add(layout2);

        Scene scene = new Scene(layout, 1500, 720);
        scene.getStylesheets().add("sample/style.css");

        return scene;
    }

}
