package org.example;

import java.util.ArrayList;
import java.util.Map;

public class ProduitEnVente {
    String titre;
    String description;
    Integer pointsParDollar;
    ArrayList<Catalogue> type = new ArrayList<Catalogue>(); ;
    Integer quantite;
    Double prix;
    Float noteMoyenne;
    ArrayList<Utilisateur> popularite = new ArrayList<Utilisateur>();
    String imagePath;
    String videoPath;
    ArrayList<Evaluation> listeEvaluations = new ArrayList<Evaluation>();
    String id;
    Revendeur vendeur;
    Catalogue cat;

    /**Le constructeur de la classe ProduitEnVente
     *
     * @param titre Le nom du produit
     * @param description Les détails sur la nature du produits
     * @param pointsParDollar Lorsque le produit est achetés une certiaines quantités de points sont accordés pour
     *                          chaque dollard dépensé.
     * @param quantite Le nombre d'articles disponible
     * @param prix Le coût du produit
     * @param noteMoyenne La moyenne des notes qui ont été donné par les clients sur le produit.
     * @param imagePath Une image du produit en vente
     * @param videoPath Une video du produit en evente
     * @param id Une identifiant unique permettant d'identifier le produit
     */
    public ProduitEnVente(Revendeur vendeur, String titre, String description, int pointsParDollar, int quantite, double prix,
                          float noteMoyenne, String imagePath, String videoPath, String id,Catalogue cat) {
        this.cat=cat;
        this.vendeur=vendeur;
        this.titre = titre;
        this.description = description;
        this.pointsParDollar = pointsParDollar;
        this.quantite = quantite;
        this.prix = prix;
        this.noteMoyenne = noteMoyenne;
        this.imagePath = imagePath;
        this.videoPath = videoPath;
        this.id = id;
    }

    /**Cette fonction d'ajouter l'avis que l'utilisateur à laissé par rapport à sa satisfaction du produit qu'il a
     * acheté sur la pgae du produit.
     *
     * @param evaluation Une note et un commentaire laissé par le client qui a acheté le produit
     */
    public void ajouterEvaluation(Evaluation evaluation){this.listeEvaluations.add(evaluation);}


    /** Cette fonction d'ajouter la mention j'aime que l'utilisateur à laissé sur la pgae du produit.
     * @param user Utilisateur/Client qui aime le produit
     */
    public void like(Utilisateur user){
        this.popularite.add(user);
    }
    Map<String, String> attribut;


    /** Getter
     *
     * @return Le prix du produit
     */
    public Double getPrix(){
        return this.prix;
    }

    /** Getter
     *
     * @return Les points accordés à l'achat du produit
     */
    public Integer getPointsParDollar(){
        return this.pointsParDollar;
    }

    /**
     *
     * @return Le revendeur du produit
     */
    public Revendeur getRevendeur(){return this.vendeur;}
}
