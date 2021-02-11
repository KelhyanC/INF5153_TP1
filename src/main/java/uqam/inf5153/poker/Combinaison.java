package uqam.inf5153.poker;

public interface Combinaison {
    public enum Combo {
        HAUTE_CARTE, PAIRE, COULEUR;
    }

    public Combo trouverCombo();
}
