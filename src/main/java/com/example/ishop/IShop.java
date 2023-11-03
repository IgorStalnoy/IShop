package com.example.ishop;

import com.example.ishop.controller.page_controllers.*;
import com.example.ishop.db.DBCreation;
import com.example.ishop.db.Dao;
import com.example.ishop.db.dbimplementation.ProductsSQLite;
import com.example.ishop.model.Product.Product;
import com.example.ishop.model.ScreenNames;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import static com.example.ishop.model.ScreenNames.*;


public class IShop extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DBCreation dbCreation = new DBCreation();
        if (!dbCreation.isDatabaseExist()) {
            try {
                dbCreation.createDatabase();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        Dao<Product> productDB = new ProductsSQLite();

        FXMLLoader mainPageLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        FXMLLoader productPageLoader = new FXMLLoader(getClass().getResource("ProductPage.fxml"));
        FXMLLoader logInPageLoader = new FXMLLoader(getClass().getResource("LogInPage.fxml"));
        FXMLLoader createAccountPageLoader = new FXMLLoader(getClass().getResource("createAccountPage.fxml"));
        FXMLLoader privateAccountPageLoader = new FXMLLoader(getClass().getResource("PrivateAccountPage.fxml"));
        FXMLLoader cartPageLoader = new FXMLLoader(getClass().getResource("CartPage.fxml"));
        FXMLLoader vendorPageLoader = new FXMLLoader(getClass().getResource("VendorPage.fxml"));

        Scene mainPageScene = new Scene(mainPageLoader.load());
        Scene productPageScene = new Scene(productPageLoader.load());
        Scene logInPageScene = new Scene(logInPageLoader.load());
        Scene createAccountPageScene = new Scene(createAccountPageLoader.load());
        Scene privateAccountPageScene = new Scene(privateAccountPageLoader.load());
        Scene cartPageScene = new Scene(cartPageLoader.load());
        Scene vendorPageScene = new Scene(vendorPageLoader.load());

        MainPageController mainPageController = mainPageLoader.getController();
        ProductPageController productPageController = productPageLoader.getController();
        LogInPageController logInPageController = logInPageLoader.getController();
        CreateAccountPageController createAccountPageController = createAccountPageLoader.getController();
        PrivateAccountPageController privateAccountPageController = privateAccountPageLoader.getController();
        CartPageController cartPageController = cartPageLoader.getController();
        VendorPageController vendorPageController = vendorPageLoader.getController();

        mainPageController.setStage(stage);
        productPageController.setStage(stage);
        logInPageController.setStage(stage);
        createAccountPageController.setStage(stage);
        privateAccountPageController.setStage(stage);
        cartPageController.setStage(stage);
        vendorPageController.setStage(stage);

        HashMap<ScreenNames, Scene> screenMap = new HashMap<>();
        screenMap.put(MAIN_SCREEN, mainPageScene);
        screenMap.put(PRODUCT_SCREEN, productPageScene);
        screenMap.put(LOGIN_PAGE_SCREEN, logInPageScene);
        screenMap.put(CREATE_ACCOUNT_PAGE_SCREEN, createAccountPageScene);
        screenMap.put(PRIVATE_ACCOUNT_SCREEN, privateAccountPageScene);
        screenMap.put(CART_PAGE_SCREEN, cartPageScene);
        screenMap.put(VENDOR_PAGE_SCREEN, vendorPageScene);
        HashMap<ScreenNames, FXMLLoader> controllerMap = new HashMap<>();
        controllerMap.put(MAIN_SCREEN, mainPageLoader);
        controllerMap.put(PRODUCT_SCREEN, productPageLoader);
        controllerMap.put(LOGIN_PAGE_SCREEN, logInPageLoader);
        controllerMap.put(CREATE_ACCOUNT_PAGE_SCREEN, createAccountPageLoader);
        controllerMap.put(PRIVATE_ACCOUNT_SCREEN, privateAccountPageLoader);
        controllerMap.put(CART_PAGE_SCREEN, cartPageLoader);
        controllerMap.put(VENDOR_PAGE_SCREEN, vendorPageLoader);

        mainPageController.setControllerMap(controllerMap);
        productPageController.setControllerMap(controllerMap);
        logInPageController.setControllerMap(controllerMap);
        createAccountPageController.setControllerMap(controllerMap);
        privateAccountPageController.setControllerMap(controllerMap);
        cartPageController.setControllerMap(controllerMap);
        vendorPageController.setControllerMap(controllerMap);

        mainPageController.setScreenMap(screenMap);
        productPageController.setScreenMap(screenMap);
        logInPageController.setScreenMap(screenMap);
        createAccountPageController.setScreenMap(screenMap);
        privateAccountPageController.setScreenMap(screenMap);
        cartPageController.setScreenMap(screenMap);
        vendorPageController.setScreenMap(screenMap);

        mainPageController.setProductsList(productDB.getAll());

        stage.setScene(mainPageScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}