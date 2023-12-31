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
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Window;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private AnchorPane menuAcheteur, navAcheteur,dashboardPane, profilPane,cataloguePane, wishlistPane, orderPane, messagePane;
    @FXML
    private Label acheteurName, userEdit;
    @FXML
    private Button dashboard, profil, wishlist,acheteurCatalogue,commande, acheteurMsgs, like;
    @FXML
    private TextField prenomEdit, nomEdit, emailEdit, telEdit, adresseEdit;
    @FXML
    private PasswordField mdpEdit, paiementEdit, ccEdit, expEdit;
    @FXML
    private TableColumn nomPanier, qtPanier, prixPanier;
    @FXML
    private GridPane listeProduitsGrid;
    @FXML
    ScrollPane listeProduitsScroll;

    // De la page du Menu revendeur
    @FXML
    private AnchorPane menuRevendeur;

    //Fonctionnalité
    private Acheteur acheteur = null;
    private Revendeur revendeur = null;
    private Coordonnees coordonnees;
    private Catalogue catalogue;
    private List<Revendeur> revendeurInscrits = new ArrayList<>();
    private List<Acheteur> acheteurInscrits = new ArrayList<>();

    //private ObservableList<ProduitEnVente> carteProduit = FXCollections.observableArrayList();
    //private List<ProduitEnVente> produits = new ArrayList<>();
    private ObservableList<ProduitEnVente> carteProduits = FXCollections.observableArrayList();



    ///////////////////////////////////////////FOR EVERYONE///////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHomePage();
        styleSetter();

        ObservableList<String> userTypes = FXCollections.observableArrayList("Acheteur", "Revendeur");
        choixMenuInscription.setItems(userTypes);

        showProductLists();

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

    @FXML
    public void handleSignIn(ActionEvent event) {
        pane.getChildren().add(acceuilInscription);
    }

    @FXML
    public void handleLogin(ActionEvent event) throws IOException {

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
                         // his.dashboard.setStyle("-fx-background-color: #190482; -fx-text-fill: white");

                         return true;

                     } else if ("Revendeur".equals(userType)) {
                         String courriel = (String) utilisateur.get("pseudo");
                         String telephone = (String) utilisateur.get("telephone");
                         String adresse = (String) utilisateur.get("adresse");
                         this.pane.getChildren().remove(acceuilConnection);
                         this.pane.getChildren().add(menuRevendeur);


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

    //Etape #1 : Afficher le menu de l'acheteur
    @FXML
    public void handleDashboard(ActionEvent event) {
        this.navAcheteur.getChildren().add(dashboardPane);
        this.navAcheteur.getChildren().remove(profilPane);
        this.navAcheteur.getChildren().remove(cataloguePane);

    }

    //////Fonctionnalité 1 : Modifier son profil////
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
        this.navAcheteur.getChildren().add(profilPane);
        this.navAcheteur.getChildren().remove(dashboardPane);
        this.navAcheteur.getChildren().remove(cataloguePane);


    }

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

    @FXML
    public void handleCatalogue(ActionEvent event) {
        this.navAcheteur.getChildren().add(cataloguePane);
        this.navAcheteur.getChildren().remove(dashboardPane);
        this.navAcheteur.getChildren().remove(profilPane);

        showProductLists();
    }


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
                cardController.setData(carteProduits.get(i));

                if (colonne == 3) {
                    colonne = 0;
                    rangee += 1;
                }

                System.out.println("colonne: " + colonne + ", rangee: " + rangee);


                pane.setPadding(new Insets(10));

                this.listeProduitsGrid.add(pane, colonne++, rangee);
                //set grid width
                listeProduitsGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                listeProduitsGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                listeProduitsGrid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                listeProduitsGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                listeProduitsGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                listeProduitsGrid.setMaxHeight(Region.USE_PREF_SIZE);

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



    // ... other methods ...

    //Fonctionnalité 2 : Gérer ses suiveurs

    //Fonctionnalité 3 : Suivre un utilisateur

    //Fonctionnalité 4 : Liker un produit
    //@FXML
    //public void likeButton(ActionEvent event) {

   // }
    //Fonctionnalité 5 : Placer une commande
    //Fonctionnalité 6 : Payer pour une commande
    //Fonctionnalité 7 : Gérer ses commandes
    //Fonctionnalité 8 : Confirmer la réception d'une commande
    //Fonctionnalité 9 : Signaler un problème à une de ses commandes
    //Fonctionnalité 10 : Retourner ou échanger les produits d'une de ses commandes
    //Fonctionnalité 11 : Liker un revendeur
    //Fonctionnalité 12: Voir ses points du programme de fidélité
    //Fonctionnalité 13 : Donner une note et écrire une évaluation à un produit
    //Fonctionnalité 14 : Voir ses métriques
    //Fonctionnalité 15 : Voir ses notifications

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
            // L'utilisateur n'est pas connecté, A VOIIIIR
        }
    }

///////////////////////////////////////////////////Partie Revendeur//////////////////////////////////////////////////////

}
