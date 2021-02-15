package uqam.inf5153.poker;

import java.util.*;

/**
 * A class to find the winner in a poker game
 */
public class Main {

    /**
     * The main function. If no arguments given, we will use stdin to read the data.
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
            System.out.println("Explications : " + partie.explication);
            for (Joueur it : partie.joueurs) {
                System.out.println("| " + it.id + " | " + it.victoires + " |");
            }
        } else {
            saisieUtilisateur(joueurs);
        }

    }

    private static void saisieUtilisateur(List<Joueur> joueurs) {
        Scanner sc = new Scanner(System.in);
        String continuer = "n";
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
            for (int i = 0; i < n; i++) {
                System.out.print("P" + (i + 1) + " ? ");
                Mains main = new Mains(sc.nextLine());
                if (joueurs.size() >= i + 1) {
                    joueurs.get(i).main = main;
                } else {
                    Joueur joueur = new Joueur(i + 1, main);
                    joueurs.add(joueur);
                }
                System.out.println();
            }
            Partie partie = new Partie(joueurs);
            System.out.println("Resultat : " + partie.resultat);
            System.out.println("Explications : " + partie.explication);
            for (Joueur it : partie.joueurs) {
                System.out.println("| " + it.id + " | " + it.victoires + " |");
            }

            System.out.println("Voulez-vous rejouer ? (o) continuer/(n) arreter");
            continuer = sc.nextLine();

        } while (continuer.equals("o"));
        sc.close();
    }

}
