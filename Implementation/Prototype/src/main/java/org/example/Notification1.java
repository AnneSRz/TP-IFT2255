package org.example;
import java.time.LocalDate;
public class Notification1 {
    String description;
    Utilisateur acheteurInitiateur;
    LocalDate dateInitiation;

    /**
     * Constructeur de la classe notification
     *
     * @param description Une description de la notification/son contenu.
     * @param acheteurInitiateur L'acheteur qui a initié la notification.
     */
    Notification1(String description, Utilisateur acheteurInitiateur){
        this.description = description;
        this.acheteurInitiateur = acheteurInitiateur;
        this.dateInitiation = LocalDate.now();
    }

    /**
     * Récupère les données relatives à l'acheteur qui a initié la notification.
     *
     * @return L'acheteur qui a initié la notification.
     */
    public String description(){
        return this.description;
    }
    public Utilisateur acheteurInitiateur(){return this.acheteurInitiateur;}

    public LocalDate dateInitiation(){return this.dateInitiation;}

}

