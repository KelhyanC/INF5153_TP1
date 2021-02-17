# TP 1 - Jeu de Poker (Fiche de réponses)

  - Auteur : Kelhyan, Carmont (`CARK68120101`)
  - Date de remise : 17/02/2021
  - Estimation du temps de travail passé sur le projet : _35_ heures.

## Réponses aux questions  
 
Les tailles de réponses sont données à titre indicatif, mais il est clair qu'une question sur quinze (15) points attends plus de matière dans la réponse qu'une question sur cinq (5). Ne faites pas un roman, soyez direct et allez droit au but dans votre argumentation.
 
### Question 1: Évolution du code légataire

1 - Pour pouvoir accumuler le nombre de parties gagnées par joueur, une `classe joueur` contenant une variable `victoires` devrait être présente et une incrémentation de cette variable aurait eu lieu à chaque victoire de ce joueur reconnu grâce à son `id`. De plus pour pouvoir accumuler les victoires, il faudrait effectuer plusieurs parties d'échec afin de maintenir l'objet en vie et incrémenter les victoires. Si nous n'effectuons qu'une seule partie à la fois, le maximal de victoire d'un des joueurs serait de 1 puis serait réinitialisée à la fin du programme.

2 - Pour pouvoir arbitrer une partie à plus de 2 joueurs, nous devrions créer une `classe Partie` contenant une liste de joueurs et y implémenter le processus d'arbitrage d'une partie au sein d'une fonction prenant en paramètre la liste de joueurs afin de `généraliser le procédé pour n joueurs` contenus dans la liste.

3 - Pour définir l'as en tant que carte la plus forte, nous devrions `stocker les valeurs des cartes dans un enum` car les enums implémentent l'interface `comparable` et ainsi nous pourrions ordonner les valeurs des cartes dans l'enum selon l'ordre désiré, dans ce cas nous placerions l'as en dernière position dans l'enum.

4 - On devrait changer le format d'entrée du programme afin de prendre en compte le nombre de parties à effectuer et le nombre de joueurs présents dans la partie.

### Question 2: Analyse des défauts du code légataire

- Le code légataire souffre d'un problème de `primitive obsession`. Il y a beaucoup de manipulation de chaînes de caractères au lieu de manipuler des cartes, des mains, des joueurs..etc.Ce qui nous éloigne du domaine d'affaires.

- Un problème de `responsabilité` est aussi présent, car la classe `main` doit assurer tout le déroulement du processus de l'arbitrage d'une partie de poker, en commençant de l'initialisation des cartes jusqu'à la détermination du vainqueur de la partie. Ce qui ne respecte pas le principe de responsabilité unique.

- Une évolution de ce projet implique de `grosses modifications` et un réagencement du code. Si nous voulons ajouter des joueurs, ajouter d'autres combinaisons de poker ou d'autres cartes, il faudrait changer la structure du projet car le code actuel est conçu exclusivement dans le cas où 2 joueurs sont présents et 3 seulement 3 stratégies gagnantes (full, pair ou highest card) . Ne respectant pas le principe `ouvert/fermé`. 

- La `séggrégation d'interface` n'est pas respectée. Il n'y a `pas de présence de classes ou interface spécialisés`. Mais une seule classe main qui effectue tout le procédé.

- Les `données ne sont pas encapsulées` et il y a une `fuite d'abstraction`. Les choix de conception et la manipulation des données sont visibles dans le main. Par exemple la variable statique resultat est facilement accessible partout dans le code et on pourrait corrompre ses données. Tout le processus d'arbitrage est stocké dans les variables p1 et p2 au lieu de répartir cette lourde tâche en faisant communiquer plusieurs instances des objets du domaine.

- Le code est `non-réutilisable`, on ne peut pas réutiliser ce code si on souhaite implémenter un nouveau jeu de poker plus complet en utilisant certaines interfaces, classes abstraites ou autres concepts liés à ce domaine car il y en a pas. Les méthodes sont spécifiques au projet donné et il y a un `couplage fort` entre les composantes. Par exemple la méthode "comp" ne détecte un gagnant que seulement s'il y a une combinaison full, pair ou highest card mais ne détecte pas les autres cas comme par exemple la quinte flush royale.

- Trop de valeurs magiques sont présentes dans le code.


### Question 3: Justification des choix de conception

1 - J'ai découpé le problème en plusieurs classes spécialisées appartenants à la logique d'affaire de notre problème.

J'ai implémenté un classe Carte définie par une valeur et une couleur. Les valeurs et couleurs de carte sont définies par des énumérations ordonnées. De sorte à ce que seules les valeurs et couleurs présentes dans l'enum sont permises.
Si on envisageait d'ajouter une nouvelle valeur ou couleur de carte, nous aurions juste à l'ajouter dans l'enum. Si nous désirerions changer l'ordre des puissances des valeurs des cartes, nous n'aurions qu'à changer sa position ordinale dans l'enum.

Une main est initialisée par une saisie (argument, entrée standard ou chaîne de caractères). La saisie est insensible à la casse et les espaces ne sont pas pris en compte pour faciliter l'utilisateur. A partir d'une entrée de chaîne de caractères, les 5 cartes de la main sont initialisées.
La classe Mains implémente l'interface Combinaison. L'interface combinaison se compose d'un enum Combo ordonné par niveau de combinaison. Qui nous permettrait de facilement ajouter de nouvelles combinaisons et modifier leur priorité. L'interface fournit une fonction qui permet de trouver la combinaison la plus forte à partir d'une main de carte.
Ainsi dans notre classes Mains, nous n'avons accès qu'à la plus forte combinaison de la main, la carte la plus forte et la liste de cartes représentant la combinaison la plus forte, ainsi nous n'avons pas besoin de manipuler la liste de cartes représentant une main. 

La classe joueur permet d'associer chaque joueur à une main et un nombre de victoires afin de conserver l'historique de ce-dernier.

La classe Partie se compose d'une liste de n joueurs, d'un resultat et d'une explication du résultat de la partie.
Elle réalise le processus d'arbitrage pour n joueurs selon les combinaisons permises du jeu de poker et selon le scénario une explication liée au résultat est donnée. Nous permettant ainsi  de comprendre l'issue d'une partie et offrant la possibilité de passer de 2 à n joueurs et de n à n-i joueurs en modifiant seulement sa liste de joueurs y associée.

La classe main gère les entrées/sorties du programme en permettant à l'utilisateur d'initier une partie soit à la ligne de commande ou soit en fournissant les données via l'entrée standard. En ligne de commande, l'utilisateur fournit autant de mains qu'il souhaite. Chaque main sera associée à un joueur. Par saisie standard, il choisira le nombre de joueur souhaité et saisira chaque main pour n joueurs souhaités. Il pourra aussi recommencer une nouvelle partie à volonté.

j'ai décidé qu'à chaque erreur d'initialisation de Carte ou de main d'afficher un message d'erreur et d'arrêter le programme car cela sert à rien de poursuivre le déroulement d'un programme et la consommation de ressources dans l'optique d'aboutir à une erreur.

L'utilisation de listes pour stocker les cartes et les joueurs a une moins bonne complexite temporelle O(n²) que l'utilisation d'un arbe O(log n). Cependant la liste permet une implementation et une maintenabilité plus facile et est facilement mutable en une autre structure par la suite. Dans notre cas, nous n'avons pas un grand nombre de cartes ou de joueurs à traiter donc une liste fera l'affaire.

2 - Selon moi, ma conception respecte le principe de responsabilité unique car chaque classe implémentée représente une composante du domaine d'affaire qui fait qu'une seule chose.
la classe Carte initialise et gère des Cartes. La classe Mains compose une liste des carte et identifie les combinaisons fortes. La classe joueur initialise et identifie un joueur. la classe Partie arbitre une partie de poker entre des joueurs. La classe main gère les entrées(initialise les intrants d'une partie de poker) et sorties(affiche les résultats).

Le principe de substitution de Liskov est également respecté car n'importe quelle classe parent peut être substituée par une classe enfant minimisant ainsi le couplage. Par exemple une Carte peut être remplacée par une chaîne de caractères. Une Main peut être substituée par une liste de cartes dans laquelle nous définirions une methode pour trouver les cartes les plus fortes ou la combinaison qu'une liste de cartes représente. un joueur peut être substitué par une chaîne de caractères à laquelle nous associons une Main et un nombre de victoires. Une Partie peut être substituée par une fonction comparant plusieurs mains données. Initialement, nous avions une seule grosse classe réalisant tout le processus d'arbitrage qui n'était pas substituables, désormais nous avons plusieurs classes modulables et substituables.

Le principe de connaissance minimale est aussi respecté car tous les objets ne communiquent pas directement ensemble. Une Partie ne communique pas directement avec une carte mais communique avec des joueurs. La main d'un joueur communique uniquement avec la main d'un autre joueur. La classe Main ne communique pas directement avec les Cartes et les mains des joueurs mais avec les parties. Un objet ne connaît que le minimum necessaire à propos d'un autre objet.

3 - Je pense que la principale faiblesse de ma conception se trouve dans la classe partie, précisemment dans la fonction evaluerPartie() car elle a une connaissance trop Approfondie de la classe Mains ainsi que la classe Carte ce qui pourrait potentiellement violer le principe de la loi de Demeter. Cela est une potentielle faiblesse car si nous sommes amenés à changer la structure d'une main de carte en supprimant par exemple le champs carteForte, la fonction ne serait plus valide et fausserait le processus d'arbitrage. De plus, si nous remplaçons l'enum Couleur ou Valeur par une classe, il faudrait apporter de grosses modifications à la conception du programme.

Les tests que j'ai implémenté ne couvrent pas tous les cas d'utilisation du programme, notamment la saisie de données de l'utilisateurs et le passage par argument du programme.
La possibilité de recommencer une partie avec plus ou moins de joueurs et l'affichage du tableau des scores finales, sont testables manuellement.

### Question 4: Évolution du code objet

L'ajout du multijouer a été rendu possible en créant une classe joueur, identifiant un joueur par un id et un attribut victoires qui est incrémenté à chaque victoire du joueur. De plus à chaque nouvelle partie, la main du joueur change permettant ainsi une réutilisabilité de l'instance de Joueur. Nous pourrons faire évoluer facilement cette classe joueur dans l'avenir en classant par exemple les joueurs par rang en y ajoutant un attribut rang ou des fonctions manipulant ses données. De plus, une partie se constitue d'une liste de joueurs. L'arbitrage se fait entre les n joueurs de la liste. A chaque nouvelle partie, il est facile d'ajouter ou d'enlever des joueurs en modifiant la liste de joueur associé à la partie. Il est donc plus facile d'y ajouter des critères d'arbitrage si nous le désirons en modifiant la classe Partie.

## Auto-évaluation (optionnelle)

Vous êtes libre de faire l'exercice de vous auto-évaluer en remplissant la grille d'évaluation du projet ci-dessous.

| Élement         | Critère d'évaluation                       | Note  |
| :---:           | :---                                       | :---: |
|  _Questions_    | (#1) Évolution du code légataire           | 5/5    |
|                 | (#2) Analyse des défauts du code légataire | 10/10   |
|                 | (#3) Justification des choix de conception | 10/15   |
|                 | (#4) Évolution du code objet               | 5/5    |
|  _Modèles_      | Justesse & Pertinence de la conception     | 12/15   |
|                 | Cohérence inter-modèles                    | 5/5    |
|                 | Respect des principes de conception        | 11/15   |
|  _Code_         | Qualité du code Java et du dépôt Git       | 9/10   |
|                 | Cohérence du code avec les modèles         | 10/10   | 
|                 | Qualité des tests                          | 9/10   | 
| **Note Finale** | Questions + Modèles + Code                 | 86/100  | 

_Cette auto-évaluation permet au correcteur de vous donner une rétro-action plus personnalisée en pointant les critères sur lesquels vous vous sur-évaluez et ceux sur lesquels au contraire vous vous sous-évaluez._