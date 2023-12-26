package com.example.implementation.classUnishop;

public class Informatique extends Catalogue {


    /** Constructeur de la classe Catalogue
     *
     * @param marque  marque modele souscategorie datelancement sont
     *                des données stockées dans chaque instance d'un nouveau catalogue d'une catégorie Informatique.
     * @param modele
     * @param sousCategorie
     * @param dateLancement
     */
    Informatique(String marque, String modele, String sousCategorie,String dateLancement) {
        super();
        this.attributs.put("marque", marque);
        this.attributs.put("modele", modele);
        this.attributs.put("sousCategorie", sousCategorie);
        this.attributs.put("dateLancement", dateLancement);
    }


}
