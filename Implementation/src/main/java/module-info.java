module com.example.implementation {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires com.fasterxml.jackson.databind;


    opens com.example.implementation to javafx.fxml;
    opens com.example.implementation.classUnishop to javafx.base;
    exports com.example.implementation;
}