package sample;

public interface Observer {

    /**
     * Controller modify Modele then Modele call 'actualiser'
     * Get the new information in the modele and change the display
     */
    void actualiser();
}
