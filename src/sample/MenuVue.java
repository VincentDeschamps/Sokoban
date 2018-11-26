package sample;

import javafx.scene.control.Button;

public class MenuVue extends Vue implements Observer{
    public Modele modele;

    public Button goToGame;

    public MenuVue(Modele modele){
        this.modele = modele;
        modele.subscribe(this);
        goToGame = new Button("Swap Jeu");

    }

    @Override
    public void actualiser() {

    }
}
