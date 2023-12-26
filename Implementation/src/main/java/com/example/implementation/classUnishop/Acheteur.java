package com.example.implementation.classUnishop;

import java.util.ArrayList;

public class Acheteur extends Utilisateur {
    Coordonnees infosAchat;
    ArrayList<Acheteur> amis=new ArrayList<Acheteur>();
    ArrayList<Revendeur> vendeursSuivis=new ArrayList<Revendeur>();
    ArrayList<ProduitEnVente> likes=new ArrayList<ProduitEnVente>();
    Panier panier;
    ListeSouhaits whishlist;

    /**
     *
     * @param username Le nom d'utilisateur de l'acheteur pour la connection.
     * @param password Le mot de passe de l'acheteur pour la connection.
     * @param infos Les oordonnées associées aux informations de l'acheteur qui servira à l'achat.
     */

    public Acheteur(String username, String password, Coordonnees infos){
        super(username, password);
        this.infosAchat=infos;
        panier = new Panier(this);
        whishlist = new ListeSouhaits(this);
        this.categorieUtilisateur="acheteur";
    }

    /**
     * Ajoute un ami à la liste de l'acheteur, s'il n'est pas déjà présent dans arraylist amis
     *
     * @param ami L'acheteur a ajouté en tant qu'ami.
     */
    public void ajouterAmi(Acheteur ami) {
        if (this.likes.indexOf(ami) == -1) {
            this.amis.add(ami);
        }
    }


    /**
     * Retire un ami de la liste d'amis de l'acheteur, s'il est dans la liste.
     *
     * @param ami L'acheteur a retiré un ami.
     */
    public void retirerAmi(Acheteur ami) {
        this.amis.remove(ami);
    }



    /**
     * Ajoute un revendeur à la liste de revendeurs suivis par l'acheteur, s'il n'est pas déjà dans arraylist
     * vendeursSuivis
     *
     * @param vendeur Le revendeur à ajouter à la liste de revendeurs suivis par l'acheteur
     */
    public void ajouterRevendeur(Revendeur vendeur){
        if(this.vendeursSuivis.indexOf(vendeur)==-1){
                this.vendeursSuivis.add(vendeur);
        }
    }

    /**
     * Retire un revendeur de la liste de revendeurs qui sont suivis par l'acheteur.
     *
     * @param vendeur Le revendeur à retirer de la liste de revendeurs suivis par l'acheteur
     */
    public void retirerRevendeur(Revendeur vendeur) {
        this.vendeursSuivis.remove(vendeur);
    }


    /**
     * Récupère les coordonnées associées aux informations d'achat de l'acheteur.
     *
     * @return Les coordonnées de l'acheteur.
     */
    public Coordonnees getCoordonnees(){
        return this.infosAchat;
    }

    /**
     * Récupère le panier associés aux articles que l'acheteur a ajouté
     *
     * @return Le panier de l'acheteur
     */
    public Panier getPanier(){
        return this.panier;
    }

    @Override
    public String toString() {
        return "Acheteur{" +
                "infosAchat=" + infosAchat +
                '}';
    }

}
