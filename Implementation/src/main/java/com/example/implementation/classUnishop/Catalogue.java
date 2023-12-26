package com.example.implementation.classUnishop;

import java.util.HashMap;
import java.util.Map;


public abstract class Catalogue {
    Map<String, String> attributs;

    /**
     * Constructeur de la classe Catalogue qui initialise la structure de données des attributs.
     */
    Catalogue() {
        this.attributs = new HashMap<String, String>();
    }


    /**
     * Récupère la valeur associée à un attribut du catalogue.
     *
     * @param nomAttribut Le nom de l'attribut dont on veut récupérer la valeur.
     * @return La valeur associée à l'attribut spécifié, null si l'attribut n'est pas présent.
     */
    public String get(String nomAttribut){
        return this.attributs.get(nomAttribut);
    }

    /**
     * Modifie la valeur associée à un attribut spécifié du catalogue.
     * @param nomAttribut nom de l'attribut dont on veut modifié la valeur
     * @param valeur La nouvelle valeur à associé à l'attribut.
     */
    public void set(String nomAttribut, String valeur){
        this.attributs.replace(nomAttribut,valeur);
    }
}
