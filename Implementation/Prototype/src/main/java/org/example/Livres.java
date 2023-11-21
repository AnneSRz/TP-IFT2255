package org.example;

public class Livres extends Catalogue{

    /**  constructeur de la classe Livres
     *
     * @param genre         genre, isbn, auteur, maison édition, dateparution, numeroedition, numerovolume sont
     *                      des données stockées dans chaque instance d'un nouveau catalogue d'une catégorie Livre.
     * @param isbn
     * @param auteur
     * @param maisonEdition
     * @param dateParution
     * @param numeroEdition
     * @param numeroVolume
     */
    Livres(String genre, String isbn, String auteur, String maisonEdition, String dateParution, String numeroEdition, String numeroVolume){
        super();
        this.attributs.put("genre",genre);
        this.attributs.put("isbn",isbn);
        this.attributs.put("auteur",auteur);
        this.attributs.put("maisonEdition",maisonEdition);
        this.attributs.put("dateParution",dateParution);
        this.attributs.put("numeroEdition",numeroEdition);
        this.attributs.put("numeroVolume",numeroVolume);
    }
}
