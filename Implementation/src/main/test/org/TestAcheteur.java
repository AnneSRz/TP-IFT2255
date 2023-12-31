//package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.implementation.classUnishop.Acheteur;
import com.example.implementation.classUnishop.Coordonnees;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TestAcheteur {

    private Acheteur acheteur1;
    private Coordonnees coordonnees1;
    private Acheteur acheteur2;
    private Coordonnees coordonnees2;

    @BeforeEach
    void setUp() {
        coordonnees1 = new Coordonnees("Doe", "John", "123 street", "1234567890", "jonh1@gmail.com", "1111222233334444", "555", "0124");
        acheteur1 = new Acheteur("acheteur","mdp",coordonnees1);
        coordonnees2 = new Coordonnees("Jordan", "Micheal", "420 street", "3334447890", "MJtheJordan@gmail.com", "1141232233334444", "421", "0425");
        acheteur2 = new Acheteur("MJordan","passwordsafe3",coordonnees2);
    }


    //Tests pour la classe parente Utilisateur

 @Test
    void testIsVerifyExpired() {
        //classe créée et vérifiée immédiatement
        assertFalse(acheteur1.isVerifyExpired(),"Expiration de la vérification erronée");
    }
 @Test
    void testVerify() {
        //classe créée et vérifiée immédiatement
        if(!acheteur1.isVerifyExpired()){
            //la vérification devrait être possible:
            assertTrue(acheteur1.verify(),"Vérification erronnée");
        }
        else{fail("Expiration de la vérification erronée (in Verify)");}
    }

    //Tests spécifiques à Acheteur

    @Test
    void testAjouterAmi() {
        ArrayList<Acheteur> amis1=new ArrayList<Acheteur>();
        //ajouter si ajout d'Acheteur valide (cas valide)
        acheteur1.ajouterAmi(acheteur2);
        amis1.add(acheteur2);
        assertTrue(amis1.equals(acheteur1.getAmis()), "Ajout d'un ami a échoué");
        //ajout si ami déjà présent ne devrait pas être problématique:
        amis1.add(acheteur2);
        assertTrue(amis1.equals(acheteur1.getAmis()), "Ajout d'un ami a échoué");
        //ajouter si ajout d'un null (cas inattendu)
        acheteur1.ajouterAmi(null);
        assertTrue(amis1.equals(acheteur1.getAmis()), "Ajout d'un ami a échoué");
    }


    @Test
    void testRetirerAmi() {
        ArrayList<Acheteur> amis1=new ArrayList<Acheteur>();
        //assume que AjouterAmi fonctionne
        acheteur1.ajouterAmi(acheteur2);
        acheteur1.retirerAmi(acheteur2);
        //retirer l'ami qu'on vient d'ajouter
        assertTrue(amis1.equals(acheteur1.getAmis()), "Retrait d'un ami a échoué");
        //retirer ami lorsque non présent ne devrait pas être problématique
        acheteur1.retirerAmi(acheteur2);
        assertTrue(amis1.equals(acheteur1.getAmis()), "Retrait d'un ami a échoué");
        //retirer null (cas limite)
        acheteur1.retirerAmi(null);
        assertTrue(amis1.equals(acheteur1.getAmis()), "Retrait d'un ami a échoué");

    }

}
