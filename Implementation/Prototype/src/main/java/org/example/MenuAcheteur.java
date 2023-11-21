package org.example;

import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuAcheteur {

    Systeme systeme;
    Session session;

    /**Constructeur de la Classe MenuAcheteur
     *
     */
    MenuAcheteur(Systeme systeme,Session session){
        this.systeme=systeme;
        this.session=session;
    }

    /**Fait les vérifications et les appels systeme pour faire un achat d'un panier d'items via les coordonnees du profil
     *
     * @param panier
     * @param infos
     */
    public void acheter(Panier panier, Coordonnees infos){
        if (!(this.session.isExpired())) {

            try {
                Acheteur acheteur = this.session.user();


                Double amount = panier.calculerTotal();
                Coordonnees paiement = acheteur.getCoordonnees();
                Map<ProduitEnVente, Integer> items = panier.getItems();
                Commande order = new Commande(acheteur, items, amount, paiement);

                if (this.systeme.pay(amount, paiement) ){

                    Notification1 alerte = new Notification1("Nouvelle commande", acheteur);

                    ArrayList<Revendeur> target = new ArrayList<Revendeur>();

                    for (ProduitEnVente item : items.keySet()) {
                        target.add(item.getRevendeur());
                    }
                    systeme.notify(target, alerte);
                    System.out.println("Success");
                }

            }
            catch(Exception e){
                System.out.println("Invalide");
            }
        }

    }
}
