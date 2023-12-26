package com.example.implementation.classUnishop;

public class Ressource extends Catalogue{


    /** Constructeur de la classe Ressource
     *
      * @param isbn        isbn, auteur, dateparution, numeroedition, type sont
     *                      des données stockées dans chaque instance d'un nouveau catalogue d'une catégorie Ressource
     * @param auteur
     * @param dateParution
     * @param numeroEdition
     * @param type
     */
    Ressource(String isbn, String auteur, String dateParution, String numeroEdition, String type){
        super();
        this.attributs.put("isbn",isbn);
        this.attributs.put("auteur",auteur);
        this.attributs.put("dateParution",dateParution);
        this.attributs.put("numeroEdition",numeroEdition);
        this.attributs.put("type",type);
    }
}
