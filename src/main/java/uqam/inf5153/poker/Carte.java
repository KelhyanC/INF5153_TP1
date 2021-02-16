package uqam.inf5153.poker;

/*
* la classe qui Initialise et manipule les cartes du jeu de Poker
*/
public class Carte {

    private Valeur valeur;
    private Couleur couleur;

    /*
     * Initialise une carte a partir d'une entrée de chaîne de caractères. Si la
     * valeur ou la couleur d'une carte n'existe pas, affiche un message d'erreur et
     * termine le programme.
     * 
     * @param val, la valeur de la carte à initialiser
     * 
     * @param clr, la couleur de la carte à initialiser
     */
    public Carte(String val, String clr) {

        this.valeur = Valeur.obtenirValeur(val.trim());
        this.couleur = Couleur.obtenirCouleur(clr.trim());

        if (this.valeur == null || this.couleur == null) {
            System.err.println("Erreur d'initialisation de Carte");
            System.exit(1);
        }
    }

    // Retourne la valeur d'une carte sous forme de String
    public String afficherValeur() {
        return String.valueOf(this.valeur);
    }

    // Retourne la couleur d'une carte sous forme de String
    public String afficherCouleur() {
        return String.valueOf(this.couleur);
    }

    // Compare l'egalite des couleurs entre deux cartes
    public boolean couleurEgale(Carte autre) {
        return this.couleur.equals(autre.couleur);
    }

    // Compare l'egalite des valeurs entre deux cartes
    public boolean valeurEgale(Carte autre) {
        return this.valeur.equals(autre.valeur);
    }

    /*
     * Compare la superiorite des valeurs entre deux cartes a partir de leurs
     * position dans l'enum Valeur (valeur ordinale).
     */
    public boolean valeurSuperieure(Carte autre) {
        return this.valeur.ordinal() > autre.valeur.ordinal();
    }
}
