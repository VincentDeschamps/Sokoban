package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class MonteurMenu {

    MenuVue menuVue;

    MonteurMenu(MenuVue menuVue){
        this.menuVue = menuVue;
    }

    public static Scene createScene(MenuVue mv){
        //creating borderPane for the scene
        BorderPane layout = new BorderPane();

        //adding vertical layout for the top part of the border pane
        VBox vertical = new VBox();
        layout.setTop(vertical);

        //adding the title and the animated soko to the top
        vertical.getChildren().addAll(mv.title, mv.menuAnimation);
        vertical.setAlignment(Pos.BOTTOM_CENTER);
        mv.menuAnimation.setAlignment(Pos.CENTER);

        //creating vertical layout for the center of the border pane
        VBox mainLayout = new VBox();
        layout.setCenter(mainLayout);

        //adding horizontal layout label and combo box for the choice of the map
        HBox horizontal = new HBox();
        horizontal.setPadding(new Insets(0,0,20,0));
        mainLayout.getChildren().add(horizontal);

        //adding label and combo box to the horizontal layout
        horizontal.getChildren().addAll(mv.choixLabel, mv.choixTableau);
        horizontal.setAlignment(Pos.TOP_CENTER);

        //creating the layout for the map preview
        GridPane mapPreview = new GridPane();
        mainLayout.getChildren().add(mapPreview);

        //adding the map preview to the grid pane
        mapPreview.getChildren().add(mv.mapPreview);
        mapPreview.setAlignment(Pos.CENTER);

        //setting up the play button to the bottom
        mv.goToGame.getStyleClass().add("rich-blue");
        layout.setBottom(mv.goToGame);
        layout.setAlignment(mv.goToGame, Pos.CENTER);
        layout.setPadding(new Insets(0,0,10,0));

        //Set the background
        layout.setBackground(new Background(mv.BGForMenu));
        mapPreview.setBackground(new Background(mv.BGForPreview));

        //creating scene and adding style
        Scene scene = new Scene(layout, 1500, 720);
        scene.getStylesheets().add("sample/style.css");

        return scene;
    }
}
