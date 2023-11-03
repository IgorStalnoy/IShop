package com.example.ishop.controller.page_controllers;

import com.example.ishop.controller.MainController;
import com.example.ishop.exceptions.CustomerDoesNotExistException;
import com.example.ishop.exceptions.InvalidCredentialsException;
import com.example.ishop.model.Customer;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class LogInPageController extends MainController {

    @FXML
    Button goToMainScreenButton;
    @FXML
    Button goToCreateAccountPageButton;
    @FXML
    Button logInButton;
    @FXML
    Button logInAsVendor;
    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    @FXML
    Label infoMessageLabel;
    @FXML
    Label infoMessageLabel2;

    @FXML
    public void goToMainScreen() {
        goToMainPage();
    }

    @FXML
    public void logIn() {
        if (isUsernameFieldEmpty() || isPasswordFieldEmpty()) {
            if (isUsernameFieldEmpty()) {
                infoMessageLabel2.setTextFill(Color.RED);
                infoMessageLabel2.setText("This value should not be blank");
            } else {
                infoMessageLabel2.setTextFill(Color.BLACK);
                infoMessageLabel2.setText("");
            }
            if (isPasswordFieldEmpty()) {
                infoMessageLabel.setTextFill(Color.RED);
                infoMessageLabel.setText("This value should not be blank");
            } else {
                infoMessageLabel.setTextFill(Color.BLACK);
                infoMessageLabel.setText("");
            }
        } else {
            try {
                infoMessageLabel2.setTextFill(Color.BLACK);
                infoMessageLabel2.setText("");
                Customer loggedCustomer = customerLogIn(new Customer(0, usernameField.getText(), passwordField.getText(), ""));
                infoMessageLabel.setTextFill(Color.BLACK);
                infoMessageLabel.setText("Welcome " + loggedCustomer.getUsername() + "!");
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(e -> {
                    usernameField.setText("");
                    passwordField.setText("");
                    infoMessageLabel.setTextFill(Color.BLACK);
                    infoMessageLabel.setText("");
                    goToMainPage();
                });
                pause.play();
            } catch (CustomerDoesNotExistException e) {
                infoMessageLabel.setTextFill(Color.RED);
                infoMessageLabel.setText("Customer does not exist");
            } catch (InvalidCredentialsException e) {
                infoMessageLabel.setTextFill(Color.RED);
                infoMessageLabel.setText("Invalid credentials");
            }
        }
    }

    private boolean isUsernameFieldEmpty() {
        return usernameField.getText().equals("");
    }

    private boolean isPasswordFieldEmpty() {
        return passwordField.getText().equals("");
    }

    public void clearMessageLabels() {
        infoMessageLabel.setTextFill(Color.BLACK);
        infoMessageLabel.setText("");
        infoMessageLabel2.setTextFill(Color.BLACK);
        infoMessageLabel2.setText("");
    }

    public void keyboardListener(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            logIn();
        }
    }

}
