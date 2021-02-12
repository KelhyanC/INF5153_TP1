package uqam.inf5153.poker;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    List<Joueur> joueurs;
    String resultat;
    String explication;

    public Partie(List<Joueur> joueurs) {
        this.joueurs = joueurs;
        Joueur gagnant = null;
        Joueur perdant = null;
        for (int i = 0; i < joueurs.size(); i++) {
            for (int j = i + 1; j < joueurs.size(); j++) {
                if (joueurs.get(i).main.plusForteCombi.ordinal() > joueurs.get(j).main.plusForteCombi.ordinal()) {
                    gagnant = joueurs.get(i);
                    perdant = joueurs.get(j);
                } else if (joueurs.get(i).main.plusForteCombi.ordinal() < joueurs.get(j).main.plusForteCombi
                        .ordinal()) {
                    gagnant = joueurs.get(j);
                    perdant = joueurs.get(i);
                } else {
                    gagnant = null;
                    perdant = null;
                }
            }
        }
        if (!gagnant.equals(null)) {
            gagnant.victoires++;
            this.resultat = gagnant.id;
            this.explication = gagnant.main.plusForteCombi + " à " + gagnant.main.plusForteCombi; //// Methodes
                                                                                                  //// d'affichage main
                                                                                                  //// forte dans mains
        } else {
            this.resultat = "TIE";
        }
    }
}
