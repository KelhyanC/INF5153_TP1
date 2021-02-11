package uqam.inf5153.poker;

import java.util.ArrayList;
import java.util.List;

public class Mains implements Combinaison {

    private List<Carte> cartes;
    private Combo plusForteCombi;

    public Mains(String saisie) {
        int taille_max = 10;
        List<Carte> main = new ArrayList<>();
        saisie = saisie.replaceAll("\\s", "");
        System.out.println("Entrée : " + saisie.trim());
        if (saisie.length() < taille_max) {
            System.err.println("Entrée invalide");
            System.exit(-1);
        }
        for (int i = 0; i < taille_max; i += 2) {
            Carte carte = new Carte(String.valueOf(saisie.charAt(i)), String.valueOf(saisie.charAt(i + 1)));

            main.add(carte);
        }

        this.cartes = main;

        this.plusForteCombi = trouverCombo(this.cartes);
    }

    @Override
    public Combo trouverCombo(List<Carte> main) {
        for (Carte x : main) {
            if (x.couleurEgale(x)) {
            }
        }
        return Combo.HAUTE_CARTE;
    }

}
