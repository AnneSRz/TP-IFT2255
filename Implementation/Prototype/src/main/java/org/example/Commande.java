package org.example;

import jdk.jshell.execution.Util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.spi.LocaleServiceProvider;
import java.time.temporal.ChronoUnit;

public class Commande {
        static Integer nbCommandes=0;
        Integer numeroCommande;
        String etat="En production";
        LocalDate dateCommande;
        Map<ProduitEnVente, Integer> produits;
        LocalDate dateEstimeeArrivee;
        LocalDate dateArrivee;
        Double prixTotal;
        Coordonnees paiementInfos;
        public Billet billet;
        Boolean itemRecu = false;
        Utilisateur acheteur;


    /**
     * Constructeur de la classe Commande.
     *
     * @param acheteur Celui qui passe la commande
     * @param items La liste des articles commandés.
     * @param prixTotal Le coût de la commande/ce que l'acheteur a payé
     * @param paiementInfos Les informations de paiement de l'acheteur associées à la commande qu'il a payé
     */
    public Commande(Utilisateur acheteur,Map<ProduitEnVente, Integer> items,Double prixTotal, Coordonnees paiementInfos){
        this.nbCommandes+=1;
        this.numeroCommande = this.nbCommandes;
        this.dateCommande = LocalDate.now();
        this.prixTotal = prixTotal;
        this.paiementInfos = paiementInfos;
        this.produits = items;
        this.acheteur=acheteur;
    }

    /**
     * Cette fonction change l'état actuel de la commande en fonction de divers processus.
     * Par exemple, quand une commande arrive au main de l'acheteur,il doit confirmer sa commande en se connectant sur
     * la plateforme, une fois la réception confirmée, l'état de sa commande devient « livré ».
     *
      * @param nomEtat Le processus dans lequel se trouve la commande : " En production", "En livraison", "livré".
     */
    public void changerEtat(String nomEtat){
        if (nomEtat.equalsIgnoreCase("En production")){
            // Le vendeur a commencé la livraison
            this.etat = nomEtat;
        }
        else if (nomEtat.equalsIgnoreCase("En livraison")){
            // Le vendeur a envoyé la commande
            this.etat = nomEtat;
        }
        else if (nomEtat.equalsIgnoreCase("livré")){
            // L'acheteur confirme la livraison
            this.etat = nomEtat;
        }
        else {
            // Dans le cas de d'autres scenarios.
            this.etat = nomEtat;
        }
    }


    public Integer getNoCommande(){return this.numeroCommande;}
    /**
     * Lorsque les articles arrivent aux portes du client, il peut confirmer la reception de sa commande.
     * La réception peut être confirmée que si la commande est en cours de livraison, dans le cas contraire, ce n'est
     * pas possible.
     */
    public void confirmerReception() {
        if (this.etat.equals("livré")) {
            this.itemRecu = true;
            this.dateArrivee = LocalDate.now();
        } else {
            this.itemRecu = false;
        }
    }
    /**
     * Implementation de l'expiration de l'échange
     * Vérifie si le délai pour demander un échange est expiré.
     *
     * @return True or False dépendamment si la date de reception excède 30 jours ou non.
     */
    public boolean echangeExpire() {
        LocalDate dateDuJour = LocalDate.now();
        long differenceDeJour = ChronoUnit.DAYS.between(dateDuJour, dateArrivee);
        if (differenceDeJour < 30) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Implementation de l'expiration du signalement
     * Vérifie si le délai pour signaler un problème est expiré.
     *
     * @return True or False dependamment si la date de reception excède 365 jours ou non.
     */
    public boolean signalementExpire() {
        LocalDate dateDuJour = LocalDate.now();
        long differenceDeJour = ChronoUnit.DAYS.between(dateDuJour, dateArrivee);
        if (differenceDeJour < 365) {
            return false;
        } else {
            return true;
        }
    }

    Map<String, String> attribut;
    /**
     * Récupère la valeur associée à un attribut spécifié dans le billet.
     *
     * @param nomAttribut Le nom de l'attribut à recuperer
     * @return Retourne le Billet associé à cet attribut
     */
    public String get(String nomAttribut){
        return this.attribut.get(nomAttribut);
    }

    /**
     * Utilise signalement pour mettre à jour le billet(attribut).
     *
     * @param signalement Remplacer la valeur associée à une clé spécifique représentant un "billet" a définir.
     */
    public void setBillet(Billet signalement) {
        this.billet = signalement;
    }

}

