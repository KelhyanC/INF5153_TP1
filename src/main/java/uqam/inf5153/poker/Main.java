package uqam.inf5153.poker;

import java.util.*;

/**
 * A class to find the winner in a poker game
 */
public class Main {

    /**
     * The main function. If no arguments given, we will use stdin to read the data.
     * Affiche le tableau des scores pour tous les joueurs qui ont participe au jeu
     * 
     * @param args les arguments (le nombre de mains souhaitees).
     */
    public static void main(String[] args) {
        List<Joueur> joueurs = new ArrayList<>();
        if (args.length >= 2) {
            for (int i = 0; i < args.length; i++) {
                joueurs.add(new Joueur(i + 1, new Mains(args[i])));
            }

            Partie partie = new Partie(joueurs);

            System.out.println("Resultat : " + partie.resultat);
            System.out.println("Explications : " + partie.explication + "\n");

            System.out.println("Tableau des scores :\n" + "| id | v |\n" + "----------");

            for (Joueur it : partie.joueurs) {
                System.out.println("| " + it.id + " | " + it.victoires + " |");
            }

            System.out.println("\nVoulez-vous rejouer ? (o) continuer / (n) arreter");

            String recommencer = System.console().readLine();

            if (recommencer.equals("n") == false) {
                saisieUtilisateur(joueurs);
            }

        } else {
            saisieUtilisateur(joueurs);
        }

    }

    private static void saisieUtilisateur(List<Joueur> joueurs) {
        Scanner sc = new Scanner(System.in);
        String continuer = "n";
        List<Joueur> sauvegarde = new ArrayList<>();
        do {
            int n;
            do {
                try {
                    System.out.print("Entrez le nombre de joueurs : ");
                    n = sc.nextInt();
                    if (n < 2) {
                        System.out.println("2 joueurs minimum requis");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Saisie invalide - 2 joueurs par defaut");
                    n = 2;
                }
            } while (n < 2);

            sc.nextLine();

            if (n < joueurs.size()) { // réduit la liste pour un passage de n à n-i joueurs
                sauvegarde = joueurs;
                joueurs = joueurs.subList(0, n);
            }

            for (int i = 0; i < n; i++) {
                System.out.print("P" + (i + 1) + " ? ");
                Mains main = new Mains(sc.nextLine());
                if (joueurs.size() > 1 && contientJoueur(joueurs, "P" + (i + 1)) == true) {
                    joueurs.get(i).main = main;
                } else {
                    Joueur joueur = new Joueur(i + 1, main);
                    joueurs.add(joueur);
                }
                System.out.println();
            }
            Partie partie = new Partie(joueurs);

            System.out.println("Resultat : " + partie.resultat);
            System.out.println("Explications : " + partie.explication + "\n");

            if (joueurs.size() < sauvegarde.size()) {
                joueurs = sauvegarde;
                partie.joueurs = joueurs;
            }

            System.out.println("Tableau des scores :\n" + "| id | v |\n" + "----------");

            for (Joueur it : partie.joueurs) {
                System.out.println("| " + it.id + " | " + it.victoires + " |");
            }

            System.out.println("\nVoulez-vous rejouer ? (o) continuer / (n) arreter");
            continuer = sc.nextLine();

        } while (continuer.equals("o"));
        sc.close();
    }

    private static boolean contientJoueur(List<Joueur> joueurs, String cible) {
        for (Joueur it : joueurs) {
            if (it.id.equals(cible)) {
                return true;
            }
        }
        return false;
    }

}
