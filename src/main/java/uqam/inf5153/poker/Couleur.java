package uqam.inf5153.poker;

/*
 * Liste les couleurs des cartes du jeu de poker
 */
public enum Couleur {
    C, H, D, S;

    public static Couleur obtenirCouleur(String c) {
        for (Couleur val : values()) {
            if (val.name().equals(c.toUpperCase())) {
                return val;
            }
        }
        return null;
    }
}
