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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

//Vue for the Menu
public class MenuVue extends Vue implements Observer{

    public Modele modele;

    //"PLAY!" button
    public Button goToGame;
    //background
    public BackgroundImage BGForMenu;
    //Gridpane layout that will host the animation
    public GridPane menuAnimation;
    //"SOKOBAN" title
    public Label title;
    //"Combo box to chose an xsb file correponding to the map"
    public ComboBox choixTableau;
    //"Choisir un tableau" label
    public Label choixLabel;
    // "[[6,1,25],[5,1,50],[6,1,75]]" for exemple -> describe the images of the animation
    // here, 1st image is file Character6.png, in column 1, of size 25, then 2nd is Character5.png, in column 1, of size 50 .....
    public ArrayList<List<Integer>> frameList;
    //gridpane layout that will host the map preview
    public GridPane mapPreview;
    //Runnable that will be called every 500ms to update animation
    Runnable changeGrid;
    //Used to call changeGrid every 500ms
    ScheduledExecutorService executorService;

    public MenuVue(Modele modele){
        this.modele = modele;
        this.modele.subscribe(this);
        this.changeGrid = new Animate();

        //setting the title / size / font
        title = new Label(modele.title);
        title.setTextFill(Color.web("#1680ad"));
        title.setFont(Font.font("Webdings", 100));

        goToGame = new Button(modele.buttonText);

        //setting the background
        BGForMenu = new BackgroundImage(
                        new Image("PNG/GroundGravel_Grass.png",50,50,true,true),
                        BackgroundRepeat.REPEAT,
                        BackgroundRepeat.REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);

        //this instance is used in MonteurMenu
        executorService = Executors.newSingleThreadScheduledExecutor();

        //init gridpane for the animation
        menuAnimation = new GridPane();
        initGridSize();

        //setting the label of the combo box
        choixLabel = new Label(modele.labelChoix);
        choixLabel.setTextFill(Color.web("#1680ad"));
        choixLabel.setFont(Font.font("Webdings", 20));

        // adding every frame of the animation
        frameList = new ArrayList<>();
        frameList.add(Arrays.asList(6,1,25));
        frameList.add(Arrays.asList(5,1,50));
        frameList.add(Arrays.asList(6,1,75));
        frameList.add(Arrays.asList(5,1,100));
        frameList.add(Arrays.asList(4,1,100));
        frameList.add(Arrays.asList(2,1,100));
        frameList.add(Arrays.asList(3,2,100));
        frameList.add(Arrays.asList(3,3,100));
        frameList.add(Arrays.asList(3,4,100));
        frameList.add(Arrays.asList(2,5,100));
        frameList.add(Arrays.asList(7,5,100));
        frameList.add(Arrays.asList(8,5,100));
        frameList.add(Arrays.asList(9,5,75));
        frameList.add(Arrays.asList(8,5,50));
        frameList.add(Arrays.asList(9,5,25));
        frameList.add(Arrays.asList(9,5,1));

        //combo box containing mapPool (the files in src/tableaux/)
        choixTableau = new ComboBox(modele.mapPool);
        choixTableau.setMinWidth(300);

        //grid pane containing the preview of the map
        mapPreview = new GridPane();
        previewAppend();
    }


    //When Controller modify Modele then Modele call this
    //Get the new informations in the modele and change the display
    @Override
    public void actualiser() {
        Platform.runLater(()-> {

            choixTableau.setItems(modele.mapPool);
            previewAppend();

            if (menuAnimation.getChildren().size() == 17){
                menuAnimation.getChildren().clear();
                initGridSize();
            }
            addImageGridpane(menuAnimation, "PNG"+File.separator+"Character"+modele.animationMenu.get(0)+".png", modele.animationMenu.get(2), modele.animationMenu.get(1), 3);

            if (menuAnimation.getChildren().size() > 2){
                menuAnimation.getChildren().get(menuAnimation.getChildren().size()-2).setVisible(false);
            }
        });
    }

    //generating the preview of the map by adding to the gridpane the images corresponding to modele.mapFile
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
                        case ' ':   addImageGridpane(mapPreview, "PNG"+ File.separator+"GroundGravel_Grass.png", sizeBloc, j, i);
                            break;
                    }
                }
            }
        }
    }

    //add 'image' in 'layout' with size 'size', in column 'col and row 'row'
    public void addImageGridpane(GridPane layout, String image, int size, int col, int row){
        ImageView img = new ImageView(new Image(image, size, size, true, true));
        layout.add(img, col, row);
        layout.setHalignment(img, HPos.CENTER);
    }

    //set the grid size
    public void initGridSize(){
        addImageGridpane(menuAnimation, "PNG/Character"+4+".png",100,1,3);
        menuAnimation.getChildren().get(0).setVisible(false);
    }

    //'indiceFrame' is allways the indice of the frame in frameList that is currently displayed
    // Animate is updating this indice at each call every 500ms
    class Animate implements Runnable {
        int indiceFrame;

        Animate() {
            this.indiceFrame = 0;
        }

        public void run() {
            System.out.println("ANIM");
            modele.animationMenu = frameList.get(indiceFrame);
            modele.notifier();

            // 0 <= indiceFrame < length of frameList
            this.indiceFrame =  this.indiceFrame+1 < frameList.size() ? this.indiceFrame+1 : 0;
        }
    }

}
