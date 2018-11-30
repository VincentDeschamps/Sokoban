package sample;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class MenuVue extends Vue implements Observer{

    public Modele modele;

    public Button goToGame;
    public BackgroundImage myBI;
    public GridPane menuAnimation;
    public Label title;

    public MenuVue(Modele modele){
        this.modele = modele;
        this.modele.subscribe(this);

        title = new Label(modele.title);
        goToGame = new Button(modele.buttonText);

        myBI = new BackgroundImage(
                        new Image("PNG/GroundGravel_Grass.png",50,50,true,true),
                        BackgroundRepeat.REPEAT,
                        BackgroundRepeat.REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
        menuAnimation = new GridPane();
        initGridSize();
    }

    @Override
    public void actualiser() {
        Platform.runLater(()-> {

            if (menuAnimation.getChildren().size() == 18){
                menuAnimation.getChildren().clear();
                initGridSize();
            }
            addImageFrame(modele.animationMenu.get(0), modele.animationMenu.get(2), modele.animationMenu.get(1), 3);

            if (menuAnimation.getChildren().size() > 2){
                menuAnimation.getChildren().get(menuAnimation.getChildren().size()-2).setVisible(false);
            }
        });
    }


    public void addImageFrame(int nbImage, int size, int col, int row){
        ImageView img = new ImageView(new Image("PNG/Character"+nbImage+".png", size, size, true, true));
        menuAnimation.add(img, col, row);
    }

    public void initGridSize(){
        addImageFrame(4,100,1,3);
        menuAnimation.getChildren().get(0).setVisible(false);
    }
}
