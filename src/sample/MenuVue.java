package sample;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

public class MenuVue extends Vue implements Observer{

    public Modele modele;

    public Button goToGame;
    public BackgroundImage BGForMenu;
    public BackgroundImage BGForPreview;
    public GridPane menuAnimation;
    public Label title;
    public ComboBox choixTableau;
    public Label choixLabel;
    public GridPane mapPreview;

    public MenuVue(Modele modele){
        this.modele = modele;
        this.modele.subscribe(this);

        title = new Label(modele.title);
        title.setTextFill(Color.web("#1680ad"));
        title.setFont(Font.font("Webdings", 100));

        goToGame = new Button(modele.buttonText);

        BGForMenu = new BackgroundImage(
                        new Image("PNG/GroundGravel_Grass.png",50,50,true,true),
                        BackgroundRepeat.REPEAT,
                        BackgroundRepeat.REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);

        BGForPreview = new BackgroundImage(
                        new Image("PNG/Ground_Sand.png",50,50,true,true),
                        BackgroundRepeat.REPEAT,
                        BackgroundRepeat.REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);

        menuAnimation = new GridPane();
        initGridSize();

        choixLabel = new Label(modele.labelChoix);
        choixLabel.setTextFill(Color.web("#1680ad"));
        choixLabel.setFont(Font.font("Webdings", 20));

        choixTableau = new ComboBox(modele.mapPool);
        choixTableau.setMinWidth(300);

        mapPreview = new GridPane();
        previewAppend();
    }

    @Override
    public void actualiser() {
        Platform.runLater(()-> {

            choixTableau.setItems(modele.mapPool);
            previewAppend();

            if (menuAnimation.getChildren().size() == 17){
                menuAnimation.getChildren().clear();
                initGridSize();
            }
            addImageGridpane(menuAnimation, "PNG/Character"+modele.animationMenu.get(0)+".png", modele.animationMenu.get(2), modele.animationMenu.get(1), 3);

            if (menuAnimation.getChildren().size() > 2){
                menuAnimation.getChildren().get(menuAnimation.getChildren().size()-2).setVisible(false);
            }
        });
    }

    public void previewAppend(){
        mapPreview.getChildren().clear();
        if (modele.mapFile.size()>0){
            int heightMap = 0;
            for (int i = 0; i < modele.mapFile.size(); i++) {
                if (modele.mapFile.get(i)[0] == 'T') {
                    heightMap = i;
                }
            }
            int sizeBloc = 400/heightMap;
            for (int i = 0; i < heightMap; i++){
                for (int j = 0; j< modele.mapFile.get(i).length; j++){
                    char e = modele.mapFile.get(i)[j];
                    switch(e){
                        case '#':   addImageGridpane(mapPreview, "PNG"+ File.separator+"Wall_Black.png", sizeBloc, j, i);
                            break;
                        case '.':   addImageGridpane(mapPreview, "PNG"+ File.separator+"EndPoint_Brown.png", sizeBloc/3, j, i);
                            break;
                        case '@':   addImageGridpane(mapPreview, "PNG"+ File.separator+"Character4.png", sizeBloc, j, i);
                            break;
                        case '$':   addImageGridpane(mapPreview, "PNG"+ File.separator+"Crate_Brown.png", (int) (0.9*sizeBloc), j, i);
                            break;
                        case ' ':   addImageGridpane(mapPreview, "PNG"+ File.separator+"Ground_Sand.png", sizeBloc, j, i);
                            break;
                    }
                }
            }
        }
    }

    public void addImageGridpane(GridPane layout, String image, int size, int col, int row){
        ImageView img = new ImageView(new Image(image, size, size, true, true));
        layout.add(img, col, row);
        layout.setHalignment(img, HPos.CENTER);
    }

    public void initGridSize(){
        addImageGridpane(menuAnimation, "PNG/Character"+4+".png",100,1,3);
        menuAnimation.getChildren().get(0).setVisible(false);
    }
}
