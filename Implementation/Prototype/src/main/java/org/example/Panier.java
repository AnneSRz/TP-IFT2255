package org.example;
import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Panier {


    Map<ProduitEnVente, Integer> contenu=new HashMap<ProduitEnVente, Integer>();
    Utilisateur user;

    /**  Constructeur de la classe Panier
     *   Le panier est vide, mais il appartient à un utilisateur
     */
    Panier(Utilisateur acheteur){
        this.user = acheteur;
    }

    /**
     * adds or increase the amount of ProduitEnVente in the cart (map)
     *
     * @param produit Produit en vente dans le panier
     * @param amount La quantité d'un l'article dans le panier
     */
    public void add(ProduitEnVente produit,Integer amount){
        if (this.contenu.containsKey(produit)){
            Integer i=this.contenu.get(produit);
            this.contenu.replace(produit,i+amount);
        }
        else{
            this.contenu.put(produit,amount);
        }
    }


    /**
     * Retirer une quantité d'un articledu panier
     *
     * @param produit Produit en vente dans le panier
     * @param amount La quantité d'un l'article dans le panier
     */
    public void remove(ProduitEnVente produit,Integer amount){
        if (this.contenu.containsKey(produit)){
                Integer i=this.contenu.get(produit);
            if(i-amount>0) {
                this.contenu.replace(produit, i - amount);
            }
            else{
                this.contenu.remove(produit);
            }
        }
        else{
            return;
        }
    }

    /**
     * Vider le panier
     *
     */
    public void clear(){
        this.contenu.clear();
    }


    /**
     * Retoune la quantité de points
     *
     * @return Integer -> how much points is worth the content of the cart
     */
    public Integer calculerPoints(){
        Integer total=0;
        for ( ProduitEnVente key : this.contenu.keySet() ) {
            total+= key.getPointsParDollar()*this.contenu.get(key);
        }
        return total;

    }

    /**
     * Returns the amount $ in cart
     *
     * @return Double -> how much is the content of the cart
     */
    public Double calculerTotal(){
        Double total=0.0;
        for ( ProduitEnVente key : this.contenu.keySet() ) {
            total+= key.getPrix()*this.contenu.get(key);
        }
        return total;

    }

    public Map<ProduitEnVente, Integer> getItems(){
        return contenu;
    }

}
