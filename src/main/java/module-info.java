module com.example.ishop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ishop to javafx.fxml;
    exports com.example.ishop;
    exports com.example.ishop.controller;
    opens com.example.ishop.controller to javafx.fxml;
    exports com.example.ishop.controller.page_controllers;
    opens com.example.ishop.controller.page_controllers to javafx.fxml;
    exports com.example.ishop.model.Product;
    opens com.example.ishop.model.Product to javafx.fxml;
    exports com.example.ishop.model;
    opens com.example.ishop.model to javafx.fxml;
    exports com.example.ishop.exceptions;
    opens com.example.ishop.exceptions to javafx.fxml;
}