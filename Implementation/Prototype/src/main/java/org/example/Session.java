package org.example;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {
    LocalDateTime start;
    LocalDateTime end;
    Utilisateur user;


    /**
     * Constructeur de la classe session
     *
     * @param user utilisateur associé à la session
     */
    Session (Utilisateur user){
        this.start = LocalDateTime.now();
        this.end = LocalDateTime.now().plusHours(1);
        this.user=user;
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