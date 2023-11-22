package org.example;

import java.util.Map;

public class Billet {
    String descriptionProbleme;
    String descriptionSolution;
    Boolean confirmationLivraison;
    Boolean confirmationRetour;
    Integer numeroSuiviRemplacement;
    Boolean confirmationLivraisonRemplacement;
    Boolean solutionAcceptee;
    Utilisateur acteurInitiateur;
    String compagnieLivraison;
    ProduitEnVente produit;
    Commande commande;
    Map<String, String> attributs;


    /**
     * Lorsqque le client initie un retour pour un produit, un billet de retour est créé et est envoyée au revendeur, ce
     * billet contient les information necessaire pour le processus de retour de l'article.
     *
     * @param descriptionProbleme La description du problème associé au billet.
     * @param descriptionSolution La description de la solution proposée ou appliquée.
     * @param confirmationLivraison Indique si la livraison a été confirmée.
     * @param confirmationRetour Indique si le retour a été confirmé.
     * @param numeroSuiviRemplacement Le numéro de suivi de l'échange.
     * @param confirmationLivraisonRemplacement Indique si la livraison du remplacement a été confirmée.
     */
    public Billet(String descriptionProbleme, String descriptionSolution, boolean confirmationLivraison,
                  boolean confirmationRetour, int numeroSuiviRemplacement, boolean confirmationLivraisonRemplacement) {
        this.descriptionProbleme = descriptionProbleme;
        this.descriptionSolution = descriptionSolution;
        this.confirmationLivraison = confirmationLivraison;
        this.confirmationRetour = confirmationRetour;
        this.numeroSuiviRemplacement = numeroSuiviRemplacement;
        this.confirmationLivraisonRemplacement = confirmationLivraisonRemplacement;
    }


    /**
     *Lorsque l'acheteur veut retourner un article, le revendeur peut lui proposer une solution au lieu de lerembourser.
     * @return
     */
    public boolean accepterSolution() {
        if (this.descriptionSolution != null && !this.descriptionSolution.isEmpty()) {
            this.solutionAcceptee = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Lorsque le revendeur recoit l'article a son entrepot, il confirme la réception et met à jour l'état du retour
     * à "livré".
     *
     * @return true si la confirmation du retour est réussie, false sinon.
     */
    public boolean confirmerRetour() {
        if (!this.confirmationRetour) {
            this.confirmationRetour = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Lorsque le client initie un retour ou un échange, le revendeur peut proposer une autre solution comme alternative
     * à la place du remboursement de la commande.
     *
      * @param description La solution proposée par le revendeur
     */
    public void proposerSolution(String description) {
        this.descriptionSolution = description;
    }

    /**
     * Récupère la valeur associée à un attribut du billet.
     *
     * @param nomAttribut Le nom de l'attribut dont on veut récupérer la valeur.
     * @return La valeur associée à l'attribut spécifié, null si l'attribut n'est pas présent.
     */
    public String get(String nomAttribut){
        return this.attributs.get(nomAttribut);
    }

    /**
     *
     * Modifie la valeur associée à un attribut spécifié du billet.
     *
     * @param nomAttribut nom de l'attribut dont on veut modifié la valeur
     * @param valeur La nouvelle valeur à associé à l'attribut.
     */
    public void set(String nomAttribut, String valeur){
        this.attributs.replace(nomAttribut,valeur);
    }

}
