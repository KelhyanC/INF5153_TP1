package uqam.inf5153.poker;

import java.util.*;

/**
 * Point d'entre du programme qui effectue l'arbitrage et affiche les resultats
 * d'un jeu de poker entre plusieur joueurs. Une partie de poker doit contenir
 * au minimum 2 joueurs. Chaque joueur doit posseder une main de 5 cartes. Les
 * cas de triche ne sont pas pris en compte. A la fin de chaque partie un
 * tableau des scores montrant les victoires de chaque joueur ayant participe au
 * moins une fois a la partie est affiche.
 */
public class Main {

    /**
     * La fonction principale. Si aucun argument est donne, nous utilisons stdin
     * pour lire les donnees. Si des arguments sont passes,la partie commence avec
     * ces arguments et si l'utilisateur souhaite recommencer une partie, les
     * donnees sont lues depuis stdin.
     * 
     * @param args les mains a evaluer (autant de mains souhaitees), chaque main
     *             passee en argument compte pour un joueur.
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

    /*
     * Effectue l'initialisation des mains des joueurs et effectue l'arbitrage de la
     * partie depuis l'entree standard. L'utilisateur choisit le nombre de joueurs
     * pour chaque partie et initialise la main des n joueurs.
     * 
     * @param joueurs, la liste de joueurs pour laquelle effectuer l'arbitrage
     */
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

            // réduit la liste pour un passage de n à n-i joueurs
            if (n < joueurs.size()) {
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

            // Selectionne la liste de tous les joueurs y compris ceux qui ne sont pas
            // presents dans la partie en cours
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

    /*
     * Verifie l'existence d'un joueur dans une liste
     * 
     * @param joueurs , la liste a verifier
     * 
     * @param cible, l'identifiant du joueur a chercher
     */
    private static boolean contientJoueur(List<Joueur> joueurs, String cible) {
        for (Joueur it : joueurs) {
            if (it.id.equals(cible)) {
                return true;
            }
        }
        return false;
    }

}
