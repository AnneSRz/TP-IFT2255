package com.example.implementation.classUnishop;

import java.time.LocalDateTime;
import java.util.Optional;

public class Session {

    private static Session instance;
    private Utilisateur utilisateurConnecte;
    LocalDateTime start;
    LocalDateTime  end;
    Utilisateur user;


    /**
     * Constructeur de la classe Session
     *
     * @param user    L'utilisateur associé à la session.
     */
    public Session(Utilisateur user) {
        this.user = user;
        this.start = LocalDateTime.now();;
        this.end = LocalDateTime.now().plusHours(1);
    }

    private Session() {

    }
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Optional<Utilisateur> getUtilisateurConnecte() {
        return Optional.ofNullable(utilisateurConnecte);
    }

    public void setUtilisateurConnecte(Utilisateur utilisateur) {
        this.utilisateurConnecte = utilisateur;
    }

    public void deconnecter() {
        this.utilisateurConnecte = null;
    }

    


    /**
     * Vérifie si la session est expirée en comparant la date de début de la session avec la date et l'heure actuelles.
     * La session est expirée si la durée écoulée depuis le début de la session est supérieure ou égale à 24 heures.
     * Ainsi l'utilisateur devra se reconnecter.
     *
     * @return Si la session est expirée, on retourne true sinon false
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.end);
    }

    /**
     *
     * @return Retourne l'attribut de l'utilisateur
     */
    public Utilisateur user(){
        return this.user;
    }

}