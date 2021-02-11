package uqam.inf5153.poker;

/*
 * Liste les valeurs des cartes du jeu de poker
 */
public enum Valeur {
    AS("1"), DEUX("2"), TROIS("3"), QUATRE("4"), CINQ("5"), SIX("6"), SEPT("7"), HUIT("8"), NEUF("9"), DIX("T"),
    VALET("J"), REINE("Q"), ROI("K");

    private String valeur;

    Valeur(String valeur) {
        this.valeur = valeur;
    }

    public static Valeur obtenirValeur(String c) {
        for (Valeur val : values()) {
            if (val.valeur.equals(c.toUpperCase())) {
                return val;
            }
        }
        return null;
    }
}
