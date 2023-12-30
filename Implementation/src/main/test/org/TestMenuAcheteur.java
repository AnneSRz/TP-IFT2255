//package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.implementation.classUnishop.Acheteur;
import com.example.implementation.classUnishop.Coordonnees;
import com.example.implementation.classUnishop.MenuAcheteur;
import com.example.implementation.classUnishop.Papeterie;
import com.example.implementation.classUnishop.Revendeur;
import com.example.implementation.classUnishop.Session;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class TestMenuAcheteur {


    private Acheteur acheteur1;
    private Coordonnees coordonnees1;
    private Session session1;
    private ProduitEnVente produit1;
    private Revendeur revendeur1;
    private Papeterie catalogue1;
    private MenuAcheteur menu1;
    private Systeme systeme1;

    @BeforeEach
    void setUp() {
        coordonnees1 = new Coordonnees("Doe", "John", "123 street", "1234567890", "jonh1@gmail.com", "1111222233334444", "555", "0124");
        acheteur1 = new Acheteur("acheteur","mdp",coordonnees1);
        revendeur1 = new Revendeur("seller1", "123mdp", "clown@shop.ca", "1112224444", "21 rue du saut");
        session1 = new Session(acheteur1);
        catalogue1 = new Papeterie("Gucchi","Mark 21", "Stylo");
        produit1 = new Produit(revendeur1, "Ultra cool stylo", "le stylo le plus stylé ever", 4,420,99.99,4.9,"Image/stylo1.jpg","","420",catalogue1);
        systeme1 = new Systeme();
        menu1 = new MenuAcheteur(systeme1,session1);
    }



    @Test
    testAjouterAuPanier(){
        try{
            //ajouter négatif
            menu1.ajouterAuPanier(acheteur1, produit1,-1);
            AssertFalse(acheteur1.getPanier().getItems().get(produit1)==-1,"Erreur panier impossible");
            //ajouter si vide
            menu1.ajouterAuPanier(acheteur1, produit1,0);
            AssertFalse(acheteur1.getPanier().getItems().get(produit1)==0,"Erreur panier impossible");
            //ajouter si null
            menu1.ajouterAuPanier(acheteur1, null,1);
            AssertFalse(acheteur1.getPanier().getItems().get(null)==1,"Erreur panier impossible");
            //cas normal
            menu1.ajouterAuPanier(acheteur1, produit1,1);
            AssertTrue(acheteur1.getPanier().getItems().get(produit1)==1,"Erreur panier impossible");

        }
        catch(Exception e){
            fail("Exception dans Acheter");
        }
    }


    @Test
    testAcheter(){
        try{
            //acheter si vide 
            menu1.acheter(acheteur1);
            assertTrue(acheteur1.getOrders().isEmpty(),"Erreur commande impossible");
            //acheter si article dans panier
            //assume que ajouter au panier fonctionne
            menu1.ajouterAuPanier(acheteur1, produit1,1);
            menu1.acheter(acheteur1);
            assertFalse(acheteur1.getOrders().isEmpty(),"Erreur commande impossible");
            assertTrue(acheteur1.getOrders().get(0).getProduits().get(produit1)==1, "Erreur commande erronée");
        }
        catch(Exception e){
            fail("Exception dans Acheter");
        }
    }
}
