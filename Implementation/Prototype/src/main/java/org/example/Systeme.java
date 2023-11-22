package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Systeme {


/*    Liste des données générées pour tester le logiciel */

        /*Revendeurs */
        Revendeur v1 = new Revendeur("GokuVente", "dragonballs", "goku@gmail.com", "1234567890", "22 kame house");
        Revendeur v2 = new Revendeur("AmongusStore", "sussy", "sus@gmail.com", "1234567190", "99 spaceship avenue");
        Revendeur v3 = new Revendeur("SauceStore", "yum", "sauce@gmail.com", "1234367190", "98 spaceship avenue");
        Revendeur v4 = new Revendeur("Sephora", "stuff", "sephora@gmail.com", "1233567190", "centre eaton or wtv");
        Revendeur v5 = new Revendeur("annesoso", "joe123", "soso@gmail.com", "1234537190", "saint laurent");
        /*Catalogues */
        Papeterie cat1 = new Papeterie("bmw","ultraJoe","SuperL");
        Bureau cat2 = new Bureau("ferrari","mark1","souris d'ordinateur");
        Bureau cat3 = new Bureau("pepsi","pentagone","gaming chair");
        Informatique cat4 = new Informatique("pepsi","pentagone","gaming chair","9/9/2009");
        Informatique cat5 = new Informatique("NVIDIA","RTX3090","GPU","9/9/2021");
        Ressource cat6 = new Ressource("LL1984", "George Orwell", "1942/01/22", "1", "guide");
        Livres cat7 = new Livres("Dystopian", "470015866", "George Orwell", "Secker&Warburg", "8 June 1949", "1", "1");
        /* ProduitEnVente*/
        ProduitEnVente p1 = new ProduitEnVente(v1, "average book1", "great for sleeping", 1, 45, 5, 3,"", "", "",cat7);
        ProduitEnVente p2 = new ProduitEnVente(v1, "average book2", "great for sleeping", 2, 3, 15, 1,"", "", "",cat7);
        ProduitEnVente p3 = new ProduitEnVente(v2, "item1", "neat looking", 2, 3, 15, 1,"", "", "",cat1);
        ProduitEnVente p4 = new ProduitEnVente(v2, "item2", "smeels great", 2, 3, 15, 1,"", "", "",cat2);
        ProduitEnVente p5 = new ProduitEnVente(v2, "item3", "makes funny noises", 2, 3, 15, 1,"", "", "",cat3);
        /* Coordonnées */
        Coordonnees infos1 = new Coordonnees("joe","nuts","123 gamer avenue","visa","1234567891","e@gmail.com");
        Coordonnees infos2 = new Coordonnees("bob","gras","124 gamer avenue","master","1234567822","c@gmail.com");
        /* Acheteurs */
        Acheteur user = new Acheteur("tp2","100pourcent",infos1);
        Acheteur user2 = new Acheteur("aa","aa",infos1);
        Acheteur user3 = new Acheteur("bb","100pourcent",infos1);
        Acheteur user4 = new Acheteur("cc","100pourcent",infos1);
        Acheteur user5 = new Acheteur("dd","100pourcent",infos1);
        /*  */
        Map<ProduitEnVente,Integer> contenu1 = new HashMap<ProduitEnVente,Integer>();
        Map<ProduitEnVente,Integer> contenu2 = new HashMap<ProduitEnVente,Integer>();
        Map<ProduitEnVente,Integer> contenu3 = new HashMap<ProduitEnVente,Integer>();
        Commande c1 = new Commande(user, contenu1, 10.1, infos1);
        Commande c2 = new Commande(user, contenu2, 12.1, infos1);
        Commande c3 = new Commande(user, contenu3, 130.1, infos1);
        /*LISTES */
        ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
        ArrayList<ProduitEnVente> items=new ArrayList<ProduitEnVente>();
        ArrayList<Revendeur> revendeurs=new ArrayList<Revendeur>();


    Systeme(){
        /*prépare les listes */
        this.contenu1.put(p1,3);
        this.contenu2.put(p2,3);
        this.contenu3.put(p3,3);

        this.users.add(this.user);
        this.users.add(this.user2);
        this.users.add(this.user3);
        this.users.add(this.user4);
        this.users.add(this.user5);
        this.v1.ajouterSubscriber(user);
        this.user.ajouterCommande(c1);
        this.user.ajouterCommande(c2);
        this.user.ajouterCommande(c3);
        this.items.add(this.p1);
        this.items.add(this.p2);
        this.items.add(this.p3);
        this.items.add(this.p4);
        this.items.add(this.p5);
        this.revendeurs.add(this.v1);
        this.revendeurs.add(this.v2);
        this.revendeurs.add(this.v3);
        this.revendeurs.add(this.v4);
        this.revendeurs.add(this.v5);
    }








    public Boolean pay(Double amount, Coordonnees paiement){
            /* on simule le paiement avec l'institution financière*/
        if (amount>0)
            return true;
        return false;
    }

    public void notify(ArrayList<Revendeur> target, Notification1 alerte){
        for(Revendeur element : target){
            element.notifyMe(alerte);
        }
    }

        public void sendOrders(ArrayList<Revendeur> target, Commande order){
        for(Revendeur element : target){
            element.ajouterCommande(order);
        }
    }


    public Session login(String username,String password) throws Exception{
        
        /* Finds the account if it exits and makes a new Session */
        for(Utilisateur user : this.users){
            if (user.get("username").equals(username)){
                if (user.get("password").equals(password)){
                            Session session = new Session(user);
        return session;
            }
            }
        } 
        /* Exception if no account matches */
        throw new Exception("Bad login");
    }

    public ArrayList<ProduitEnVente> showItems(){

        return this.items;
    }

        public ArrayList<Revendeur> showRevendeurs(){

        return this.revendeurs;
    }
}
