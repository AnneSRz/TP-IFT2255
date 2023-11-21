package org.example;

import java.time.LocalDate;

public class Coordonnees {
    private String nom;
    private String prenom;
    private String adresse;
    private String paiement;
    private String telephone;
    private String courriel;

    /**
     * Rassemble les informations du client qui sont necessaire pour notamment la comamnde et la livraison
     * @param nom Nom de famille
     * @param prenom Nom personnel desigant la persone
     * @param adresse Où le client vit et ou sa commande peut lui être livré
     * @param paiement Les informations du client pour payer sa commande/le total dù
     * @param telephone Le numéro de téléphone où il est possible de joindre l'acheteur
     * @param courriel Le courril où il est possible d'envoyer un message à l'acheteur
     */
    public Coordonnees(String nom, String prenom, String adresse, String paiement, String telephone, String courriel){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.paiement = paiement;
        this.telephone = telephone;
        this.courriel = courriel;
    }

    /**
     * Obtient la valeur de l'attribut spécifié par le nom de ce dernier.
     *
     * @param nomAttribut Le nom de l'attribut.
     * @return La valeur de l'attribut ou null si l'attribut n'est pas trouvé.
     */
    public String get(String nomAttribut) {
        switch (nomAttribut.toLowerCase()) {
            case "nom":
                return this.nom;
            case "prenom":
                return this.prenom;
            case "adresse":
                return this.adresse;
            case "paiement":
                return this.paiement;
            case "telephone":
                return this.telephone;
            case "courriel":
                return this.courriel;
            default:
                return null; // Retourne null si on ne trouve rien.
        }
    }



}
