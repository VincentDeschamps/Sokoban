package sample;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MonteurMenu {


    public static Scene createScene(MenuVue mv){
        StackPane layout = new StackPane();
        layout.getChildren().add(mv.goToGame);
       return new Scene(layout, 1500, 720);
    }
}
