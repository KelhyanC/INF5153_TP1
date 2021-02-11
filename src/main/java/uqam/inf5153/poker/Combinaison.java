package uqam.inf5153.poker;

import java.util.List;

public interface Combinaison {
    public enum Combo {
        HAUTE_CARTE, PAIRE, COULEUR;
    }

    public Combo trouverCombo(List<Carte> main);
}
