package com.example.ishop.controller.page_controllers;

import com.example.ishop.controller.MainController;
import com.example.ishop.model.Customer;
import com.example.ishop.model.Product.Product;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.List;

import static com.example.ishop.model.ScreenNames.MAIN_SCREEN;

public class MainPageController extends MainController {

    private ObservableList<Product> products;
    private String productsOrder;

    @FXML
    ListView<Product> productsListView;
    @FXML
    Button orderByPriceButton;
    @FXML
    Button orderByModelButton;
    @FXML
    Button orderByCategoryButton;
    @FXML
    Button orderByIdButton;
    @FXML
    Button orderByCountButton;
    @FXML
    Button goToLogInPageButton;
    @FXML
    Button logOutButton;
    @FXML
    Label loggedCustomerInfo;
    @FXML
    Label infoMessage;
    @FXML
    Button goToPrivateAccountPageButton;

    public void goToLogInScreenScreen() {
        goToLogInPage();
    }

    public void goProductPage() {
        try {
            MultipleSelectionModel<Product> selectionModel = productsListView.getSelectionModel();
            int itemPosition = selectionModel.getSelectedIndex();
            goToProductPage(productsListView.getItems().get(itemPosition));
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

    public void sortProductsList(ActionEvent actionEvent) {
        String order;
        order = productsOrder == null || productsOrder.equals("ASC") ? "DESC" : "ASC";
        productsOrder = order;
        if (actionEvent.getSource() == orderByPriceButton) {
            products = FXCollections.observableArrayList(getAllOrderedProducts("price", order));
            productsListView.setItems(products);
            getStage().setScene(getScreenMap().get(MAIN_SCREEN));
        } else if (actionEvent.getSource() == orderByModelButton) {
            products = FXCollections.observableArrayList(getAllOrderedProducts("model", order));
            productsListView.setItems(products);
            getStage().setScene(getScreenMap().get(MAIN_SCREEN));
        } else if (actionEvent.getSource() == orderByIdButton) {
            products = FXCollections.observableArrayList(getAllOrderedProducts("id", order));
            productsListView.setItems(products);
            getStage().setScene(getScreenMap().get(MAIN_SCREEN));
        } else if (actionEvent.getSource() == orderByCategoryButton) {
            products = FXCollections.observableArrayList(getAllOrderedProducts("categoryID", order));
            productsListView.setItems(products);
            getStage().setScene(getScreenMap().get(MAIN_SCREEN));
        } else if (actionEvent.getSource() == orderByCountButton) {
            products = FXCollections.observableArrayList(getAllOrderedProducts("count", order));
            productsListView.setItems(products);
            getStage().setScene(getScreenMap().get(MAIN_SCREEN));
        }
    }

    public void setProductsList(List<Product> productsList) {
        products = FXCollections.observableArrayList(productsList);
        productsListView.setItems(products);
    }

    public void setButtonsVisibility(Boolean isCustomerLogged) {
        goToLogInPageButton.setVisible(!isCustomerLogged);
        logOutButton.setVisible(isCustomerLogged);
        goToPrivateAccountPageButton.setVisible(isCustomerLogged);
    }

    public void setCustomersInfo(Customer customer) {
        if (customer != null) {
            loggedCustomerInfo.setText(customer.getUsername());
        } else {
            loggedCustomerInfo.setText("");
        }
    }

    public void logOut() {
        logOutCustomer();
    }

    public void goToPrivateAccountPage() {
        goToPrivateAccount();
    }
}
