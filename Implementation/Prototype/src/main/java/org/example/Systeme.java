package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

public class Systeme {


    Systeme(){}

    public Boolean pay(Double amount, Coordonnees paiement){
            /* on simule le paiement avec l'institution financière*/
        return true;
    }

    public void notify(ArrayList<Revendeur> target, Notification1 alerte){
        for(Revendeur element : target){
            element.notifyMe(alerte);
        }
    }


    public Session login(String username,String password){
        Coordonnees infos = new Coordonnees("joe","nuts","123 gamer avenue","visa","123456789","e@gmail.com");
        Acheteur user = new Acheteur(username,password,infos);
        Session session = new Session(user);
        return session;
    }
}
