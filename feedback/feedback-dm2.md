# Commentaires DM2

Corrigé par An Li

Total: 86%

## Description du système opérationnel [5/5]

Bien!

## 6 diagrammes d'activité UML [22/25]

- Il n'y a pas de lien vers les diagrammes d'activités dans le rapport
- Flux principal
  - Pas besoin d'avoir une activité par champ pour montrer l'inscription
- Offrir un produit à vendre
  - Pas besoin d'avoir une activité par champ pour entrer les informations du produit
- Passer une commande
  - Assumez-vous au début du scénario si l'acheteur a mis ce qu'il veut acheter dans le panier? Sinon, il manque la recherche dans le catalogue et l'ajout des produits dans le panier

## Diagramme de classes UML [14/20]

- Il n'y a pas de lien vers le diagramme de classes dans le rapport
- Le système doit contenir la liste d'utilisateurs et de produits pour pouvoir les répertorier
  - Un catalogue avec les produits serait aussi acceptable
- MenuAcheteur et MenuVendeur devraient être des classes, pas des interfaces
  - Il n'est pas possible d'implémenter les méthodes dans une interface, il faut qu'une classe l'implémente pour pouvoir définir les méthodes
- Certaines classes devraient être abstraites
  - Utilisateur et ProduitEnVente
- Les sous-types de produits devraient être des sous-classes de ProduitEnVente
- Redondance: Liste de souhaits = panier?

## 5 diagrammes de séquence UML [21/25]

- Il n'y a pas de lien vers les diagrammes de séquence dans le rapport
- Il faut mettre les cas d'échec dans les diagrammes
  - Session expirée
  - Informations invalides
  - Pas de commandes/produits

## Code source Java du programme et fichier JAR [20/25]

- System.console() ne fonctionne pas en exécutant le code dans IntelliJ
  - Il faut utiliser java.util.Scanner à la place
- Les classes sont implémentées, mais seule l'achat fonctionne en partie
- Pourquoi utiliser un map() pour les attributs?
  - Recommandé de mettre les attributs des produits comme attributs de classe
- pointsParDollar est fixe à 1 point par $ arrondi à la baisse si le produit n'a pas de promotions

## Bonus: Utilisation de GitHub [4/5]

- Pas de release pour le devoir 2, on a dû le créer manuellement
