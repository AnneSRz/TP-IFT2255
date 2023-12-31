//package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.implementation.ProductCardController;
import com.example.implementation.classUnishop.Acheteur;
import com.example.implementation.classUnishop.Coordonnees;
import com.example.implementation.classUnishop.MenuAcheteur;
import com.example.implementation.classUnishop.Papeterie;
import com.example.implementation.classUnishop.Revendeur;
import com.example.implementation.classUnishop.Session;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TestProductCardController {
    

    private ProductCardController controller1;
    private ProduitEnVente produit1;
    private Papeterie catalogue1;


    @BeforeEach
    void setUp() {
        controller1 = new ProductCardController();
        catalogue1 = new Papeterie("Gucchi","Mark 21", "Stylo");
        produit1 = new Produit(revendeur1, "Ultra cool stylo", "le stylo le plus stylé ever", 4,420,99.99,4.9,"Image/stylo1.jpg","","420",catalogue1);
    }

    @Test
    testSetData(){
        //cas normal
        try{
            controller1.setData(produit1);
        }
        catch(Exception e){
            fail("Exception dans SetData");
        }
        //cas limite null
                try{
            controller1.setData(null);
            //if success = fail
            fail("Exception dans SetData (Null)");
        }
        catch(Exception e){
            //expected behavior
        }
    }

}
