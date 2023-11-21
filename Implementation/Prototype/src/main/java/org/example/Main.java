package org.example;
import java.io.*;
import java.util.regex.*;
public class Main {
    public static void main(String[] args) {
        
        System.out.println("Interface du site web UniShop version console");
        System.out.println("Tapez votre utilisateur mot de passe (Simulé, assume un acheteur):");
        String str = System.console().readLine("Format:  Utilisateur  MotDePasse");

        Pattern pat = Pattern.compile("([a-zA-Z0-9_]+)\\ ([a-zA-Z0-9]+)");
        Matcher mat = pat.matcher(str);

        if(!(mat.matches())){
            System.out.println("Tapez votre utilisateur mot de passe (Simulé, assume un acheteur):");
            str = System.console().readLine("Format:  Utilisateur  MotDePasse");
            mat = pat.matcher(str);
        }
        else{

            Systeme systeme = new Systeme();
            Session session = systeme.login(mat.group(0),mat.group(1));
            /**/
            System.out.println(" \n\n Login success\n\n");

            MenuAcheteur menu = new MenuAcheteur(systeme,session);

            System.out.println(" \n\n Liste des fonctions implémentées pour le menu acheteur:\n\n");
            System.out.println("1. Acheter le contenu du panier à partir des infos du compte:\n");
            str = System.console().readLine("Entrez le numéro de la fx à exécuter");
            if(str!="1"){
                str = System.console().readLine("Entrez le numéro de la fx à exécuter");
            }
            else{
                Revendeur vendeur = new Revendeur("mark","123","bbw@gmail.com","454022001372","rue 1");
                Bureau cat = new Bureau("bmw","mark1","sad");
                ProduitEnVente item= new ProduitEnVente(vendeur,"corde","pour attacher les choses",1,1,10.0,3,"","","aaa1",cat);
                session.user().getPanier().add(item,1);

                /* we have 15 mins left we are desperate  */
                menu.acheter(session.user().getPanier(),session.user().getCoordonnees() );

                System.out.println("Bonjour, j'espère que vous avez aimé notre logiciel, deux de nos cooéquipiés ont contribué très peu au devoir et cela donne ce résultat :)" );
            }


        }
    }
}