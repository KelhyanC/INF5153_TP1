package uqam.inf5153.poker;

public class Joueur {
    String id;
    Mains main;
    int victoires;

    public Joueur(int numero, Mains main) {
        this.id = "P" + numero;
        this.main = main;
    }
}
