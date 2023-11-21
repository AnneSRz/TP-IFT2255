package org.example;

import javax.management.Notification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.time.*;
import java.time.temporal.ChronoUnit;

public abstract class Utilisateur {

    Map<String, String> attributs=new HashMap<String, String>();
    String categorieUtilisateur;
    LocalDateTime momentInscription;
    Boolean verified = false;
    ArrayList<Commande> orders=new ArrayList<Commande>();
    ArrayList<Billet> signalements=new ArrayList<Billet>();
    ArrayList<Notification1> alertes=new ArrayList<Notification1>();

    /**
     * Constructeur de la classe Utilisateur
     *
     * @param username Le nom de l'utisateur/Identifiant
     * @param password Le mot de passe de l'utilisateur pour l'identification.
     */
    Utilisateur(String username, String password){
        attributs.put("username",username);
        attributs.put("password",password);
        this.momentInscription=LocalDateTime.now();
    }

    public String getCategorieUtilisateur(){
        return this.categorieUtilisateur;
    }

    /**
     * Adds Notification to the attribute list alertes
     *
     * @param alerte La notification à ajouter.
     */
    public void notifyMe(Notification1 alerte) {
        this.alertes.add(alerte);
    }

    /**
     * Adds Commande to the attribute list orders
     *
     * @param commande  La commande à ajouter.
     */
    public void ajouterCommande(Commande commande) {
        this.orders.add(commande);
    }


    /**
     * Adds Billet to the attribute list signalements
     *
     * @param billet Le billet à ajouter
     */
    public void ajouterBillet(Billet billet) {
        this.signalements.add(billet);
    }

    /**
     *
     * @return
     */
    public Boolean isVerifyExpired(){
        LocalDateTime now = LocalDateTime.now();
        Long days = ChronoUnit.DAYS.between(this.momentInscription,now);
        if (days < 30) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Utiliser pour vérifier le compte
     *
     */
    public void verify(){
        if(!(this.isVerifyExpired())) {
            this.verified = true;
        }
    }

    /**
     * Returns one of the attributes stored in the map attributs
     *
     * @param attribut Le nom de l'attribut à aller chercher.
     * @return La valeur de l'attribue
     */
    public String get(String attribut){
        return this.attributs.get(attribut);
    }

}
