package org.example;
import java.time.LocalDate;
public class Metrique {
    String indicateur;
    Float ratiosAgreges;
    Double Revenu;
    Integer nbArticlesMisEnVente;
    Integer nbArticlesVendus;

    /** Constructeur de la classe Metrique
     *
     * @param indicateur L'indicateur de la métrique.
     * @param ratiosAgreges Les ratios agrégés associés à la métrique.
     */
    Metrique(String indicateur, Float ratiosAgreges){
        this.indicateur = indicateur;
        this.ratiosAgreges = ratiosAgreges;
    }

    /** Getters
     *
     * @return Récupère les informations de la métrique
     */
    public String getIndicateur(){
        return this.indicateur;
    }
    public Float getRatiosAgreges(){
        return this.ratiosAgreges;
    }
    public Integer getNbArticlesVendus(){
        return this.nbArticlesVendus;
    }

}


/* <3  */