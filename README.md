# TP-IFT2255
Titre: Unishop

Description du projet: Une application pour acheter du matériel informatique, de l'équipement de bureau, des articles de papetrie, des livres et manuels et des ressources d'apprentissage.

Description des données de départ: 
Définitions:
ID des produits: L'ordre dans lequel les produits ont apparu sur Unishop et vont apparaître.
Acheteur: L'user qui regarde les produits disponibles et décide de les acheter ou non.
Revendeur: L'user qui vend des produits sur Unishop et peut lui aussi acheter des produits.
Description des produits: La description détaillé des produits, car seul le titre n'est pas assez pour decrire le produit.
PointParDollar: La promotion du produit. Plus il y a de points par dollar plus c'est profitable pour les acheteurs et plus le revendeur veut vendre son produit. 
Quantité: La quantité de produits que le revendeur peut vendre(les stock ne sont pas illimités).
Prix: Le prix en dollar canadien qui représante le prix pour lequel le revendeur est prêt à vendre le produit.
NoteMoyenne: La note que les users qui ont acheté se produit ont atrribué à un produit.
Categories: Les grandes catégories selon lesquels on a trié les produits. Aide à l'user de chercher se qu'il veut dans l'aplication.
Images: Les images des produits à fin que l'user puisse ssavoir qu'est-ce qu'il achète et pas juste avoir la description sous les yeux.

Profil: Un account de la personne qui possède nom, prénom, psedo, adresse, adresse courriel, téléphone et un userType, si la personne est un revendeur, il a une note donné par ses acheteurs s'il a réussi à vendre au moins un produit.
Pseudo: Le nom que l'user a choisi pour se representer le mieux.
Prénom: Prénom de naissance de la personne(il se peut que se soit pas son vrai prénom).
Nom: Nom de famille de l'user(il se peut que se soit pas son vrai nom).
Courriel: Courriel par lequel on peut joindre l'user et confirmer les états de sa commande.
Mot de passe: Sécurisation du compte de l'user.
Téléphone: Téléphone de l'user par lequel on peut le rejoindre et par lequel il peut recevoir des codes de vérifications.
Adresse: L'adresse de l'user vers laquelle se fera la livraison des produits commandés.
UserType: Si le user est un revendeur ou acheteur.
Note du revendeur: Les acheteurs peuvent noter les revendeur et donc c'est la moyenne des notes données.

Évaluation: Un commentaire de la part de l'user qui a acheté le produit pour dire s'il mérite d'être acheté ou non. 
Suiveur: Un user qui aime les produits que ce user achète et le suit pour voir se qu'il va acheter et peut-être qu'il voudra acheter le même produit, car il para^^it qi'ils ont des interets communs et peuvent devenir amis dans le futur.
Liker: Liker offre la possibilités de recevoir des notifications s'il y a une promotion du produit que le user veut ou que le revendeur liké soit propose des promotions sur un de ses produits soit qu'il propose un nouveau produit. Aussi, permet de recevoir une notification s'il y a une promotion sur un des produits que l'acheteur, ou un de ses suiveurs, a liké. 
Notifications: À part, ce qui a été mentionné, l'user reçoit une notification si un nouvel acheteur suit son profil, si l'état (en production, en livraison, livré) d'une commande, d'un retour ou d'un échange a changé ou si un revendeur propose une solution sur un produit que l'acheteur a signalé un problème.  

Exemples:
Acheteur: pseudo: Amongus, mot de passe: mdp12345
Revendeur: pseudo: OfficeMaster, mot de passe: gadget123 

Comment installer le projet: version Java(jdk-19), version Maven(4.0.0), librairies externes(mostly pour javafx)(
package com.example.implementation;

import com.example.implementation.classUnishop.;
import com.fasterxml.jackson.databind.;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Window;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;)

Comment exécuter le projet: Implementation/src/main/java/com/example/implementation/classUnishop/Main.java
