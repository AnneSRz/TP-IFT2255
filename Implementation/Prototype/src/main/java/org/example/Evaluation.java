package org.example;
import java.util.ArrayList;

public class Evaluation {
    String commentaireAuteur;
    Integer noteDeAuteur;
    ArrayList<Utilisateur> popularite=new ArrayList<Utilisateur>();
    Utilisateur auteur;
    Boolean pointsGiven=false;


    /**constructeur de la classe Evaluation
     *
     * @param commentaireAuteur Le commentaire laissé par l'acheteur du produit.
     * @param noteDeAuteur La note attribuée par laissé par l'acheteur du produit.
     * @param auteur Celui qui a écrit l'évaluation
     */
    Evaluation(String commentaireAuteur, Integer noteDeAuteur, Utilisateur auteur){
        this.commentaireAuteur=commentaireAuteur;
        this.noteDeAuteur=noteDeAuteur;
        this.auteur=auteur;
    }

    /**
     * Adds a new user to the list of users who liked the item
     *
     * @param user L'utilisateur à ajouter dans = la liste des mentions.
     */
    public void like(Utilisateur user){
        this.popularite.add(user);
    }


    /**All getters
     *
     * @return Récupère les informations associés à l'évaluation.
     */
    public Integer getNote(){
        return this.noteDeAuteur;
    }

    public Utilisateur getAuteur(){
        return this.auteur;
    }

    public ArrayList<Utilisateur> getLikes(){
        return this.popularite;
    }

    public Boolean pointsGiven(){
        return this.pointsGiven;
    }


    /**
     * When the system gives points, it flags this Evaluation with true
     */
    public void pointsGive(){
        this.pointsGiven=true;
    }

    /**
     * When the system removes or deletes an evaluation, it can ''unflag'' the evaluation
     */
    public void pointsRemove(){
        this.pointsGiven=false;
    }

}
