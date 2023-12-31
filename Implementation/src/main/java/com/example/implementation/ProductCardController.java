package com.example.implementation;

import com.example.implementation.classUnishop.ProduitEnVente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
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
    private ProduitEnVente produitEnVente;
    @FXML
    private Spinner<Integer> panierSpinner;
    private SpinnerValueFactory<Integer> spinQuantity;
    private Integer quantite;
    private String prodId;
    @FXML
    private Button like;

    private UniShopController uniShopController;

    
    /** Le controlleur gère les events sur l'application
     * @param uniShopController
     */
    public void setController(UniShopController uniShopController) {
        this.uniShopController = uniShopController;
    }

    
    /** On crée un pane à partir des infos de l'objet ProduitEnVente
     * @param produitEnVente
     * @throws Exception
     */
    public void setData(ProduitEnVente produitEnVente) throws Exception {
        if(produitEnVente==null){
            throw new Exception("null in setData");
        }

        this.produitEnVente = produitEnVente;

        this.prodId = produitEnVente.getId();

        this.nomProduit.setText(produitEnVente.getTitre());
        this.prixProduit.setText(produitEnVente.getPrix().toString());
        try {

            
            Image image = new Image(getClass().getResourceAsStream(produitEnVente.getImagePath()), 230,102,false,true);
            this.cardImage.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

    public void setQuantity(){
        this.spinQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        this.panierSpinner.setValueFactory(this.spinQuantity);
    }

    /**
     * methode ajoutant la quantité indiquée dans le champ
     * au panier du unishopcontroller
     */
    public void ajoutPanier() {

        this.quantite = this.panierSpinner.getValue();
        if (this.quantite > 0) {
            //Ajouter l'item au panier
            uniShopController.ajouterAuPanier(produitEnVente, this.quantite );

            //Mettre ;a jour le total du panier
            uniShopController.updateTotal();

            showAlert(Alert.AlertType.INFORMATION, cardImage.getScene().getWindow(), "Produit ajouté au panier avec Succès");
        } else {
            // Optionally, provide feedback to the user that the quantity should be greater than 0
            showAlert(Alert.AlertType.WARNING, cardImage.getScene().getWindow(), "Veuillez sélectionner une quantité valide");
        }
    }

    
    /**
     * Message d'alerte custom pour les erreurs concernant le produit en vente
     * affiche un pane sur le window 
     * @param error
     * @param window
     * @param Message
     */
    private void showAlert(Alert.AlertType error, Window window, String Message) {
        Alert messageAlert = new Alert(error);
        String messageErreur = "Message";
        messageAlert.setTitle(messageErreur);
        messageAlert.setContentText(Message);
        messageAlert.initOwner(window);
        messageAlert.show();
    }


        
        /** something something comment javadoc yes
         * @param url
         * @param resourceBundle
         */
        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setQuantity();
            like.setOnAction(event -> {
                // Ajouter le produit à la liste des souhaits
                uniShopController.addWishList(produitEnVente);
            });
    }
}




