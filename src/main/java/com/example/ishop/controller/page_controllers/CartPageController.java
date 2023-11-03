package com.example.ishop.controller.page_controllers;

import com.example.ishop.controller.MainController;
import com.example.ishop.model.Product.Product;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class CartPageController extends MainController {
    @FXML
    ListView<Product> productsInCartListView = new ListView<>();
    @FXML
    Button createOrderButton;
    @FXML
    Label infoMessage;

    public void setProductsListView(ObservableList<Product> products) {
        this.productsInCartListView.setItems(products);
    }

    public void createOrder() {
        if (getCartList().isEmpty()) {
            infoMessage.setTextFill(Color.RED);
            infoMessage.setText("Your cart is empty");
            goToCartPage();
        } else if (getLoggedCustomer() == null) {
            infoMessage.setTextFill(Color.RED);
            infoMessage.setText("You need to login first");
            goToCartPage();
        } else {
            infoMessage.setTextFill(Color.BLACK);
            infoMessage.setText("Order was successfully created");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> {
                clearInfoMessages();
                getControllerMap().forEach((key, value) -> ((MainController) value.getController()).setCartList(new ArrayList<>()));
                goToMainPage();
            });
            pause.play();
        }
    }

    public void clearInfoMessages() {
        infoMessage.setTextFill(Color.BLACK);
        infoMessage.setText("");
    }

    public void removeProduct() {
        try {
            MultipleSelectionModel<Product> selectionModel = productsInCartListView.getSelectionModel();
            int itemPosition = selectionModel.getSelectedIndex();
            removeProductFromTheCart(productsInCartListView.getItems().get(itemPosition));
            infoMessage.setTextFill(Color.BLACK);
            infoMessage.setText(productsInCartListView.getItems().get(itemPosition).getCategoriesName()
                    .get(productsInCartListView.getItems().get(itemPosition).getCategoryId()) + " " + productsInCartListView.getItems().get(itemPosition).getModel() + " was deleted from cart");
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(i -> {
                infoMessage.setTextFill(Color.BLACK);
                infoMessage.setText("");
            });
            pause.play();
        } catch (RuntimeException e) {
            infoMessage.setTextFill(Color.RED);
            infoMessage.setText("Please choose a product");
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(i -> {
                infoMessage.setTextFill(Color.BLACK);
                infoMessage.setText("");
            });
            pause.play();
        }
    }

    public void goToMain() {
        clearInfoMessages();
        goToMainPage();
    }

}
