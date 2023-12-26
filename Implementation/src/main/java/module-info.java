module com.example.implementation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.implementation to javafx.fxml;
    exports com.example.implementation;
}