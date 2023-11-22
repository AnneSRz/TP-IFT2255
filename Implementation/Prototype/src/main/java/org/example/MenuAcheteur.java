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


    public ArrayList<ProduitEnVente> showItems(String filter){
        String text="";
        filter=filter.toLowerCase();
        ArrayList<ProduitEnVente> items2 = new ArrayList<ProduitEnVente>();
        ArrayList<ProduitEnVente> items = systeme.showItems();
        switch(filter) {
            case "":
                items2=items;
                text= this.stringify(items2);
            break;
            case "bureau":
                for(ProduitEnVente p : items){
                    if ((p.getCatalogue() instanceof Bureau)){
                        items2.add(p);
                    }
                }
                text= this.stringify(items2);
            break;
            case "informatique":
                for(ProduitEnVente p : items){
                    if ((p.getCatalogue() instanceof Informatique)){
                        items2.add(p);
                    }
                }
                text= this.stringify(items2);
            break;
            case "livres":
                for(ProduitEnVente p : items){
                    if ((p.getCatalogue() instanceof Livres)){
                        items2.add(p);
                    }
                }
                text= this.stringify(items2);
            break;
            case "papeterie":
                for(ProduitEnVente p : items){
                    if ((p.getCatalogue() instanceof Papeterie)){
                        items2.add(p);
                    }
                }
                text= this.stringify(items2);
            break;
            case "ressource":
                for(ProduitEnVente p : items){
                    if ((p.getCatalogue() instanceof Ressource)){
                        items2.add(p);
                    }
                }
                text= this.stringify(items2);
            break;
            default:
                System.out.println("Bad filter, showing all items instead");
                items2=items;
                text= this.stringify(items2);
        }
        System.out.println(text);
        return items2;
    }

    public String stringify(ArrayList<ProduitEnVente> items){
        String text="\nListe des objets:\n\n";
        Integer i = 0;
        for(ProduitEnVente item :items){

            text+= i.toString()+". "+item.get("titre")+"\n";
            i+=1;
        }
        return text;
    }

        public String stringify2(Map<ProduitEnVente,Integer> items){
        String text="\nListe des objets:\n\n";
        Integer i = 0;
        for(ProduitEnVente item :items.keySet()){

            text+= i.toString()+". "+item.get("titre")+" ("+items.get(item)+")"+"\n";
            i+=1;
        }
        return text;
    }



    public String afficherItem(ProduitEnVente produit){
        String text="Produit "+produit.get("titre")+"\n";
        text+="Vendu par : "+produit.getRevendeur().get("username")+"\n";
        text+="Description : "+produit.get("description")+":\n";
        text+="Promotion de  : "+produit.getPointsParDollar().toString()+" points par dollar.\n";
        text+="Quantité disponible : "+produit.getQty().toString()+"\n";
        text+="Prix :"+produit.getPrix().toString()+" $\n";
        text+="Note moyenne : "+produit.getNote().toString()+"\n";
        return text;
    }


    public void acheter(Acheteur acheteur)throws Exception{

        try{
            Map<ProduitEnVente, Integer> items = acheteur.getPanier().getItems();
            Double amount = acheteur.getPanier().calculerTotal();
            Coordonnees infos = acheteur.getCoordonnees();
            Commande order = new Commande(acheteur, items, amount, infos);

            if ( this.systeme.pay(amount, infos) ){

                Notification1 alerte = new Notification1("Nouvelle commande", acheteur);
                ArrayList<Revendeur> target = new ArrayList<Revendeur>();

                for (ProduitEnVente item : items.keySet()) {
                            target.add(item.getRevendeur());
                        }

                systeme.notify(target, alerte);
                systeme.sendOrders(target,order);
                acheteur.ajouterCommande(order);
                System.out.println("Success");
                acheteur.panier.clear();
            }
            else{
                throw new Exception("Paiment a échoué");
            }
        }
        catch(Exception e){
            System.out.println("Panier vide?");
        }
    }
    
    public void ajouterAuPanier(Acheteur acheteur,ProduitEnVente item, Integer qty) throws Exception{
        if(item.getQty()<qty){
            throw new Exception("Quantité supérieure à l'offre");
        }
        if(qty==0){
            throw new Exception("Vous ne pouvez pas ajouter 0 item");
        }
        acheteur.getPanier().add(item, qty);

    }



        public String stringify3(ArrayList<Commande> commandes){
        String text="\nListe des Commandes:\n\n";
        Integer i = 0;
        for(Commande order :commandes){

            text+= i.toString()+". Commande ID : "+order.getNoCommande().toString()+"\n";
            i+=1;
        }
        return text;
    }

    /*tp2
    public void choisirItems(Acheteur acheteur)throws Exception{
        try{
            //afficher tout les produits

            //Choisir 

        }catch(IOException e){
            System.out.println("Le produit n'a pas été sélectionneé");
            
        }
    }

*/
}
