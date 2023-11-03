package com.example.ishop.controller.page_controllers;

import com.example.ishop.controller.MainController;
import com.example.ishop.db.Dao;
import com.example.ishop.db.dbimplementation.ProductsSQLite;
import com.example.ishop.model.Product.Computer;
import com.example.ishop.model.Product.Furniture;
import com.example.ishop.model.Product.HouseholdChemicals;
import com.example.ishop.model.Product.Product;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VendorPageController extends MainController {

    Dao<Product> productsDB = new ProductsSQLite();

    @FXML
    Button fileOpenButton;
    @FXML
    Label infoMessage;
    @FXML
    TextField vendorPasswordField;
    @FXML
    Label vendorPasswordInfoMessage;
    @FXML
    Button goToMainPage;

    public void loadFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(getStage());
        Task<Void> task = new Task<>() {
            @Override
            public Void call() {
                try {
                    List<Product> productsList = new ArrayList<>();
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            String[] values = line.split(",");
                            try {
                                if (Integer.parseInt(values[1]) == 1) {
                                    System.out.println(Thread.currentThread().getName());
                                    int categoryID = Integer.parseInt(values[1]);
                                    int price = Integer.parseInt(values[2]);
                                    int count = Integer.parseInt(values[3]);
                                    String model = values[5];
                                    String brand = values[4];
                                    String type = values[6];
                                    int processorSpeed = Integer.parseInt(values[7]);
                                    int ramSize = Integer.parseInt(values[8]);
                                    int hddSize = Integer.parseInt(values[9]);
                                    Product product = new Computer(1, categoryID, price, count, model, brand, type, processorSpeed, ramSize, hddSize);
                                    productsList.add(product);
                                } else if (Integer.parseInt(values[1]) == 2) {
                                    int categoryID = Integer.parseInt(values[1]);
                                    int price = Integer.parseInt(values[2]);
                                    int count = Integer.parseInt(values[3]);
                                    String model = values[4];
                                    String material = values[5];
                                    String color = values[6];
                                    Product product = new Furniture(1, categoryID, price, count, model, material, color);
                                    productsList.add(product);
                                } else if (Integer.parseInt(values[1]) == 3) {
                                    int categoryID = Integer.parseInt(values[1]);
                                    int price = Integer.parseInt(values[2]);
                                    int count = Integer.parseInt(values[3]);
                                    String model = values[4];
                                    String brand = values[5];
                                    String appointment = values[6];
                                    String smell = values[7];
                                    Product product = new HouseholdChemicals(1, categoryID, price, count, model, brand, appointment, smell);
                                    productsList.add(product);
                                }
                            } catch (RuntimeException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    productsDB.saveAll(productsList);
                } catch (IllegalStateException e) {
                    System.out.println(" ");
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    public void clearMessages() {
        infoMessage.setTextFill(Color.RED);
        infoMessage.setText("");
    }

    public void vendorPasswordInput(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (!vendorPasswordField.getText().equals("admin")) {
                vendorPasswordInfoMessage.setTextFill(Color.RED);
                vendorPasswordInfoMessage.setText("Invalid password");
            } else {
                setVisibilityLogged();
            }
        }
    }

    public void setVisibilityNotLogged() {
        vendorPasswordInfoMessage.setText("");
        vendorPasswordField.setVisible(true);
        infoMessage.setVisible(false);
        fileOpenButton.setVisible(false);
        goToMainPage.setVisible(false);
        vendorPasswordField.setText("");
    }

    public void setVisibilityLogged() {
        vendorPasswordInfoMessage.setText("");
        vendorPasswordField.setVisible(false);
        infoMessage.setVisible(true);
        fileOpenButton.setVisible(true);
        goToMainPage.setVisible(true);
    }

}
