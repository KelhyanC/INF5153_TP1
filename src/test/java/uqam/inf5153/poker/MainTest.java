package uqam.inf5153.poker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

//Permet de capturer les codes de retour de System.exit() et les sorties standard
//J'ai ajoute la dependance dans pom.xml (https://github.com/stefanbirkner/system-lambda)
import static com.github.stefanbirkner.systemlambda.SystemLambda.*;

import org.junit.Test;

import uqam.inf5153.poker.Combinaison.Combo;

public class MainTest {

    // Detection d'erreurs

    @Test
    public void valeurCarteInvalide() throws Exception {
        String msg = tapSystemErrNormalized(() -> {
            int statut = catchSystemExit(() -> {
                new Carte("X", "D");
            });
            assertEquals(1, statut);
        });
        assertEquals("Erreur d'initialisation de Carte".toLowerCase().trim(), msg.toLowerCase().trim());
    }

    @Test
    public void couleurCarteInvalide() throws Exception {
        String msg = tapSystemErrNormalized(() -> {
            int statut = catchSystemExit(() -> {
                new Carte("5", "Z");
            });
            assertEquals(1, statut);
        });
        assertEquals("Erreur d'initialisation de Carte".toLowerCase().trim(), msg.toLowerCase().trim());
    }

    @Test
    public void mainTropLongue() throws Exception {
        String msg = tapSystemErrNormalized(() -> {
            int statut = catchSystemExit(() -> {
                new Mains("1S TS 4D 3C 6S JD KS");
            });
            assertEquals(2, statut);
        });
        assertEquals("Entree de taille invalide".toLowerCase().trim(), msg.toLowerCase().trim());
    }

    @Test
    public void mainTropCourte() throws Exception {
        String msg = tapSystemErrNormalized(() -> {
            int statut = catchSystemExit(() -> {
                new Mains("1S TS");
            });
            assertEquals(2, statut);
        });
        assertEquals("Entree de taille invalide".toLowerCase().trim(), msg.toLowerCase().trim());
    }

    @Test
    public void mainInvalide() throws Exception {
        String msg = tapSystemErrNormalized(() -> {
            int statut = catchSystemExit(() -> {
                new Mains("1S TS 4X 3C 6S");
            });
            assertEquals(1, statut);
        });
        assertEquals("Erreur d'initialisation de Carte".toLowerCase().trim(), msg.toLowerCase().trim());
    }

    // Creation d'objets

    @Test
    public void creationCarte() {
        Carte test = new Carte("K", "D");
        assertEquals(test.afficherValeur(), Valeur.ROI.toString());
        assertEquals(test.afficherCouleur(), Couleur.D.toString());
    }

    @Test
    public void creationMain() {
        Mains test = new Mains("kd1c2hks 4H");
        Carte carteTest = new Carte("K", "D");
        List<Carte> testMainForte = new ArrayList<>();
        testMainForte.add(new Carte("K", "D"));
        testMainForte.add(new Carte("K", "S"));
        assertEquals(test.plusForteCombi, Combo.PAIRE);
        assert (test.carteForte.valeurEgale(carteTest) && test.carteForte.couleurEgale(carteTest));
        for (int i = 0; i < test.mainForte.size(); i++) {
            assert (test.mainForte.get(i).couleurEgale(testMainForte.get(i))
                    && test.mainForte.get(i).valeurEgale(testMainForte.get(i)));
        }
    }

    @Test
    public void creationJoueur() {

        Mains main = new Mains("1S 2S 3S 4S 5S");
        Joueur test = new Joueur(1, main);
        assertEquals("P1", test.id);
        assertEquals(0, test.victoires);
        assertEquals(main, test.main);

    }

    // Processus d'arbitrage pour n joueurs

    @Test
    public void partie2joueurs() {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new Joueur(1, new Mains("2D 5D QD KD 7D")));
        joueurs.add(new Joueur(2, new Mains("1S 4C KH TD 3S")));

        Partie partie = new Partie(joueurs);

        assertEquals("P1", partie.resultat);
        assertEquals("couleur a D pour P1 bat ROI de H pour P2".toLowerCase().trim(),
                partie.explication.toLowerCase().trim());
    }

    @Test
    public void partieNjoueurs() {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("JH 9C 6s QH 1s")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("1S 4C KH TD 3S")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("1H 4H JH TH 3H")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("TS 6D 7H TD 3S")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("2D 5D QD KD 7D")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("1C 5H 1D KS 7C")));

        Partie partie = new Partie(joueurs);

        assertEquals("P5", partie.resultat);
        assertEquals("couleur a D pour P5 bat couleur a H pour P3".toLowerCase().trim(),
                partie.explication.toLowerCase().trim());
    }

    @Test
    public void partie_6_a_3_joueurs() {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("JH 9C 6s QH 1s")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("1S 4C KH TD 3S")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("1H 4H JH TH 3H")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("TS 6D 7H TD 3S")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("2D 5D QD KD 7D")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("1C 5H 1D KS 7C")));

        Partie partie = new Partie(joueurs);

        assertEquals("P5", partie.resultat);
        assertEquals("couleur a D pour P5 bat couleur a H pour P3".toLowerCase().trim(),
                partie.explication.toLowerCase().trim());

        joueurs.remove(4);
        joueurs.remove(2);
        joueurs.remove(0);

        partie = new Partie(joueurs);

        assertEquals("P4", partie.resultat);
        assertEquals("paire de DIX pour P4 bat ROI de H pour P2".toLowerCase().trim(),
                partie.explication.toLowerCase().trim());
    }

    // Egalite

    @Test
    public void egaliteCouleur() {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("3H 8H JH KH 7H")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("2D 5D QD KD 7D")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("3C 8C JC kC 7C")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("2S 5S qS kS 7S")));

        Partie partie = new Partie(joueurs);

        assertEquals("TIE", partie.resultat);
        assertEquals("couleur a H pour P1 equivaut a couleur a S pour P4".toLowerCase().trim(),
                partie.explication.toLowerCase().trim());
    }

    @Test
    public void egalitePaire() {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("1S 4C 1H 4D 3S")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("1C 5H 1D KS 7C")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("5S 4S 4H TD 3H")));

        Partie partie = new Partie(joueurs);

        assertEquals("TIE", partie.resultat);
        assertEquals("paire de QUATRE pour P1 equivaut a paire de QUATRE pour P3".toLowerCase().trim(),
                partie.explication.toLowerCase().trim());
    }

    @Test
    public void egaliteHauteCarte() {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("KS 3C 4H 5D 6S")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("KD 7C 8H 9D TS")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("KH 1C 2H 3D 4S")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("KC jC 1H 2D 3S")));

        Partie partie = new Partie(joueurs);

        assertEquals("TIE", partie.resultat);
        assertEquals("ROI de S pour P1 equivaut a ROI de C pour P4".toLowerCase().trim(),
                partie.explication.toLowerCase().trim());
    }

    // Victoires

    @Test
    public void victoireCouleur() {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("1S 2s 3s 4s 5S")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("6s 7s 8s 9s TS")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("1H 2h 3H 4h 5h")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("9h Th JH QH KH")));

        Partie partie = new Partie(joueurs);

        assertEquals("P4", partie.resultat);
        assertEquals("couleur a H pour P4 bat couleur a S pour P2".toLowerCase().trim(),
                partie.explication.toLowerCase().trim());
    }

    @Test
    public void victoirePaire() {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("1S 1C 2s 2C 5S")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("6s 6c 8s 8c TS")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("kH Ks   3H  3c 5h")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("9h 9c JH 7d Kd")));

        Partie partie = new Partie(joueurs);

        assertEquals("P3", partie.resultat);
        assertEquals("paire de ROI pour P3 bat paire de HUIT pour P2".toLowerCase().trim(),
                partie.explication.toLowerCase().trim());
    }

    @Test
    public void victoireHauteCarte() {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("KS 1C 2s 3C 5S")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("6s 7c 8s 9c TS")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("1H 2s   3H  4c 5h")));
        joueurs.add(new Joueur(joueurs.size() + 1, new Mains("6h 7c 8H 9d Qd")));

        Partie partie = new Partie(joueurs);

        assertEquals("P1", partie.resultat);
        assertEquals("ROI de S pour P1 bat DIX de S pour P2".toLowerCase().trim(),
                partie.explication.toLowerCase().trim());
    }
}
