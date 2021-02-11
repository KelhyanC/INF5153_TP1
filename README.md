# TP 1 - Jeu de Poker (Fiche de réponses)

  - Auteur : Kelhyan, Carmont (`CARK68120101`)
  - Date de remise : 17/02/2021
  - Estimation du temps de travail passé sur le projet : _xx_ heures.

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

_Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum._

_Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum._

_Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum._

### Question 4: Évolution du code objet

_Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum._

## Auto-évaluation (optionnelle)

Vous êtes libre de faire l'exercice de vous auto-évaluer en remplissant la grille d'évaluation du projet ci-dessous.

| Élement         | Critère d'évaluation                       | Note  |
| :---:           | :---                                       | :---: |
|  _Questions_    | (#1) Évolution du code légataire           | /5    |
|                 | (#2) Analyse des défauts du code légataire | /10   |
|                 | (#3) Justification des choix de conception | /15   |
|                 | (#4) Évolution du code objet               | /5    |
|  _Modèles_      | Justesse & Pertinence de la conception     | /15   |
|                 | Cohérence inter-modèles                    | /5    |
|                 | Respect des principes de conception        | /15   |
|  _Code_         | Qualité du code Java et du dépôt Git       | /10   |
|                 | Cohérence du code avec les modèles         | /10   | 
|                 | Qualité des tests                          | /10   | 
| **Note Finale** | Questions + Modèles + Code                 | /100  | 

_Cette auto-évaluation permet au correcteur de vous donner une rétro-action plus personnalisée en pointant les critères sur lesquels vous vous sur-évaluez et ceux sur lesquels au contraire vous vous sous-évaluez._