package org.example;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {
    LocalDate start;
    LocalDate end;
    Acheteur user;


    /**
     * Constructeur de la classe session
     *
     * @param user utilisateur associé à la session
     */
    Session (Acheteur user){
        this.start = LocalDate.now();
        this.end = LocalDate.now().plusDays(1);
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
        LocalDateTime currentDateTime = LocalDateTime.now();
        Duration tempsSession = Duration.between(start, currentDateTime);
        long hoursElapsed = tempsSession.toHours();

        return hoursElapsed >= 24;
    }

    public Acheteur user(){
        return this.user;
    }

}