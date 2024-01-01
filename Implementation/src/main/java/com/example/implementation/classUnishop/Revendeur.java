package com.example.implementation.classUnishop;

import java.util.ArrayList;
import java.util.List;

public class Revendeur extends Utilisateur {

    ArrayList<Metrique> metriques=new ArrayList<Metrique>();
    ArrayList<Utilisateur> likes=new ArrayList<Utilisateur>();
    ArrayList<ProduitEnVente> produits=new ArrayList<ProduitEnVente>();
    private List<String> suiveurs = new ArrayList<>();

    /**Constructeur de la classe Revendeur
     *
     * @param username Nom d'utilisateur du revendeur.
     * @param password Mot de passe du revendeur.
     * @param courriel Le courriel du Revendeur
     * @param telephone Le numéro de téléphone du revendeur
     * @param adresse L'adresse physique du revendeur
     */
    public Revendeur(String username, String password,String courriel,String telephone, String adresse){
        super(username,password);
        this.attributs.put("courriel",courriel);
        this.attributs.put("telephone",telephone);
        this.attributs.put("adresse",adresse);
        this.categorieUtilisateur="revendeur";
    }

    /**
     *
     * @return La liste des uiveurs d'un utilisateur
     */
    public List<String> getSuiveurs() {return this.suiveurs;}
    /**
     * Pour retrieve la liste des suiveurs
     * @param suiveurs
     */
    public void setSuiveurs(List<String> suiveurs) {this.suiveurs = suiveurs;}


    /**
     * Ajoute un ami à la liste de l'acheteur, s'il n'est pas déjà présent dans arraylist amis
     *
     * @param suiveur L'acheteur a ajouté en tant qu'ami.
     */
    public void ajoutAmi(String suiveur) {this.suiveurs.add(suiveur);}

    /**
     * Retire un ami de la liste d'amis de l'acheteur, s'il est dans la liste.
     *
     * @param suiveur L'acheteur a retiré un ami.
     */
    public void enleverAmi(String suiveur) {this.suiveurs.remove(suiveur);}


    /** Ajoute Metrique de l'arraylist metriques du revendeur
     *
     * @param metrique La métrique à ajouter.
     */
    public void ajouterMetrique(Metrique metrique) {
        if (this.metriques.indexOf(metrique) == -1) {
            this.metriques.add(metrique);
        }
    }


    /**
     * Ajoute ProduitEnVente de l'arraylist produits
     *
     * @param produit Le produit en vente à ajouter.
     */
    public void ajouterProduit(ProduitEnVente produit) {
        if (this.produits.indexOf(produit) == -1) {
            this.produits.add(produit);
        }
    }

    /**
     * Retire ProduitEnVente de l'arraylist produits
     *
     * @param produit Le produit en vente à retirer.
     */
    public void retirerProduit(ProduitEnVente produit){
        this.produits.remove(produit);
    }


    /**
     * Ajoute un subscriber de l'arraylist likes
     *
     * @param user L'utilisateur à ajouter à la liste des mentions du revendeur
     */
    public void ajouterSubscriber(Utilisateur user) {
        if (this.likes.indexOf(user) == -1) {
            this.likes.add(user);
        }
    }


    /**
     * Retire un subscriber de l'arraylist likes
     *
     * @param user L'utilisateur à retirer à la liste des mentions du revendeur
     */
    public void retirerSubscriber(Utilisateur user){
        this.likes.remove(user);
    }

}
