package uqam.inf5153.poker;

/*
* Classe representant un joueur qui est represente par :
* un identifiant
* une main de cartes
* un nombre de victoires
*/
public class Joueur {
    String id;
    Mains main;
    int victoires;

    /*
     * Initialise un joueur a partir d'un numero et une main de cartes
     */
    public Joueur(int numero, Mains main) {
        this.id = "P" + numero;
        this.main = main;
    }
}
