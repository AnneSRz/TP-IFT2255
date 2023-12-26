package com.example.implementation;

import com.example.implementation.classUnishop.ProduitEnVente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductCardController implements Initializable {
    @FXML
    private Button ajoutPanier;

    @FXML
    private ImageView cardImage;

    @FXML
    private AnchorPane card;

    @FXML
    private Label nomProduit;

    @FXML
    private Label prixProduit;

    @FXML
    private Spinner<Integer> quantitePourPanier;
    private ProduitEnVente produitEnVente;



    public void setData(ProduitEnVente produitEnVente) {
        this.produitEnVente = produitEnVente;
        nomProduit.setText(produitEnVente.getTitre());
        prixProduit.setText(produitEnVente.getPrix().toString());
        //Image image = new Image(getClass().getResourceAsStream(produitEnVente.getImagePath()));
        //cardImage.setImage(image);
        try {
            Image image = new Image(getClass().getResourceAsStream(produitEnVente.getImagePath()));
            cardImage.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}




