package com.example.implementation.classUnishop;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Acheteur extends Utilisateur {

    Coordonnees infosAchat;
    ArrayList<Acheteur> amis = new ArrayList<Acheteur>();
    ArrayList<Revendeur> vendeursSuivis = new ArrayList<Revendeur>();
    ArrayList<ProduitEnVente> likes = new ArrayList<ProduitEnVente>();
    Panier panier;
    ListeSouhaits whishlist;
    Catalogue catalogue;

    /**
     * @param username Le nom d'utilisateur de l'acheteur pour la connection.
     * @param password Le mot de passe de l'acheteur pour la connection.
     * @param infos    Les oordonnées associées aux informations de l'acheteur qui servira à l'achat.
     */

    public Acheteur(String username, String password, Coordonnees infos) {
        super(username, password);
        this.infosAchat = infos;
        //panier = new Panier(this);
        this.whishlist = new ListeSouhaits(this);
        this.categorieUtilisateur = "acheteur";
    }


    public void chargerListeSouhaits(JSONObject acheteurJSON) {
        JSONArray souhaitsJSON = (JSONArray) acheteurJSON.get("souhaits");

        for (Object wish : souhaitsJSON) {
            JSONObject wishJSON = (JSONObject) wish;
            Object wishIdObj = wishJSON.get("wishId");

            if (wishIdObj != null) {
                String wishId = wishIdObj.toString();

                ProduitEnVente produit = chargerProduit(wishId);

                // Ajoutez le produit à la liste de souhaits
                whishlist.getSouhaits().add(produit);
            } else {
                System.out.println("Skipping a JSON object with null 'wishId'");
            }
        }
    }

    private ProduitEnVente chargerProduit(String productId) {
// Charger le fichier JSON des produits
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("Implementation/src/main/resources/com/example/implementation/data/produits.json"));

            JSONArray produits = (JSONArray) obj;

            // Parcourir la liste des produits
            for (Object produitObj : produits) {
                JSONObject produitJSON = (JSONObject) produitObj;
                String produitId = (String) produitJSON.get("id");

                if (produitId.equals(productId)) {
                    Revendeur revendeur = null;
                    Object revendeurJSON = produitJSON.get("revendeur");

                    if (revendeurJSON != null && revendeurJSON instanceof JSONObject) {
                        JSONObject revendeurObj = (JSONObject) revendeurJSON;

                        revendeur = new Revendeur(
                                (String) revendeurObj.get("pseudo"),
                                (String) revendeurObj.get("mdp"),
                                (String) revendeurObj.get("courriel"),
                                String.valueOf(revendeurObj.get("telephone")),
                                (String) revendeurObj.get("adresse")
                        );
                    }

                    // Récupérer les informations des produits
                    String storedTId = (String) produitJSON.get("id");
                    String storedTitle = (String) produitJSON.get("titre");
                    String storedDescriptions = (String) produitJSON.get("description");
                    int storedPoints = Integer.parseInt((String) produitJSON.get("pointsParDollar"));
                    int storedQuantite = Integer.parseInt((String) produitJSON.get("quantite"));
                    double storedPrix = Double.parseDouble((String) produitJSON.get("prix"));
                    float storedNote = Float.parseFloat((String) produitJSON.get("noteMoyenne"));
                    String storedImage = (String) produitJSON.get("imagePath");
                    String storedVideo = (String) produitJSON.get("videoPath");

                    return new ProduitEnVente(
                            revendeur,
                            storedTitle, storedDescriptions, storedPoints,
                            storedQuantite, storedPrix, storedNote, storedImage, storedVideo,
                            storedTId, this.catalogue);
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            // Gérer les exceptions selon vos besoins
        }
        return null;
    }

    public ListeSouhaits getWishlist() {
        return this.whishlist;
    }
    public void retirerDeLaListeDeSouhait(ProduitEnVente produit) {
        this.whishlist.getSouhaits().remove(produit);
    }



    /**
     * Ajoute un ami à la liste de l'acheteur, s'il n'est pas déjà présent dans arraylist amis
     *
     * @param ami L'acheteur a ajouté en tant qu'ami.
     */
    public void ajouterAmi(Acheteur ami) {
        if (ami == null) {
            return;
        }
        ;
        if (this.likes.indexOf(ami) == -1) {
            this.amis.add(ami);
        }
    }


    /**
     * Retire un ami de la liste d'amis de l'acheteur, s'il est dans la liste.
     *
     * @param ami L'acheteur a retiré un ami.
     */
    public void retirerAmi(Acheteur ami) {
        if (ami == null) {
            return;
        }
        ;
        this.amis.remove(ami);
    }

    public ArrayList<Acheteur> getAmis() {
        return this.amis;
    }

    /**
     * Ajoute un revendeur à la liste de revendeurs suivis par l'acheteur, s'il n'est pas déjà dans arraylist
     * vendeursSuivis
     *
     * @param vendeur Le revendeur à ajouter à la liste de revendeurs suivis par l'acheteur
     */
    public void ajouterRevendeur(Revendeur vendeur) {
        if (this.vendeursSuivis.indexOf(vendeur) == -1) {
            this.vendeursSuivis.add(vendeur);
        }
    }

    /**
     * Retire un revendeur de la liste de revendeurs qui sont suivis par l'acheteur.
     *
     * @param vendeur Le revendeur à retirer de la liste de revendeurs suivis par l'acheteur
     */
    public void retirerRevendeur(Revendeur vendeur) {
        this.vendeursSuivis.remove(vendeur);
    }


    /**
     * Récupère les coordonnées associées aux informations d'achat de l'acheteur.
     *
     * @return Les coordonnées de l'acheteur.
     */
    public Coordonnees getCoordonnees() {
        return this.infosAchat;
    }

    /**
     * Récupère le panier associés aux articles que l'acheteur a ajouté
     *
     * @return Le panier de l'acheteur
     */
    public Panier getPanier() {
        return this.panier;
    }

    @Override
    public String toString() {
        return "Acheteur{" +
                "infosAchat=" + infosAchat +
                '}';
    }

}
