package com.example.implementation.classUnishop;

import java.util.HashMap;
import java.util.Map;

public class Panier {
    private final ProduitEnVente product;
    private int quantity;

    Map<ProduitEnVente, Integer> contenu=new HashMap<ProduitEnVente, Integer>();
    Acheteur user;

    /**  Constructeur de la classe Panier
     *   Le panier est vide, mais il appartient à un utilisateur
     */

    public Panier(ProduitEnVente product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    //public Panier(Acheteur acheteur){this.user = acheteur;}

    public ProduitEnVente getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNomPanier() {
        return product.getTitre();
    }

    public int getQtPanier() {
        return quantity;
    }

    public double getPrixPanier() {
        return product.getPrix() * quantity;
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
     * Retirer une quantité d'un article du panier
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

    /**
     * Getter
     *
     * @return Retourne l'attribut de l'acheteur
     */
    public Acheteur getAcheteur(){
        return this.user;
    }

}
