package com.example.ishop.controller.page_controllers;

import com.example.ishop.controller.MainController;
import com.example.ishop.model.Product.Computer;
import com.example.ishop.model.Product.Furniture;
import com.example.ishop.model.Product.HouseholdChemicals;
import com.example.ishop.model.Product.Product;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ProductPageController extends MainController {

    private Product product;

    @FXML
    Label label;
    @FXML
    Button goToMainScreen;
    @FXML
    Button addToCart;
    @FXML
    Label infoMessage;

    public void setProduct(Product product) {
        this.product = product;
        if (product instanceof Computer) {
            label.setText(product.toString());
        } else if (product instanceof Furniture) {
            label.setText(product.toString());
        } else if (product instanceof HouseholdChemicals) {
            label.setText(product.toString());
        }
    }

    @FXML
    public void addProduct() {
        infoMessage.setTextFill(Color.BLACK);
        infoMessage.setText(product.getCategoriesName().get(product.getCategoryId()) + " " + product.getModel() + " was successfully added to the cart");
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(e -> infoMessage.setText(""));
        pause.play();
        addProductToTheCart(product);
    }
}
