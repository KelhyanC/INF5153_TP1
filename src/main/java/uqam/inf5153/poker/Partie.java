package uqam.inf5153.poker;

import java.util.List;

public class Partie {
    List<Joueur> joueurs;
    String resultat;
    String explication;

    public Partie(List<Joueur> joueurs) {
        this.joueurs = joueurs;
        this.evaluerPartie(joueurs);
    }

    private void evaluerPartie(List<Joueur> joueurs) {
        Joueur gagnant = joueurs.get(0);
        Joueur perdant = joueurs.get(1);
        boolean egalite = false;
        for (int i = 1; i < joueurs.size(); i++) {
            System.out.println(
                    gagnant.main.plusForteCombi.ordinal() + " vs " + joueurs.get(i).main.plusForteCombi.ordinal());
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
