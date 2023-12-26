package com.example.implementation.classUnishop;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) {

        Systeme systeme = new Systeme();
        Session session = new Session(null);
    

        /* Login Handling */
        System.out.println("Interface du site web UniShop version console");
        String str;
        Pattern pat = Pattern.compile("([a-zA-Z0-9_]+)\\ ([a-zA-Z0-9]+)");
        Matcher mat;
        Boolean exit = true;

        while(exit){
            /* Login regex */
            System.out.println("Tapez votre utilisateur mot de passe (Simulé, assume un acheteur):");
            str = System.console().readLine("Format:  Utilisateur  MotDePasse");
            mat = pat.matcher(str);
            if(mat.matches()){
            try{
                System.out.println("Good format");
                System.out.println(mat.group(1));
                System.out.println(mat.group(2));
                session = systeme.login(mat.group(1),mat.group(2));
                exit=false;
                System.out.println(" \n\n Login success\n\n");
            }
            catch(Exception e){
                System.out.println("No user exists with that account");
            }}

        }
/* Menu Acheteur */
                MenuAcheteur menu = new MenuAcheteur(systeme,session);

                while(!(session.isExpired())){

                    System.out.println(" \n\n Liste des fonctions implémentées pour le menu acheteur:\n\n");
                    System.out.println("1. Acheter le contenu du panier à partir des infos du compte:\n");
                    System.out.println("2. Afficher items du panier:\n");
                    System.out.println("3. Naviguer\n");
                    System.out.println("4. Voir mes commandes\n");
                    str = System.console().readLine("Entrez le numéro de la fx à exécuter");

                    Utilisateur user = session.user();
                    Acheteur acheteur = new Acheteur("", "", null);
                    if (user instanceof Acheteur){
                        acheteur = (Acheteur) user;
                    }

                    try{

                        switch(str) {
                            case "1":
                                try{
                                menu.acheter(acheteur);
                                System.out.println("Paiement réalisé avec les informations du compte");
                                }
                                catch (Exception e) {
                                    System.out.println("Paiement échoué");
                                }
                            break;
                            case "2":
                            System.out.println("2. Afficher items du panier:\n");
                                System.out.println(menu.stringify2(acheteur.getPanier().getItems()));
                                str = System.console().readLine("Entrez le numéro de la fx à exécuter");
                            break;
                            case "3":

                                System.out.println("1. Items\n");
                                System.out.println("2. Revendeurs\n");
                                str = System.console().readLine("Entrez le numéro de la fx à exécuter");


                                switch(str) {
                                    case "1":
                                        /* Show items */
                                        String filtre="";
                                        System.out.println("Sélectionnez un filtre: \n");
                                        System.out.println("1. Aucun (Tout afficher)\n");
                                        System.out.println("2. bureau\n");
                                        System.out.println("3. informatique\n");
                                        System.out.println("4. livres\n");
                                        System.out.println("5. papeterie\n");
                                        System.out.println("6. ressource\n");
                                        str = System.console().readLine("Entrez le numéro de la fx à exécuter");
                                        switch(str) {
                                        case "1":
                                            filtre="";
                                        break;
                                        case "2":
                                            filtre="bureau";
                                        break;
                                        case "3":
                                            filtre="informatique";
                                        break;
                                        case "4":
                                            filtre="livres";
                                        break;
                                        case "5":
                                            filtre="papeterie";
                                        break;
                                        case "6":
                                            filtre="ressource";
                                        break;
                                        default:
                                            filtre="";}


                                        System.out.println("Voici la liste des items:");
                                        ArrayList<ProduitEnVente> items = menu.showItems(filtre);
                                        str = System.console().readLine("Entrez le numéro de l'item a visualiser");
                                        Pattern pat1 = Pattern.compile("([0-9])");
                                        Matcher mat1=pat1.matcher(str);
                                        if(mat1.matches()){
                                            try{
                                                ProduitEnVente item = items.get(Integer.parseInt(str));
                                                System.out.println(menu.afficherItem(item));
                                                str = System.console().readLine("Ajouter la quantité à ajouter au panier:");
                                                Pattern pat2 = Pattern.compile("([0-9]{1,2})");
                                                Matcher mat2=pat2.matcher(str);
                                                if(mat2.matches()){
                                                    menu.ajouterAuPanier(acheteur, item,Integer.parseInt(str));
                                                    System.out.println("Item Ajouté");
                                                    str = System.console().readLine("Retourner au menu");
                                                }
                                                else{break;}
                                            }
                                            catch(Exception e){
                                                System.out.println("Choix n'est pas dans la liste");
                                            }
                                        }
                                        else{
                                            break;
                                        }
                                    break;
                                    case "2":
                                        System.out.println("Liste des revendeurs:");
                                    // code block


                                    break;
                                    default:
                                        System.out.println("Going back to the menu.");
                                    }





                            break;
                            case "4":
                                    System.out.println("Liste des commandes:\n");
                                    System.out.println(menu.stringify3(acheteur.getOrders()));
                                    str = System.console().readLine("Retourner au menu");
                                    break;                       
                            default:
                                System.out.println(" \n\n Option invalide \n\n");
                        }
    
                    }
                    catch(Exception e){
                        System.out.println("Menu acheteur unavailable");
                    }


    }
}
}


        
    
