//package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.implementation.classUnishop.Acheteur;
import com.example.implementation.classUnishop.Coordonnees;
import com.example.implementation.classUnishop.MenuAcheteur;
import com.example.implementation.classUnishop.Panier;
import com.example.implementation.classUnishop.Papeterie;
import com.example.implementation.classUnishop.ProduitEnVente;
import com.example.implementation.classUnishop.Revendeur;
import com.example.implementation.classUnishop.Session;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class TestPanier {

    private Acheteur acheteur1;
    private Coordonnees coordonnees1;
    private Session session1;
    private ProduitEnVente produit1;
    private Revendeur revendeur1;
    private Papeterie catalogue1;
    private Panier panier1;

    @BeforeEach
    void setUp() {
        coordonnees1 = new Coordonnees("Doe", "John", "123 street", "1234567890", "jonh1@gmail.com", "1111222233334444", "555", "0124");
        acheteur1 = new Acheteur("acheteur","mdp",coordonnees1);
        revendeur1 = new Revendeur("seller1", "123mdp", "clown@shop.ca", "1112224444", "21 rue du saut");
        session1 = new Session(acheteur1);
        catalogue1 = new Papeterie("Gucchi","Mark 21", "Stylo");
        produit1 = new Produit(revendeur1, "Ultra cool stylo", "le stylo le plus stylé ever", 4,420,99.99,4.9,"Image/stylo1.jpg","","420",catalogue1);
        panier1 = acheteur1.getPanier();
    }



    @Test
    testCalculerPoints(){
        //cas si panier vide (case limite)
        AssertTrue(panier1.calculerPoints()==0,"erreur points panier");
        //cas normal
        panier1.add(produit1, 1);
        AssertTrue(panier1.calculerPoints()==produit1.getPointsParDollar(),"erreur points panier");
        //test sanitaire par répétition
        panier1.add(produit1, 1);
        AssertTrue(panier1.calculerPoints()==produit1.getPointsParDollar()*2,"erreur points panier");
        //test sanitaire par composition, assume que clear fonctionne
        panier1.clear();
        AssertTrue(panier1.calculerPoints()==0,"erreur points panier");

    }

    
}
