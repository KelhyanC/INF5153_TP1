package uqam.inf5153.poker;

import java.util.ArrayList;
import java.util.List;

public class Mains implements Combinaison {

    private List<Carte> cartes;

    Combo plusForteCombi;
    List<Carte> mainForte;
    Carte carteForte;

    public Mains(String saisie) {

        int taille_max = 10;
        List<Carte> main = new ArrayList<>();
        saisie = saisie.replaceAll("\\s", "");

        if (saisie.length() < taille_max || saisie.length() > taille_max) {
            System.err.println("Entree de taille invalide");
            System.exit(-1);
        }
        for (int i = 0; i < taille_max; i += 2) {
            Carte carte = new Carte(String.valueOf(saisie.charAt(i)), String.valueOf(saisie.charAt(i + 1)));

            main.add(carte);
        }

        this.cartes = main;

        this.plusForteCombi = trouverCombo();

        this.carteForte = plusForteCarte();

    }

    private Carte plusForteCarte() {
        Carte forte = this.mainForte.get(0);
        for (Carte it : this.mainForte) {
            if (it.valeurSuperieure(forte)) {
                forte = it;
            }
        }
        return forte;
    }

    private boolean flush() {
        for (int i = 1; i < this.cartes.size(); i++) {
            if (this.cartes.get(0).couleurEgale(this.cartes.get(i)) == false) {
                return false;
            }
        }
        this.mainForte = this.cartes;
        return true;
    }

    /*
     * Utilisation d'un set dans la methode pour une meilleur complexite de O(n) Au
     * lieu de O(n²) avec une double boucle sur un tableau
     */
    private boolean paire() {
        boolean estPaire = false;
        List<Carte> duo = new ArrayList<>();
        Carte faible = new Carte("1", "S");
        duo.add(faible);
        duo.add(faible);
        for (int i = 0; i < this.cartes.size(); i++) {
            for (int j = i + 1; j < this.cartes.size(); j++) {
                if (this.cartes.get(i).valeurEgale(this.cartes.get(j))
                        && (this.cartes.get(i).valeurSuperieure(duo.get(0))
                                || this.cartes.get(i).valeurEgale(duo.get(0)))) {
                    estPaire = true;
                    duo.set(0, this.cartes.get(i));
                    duo.set(1, this.cartes.get(j));
                }
            }
        }
        if (estPaire == true) {
            this.mainForte = duo;
        }
        return estPaire;
    }

    @Override
    public Combo trouverCombo() {
        if (this.flush()) {
            return Combo.COULEUR;
        }
        if (this.paire()) {
            return Combo.PAIRE;
        }
        Carte max = this.cartes.get(0);
        for (int i = 1; i < this.cartes.size(); i++) {
            if (this.cartes.get(i).valeurSuperieure(max)) {
                max = this.cartes.get(i);
            }
        }
        List<Carte> solo = new ArrayList<>();
        solo.add(max);
        this.mainForte = solo;
        return Combo.HAUTE_CARTE;
    }

    public String afficherMainForte() {
        switch (this.plusForteCombi) {
            case COULEUR:
                return Combinaison.Combo.COULEUR.toString().toLowerCase() + " a "
                        + this.mainForte.get(0).afficherCouleur();
            case PAIRE:
                return Combinaison.Combo.PAIRE.toString().toLowerCase() + " de "
                        + this.mainForte.get(0).afficherValeur();
            default:
                return this.mainForte.get(0).afficherValeur() + " de " + this.mainForte.get(0).afficherCouleur();
        }
    }

}
