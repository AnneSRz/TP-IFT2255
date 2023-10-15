# Commentaires DM1

Corrigé par An Li

Total: 83%

## Glossaire [9/10]

Termes obligatoires manquants:

- UniShop

Doutes:

- Liste de souhaits = panier?

## Diagramme de cas utilisation [16/25]

Cas d'utilisation manquants:

- Trouver un produit
- Trouver un revendeur
- Consulter l'état d'une commande
- Changer l'état d'une commande

Ces étapes ne sont pas des cas d'utilisation:

- Vérifier si le(s) produit(s) sont disponibles
- Entrer les informations pour créer un compte
- Vérification des champs
- Authentification partielle
- Envoi des données à la base de données
- Gérer les comptes en attente

Acteurs externes manquants:

- Service postal
- Fournisseur de paiement

Ces acteurs ne sont pas des acteurs nécessaires:

- Équipe de modération
- Base de données

## Description des cas d'utilisation [46/50]

- Mise à jour de la liste de souhaits:
  - Un acheteur ne peut pas écrire un commentaire sur un produit sans l'avoir acheté
- Achat de plusieurs produits
  - L'acheteur doit entrer ses informations de livraison avant de payer
  - Le courriel de rappel ne fait pas partie des exigences de base
- Vendre un produit
  - Étape 2: Les sous-étapes montrent le processus d'inscription et non le processus de connexion
- Voir une métrique
  - Voir l'historique d'achat n'a pas rapport avec la consultation des métriques
- Validation des comptes par la modération
  - La modération ne fait pas partie des exigences

Général:

- Pour la plupart des descriptions, alterner entre le client et le système si possible
- Après chaque scénario alternatif, montrer l'étape du scénario principal où retourner
- Les cas "Acheter un manuel" et "Acheter plusieurs produits" peuvent être regroupés, tout ce qui change est le nombre d'articles qu'il a dans son panier

## Risques [5/5]

Bien!

## Besoins non-fonctionnels [4/5]

Parler de la disponibilité (e.g., l'application doit être disponibles 24 heures sur 24, 7 jours sur 7)

## Bonne utilisation de GitHub et statistiques [3/5]

- Manque capture d'écran des Insights dans le rapport
- Nombre de pull requests ne représente pas le nombre d'Issues

## Bonus: Application Java [N/A]
