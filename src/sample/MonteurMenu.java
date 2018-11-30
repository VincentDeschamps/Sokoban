package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class MonteurMenu {

    MenuVue menuVue;

    MonteurMenu(MenuVue menuVue){
        this.menuVue = menuVue;
    }

    public static Scene createScene(MenuVue mv){
        BorderPane layout = new BorderPane();

        VBox vertical = new VBox();
        layout.setTop(vertical);

        vertical.getChildren().addAll(mv.title, mv.menuAnimation);
        vertical.setAlignment(Pos.BOTTOM_CENTER);
        mv.menuAnimation.setAlignment(Pos.CENTER);

        layout.setBottom(mv.goToGame);
        layout.setAlignment(mv.goToGame, Pos.BOTTOM_CENTER);

        //Set the background
        layout.setBackground(new Background(mv.myBI));

        Scene scene = new Scene(layout, 1500, 720);

        return scene;
    }
}
