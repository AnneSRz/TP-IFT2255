package com.example.implementation.classUnishop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProduitEnVente {

    private int pointsParDollar;

    public Map<String,String> attributs = new HashMap<String,String>();
    ArrayList<Utilisateur> popularite = new ArrayList<Utilisateur>();
    ArrayList<Evaluation> listeEvaluations = new ArrayList<Evaluation>();
    private String titre;
    private String description;
    private Integer quantite;
    private String id;

    private String imagePath;
    private Double prix;
    private Float noteMoyenne;
    private Revendeur vendeur;
    private Catalogue cat;


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
    /**public ProduitEnVente(Revendeur vendeur, String titre, String description, int pointsParDollar, int quantite, double prix,
                          float noteMoyenne, String imagePath, String videoPath, String id,Catalogue cat) {
        try{
            this.attributs.put("titre",titre);
            this.attributs.put("description",description);
            this.attributs.put("image",imagePath);
            this.attributs.put("videoPath",videoPath);
            this.attributs.put("id",id);

            this.cat=cat;
            this.vendeur=vendeur;
            this.pointsParDollar = pointsParDollar;
            this.quantite = quantite;
            this.prix = prix;
            this.noteMoyenne = noteMoyenne;
        } catch (ClassCastException e) {
            throw new RuntimeException("Format invalide");
        }
    }**/

    public ProduitEnVente(Revendeur vendeur, String titre, String description, int pointsParDollar, int quantite, double prix,
                          float noteMoyenne, String imagePath, String videoPath, String id,Catalogue cat) {

            this.titre = titre;
            this.description=description;
            this.imagePath = imagePath;
            this.attributs.put("videoPath",videoPath);
            this.id=id;
            this.cat=cat;
            this.vendeur=vendeur;
            this.pointsParDollar = pointsParDollar;
            this.quantite = quantite;
            this.prix = prix;
            this.noteMoyenne = noteMoyenne;
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
     * Getter
     *
     * @return Retourne l'attribut du revendeur du produit
     */
    public Revendeur getRevendeur(){return this.vendeur;}

    /**
     * Getter
     *
     * @return Retoourne l'attribut du Catalogue
     */
    public Catalogue getCatalogue(){return this.cat;}

    /**
     * Récupère la valeur associée à un attribut de produit en vente
     *
     * @param attribut Le nom de l'attribut à recuperer
     * @return Retourne le Billet associé à cet attribut
     */
    public String get(String attribut){return this.attributs.get(attribut);}

    /**
     * Récupère la valeur associée à la quantité de produit en vente
     *
     * @return Retourne la quantité
     */
    public Integer getQty(){return this.quantite;}

    /**
     * Récupère la note associée au produit
     * @return Retourne la note
     */
    public Float getNote(){return this.noteMoyenne;}

    public String getTitre(){return this.titre;}
    public String getDescription(){return this.description;}
    public String getId(){return this.id;}
    public String getImagePath(){return this.imagePath;}

}
