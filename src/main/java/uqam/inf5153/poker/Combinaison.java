package uqam.inf5153.poker;

/*
* Interface decrivant les combinaisons permises dans le jeu de poker
*/
public interface Combinaison {
    /*
     * Enumeration des combinaisons permises Ordonnees dans l'enum de la moins forte
     * a la plus forte
     */
    public enum Combo {
        HAUTE_CARTE, PAIRE, COULEUR;
    }

    // Permet de trouver la combinaison la plus forte d'une main
    public Combo trouverCombo();
}
