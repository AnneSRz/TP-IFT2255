# Commentaires DM3

Corrigé par An Li

Total: 75%

## Code source Java du programme [30/45]

- J'ai dû réparer les parcours vers vos fichiers de données
- Le menu du revendeur n'est pas implémenté
- Certaines opérations fonctionnent mal
  - Impossible de se connecter avec un compte autre que le premier de la liste des utilisateurs
  - Le bouton "Mes commandes" ne fait rien
  - Le panier ne se vide pas après une commande est passée
  - Mauvais moment de mettre à jour la liste des utilisateurs: on doit la mettre à jour après chaque commande passée, pas seulement quand on modifie le profil
    - Quand on modifie le profil, les champs non modifiés par l'utilisateur sont effacés quand ça ne devrait pas
  - La fenêtre des suiveurs n'est pas populé

## Tests unitaires en JUnit [10/20]

- J'ai dû déplacer les fichiers
- Les imports doivent être réparés pour pouvoir rouler les tests
- Les classes Systeme, MenuAcheteur n'ont pas de constructeur public, il faut les ajouter pour que les tests roulent
- La moitié des tests ne passent pas

## Configuration Maven [4/5]

- La classe Main est mal définie dans pom.xml

## Production du JAR [3/5]

- Le JAR n'est pas exécutable
  - Vous pouvez utiliser le maven-shade-plugin pour rendre votre JAR exécutable (exemple [ici](https://github.com/ingconti/JavaFXWithImagesAndText))

## Manuel utilisateur (README) [4/5]

- Mettez vos noms et le lien vers vos profils GitHub dans ce fichier
- Un peu trop de descriptions pour les entités
- On ne veut pas du code pour montrer toutes les librairies nécessaires, une liste simple serait plus facile à lire

## JavaDocs générés [3/5]

- Vous avez oublié de générer le JavaDoc
- Il n'y a pas de description pour les classes
- Certaines des méthodes n'ont pas de description

## Cohérence entre les modèles et le code [11/15]

- Le diagramme de classes révisé n'est pas en SVG
- Les classes Utilisateur et ProduitEnVente devraient être abstraites
- Mauvaise utilisation de Catalogue entre ProduitEnVente et les sous-classes des produits
  - Associer le catalogue à l'application et utiliser une aggrégation pour les produits
- Séparer les opérations spécifiques à chaque rôle du UnishopController pour une meilleure gestion d'accès
- Redondance: Liste de souhaits = panier?

## Bonus: Interface graphique [10/10]

Belle interface!

## Bonus: Action GitHub [N/A]
