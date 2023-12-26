package com.example.implementation.classUnishop;

import org.json.simple.JSONObject;


public class Coordonnees {
    private String nom;
    private String prenom;
    private String adresse;
    //private String paiement;
    private JSONObject paiement;
    private String telephone;
    private String courriel;


    /**
     * Rassemble les informations du client qui sont necessaire pour notamment la comamnde et la livraison
     * @param nom Nom de famille
     * @param prenom Nom personnel desigant la persone
     * @param adresse Où le client vit et ou sa commande peut lui être livré
     * @param telephone Le numéro de téléphone où il est possible de joindre l'acheteur
     * @param courriel Le courril où il est possible d'envoyer un message à l'acheteur
     * @param cc
     * @param conf
     * @param exp
     */
    public Coordonnees(String nom, String prenom, String adresse, String telephone, String courriel, String cc, String conf, String exp){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.courriel = courriel;

        //this.paiement = paiement;

        this.paiement = new JSONObject();
        this.paiement.put("cc", cc);
        this.paiement.put("conf", conf);
        this.paiement.put("exp", exp);
    }


    //public String get(String nomAttribut) {
  //      switch (nomAttribut.toLowerCase()) {
     //       case "nom":
     //           return this.nom;
     //       case "prenom":
     //           return this.prenom;
    //      case "adresse":
    //          return this.adresse;
    //      case "paiement":
     //           return this.paiement;
      //      case "telephone":
      //          return this.telephone;
      //      case "courriel":
     //           return this.courriel;
     //       default:
     //           return null; // Retourne null si on ne trouve rien.
      //  }
    //}

    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public String getAdresse() {return adresse;}
    public void setAdresse(String adresse) {this.adresse = adresse;}
    public String getTelephone() {return telephone;}

    public void setTelephone(String telephone) {this.telephone = telephone;}
    public String getCourriel() {return courriel;}
    public void setCourriel(String courriel) {this.courriel = courriel;}

    // Paiement
    public JSONObject getPaiement() {
        return paiement;
    }

    public void setPaiement(JSONObject paiement) {
        this.paiement = paiement;
    }


    // Extraire les données du fichier JSON
    public static Coordonnees donneesJSON(JSONObject userObject) {
        if (userObject instanceof JSONObject) {
            JSONObject userNode = (JSONObject) userObject;
            String nom = userNode.get("nom").toString();
            String prenom = userNode.get("prenom").toString();
            String adresse = userNode.get("adresse").toString();
            String telephone = userNode.get("telephone").toString();
            String courriel = userNode.get("courriel").toString();
            String paiement = userNode.get("paiement").toString();
            // Récupérer le champ "paiement" en tant qu'objet JSON
            JSONObject paiementObject = (JSONObject) userNode.get("paiement");

            // Extraire les champs du paiementObject
            String cc = paiementObject.get("cc").toString();
            String conf = paiementObject.get("conf").toString();
            String exp = paiementObject.get("exp").toString();

            System.out.println("Elément retourné du JSON pour la connexion : " + nom + " " + prenom + " " + adresse + " "
                    + telephone + " " + courriel + " " + cc + " " + conf + " " + exp);

            return new Coordonnees(nom, prenom, adresse, telephone, courriel, cc, conf, exp);
        } else {

            return null;
        }
    }

    @Override
    public String toString() {
        return "Coordonnees{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", courriel='" + courriel + '\'' +
                ", paiement='" + paiement + '\'' +
                '}';
    }

}
