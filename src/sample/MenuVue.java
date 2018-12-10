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

import javax.sound.midi.SysexMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * View for the application menu
 */
public class MenuVue extends Vue implements Observer{

    public Modele modele;


    public Button goToGame;
    public BackgroundImage BGForMenu;
    public Label title;
    public Label choixLabel;
    //Gridpane layout that will host the animation
    public GridPane menuAnimation;
    //ComboBox to chose an xsb file corresponding to the map
    public ComboBox choixTableau;
    public Label mapInfo;
    /*
     * A list that describes the images of the animation
     * For exemple : "[[6,1,25],[5,1,50],[6,1,75]]"
     * means that 1st image is file Character6.png, in column 1, of size 25 ; then 2nd is Character5.png, in column 1, of size 50 .....
     */
    public ArrayList<List<Integer>> frameList;
    //Gridpane layout that will host the map preview
    public GridPane mapPreview;
    //A runnable that will be called every 500mx (animation of the menu)
    Animate changeGrid;

    public MenuVue(Modele modele){
        this.modele = modele;
        this.modele.subscribe(this);

        //setting the title / size / font
        title = new Label(modele.title);
        title.setTextFill(Color.web("#1680ad"));
        title.setFont(Font.font("Webdings", 100));

        goToGame = new Button(modele.buttonText);
        goToGame.setDisable(true);

        //setting the background
        BGForMenu = new BackgroundImage(
                        new Image("PNG/GroundGravel_Grass.png",50,50,true,true),
                        BackgroundRepeat.REPEAT,
                        BackgroundRepeat.REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);

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
        mapInfo = new Label();
        mapInfo.setFont(Font.font("Webdings", 16));

        //grid pane containing the preview of the map
        mapPreview = new GridPane();
        previewAppend();

        changeGrid = new Animate();
        Thread animate = new Thread(changeGrid);
        animate.setDaemon(true);
        animate.start();
    }

    /**
     * Controller modify Modele then Modele call 'actualiser'
     * Get the new information in the modele and change the display
     */
    @Override
    public void actualiser() {
        Platform.runLater(()-> {
            choixTableau.setItems(modele.mapPool);
            previewAppend();
        });
    }

    public void actuAnim(){
        Platform.runLater(()-> {
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

    /**
     * Generate the preview of the map by adding to the GridPane the images corresponding to 'modele.mapFile'
     */
    public void previewAppend(){
        mapPreview.getChildren().clear();
        mapInfo.setText("");
        if (modele.mapFile.size()>0){
            int heightMap = modele.mapFile.size();
            mapInfo.setText("\"" + modele.mapName + "\" (créé par " + modele.authorName + ")");
            int sizeBloc = 380/heightMap;
            for (int i = 0; i < heightMap; i++){
                for (int j = 0; j< modele.mapFile.get(i).length; j++){
                    char e = modele.mapFile.get(i)[j];
                    switch(e){
                        case '#':   addImageGridpane(mapPreview, "PNG"+ File.separator+"Wall_Black.png", sizeBloc, j, i);
                            break;
                        case '.':   addImageGridpane(mapPreview, "PNG"+ File.separator+"EndPoint_Brown.png", sizeBloc/3, j, i);
                            break;
                        case '@' : case '+':   addImageGridpane(mapPreview, "PNG"+ File.separator+"Character4.png", sizeBloc, j, i);
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

    /**
     * Method used to put an image in a Gridpane at specified collumn and row indices and with specified size
     * @params layout The GridPane object where to add the image
     * @params image The String corresponding to the path of the image
     * @params size int : size of the image
     * @params col int : column index
     * @params row int : row index
     */
    public void addImageGridpane(GridPane layout, String image, int size, int col, int row){
        ImageView img = new ImageView(new Image(image, size, size, true, true));
        layout.add(img, col, row);
        layout.setHalignment(img, HPos.CENTER);
    }

    /**
     * Set the gridpane collumns and rows size
     */
    public void initGridSize(){
        addImageGridpane(menuAnimation, "PNG/Character"+4+".png",100,1,3);
        menuAnimation.getChildren().get(0).setVisible(false);
    }


    /**
     * A thread that can be paused and released
     * 'indiceFrame' is allways the indice of the frame in 'frameList' that is currently displayed
     * The class is updating this indice at each call, interval set by 'timeInterval', in ms
     */
    class Animate implements Runnable {
        int timeInterval = 500;
        boolean running = true;
        int indiceFrame;

        Animate() { this.indiceFrame = 0; }

        public void run() {
            while (running) {
                modele.animationMenu = frameList.get(indiceFrame);
                actuAnim();

                // 0 <= indiceFrame < length of frameList
                this.indiceFrame =  this.indiceFrame+1 < frameList.size() ? this.indiceFrame+1 : 0;
                try {
                    Thread.sleep(timeInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void pause(){
            running = false;
        }

        public void release(){
            running = true;
            Thread animate = new Thread(this);
            animate.setDaemon(true);
            animate.start();
        }
    }
}
