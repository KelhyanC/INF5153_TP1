package uqam.inf5153.poker;

import java.util.List;

/*
 * Classe realisant le processus d'arbitrage d'une partie de poker
 * Une partie se compose d'une liste de joueurs, d'un resultat et 
 * d'une explication du resultat de cette partie
*/
public class Partie {
    List<Joueur> joueurs;
    String resultat;
    String explication;

    /*
     * Initialise une partie a partir d'une liste de joueur
     */
    public Partie(List<Joueur> joueurs) {
        this.joueurs = joueurs;
        this.evaluerPartie(joueurs);
    }

    /*
     * Determine le gagnant, le perdant et l'egalite d'une partie de poker a partir
     * d'une liste de joueurs et remplit les champs resultat et explication
     */
    private void evaluerPartie(List<Joueur> joueurs) {
        Joueur gagnant = joueurs.get(0);
        Joueur perdant = joueurs.get(1);
        boolean egalite = false;
        for (int i = 1; i < joueurs.size(); i++) {

            if (gagnant.main.plusForteCombi.ordinal() < joueurs.get(i).main.plusForteCombi.ordinal()) {
                perdant = gagnant;
                gagnant = joueurs.get(i);
                egalite = false;
            } else if (gagnant.main.plusForteCombi.ordinal() == joueurs.get(i).main.plusForteCombi.ordinal()) {
                if (gagnant.main.carteForte.valeurSuperieure(joueurs.get(i).main.carteForte) == true) {
                    egalite = false;
                } else if (joueurs.get(i).main.carteForte.valeurSuperieure(gagnant.main.carteForte) == true) {
                    perdant = gagnant;
                    gagnant = joueurs.get(i);
                    egalite = false;
                } else {
                    perdant = joueurs.get(i);
                    egalite = true;
                }
            }
        }
        if (egalite == false) {
            gagnant.victoires++;
            this.resultat = gagnant.id;
            this.explication = gagnant.main.afficherMainForte() + " pour " + gagnant.id + " bat "
                    + perdant.main.afficherMainForte() + " pour " + perdant.id;
        } else {
            this.resultat = "TIE";
            this.explication = gagnant.main.afficherMainForte() + " pour " + gagnant.id + " equivaut a "
                    + perdant.main.afficherMainForte() + " pour " + perdant.id;
        }
    }
}
