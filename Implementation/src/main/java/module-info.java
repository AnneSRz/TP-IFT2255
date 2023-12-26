module com.example.implementation {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires json.simple;


    opens com.example.implementation to javafx.fxml;
    exports com.example.implementation;
}