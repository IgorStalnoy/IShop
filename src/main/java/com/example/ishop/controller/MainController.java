package com.example.ishop.controller;

import com.example.ishop.controller.page_controllers.*;
import com.example.ishop.db.Dao;
import com.example.ishop.db.dbimplementation.CustomersSQLite;
import com.example.ishop.db.dbimplementation.ProductsSQLite;
import com.example.ishop.exceptions.CustomerAlreadyExistException;
import com.example.ishop.exceptions.CustomerDoesNotExistException;
import com.example.ishop.exceptions.InvalidCredentialsException;
import com.example.ishop.model.Customer;
import com.example.ishop.model.Product.Product;
import com.example.ishop.model.ScreenNames;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.ishop.model.ScreenNames.*;

public abstract class MainController {

    private Stage stage;
    private HashMap<ScreenNames, FXMLLoader> controllerMap;
    private HashMap<ScreenNames, Scene> screenMap;
    private Customer loggedCustomer;
    private List<Product> cartList = new ArrayList<>();

    Dao<Product> productsDB = new ProductsSQLite();
    Dao<Customer> customersDB = new CustomersSQLite();

    public MainController() {
    }

    public Customer customerLogIn(Customer logginningCustomer) throws CustomerDoesNotExistException, InvalidCredentialsException {
        Customer existingCustomer = customersDB.get(logginningCustomer);
        if (existingCustomer == null) {
            throw new CustomerDoesNotExistException();
        } else if (!logginningCustomer.getUsername().equals(existingCustomer.getUsername()) || !logginningCustomer.getPassword().equals(existingCustomer.getPassword())) {
            throw new InvalidCredentialsException();
        } else {
            controllerMap.forEach((key, value) -> ((MainController) value.getController()).setLoggedCustomer(existingCustomer));
            return existingCustomer;
        }
    }

    public void createNewCustomer(Customer customer) throws CustomerAlreadyExistException {
        try {
            customersDB.save(customer);
        } catch (RuntimeException | SQLException e) {
            throw new CustomerAlreadyExistException(e);
        }
    }

    public void goToMainPage() {
        MainPageController mainPageController = controllerMap.get(MAIN_SCREEN).getController();
        mainPageController.setButtonsVisibility(loggedCustomer != null);
        mainPageController.setCustomersInfo(loggedCustomer);
        getStage().setScene(getScreenMap().get(MAIN_SCREEN));
    }

    public void goToProductPage(Product product) {
        ProductPageController productPageController = controllerMap.get(PRODUCT_SCREEN).getController();
        productPageController.setProduct(product);
        getStage().setScene(getScreenMap().get(PRODUCT_SCREEN));
    }

    public void goToVendorPage() {
        VendorPageController vendorPageController = controllerMap.get(VENDOR_PAGE_SCREEN).getController();
        vendorPageController.clearMessages();
        vendorPageController.setVisibilityNotLogged();
        getStage().setScene(getScreenMap().get(VENDOR_PAGE_SCREEN));
    }

    public void goToLogInPage() {
        LogInPageController logInPageController = controllerMap.get(LOGIN_PAGE_SCREEN).getController();
        logInPageController.clearMessageLabels();
        getStage().setScene(getScreenMap().get(LOGIN_PAGE_SCREEN));
    }

    public void goToCreateAccountPage() {
        CreateAccountPageController createAccountPageController = controllerMap.get(CREATE_ACCOUNT_PAGE_SCREEN).getController();
        createAccountPageController.clearInfoMessages();
        getStage().setScene(getScreenMap().get(CREATE_ACCOUNT_PAGE_SCREEN));
    }

    public void goToPrivateAccount() {
        PrivateAccountPageController privateAccountPageController = controllerMap.get(PRIVATE_ACCOUNT_SCREEN).getController();
        privateAccountPageController.setCustomersInfo();
        getStage().setScene(getScreenMap().get(PRIVATE_ACCOUNT_SCREEN));
    }

    public void goToCartPage() {
        CartPageController cartPageController = controllerMap.get(CART_PAGE_SCREEN).getController();
        cartPageController.setProductsListView(FXCollections.observableArrayList(cartList));
        getStage().setScene(getScreenMap().get(CART_PAGE_SCREEN));
    }

    public void logOutCustomer() {
        controllerMap.forEach((key, value) -> ((MainController) value.getController()).setLoggedCustomer(null));
        goToMainPage();
    }

    public void addProductToTheCart(Product product) {
        cartList.add(product);
        controllerMap.forEach((key, value) -> ((MainController) value.getController()).setCartList(cartList));
    }

    public void setCartList(List<Product> cartList) {
        this.cartList = cartList;
    }

    public void removeProductFromTheCart(Product product) {
        cartList.remove(product);
        controllerMap.forEach((key, value) -> ((MainController) value.getController()).setCartList(cartList));
        goToCartPage();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public List<Product> getAllOrderedProducts(String field, String order) {
        return productsDB.getAll(field, order);
    }

    public HashMap<ScreenNames, FXMLLoader> getControllerMap() {
        return controllerMap;
    }

    public void setControllerMap(HashMap<ScreenNames, FXMLLoader> controllerMap) {
        this.controllerMap = controllerMap;
    }

    public HashMap<ScreenNames, Scene> getScreenMap() {
        return screenMap;
    }

    public void setScreenMap(HashMap<ScreenNames, Scene> screenMap) {
        this.screenMap = screenMap;
    }

    public void setLoggedCustomer(Customer loggedCustomer) {
        this.loggedCustomer = loggedCustomer;
    }

    public Customer getLoggedCustomer() {
        return loggedCustomer;
    }

    public Stage getStage() {
        return stage;
    }

    public List<Product> getCartList() {
        return cartList;
    }

}