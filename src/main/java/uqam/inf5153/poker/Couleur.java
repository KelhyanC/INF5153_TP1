package uqam.inf5153.poker;

/*
 * Liste les couleurs des cartes du jeu de poker
 */
public enum Couleur {
    C, H, D, S;

    /*
     * Retourne la couleur enumeree a partir du String passe en parametre. Si la
     * valeur de retour est nulle, la couleur n'existe pas
     */
    public static Couleur obtenirCouleur(String c) {
        for (Couleur val : values()) {
            if (val.name().equals(c.toUpperCase())) {
                return val;
            }
        }
        return null;
    }
}
