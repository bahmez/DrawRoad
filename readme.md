# DrawRoad

## Description
DrawRoad est un plugin Minecraft conçu pour créer des routes et des chemins de manière intuitive et flexible dans le jeu. Utilisant des algorithmes avancés comme Bezier et Bresenham pour le rendu des courbes et des lignes, ce plugin offre aux joueurs la possibilité de dessiner des routes avec une précision et une esthétique impressionnantes.

## Fonctionnalités
- Commandes personnalisées pour dessiner des routes.
- Utilisation des courbes de Bézier pour créer des chemins lisses.
- Algorithme de Bresenham pour le rendu efficace des lignes.
- Options de personnalisation telles que la modification de la précision et la sélection de blocs.
- Gestion asynchrone pour les constructions importantes, optimisant les performances.
- Fonctions d'annulation pour corriger facilement les erreurs.

## Installation

DrawRoad est un plugin conçu pour la version 1.12.2 de Minecraft. Pour l'installer :

1. Assurez-vous que votre serveur Minecraft fonctionne sous la version 1.12.2, car ce plugin est spécifiquement développé pour cette version.
2. Téléchargez le fichier `.jar` du plugin DrawRoad.
3. Placez le fichier `.jar` dans le dossier `plugins` de votre serveur Minecraft.
4. Redémarrez votre serveur. Ceci chargera le plugin et le rendra opérationnel.

Il est important de noter que l'utilisation de ce plugin sur une version autre que Minecraft 1.12.2 peut entraîner des incompatibilités ou des dysfonctionnements.

## Commandes

Le plugin DrawRoad offre plusieurs sous-commandes pour une personnalisation et une utilisation avancées :

### `/drawroad`
La commande principale pour interagir avec le plugin. Elle a plusieurs sous-commandes :

#### `blockPattern`
- Utilisation : `/drawroad blockPattern`
- Description : Permet aux joueurs de définir un motif de blocs pour le chemin. L'utilisateur peut placer un bloc de type spécifique qui sera utilisé comme partie du motif pour le chemin.

#### `changePrecision`
- Utilisation : `/drawroad changePrecision [precision]`
- Description : Change la précision des courbes de Bézier utilisées dans la création de routes. Une précision plus élevée donne des courbes plus lisses.

#### `clearSelection`
- Utilisation : `/drawroad clearSelection`
- Description : Efface toutes les sélections de points actuelles. Utile pour recommencer la conception d'une route.

#### `draw`
- Utilisation : `/drawroad draw`
- Description : Dessine la route en utilisant les points sélectionnés et le motif de bloc défini.

#### `popSelection`
- Utilisation : `/drawroad popSelection`
- Description : Supprime le dernier point ajouté à la sélection. Cela permet de corriger les erreurs lors de la sélection des points de la route.

#### `savePattern`
- Utilisation : `/drawroad savePattern`
- Description : Enregistre le motif de blocs actuellement défini par le joueur.

#### `tool`
- Utilisation : `/drawroad tool`
- Description : Donne au joueur l'outil de sélection pour marquer les points de la route.

#### `undo`
- Utilisation : `/drawroad undo`
- Description : Annule la dernière action effectuée, ce qui est utile pour corriger les erreurs.

#### `watchPattern`
- Utilisation : `/drawroad watchPattern`
- Description : Affiche le motif de bloc actuellement enregistré pour le joueur.

#### `setAsyncLoop`
- Utilisation : `/drawroad setAsyncLoop [nombre]`
- Description : Définit le nombre d'itérations de boucle pour la construction asynchrone, permettant de contrôler la vitesse de construction.

#### `setUndoLoop`
- Utilisation : `/drawroad setUndoLoop [nombre]`
- Description : Définit le nombre d'actions à annuler dans une boucle d'annulation.