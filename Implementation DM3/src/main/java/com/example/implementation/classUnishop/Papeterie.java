package com.example.implementation.classUnishop;

public class Papeterie extends Catalogue {


    /** constructeur de la classe papeterie
     *
     * @param marque          marque modèle et souscatégorie
     *                        des données stockées dans chaque instance d'un nouveau catalogue d'une catégorie Papeterie
     * @param modele
     * @param sousCategorie
     */
    Papeterie(String marque, String modele, String sousCategorie) {
        super();
        this.attributs.put("marque", marque);
        this.attributs.put("modele", modele);
        this.attributs.put("sousCategorie", sousCategorie);
    }


}
