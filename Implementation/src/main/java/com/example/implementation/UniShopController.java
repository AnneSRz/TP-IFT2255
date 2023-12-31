package com.example.implementation;

import com.example.implementation.classUnishop.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;





public class UniShopController implements Initializable {
    @FXML
    private AnchorPane pane;

    //De la page de connection
    @FXML
    private AnchorPane acceuilConnection;
    @FXML
    private TextField connectionUtilisateur;
    @FXML
    private PasswordField connectionMotdepasse;
    @FXML
    private Button connection;
    @FXML
    private Label mdpInvalide;
    private String pseudo;
    private String motDePasse;

    // De la page d'inscription
    @FXML
    private AnchorPane acceuilInscription;
    @FXML
    private TextField inscriptionPseudo, inscriptionPrenom, inscriptionNom, InscriptionEmail;
    @FXML
    private PasswordField inscriptionMotdepasse;
    @FXML
    private ChoiceBox choixMenuInscription;
    @FXML
    private Button inscription, procederInscription;

    @FXML
    private Label inscriptionEmailError, inscriptionMdpError, userError, emailError, nameError;

    // De la page du menuAcheteur
    @FXML
    private AnchorPane menuAcheteur, navAcheteur,dashboardPane, profilPane,cataloguePane, mainProfilPane, wishlistPane, orderPane, messagePane;
    @FXML
    private Label acheteurName, userEdit;
    @FXML
    private Button dashboard, profil, wishlist,acheteurCatalogue,commande, acheteurMsgs;
    @FXML
    private TextField prenomEdit, nomEdit, emailEdit, telEdit, adresseEdit;
    @FXML
    private PasswordField mdpEdit, paiementEdit, ccEdit, expEdit;
    @FXML
    private GridPane listeProduitsGrid;
    @FXML
    ScrollPane listeProduitsScroll;
    private ObservableList<ProduitEnVente> carteProduits = FXCollections.observableArrayList();
    @FXML
    private TableView<Panier> panier;
    @FXML
    private TableColumn<Panier, String> nomPanier;
    @FXML
    private TableColumn<Panier, Integer> qtPanier;
    @FXML
    private TableColumn<Panier, Double> prixPanier;
    @FXML
    private Label totalPanier;
    private ObservableList<Panier> produitsPanier = FXCollections.observableArrayList();
    @FXML
    private TableView<ProduitEnVente> listeDeSouhait;
    @FXML
    private TableColumn<ProduitEnVente, String> wishName;
    @FXML
    private TableColumn<ProduitEnVente, Integer> wishDescription;
    @FXML
    private TableView<String> suiveursTab;
    @FXML
    private TableColumn<String, String> typeCol;
    @FXML
    private TableColumn<String, String> pseudoCol;
    @FXML
    private TableColumn<String, String> prenomCol;
    @FXML
    private Button convo1, convo2, convo3;
    @FXML
    private ScrollPane convoPane;
    @FXML
    private AnchorPane convo1Pane, convo2Pane, convo3Pane;

    // De la page du Menu revendeur
    @FXML
    private AnchorPane menuRevendeur,revendeurProfil,navRevendeur;

    //Fonctionnalité
    private Acheteur acheteur = null;
    private Revendeur revendeur = null;
    private Coordonnees coordonnees;
    private Catalogue catalogue;
    private List<Revendeur> revendeurInscrits = new ArrayList<>();
    private List<Acheteur> acheteurInscrits = new ArrayList<>();

    //private ObservableList<ProduitEnVente> carteProduit = FXCollections.observableArrayList();
    //private List<ProduitEnVente> produits = new ArrayList<>();



    ///////////////////////////////////////////FOR EVERYONE///////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHomePage();
        styleSetter();

        ObservableList<String> userTypes = FXCollections.observableArrayList("Acheteur", "Revendeur");
        choixMenuInscription.setItems(userTypes);

        showProductLists();


        // Initialise la TableView et ses colonnes
        this.panier.setItems(FXCollections.observableArrayList());
        this.nomPanier.setCellValueFactory(new PropertyValueFactory<>("nomPanier"));
        this.qtPanier.setCellValueFactory(new PropertyValueFactory<>("qtPanier"));
        this.prixPanier.setCellValueFactory(new PropertyValueFactory<>("prixPanier"));

        this.listeDeSouhait.setItems(FXCollections.observableArrayList());
        this.wishName.setCellValueFactory(new PropertyValueFactory<>("wishName"));
        this.wishDescription.setCellValueFactory(new PropertyValueFactory<>("wishDescription"));

//OwO hewwo sauce!!! :DDD

        this.suiveursTab.setItems(FXCollections.observableArrayList());
        this.typeCol.setCellValueFactory(new PropertyValueFactory<>("typeCol"));
        this.pseudoCol.setCellValueFactory(new PropertyValueFactory<>("pseudoCol"));
        this.prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenomCol"));



    }

    @FXML
    /**
     * Charge la page de connection au démarrage de l'application
     */
    public void setHomePage() {
        pane.getChildren().add(acceuilConnection);
    }

    @FXML
    public void styleSetter() {
        // Effect handler pour les boutons de l'application.
        connection.setOnMouseEntered(e -> connection.setStyle("-fx-background-color: #7752FE;"));
        connection.setOnMouseExited(e -> connection.setStyle("-fx-background-color:   #190482;"));

        procederInscription.setOnMouseEntered(e -> procederInscription.setStyle("-fx-background-color: white;"));
        procederInscription.setOnMouseExited(e -> procederInscription.setStyle("-fx-background-color:  # bbd0ff;"));

        inscription.setOnMouseEntered(e -> inscription.setStyle("-fx-background-color: #7752FE;"));
        inscription.setOnMouseExited(e -> inscription.setStyle("-fx-background-color:   #190482;"));

        dashboard.setOnMouseEntered(e -> dashboard.setStyle("-fx-background-color: #190482;-fx-text-fill: white"));
        dashboard.setOnMouseExited(e -> dashboard.setStyle("-fx-background-color:   transparent;"));

        profil.setOnMouseEntered(e -> profil.setStyle("-fx-background-color: #190482;-fx-text-fill: white"));
        profil.setOnMouseExited(e -> profil.setStyle("-fx-background-color:   transparent;"));

        commande.setOnMouseEntered(e -> commande.setStyle("-fx-background-color: #190482;-fx-text-fill: white"));
        commande.setOnMouseExited(e -> commande.setStyle("-fx-background-color:   transparent;"));

        wishlist.setOnMouseEntered(e -> wishlist.setStyle("-fx-background-color: #190482;-fx-text-fill: white"));
        wishlist.setOnMouseExited(e -> wishlist.setStyle("-fx-background-color:   transparent;"));

        acheteurCatalogue.setOnMouseEntered(e -> acheteurCatalogue.setStyle("-fx-background-color: #190482;-fx-text-fill: white"));
        acheteurCatalogue.setOnMouseExited(e -> acheteurCatalogue.setStyle("-fx-background-color:   transparent;"));

        acheteurMsgs.setOnMouseEntered(e -> acheteurMsgs.setStyle("-fx-background-color: #190482;-fx-text-fill: white"));
        acheteurMsgs.setOnMouseExited(e -> acheteurMsgs.setStyle("-fx-background-color:   transparent;"));
    }

    /**
     * @param event  bouton ayant été triggered
     * fx publique servant à afficher le menu de connexion
     */
    @FXML
    public void handleSignIn(ActionEvent event) {
        pane.getChildren().add(acceuilInscription);
    }


    /**
     * @param event   même event (bouton)
     * @throws IOException   si erreur dans le format d'entrée des champs d'authentification
     *
     * prend les champs en entrée et les envoie à authentificationUser
     */
    @FXML
    public void handleLogin(ActionEvent event) throws Exception {

        // Step 1 : Obtenir les données associéas à la connection de l'utilisateur que ce dernier à entré
        this.pseudo = connectionUtilisateur.getText();
        this.motDePasse = connectionMotdepasse.getText();

        //teeeests
        //System.out.println("Informations entrés pour la connection : " + pseudo + " " + motDePasse);

        // Step 2 : Vérification des champs
        if (connectionUtilisateur.getText().isEmpty() || connectionMotdepasse.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(),
                    " S'il vous plaît, assurez-vous que tous les champs sont remplis");
            return;
        } else {
            authentificationUser(this.pseudo, this.motDePasse);
        }
    }


    /**
     *  resetUnishop();
     *         showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(),
     *                 "Auncun compte trouvé");
     */


    /**
     * Authenticate the buyer based on the entered username and password.
     *
     * @param username The entered username.
     * @param password The entered password.
     * @return True if authentication is successful, false otherwise.
     */

     @FXML
     private boolean authentificationUser(String username, String password) throws Exception {
         JSONParser parser = new JSONParser();

         try {
             // Charger le fichier JSON
             Object obj = parser.parse(new FileReader("Implementation/src/main/resources/com/example/implementation/data/listeUtilisateurs.json"));

             // Convertir l'objet en tableau JSON
             JSONArray utilisateurs = (JSONArray) obj;

             // Parcourir la liste des utilisateurs
             for (Object utilisateurObj : utilisateurs) {
                 JSONObject utilisateur = (JSONObject) utilisateurObj;

                 // Récupérer les informations d'identification de l'utilisateur actuel
                 String storedUsername = (String) utilisateur.get("pseudo");
                 String storedPassword = (String) utilisateur.get("mdp");
                 String userType = (String) utilisateur.get("userType");

                 System.out.println(storedUsername);
                 System.out.println(storedPassword);
                 System.out.println(userType);

                 if (username.equalsIgnoreCase(storedUsername) && password.equalsIgnoreCase(storedPassword)) {
                     if ("Acheteur".equals(userType)) {
                         // Stockez les informations de connexion dans la session
                         this.coordonnees = Coordonnees.donneesJSON((JSONObject) utilisateurObj);
                         this.acheteur = new Acheteur(username, password, this.coordonnees);
                         Session.getInstance().setUtilisateurConnecte(this.acheteur);

                         this.pane.getChildren().remove(acceuilConnection);
                         this.pane.getChildren().add(menuAcheteur);
                         this.navAcheteur.getChildren().add(dashboardPane);
                         this.acheteurName.setText(username);

                         this.acheteur.chargerListeSouhaits(utilisateur);

                         List<String> test = new ArrayList<String>();
                         try{
                            test=(List<String>) utilisateur.get("suiveurs");
                            this.acheteur.setSuiveurs(test);
                         }
                         catch(Exception e){
                             //ignore otherwise
                         }

                         return true;

                     } else if ("Revendeur".equals(userType)) {
                         String courriel = (String) utilisateur.get("pseudo");
                         String telephone = (String) utilisateur.get("telephone");
                         String adresse = (String) utilisateur.get("adresse");
                         this.pane.getChildren().remove(acceuilConnection);
                         this.pane.getChildren().add(menuRevendeur);
                         this.navRevendeur.getChildren().add(revendeurProfil);

                         //Stockez les informations de connexion dans la session
                         this.revendeur = new Revendeur(username, password, courriel, telephone, adresse);
                         Session.getInstance().setUtilisateurConnecte(revendeur);

                         return true;
                     }
                 } else if (password.equals(storedPassword)) {
                     this.mdpInvalide.setText("Mot de passe invalide");
                     this.mdpInvalide.setStyle("-fx-text-fill: red");
                     this.mdpInvalide.setStyle("-fx-border-color: transparent");

                     showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(),
                             "Erreur d'authentification. Vérifiez vos informations de connexion.");
                     return false;

                 } else {
                     // Aucun utilisateur correspondant trouvé
                     showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(),
                             "Erreur d'authentification. Vérifiez vos informations de connexion.");
                     return false;
                 }
             }
         } catch (IOException e) {
             showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(),
                     "Erreur de lecture du fichier JSON. Vérifiez le chemin du fichier.");
             e.printStackTrace();
             return false;
         } catch (ParseException e) {
             throw new RuntimeException(e);
         }
         return true;
     }

    /**
     * @param event
     *
     * Vérifie si les champs sont valides, sinon on affiche alerte
     * Sinon on appelle inscriptionProcess avec les champs
     */
    @FXML
    public void handleInscription(ActionEvent event) {
        // Step 1 : Obtenir les données associéas à la connection de l'utilisateur que ce dernier à entré
        String pseudo = inscriptionPseudo.getText();
        String prenom = inscriptionPrenom.getText();
        String nom = inscriptionNom.getText();
        String email = InscriptionEmail.getText();
        String motDePasse = inscriptionMotdepasse.getText();

        // Sélection du ChoiceBox
        String userType = (String) choixMenuInscription.getValue();

        //teeeests
        System.out.println(userType);
        System.out.println("Informations pour l'inscription entrés : " + pseudo + prenom + nom + email + motDePasse);

        // Step 2 : Vérification des champs
        if (pseudo.isEmpty() || prenom.isEmpty() || nom.isEmpty() || email.isEmpty() || motDePasse.isEmpty() || userType.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(),
                    "S'il vous plaît, assurez-vous que tous les champs sont remplis");
            return;
        }
        // Step 3 : Le amail et le  doivent être valide
        if (!emailValidation(email) && mdpValidation(motDePasse)) {
            this.inscriptionEmailError.setText("Email invalide");
            this.inscriptionEmailError.setStyle("-fx-text-fill: red; -fx-border-color: transparent");

            this.inscriptionMdpError.setText("Mot de passe valide");
            this.inscriptionMdpError.setStyle("-fx-text-fill: transparent; -fx-border-color: transparent");

            showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(),
                    "L'email est invalide");
            return;
        }
        if (!mdpValidation(motDePasse) && emailValidation(email)) {
            this.inscriptionMdpError.setText("mot de passe invalide");
            this.inscriptionMdpError.setStyle("-fx-text-fill: red; -fx-border-color: transparent");

            this.inscriptionEmailError.setText("Email valide");
            this.inscriptionEmailError.setStyle("-fx-text-fill: transparent; -fx-border-color: transparent");

            showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(),
                    "Le mot de passe est invalide, Entrez au moins 8 caractères");
            return;
        }
        if (!emailValidation(email) && !mdpValidation(motDePasse)) {
            this.inscriptionEmailError.setText("Email invalide");
            this.inscriptionEmailError.setStyle("-fx-text-fill: red; -fx-border-color: transparent");

            this.inscriptionMdpError.setText("mot de passe invalide");
            this.inscriptionMdpError.setStyle("-fx-text-fill: red; -fx-border-color: transparent");

            showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(),
                    "Veuillez vérifier vos informations");
        } else {
            try{
            inscriptionProcess(pseudo, prenom, nom, email, motDePasse, userType);}
            catch(Exception e){
                    showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(),
                    "Erreur inattendue dans Inscription Process");
                    return;
            }
        }
    }


    /**
     *
     * paramètres issus des champs récoltés par handleInscription
     * @param pseudo
     * @param prenom
     * @param nom
     * @param email
     * @param userType
     *
     * fx privée stockant dans un json local les données d'inscription pré formattées
     * si success retourne true
     * @return boolean
     */
    @FXML
    private boolean inscriptionProcess(String pseudo, String prenom, String nom, String email, String
            motDePasse, String userType) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        File jsonFile = new File("Implementation/src/main/resources/com/example/implementation/data/listeUtilisateurs.json");


        if(pseudo==null || prenom ==null || nom==null || email==null || motDePasse==null ||userType==null){
            throw new Exception("null in inscriptionProcess");
        }

        try {
            // Step 1: Lire le contenu du fichier JSON
            ArrayNode jsonNode = (ArrayNode) objectMapper.readTree(jsonFile);

            // Step 2: Vérifier si l'email, le pseudo ou le nom sont déjà présents
            for (JsonNode utilisateurNode : jsonNode) {
                String existingEmails = utilisateurNode.path("courriel").asText("");
                String existingPseudos = utilisateurNode.path("pseudo").asText("");
                String existingNames = utilisateurNode.path("nom").asText("");

                if (existingEmails.equalsIgnoreCase(email) || existingPseudos.equalsIgnoreCase(pseudo) ||existingNames.equalsIgnoreCase(nom)) {
                    this.userError.setStyle("-fx-text-fill: transparent; -fx-border-color: transparent");
                    this.emailError.setStyle("-fx-text-fill: red; -fx-border-color: transparent");
                    this.nameError.setStyle("-fx-text-fill: transparent; -fx-border-color: transparent");
                    return false;
                }
                if (userType.equalsIgnoreCase("Revendeur") && existingNames.equalsIgnoreCase(nom)) {
                    this.userError.setStyle("-fx-text-fill: transparent; -fx-border-color: transparent");
                    this.emailError.setStyle("-fx-text-fill: transparent; -fx-border-color: transparent");
                    this.nameError.setStyle("-fx-text-fill: red; -fx-border-color: transparent");
                    return false;
                }
                if (userType.equalsIgnoreCase("Acheteur") && existingPseudos.equalsIgnoreCase(pseudo)) {
                    this.userError.setStyle("-fx-text-fill: red; -fx-border-color: transparent");
                    this.emailError.setStyle("-fx-text-fill: transparent; -fx-border-color: transparent");
                    this.nameError.setStyle("-fx-text-fill: transparent; -fx-border-color: transparent");
                    return false;
                } else {
                    // Step 2: Ajouter un nouvel utilisateur
                    ObjectNode nouvelUtilisateur = JsonNodeFactory.instance.objectNode();

                    //Utilise la taille  de la liste comme nouvel ID
                    nouvelUtilisateur.put("id", jsonNode.size());
                    nouvelUtilisateur.put("pseudo", pseudo);
                    nouvelUtilisateur.put("prenom", prenom);
                    nouvelUtilisateur.put("nom", nom);
                    nouvelUtilisateur.put("courriel", email);
                    nouvelUtilisateur.put("mdp", motDePasse);
                    nouvelUtilisateur.put("userType", userType);

                    // Step 3: Ajouter le nouvel utilisateur dans la liste de ceux existants
                    jsonNode.add(nouvelUtilisateur);

                    // Étape 4: Écrire la liste mise à jour dans le fichier JSON
                    ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
                    objectWriter.writeValue(jsonFile, jsonNode);

                    // Afficher un message pour confirmer l'inscription réussie
                    System.out.println("Inscription réussie pour  " + pseudo + " en tant que " + userType);


                    showAlert(Alert.AlertType.CONFIRMATION, pane.getScene().getWindow(),
                            "Inscription réussie");

                    resetUnishop();

                    //Retour à la page de connection
                    this.pane.getChildren().remove(acceuilInscription);
                    this.pane.getChildren().add(acceuilConnection);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(),
                    "Une erreur est survenue lors de l'inscription");
            return false;
        }

        return true;
    }

    /**
     * Show alerte affiche une fenêtre à l'utilisateur sur le GUI
     * Utilisé lors de certaines erreurs
     * On définit le contenu de la fenêtre avec les paramètres:
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

    @FXML
    public void handleRetour(ActionEvent event) {
        //Retourne à la page de connection
        this.pane.getChildren().remove(acceuilInscription);
        this.pane.getChildren().add(acceuilConnection);
    }

    // *** Validation des Champs ***

    /**
     * Cette méthode permet de validerle format de l'email que l'utilisateur à inscrit
     *
     * @param courriel Prend en paramètre le texte que l'utilisateur a entré qui correspond à l'email qui doit être validé.
     */
    // Valider le Email qui respecte le bon format
    private static boolean emailValidation(String courriel) {
        return courriel.matches("^[\\w!#$%&'*+/=?`{|}~^.-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^.-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
    }

    //(?=.*[0-9])   Au moins 1 nombre, (?=.*[a-z])  au moins une lettre minuscule
    //(?=\S+$)  pas d'espace
    //.{8,} au moins 8 caractères
    private static boolean mdpValidation(String mdp) {
        return mdp.matches("^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$");
    }

    /**
     * @param tel Prend en paramètre le texte que l'utilisateur a entré qui correspond au matricule qui doit être validé.
     */
    // Valider le matricule que le matricule respecte le bon format


    private static boolean telValidation(String tel) {
        return tel.matches("^[0-9]{10}$");
    }

    /** Fetches tous les acheteurs de la database dans une liste d'objets Acheteurs
     * @return List<Acheteur>
     */
    public List<Acheteur> listeAcheteurs() {

        JSONParser parser = new JSONParser();

        try {
            // Charger le fichier JSON
            Object obj = parser.parse(new FileReader("Implementation/src/main/resources/com/example/implementation/data/listeUtilisateurs.json"));

            // Convertir l'objet en tableau JSON
            JSONArray acheteurs = (JSONArray) obj;

            //List<ProduitEnVente> products = new ArrayList<>() ;
            Acheteur buyer;

            // Parcourir la liste des utilisateurs
            for (Object acheteurObj : acheteurs) {
                JSONObject acheteur = (JSONObject) acheteurObj;

                // Récupérer les informations d'identification de l'utilisateur actuel
                String storedUsername = (String) acheteur.get("pseudo");
                String storedPassword = (String) acheteur.get("mdp");
                String userType = (String) acheteur.get("userType");


                if ("Acheteur".equals(userType)) {
                    // Stockez les informations de connexion dans la session
                    Coordonnees coordonnee = Coordonnees.donneesJSON((JSONObject) acheteurObj);

                    buyer = new Acheteur(storedUsername, storedPassword, coordonnee);

                    this.acheteurInscrits.add(buyer);
                    //System.out.println(products);
                }
                //System.out.println(products.get(1).getTitre());
                return this.acheteurInscrits;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    /** Fetches tous les revendeurs de la database json en une liste d'objets Revendeurs
     * @return List<Revendeur>
     */
    public List<Revendeur> listeRevendeurs() {

        JSONParser parser = new JSONParser();

        try {
            // Charger le fichier JSON
            Object obj = parser.parse(new FileReader("Implementation/src/main/resources/com/example/implementation/data/listeUtilisateurs.json"));

            // Convertir l'objet en tableau JSON
            JSONArray revendeurs = (JSONArray) obj;

            //List<ProduitEnVente> products = new ArrayList<>() ;
            Revendeur seller;

            // Parcourir la liste des utilisateurs
            for (Object revendeurObj : revendeurs) {
                JSONObject acheteur = (JSONObject) revendeurObj;

                // Récupérer les informations d'identification de l'utilisateur actuel
                String storedUsername = (String) acheteur.get("pseudo");
                String storedPassword = (String) acheteur.get("mdp");
                String storedEmail = (String) acheteur.get("courriel");
                String storedTel = (String) acheteur.get("telephone");
                String storedAdresse = (String) acheteur.get("adresse");
                String userType = (String) acheteur.get("userType");


                if ("Revendeur".equals(userType)) {

                    seller = new Revendeur(storedUsername, storedPassword, storedEmail,storedTel,storedAdresse);

                    this.revendeurInscrits.add(seller);

                }
                //System.out.println(products.get(1).getTitre());
                return this.revendeurInscrits;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /** clears all fields
     */
    private void resetUnishop() {
        // Réinitialiser les champs de connexion
        connectionUtilisateur.clear();
        connectionMotdepasse.clear();
        mdpInvalide.setText("");

        // Réinitialiser les champs d'inscriptions
        inscriptionPseudo.clear();
        inscriptionPrenom.clear();
        inscriptionNom.clear();
        InscriptionEmail.clear();
        inscriptionMotdepasse.clear();
    }


///////////////////////////////////////////////////Partie Acheteur//////////////////////////////////////////////////////

    //Etape #1 : button management

    /**
     * @param event event bouton : affiche tous les champs servant à modifier le profil lorsque triggered
     */
    @FXML
    public void handleDashboard(ActionEvent event) {
        dashboardPane.setVisible(true);
        mainProfilPane.setVisible(false);
        profilPane.setVisible(false);
        cataloguePane.setVisible(false);
        wishlistPane.setVisible(false);
        orderPane.setVisible(false);
        messagePane.setVisible(false);

    }

    //Fonctionnalités : Modifier son profil, Fonctionnalité 14 : Voir ses métriques, Fonctionnalité 12: Voir ses points du programme de fidélité

    /**
     * @param event event bouton : affiche tous les champs servant à modifier le profil lorsque triggered
     */
    @FXML
    public void handleProfil(ActionEvent event) {
        System.out.println("Pour voir si pn a toujours l'infos du pseudo de la connection" + this.pseudo);

        if (acheteur != null) {
            userEdit.setText(this.pseudo);
            prenomEdit.setPromptText(this.coordonnees.getPrenom());
            nomEdit.setPromptText(this.coordonnees.getNom());
            emailEdit.setPromptText(this.coordonnees.getCourriel());
            mdpEdit.setPromptText(this.motDePasse);
            telEdit.setPromptText(this.coordonnees.getTelephone());
            adresseEdit.setPromptText(this.coordonnees.getAdresse());

                // Extraire les informations de paiement
                String cc = this.coordonnees.getPaiement().get("cc").toString();
                String conf = this.coordonnees.getPaiement().get("conf").toString();
                String exp = this.coordonnees.getPaiement().get("exp").toString();

                // Assigner les informations de paiement aux champs correspondants
                paiementEdit.setText(cc);
                ccEdit.setText(conf);
                expEdit.setText(exp);
        }
        dashboardPane.setVisible(false);
        mainProfilPane.setVisible(true);
        profilPane.setVisible(false);
        cataloguePane.setVisible(false);
        wishlistPane.setVisible(false);
        orderPane.setVisible(false);
        messagePane.setVisible(false);



        this.suiveursTab.setItems(FXCollections.observableArrayList());
        this.pseudoCol.setCellValueFactory(new PropertyValueFactory<>("pseudoCol"));
        this.prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenomCol"));
        this.typeCol.setCellValueFactory(new PropertyValueFactory<>("typeCol"));

        Acheteur acheteur = this.acheteur;

        List<String> suiveurs = acheteur.getSuiveurs();
        List<Acheteur> suiveurs2 = new ArrayList<Acheteur>();
        for (String e : suiveurs){
            try {
                suiveurs2.add(this.getUser2(e));
            }
            catch (Exception exception22){//skip
                 }
        }

        System.out.println("   TEST !!!!!!!!  : SUIVEURS  !!! "+suiveurs);
        ObservableList suiveursList = FXCollections.observableArrayList(suiveurs2);
        suiveursTab.setItems(suiveursList);

    }



    /**
     * Authenticate the buyer based on the entered username and password.
     *
     * @param username The entered username.
     * @return True if authentication is successful, false otherwise.
     */

    @FXML
    private Acheteur getUser2(String username) throws Exception {
        JSONParser parser = new JSONParser();

        try {
            // Charger le fichier JSON
            Object obj = parser.parse(new FileReader("Implementation/src/main/resources/com/example/implementation/data/listeUtilisateurs.json"));

            // Convertir l'objet en tableau JSON
            JSONArray utilisateurs = (JSONArray) obj;

            // Parcourir la liste des utilisateurs
            for (Object utilisateurObj : utilisateurs) {
                JSONObject utilisateur = (JSONObject) utilisateurObj;

                // Récupérer les informations d'identification de l'utilisateur actuel
                String storedUsername = (String) utilisateur.get("pseudo");
                String storedPassword = (String) utilisateur.get("mdp");
                String userType = (String) utilisateur.get("userType");

                System.out.println(storedUsername);

                if (username.equalsIgnoreCase(storedUsername)) {
                    if ("Acheteur".equals(userType)) {
                        // Stockez les informations de connexion dans la session
                        Coordonnees coordonnees1 = Coordonnees.donneesJSON((JSONObject) utilisateurObj);
                        Acheteur acheteur1 = new Acheteur(username, storedPassword, coordonnees1);

                        List<String> test = new ArrayList<String>();
                        try{
                            test=(List<String>) utilisateur.get("suiveurs");
                            acheteur1.setSuiveurs(test);
                        }
                        catch(Exception e){
                            //ignore otherwise
                        }


                        return acheteur1;

                    }
                    else {throw new Exception("lmao1");}
                }

            }


        } catch (IOException e) {
            throw new Exception("lmao2");
        }
        return null;
    }







    /**
     * Permettre à l'utilisateur de modifier le profil en allant sur la page de ce dernier
     *
     * @param event
     */
    @FXML
    public void modifierProfil(ActionEvent event){
        dashboardPane.setVisible(false);
        mainProfilPane.setVisible(false);
        profilPane.setVisible(true);
        cataloguePane.setVisible(false);
        wishlistPane.setVisible(false);
        orderPane.setVisible(false);
        messagePane.setVisible(false);
    }

    /**
     * Retirer un suiveur sélectionnné dans le tableau de suiveurs suite à un event bouton
     * @param event
     */

    //Fonctionnalité 2 : Gérer ses suiveurs
    @FXML
    private void retirerSuiveur(ActionEvent event) {
        String followerToRemove = suiveursTab.getSelectionModel().getSelectedItem();

        if (followerToRemove != null) {
            // Retirer le suiveur sélectionné
            acheteur.enleverAmi(followerToRemove);

            // Mettre à jour la TableView
            suiveursTab.setItems(FXCollections.observableArrayList(acheteur.getSuiveurs()));
            showAlert(Alert.AlertType.CONFIRMATION, suiveursTab.getScene().getWindow(), "Suiveur retiré avec succès.");

        } else {
            showAlert(Alert.AlertType.WARNING, suiveursTab.getScene().getWindow(), "Veuillez sélectionner un suiveur à retirer.");
        }
    }

    /** Met à jour l'objet coordonées
     *
     * @param event
     */
    @FXML
    public void saveProfilEdit(ActionEvent event) {

        // M.A.J Acheteur
        this.coordonnees.setPrenom(prenomEdit.getText());
        this.coordonnees.setNom(nomEdit.getText());
        this.coordonnees.setCourriel(emailEdit.getText());
        this.motDePasse = mdpEdit.getText();
        this.coordonnees.setTelephone(telEdit.getText());
        coordonnees.setAdresse(adresseEdit.getText());

        coordonnees.getPaiement().put("cc", ccEdit.getText());
        coordonnees.getPaiement().put("conf", ccEdit.getText());
        coordonnees.getPaiement().put("exp", expEdit.getText());

        //Imprimer les informations M.A.J
        System.out.println(" ");
        System.out.println("Informations M.A.J de l'acheteur : " + acheteur);
        System.out.println("Coordonnes M.A.J : " + acheteur.getCoordonnees());


        // Update "paiement" field in Coordonnees
        updateUserData(acheteur);
        // Afficher un message de confirmation
        this.acheteurName.setText(this.pseudo);
        showAlert(Alert.AlertType.CONFIRMATION, pane.getScene().getWindow(), "Profil mis à jour avec succès");



        // Add logic to switch panes or display a success message

        // logic pour valider les champs
    }

    /** Mise à jour du JSON avec les données de l'objet Acheteur
     * @param acheteur
     */
    private void updateUserData(Acheteur acheteur) {
        JSONParser parser = new JSONParser();

        try {
            // Charger le fichier JSON
            Object obj = parser.parse(new FileReader("Implementation/src/main/resources/com/example/implementation/data/listeUtilisateurs.json"));

            // Convertir l'objet en tableau JSON
            JSONArray utilisateurs = (JSONArray) obj;

            // Parcourir la liste des utilisateurs pour trouver l'acheteur
            for (Object utilisateurObj : utilisateurs) {
                JSONObject utilisateur = (JSONObject) utilisateurObj;
                if (this.pseudo.equalsIgnoreCase((String) utilisateur.get("pseudo"))) {
                    // Mettre à jour les informations de l'utilisateur
                    utilisateur.put("prenom", acheteur.getCoordonnees().getPrenom());
                    utilisateur.put("nom", acheteur.getCoordonnees().getNom());
                    utilisateur.put("mdp", this.motDePasse);
                    utilisateur.put("telephone", acheteur.getCoordonnees().getTelephone());
                    utilisateur.put("adresse", acheteur.getCoordonnees().getAdresse());

                    // Mettre à jour le champ "paiement"
                    JSONObject paiement = new JSONObject();
                    paiement.put("cc", acheteur.getCoordonnees().getPaiement().get("cc"));
                    paiement.put("conf", acheteur.getCoordonnees().getPaiement().get("conf"));
                    paiement.put("exp", acheteur.getCoordonnees().getPaiement().get("exp"));
                    utilisateur.put("paiement", paiement);

                    // Écrire la liste mise à jour dans le fichier JSON
                    try (FileWriter file = new FileWriter("Implementation/src/main/resources/com/example/implementation/data/listeUtilisateurs.json")) {
                        file.write(utilisateurs.toJSONString());
                        file.flush();
                    }

                    break;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Erreur lors de la mise à jour du profil");
        }
    }


    /** Masque les panes
     * @param event
     */
    @FXML
    public void handleCatalogue(ActionEvent event) {
        dashboardPane.setVisible(false);
        mainProfilPane.setVisible(false);
        profilPane.setVisible(false);
        cataloguePane.setVisible(true);
        wishlistPane.setVisible(false);
        orderPane.setVisible(false);
        messagePane.setVisible(false);


        showProductLists();
    }


    /**
     * @return ObservableList<ProduitEnVente>
     */
    public ObservableList<ProduitEnVente> menuData() {
        JSONParser parser = new JSONParser();

        try {
            // Charger le fichier JSON
            Object obj = parser.parse(new FileReader("Implementation/src/main/resources/com/example/implementation/data/produits.json"));

            // Convertir l'objet en tableau JSON
            JSONArray produits = (JSONArray) obj;

            ObservableList<ProduitEnVente> products = FXCollections.observableArrayList();
            ProduitEnVente product;

            // Parcourir la liste des utilisateurs
            for (Object produitObj : produits) {
                JSONObject produit = (JSONObject) produitObj;

                // Récupérer les informations des produits
                String storedTId = (String) produit.get("id");
                String storedTitle = (String) produit.get("titre");
                String storedDescriptions = (String) produit.get("description");
                int storedPoints = Integer.parseInt((String) produit.get("pointsParDollar"));
                int storedQuantite = Integer.parseInt((String) produit.get("quantite"));
                double storedPrix = Double.parseDouble((String) produit.get("prix"));
                float storedNote = Float.parseFloat((String) produit.get("noteMoyenne"));
                String storedImage = (String) produit.get("imagePath");
                String storedVideo = (String) produit.get("videoPath");
                String storedCategorie = (String) produit.get("categorie");

                System.out.println(storedTId + "  " + storedTitle + " " + " " + storedImage);

                //Manque le revendeur objet
                product = new ProduitEnVente(
                        this.revendeur, storedTitle, storedDescriptions, storedPoints,
                        storedQuantite, storedPrix, storedNote, storedImage, storedVideo,
                        storedTId, this.catalogue);

                products.add(product);

                //System.out.println(products);
            }
            //System.out.println(products.get(1).getTitre());
            return products;


        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Loads every pane for every object
     */
    public void showProductLists(){
        carteProduits.clear();
        carteProduits.addAll(menuData());

        int rangee = 0;
        int colonne = 0;

        try {
            for (int i = 0; i < carteProduits.size(); i++) {
                FXMLLoader load = new FXMLLoader(getClass().getResource("carteProduit.fxml"));
                AnchorPane pane = load.load();

                ProductCardController cardController = load.getController();

                cardController.setController(this);

                cardController.setData(carteProduits.get(i));

                if (colonne == 3) {
                    colonne = 0;
                    rangee += 1;
                }

                System.out.println("colonne: " + colonne + ", rangee: " + rangee);


                pane.setPadding(new Insets(10));

                this.listeProduitsGrid.add(pane, colonne++, rangee);

                GridPane.setMargin(pane, new Insets(10));

            }
            // Check if the list is not empty before accessing its elements
            if (!carteProduits.isEmpty()) {
                System.out.println("Image path du produit : " + carteProduits.get(0).getImagePath());
            }else
                System.out.println ("NOT WORKEY ");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }


    //Fonctionnalité 3 : Suivre un utilisateur

    //Fonctionnalité 4 : Liker un produit
    @FXML
    public void handleWishlist(ActionEvent event) {
        dashboardPane.setVisible(false);
        mainProfilPane.setVisible(false);
        profilPane.setVisible(false);
        cataloguePane.setVisible(false);
        wishlistPane.setVisible(true);
        orderPane.setVisible(false);
        messagePane.setVisible(false);

        afficherListeDeSouhaits();
    }
    public void addWishList(ProduitEnVente produit) {
        acheteur.getWishlist().getSouhaits().add(produit);
        majJson(acheteur);
        afficherListeDeSouhaits();
    }

    public void majJson(Acheteur acheteur){

    }

    public void afficherListeDeSouhaits() {
        Acheteur acheteur = this.acheteur;
        System.out.println("Wishlist data in afficherListeDeSouhaits: " + acheteur.getWishlist().getSouhaits());

        ObservableList<ProduitEnVente> wishlist = FXCollections.observableArrayList(acheteur.getWishlist().getSouhaits());
        this.listeDeSouhait.setItems(wishlist);
    }

    @FXML
    private void retirerDeLaListe(ActionEvent event) {
        ProduitEnVente itemARetirer = listeDeSouhait.getSelectionModel().getSelectedItem();

        if (itemARetirer != null) {
            // Use the Acheteur method to remove the item
            acheteur.retirerDeLaListeDeSouhait(itemARetirer);

            // Refresh the TableView
            afficherListeDeSouhaits();

            showAlert(Alert.AlertType.CONFIRMATION, listeDeSouhait.getScene().getWindow(), "Produit retiré de la liste de souhaits avec succès.");
        } else {
            showAlert(Alert.AlertType.WARNING, listeDeSouhait.getScene().getWindow(), "Veuillez sélectionner un article à retirer.");
        }
    }

    //Fonctionnalité 5 : Placer une commande
    //Fonctionnalité 6 : Payer pour une commande

    /** Ajouter un article au panier via les panes
     * @param produit objet panier
     * @param quantity int qty
     */
    public void ajouterAuPanier(ProduitEnVente produit, int quantity) {
        // Check if the product is already in the cart
        Optional<Panier> existingItem = produitsPanier.stream()
                .filter(item -> item.getProduct().equals(produit))
                .findFirst();

        if (existingItem.isPresent()) {
            Panier item= existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            Panier newItem = new Panier(produit, quantity);
            this.produitsPanier.add(newItem);
        }

        // Cart View
        panier.setItems(this.produitsPanier);

        updateTotal();
    }



    /**
     *  Calculer le prix des items dans le panier
     */
    public void updateTotal() {
        // Calculate the total price of items in the cart
        double total = this.produitsPanier.stream()
                .mapToDouble(item -> item.getProduct().getPrix() * item.getQuantity())
                .sum();

        // Update the totalPanier label
        totalPanier.setText(String.format("%.2f", total));

        // Mise à jour de la TableView
        panier.setItems(FXCollections.observableArrayList(this.produitsPanier));
    }

    /** Retier un item du panier suite à un trigger
     * @param event Button trigger
     */
    public void retirerDuPanier(ActionEvent event) {
        Panier itemARetirer = panier.getSelectionModel().getSelectedItem();

        if (itemARetirer != null) {
            // Retirer l'article sélectionné du panier
            produitsPanier.remove(itemARetirer);

            // Mettre à jour la TableView
            panier.setItems(FXCollections.observableArrayList(produitsPanier));

            // Mettre à jour le total
            updateTotal();
        } else {
            showAlert(Alert.AlertType.WARNING, panier.getScene().getWindow(), "Veuillez sélectionner un article à retirer.");
        }

    }

    /**
     * Passe une commande lorsque le bouton est triggered
     * @param event
     */
    @FXML
    public void passerCommande(ActionEvent event) {
        showAlert(Alert.AlertType.CONFIRMATION, panier.getScene().getWindow(), "Votre commande à été passer avec succès");
        panier.getItems().clear();
    }



    //Fonctionnalité 7 : Gérer ses commandes
    //Fonctionnalité 8 : Confirmer la réception d'une commande
    //Fonctionnalité 9 : Signaler un problème à une de ses commandes
    //Fonctionnalité 10 : Retourner ou échanger les produits d'une de ses commandes
    //Fonctionnalité 11 : Liker un revendeur

    //Fonctionnalité 13 : Donner une note et écrire une évaluation à un produit

    //Fonctionnalité 15 : Voir ses notifications

    /**
     * Cache la fenêtre centrale suite au clic de l'onglet de gauche (event)
     * @param event
     */
    @FXML
    public void handleNotif(ActionEvent event) {
        dashboardPane.setVisible(false);
        mainProfilPane.setVisible(false);
        profilPane.setVisible(false);
        cataloguePane.setVisible(false);
        wishlistPane.setVisible(false);
        orderPane.setVisible(false);
        messagePane.setVisible(true);
    }

    /**
     * gibberish
     * @param event
     */
    @FXML
    public void handleFirst(ActionEvent event) {
        convo1Pane.setVisible(true);
        convo2Pane.setVisible(false);
        convo3Pane.setVisible(false);
    }
    @FXML
    public void handleSecond(ActionEvent event) {
        convo1Pane.setVisible(false);
        convo2Pane.setVisible(true);
        convo3Pane.setVisible(false);
    }
    @FXML
    public void handleThird(ActionEvent event) {
        convo1Pane.setVisible(false);
        convo2Pane.setVisible(false);
        convo3Pane.setVisible(true);
    }


    /**
     * Hides panes from view
     * Fired when logout button clicked
     * @param event
     */
    @FXML
    private void handleLogoutacheteur(ActionEvent event) {
        if (acheteur != null) {
            // Réinitialiser les données associées à la session actuelle
            //acheteur.getPanier().viderPanier();
            //acheteur.getWhishlist().viderListe();


            // Réinitialiser l'instance de l'utilisateur connecté
            acheteur = null;

            //Tests
            //System.out.println("Informations de l'acheteur : " + acheteur);
            //System.out.println("Informations de coordonnees : " + acheteur.getCoordonnees());

            resetUnishop();

            // Retourner à la page de connexion
            pane.getChildren().remove(menuAcheteur);
            pane.getChildren().add(acceuilConnection);
        } else {
            // L'utilisateur n'est pas connecté
        }
    }

///////////////////////////////////////////////////Partie Revendeur//////////////////////////////////////////////////////


    /**
     * Non implémentée
      * @param event
     */
    @FXML
    public void profilRevendeur(ActionEvent event){

    }


    /**
     * Logout du revendeur suite à une action event issu d'un bouton
     * @param event
     */
    @FXML
    private void handleLogoutRevendeur(ActionEvent event) {
        if (revendeur != null) {
            // Réinitialiser l'instance de l'utilisateur connecté
            revendeur = null;

            resetUnishop();

            // Retourner à la page de connexion
            pane.getChildren().remove(menuRevendeur);
            pane.getChildren().add(acceuilConnection);
        } else {
            // L'utilisateur n'est pas connecté
        }
    }

}
