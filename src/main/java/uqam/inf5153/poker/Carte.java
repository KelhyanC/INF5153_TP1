package uqam.inf5153.poker;

public class Carte {

    private Valeur valeur;
    private Couleur couleur;

    public Carte(String val, String clr) {

        this.valeur = Valeur.obtenirValeur(val.trim());
        this.couleur = Couleur.obtenirCouleur(clr.trim());

        if (this.valeur == null || this.couleur == null) {
            System.err.println("Erreur d'initialisation de Carte");
            System.exit(-1);
        }

        this.print();

    }

    public void print() {
        System.out.println("valeur : " + this.valeur + " Couleur : " + this.couleur);
    }

    public String afficherValeur() {
        return String.valueOf(this.valeur);
    }

    public String afficherCouleur() {
        return String.valueOf(this.couleur);
    }

    public boolean couleurEgale(Carte autre) {
        return this.couleur.equals(autre.couleur);
    }

    public boolean valeurEgale(Carte autre) {
        return this.valeur.equals(autre.valeur);
    }

    public boolean valeurSuperieure(Carte autre) {
        return this.valeur.ordinal() > autre.valeur.ordinal();
    }
}
