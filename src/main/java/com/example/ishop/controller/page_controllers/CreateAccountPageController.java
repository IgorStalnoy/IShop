package com.example.ishop.controller.page_controllers;

import com.example.ishop.controller.MainController;
import com.example.ishop.exceptions.CustomerAlreadyExistException;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccountPageController extends MainController {

    @FXML
    Button goToMainScreenButton;
    @FXML
    Button goToLoginPageButton;
    @FXML
    Button createAccountButton;
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    TextField email;
    @FXML
    Label infoMessage1;
    @FXML
    Label infoMessage2;
    @FXML
    Label infoMessage3;

    public void goToMainScreen() {
        goToMainPage();
    }

    public void goToLoginPageScreen() {
        goToLogInPage();
    }

    public void createAccount() {
        Pattern validEmailPattern = Pattern.compile("\\w[\\w.\\-]+@([а-я\\w\\-]+\\.)+([(a-z]{2,6}|[а-я]{2,6})");
        Matcher validEmailMatcher = validEmailPattern.matcher(email.getText());
        if (isUsernameFieldEmpty() || isPasswordFieldEmpty() || isEmailFieldEmpty()) {
            if (isUsernameFieldEmpty()) {
                infoMessage3.setTextFill(Color.RED);
                infoMessage3.setText("This value should not be blank");
            } else {
                infoMessage3.setTextFill(Color.BLACK);
                infoMessage3.setText("");
            }
            if (isPasswordFieldEmpty()) {
                infoMessage2.setTextFill(Color.RED);
                infoMessage2.setText("This value should not be blank");
            } else {
                infoMessage2.setTextFill(Color.BLACK);
                infoMessage2.setText("");
            }
            if (isEmailFieldEmpty()) {
                infoMessage1.setTextFill(Color.RED);
                infoMessage1.setText("This value should not be blank");
            } else {
                infoMessage1.setTextFill(Color.BLACK);
                infoMessage1.setText("");
            }
        } else if (!validEmailMatcher.matches()) {
            clearInfoMessages();
            infoMessage1.setTextFill(Color.RED);
            infoMessage1.setText("This is not a valid email");
        } else {
            Customer newCustomer = new Customer(0, username.getText(), password.getText(), email.getText());
            try {
                infoMessage2.setTextFill(Color.BLACK);
                infoMessage2.setText("");
                infoMessage3.setTextFill(Color.BLACK);
                infoMessage3.setText("");
                createNewCustomer(newCustomer);
                infoMessage1.setTextFill(Color.BLACK);
                infoMessage1.setText("Customer successfully created");
                PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                pause.setOnFinished(e -> {
                    infoMessage1.setText("");
                    username.setText("");
                    password.setText("");
                    email.setText("");
                    goToLogInPage();
                });
                pause.play();
            } catch (CustomerAlreadyExistException e) {
                infoMessage1.setTextFill(Color.RED);
                infoMessage1.setText("Customer already exist");
            }
        }
    }

    public void keyboardListener(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            createAccount();
        }
    }


    public void clearInfoMessages() {
        infoMessage1.setTextFill(Color.BLACK);
        infoMessage1.setText("");
        infoMessage2.setTextFill(Color.BLACK);
        infoMessage2.setText("");
        infoMessage3.setTextFill(Color.BLACK);
        infoMessage3.setText("");

    }

    private boolean isUsernameFieldEmpty() {
        return username.getText().equals("");
    }

    private boolean isPasswordFieldEmpty() {
        return password.getText().equals("");
    }

    private boolean isEmailFieldEmpty() {
        return email.getText().equals("");
    }

}
