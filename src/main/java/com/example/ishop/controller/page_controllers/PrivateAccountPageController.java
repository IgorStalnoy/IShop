package com.example.ishop.controller.page_controllers;

import com.example.ishop.controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrivateAccountPageController extends MainController {
    @FXML
    Button goToOrdersPage;
    @FXML
    Button goToCartPage;
    @FXML
    Button logOut;
    @FXML
    Button goToMainPage;
    @FXML
    Label idInfo;
    @FXML
    Label usernameInfo;
    @FXML
    Label passwordInfo;
    @FXML
    Label emailInfo;

    public void setCustomersInfo() {
        idInfo.setText(String.valueOf(getLoggedCustomer().getId()));
        usernameInfo.setText(getLoggedCustomer().getUsername());
        passwordInfo.setText(getLoggedCustomer().getPassword());
        emailInfo.setText(getLoggedCustomer().getEmail());
    }
}
