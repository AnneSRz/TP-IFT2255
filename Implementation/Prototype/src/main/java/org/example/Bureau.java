package org.example;

public class Bureau extends Catalogue {

    /**
     * Constructeur de la classe Bureau qui initialise une nouvelle instance de la catégorie Bureau
     * avec les attributs spécifiés.
     *
     * @param marque         Marque,Modèle,SousCatégorie sont
     * @param modele         des données stockées dans chaque instance d'un nouveau catalogue d'une catégorie Bureau
     * @param sousCategorie
     */
    Bureau(String marque, String modele, String sousCategorie) {
        super();
        this.attributs.put("marque", marque);
        this.attributs.put("modele", modele);
        this.attributs.put("sousCategorie", sousCategorie);
    }
}
