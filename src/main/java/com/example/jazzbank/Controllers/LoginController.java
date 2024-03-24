package com.example.jazzbank.Controllers;

import com.example.jazzbank.Models.Model;
import com.example.jazzbank.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox acc_selector;
    public Label payee_address_label;
    public TextField payee_address_field;
    public Label password_label;
    public TextField password_field;
    public Label error_label;
    public Button login_button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> setAcc_selector());
        login_button.setOnAction(event -> onLogin());
    }

    private void onLogin(){
        Stage stage = (Stage) error_label.getScene().getWindow(); // trick to get the stage for login scene
        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT){
            //evaluate login credentials
            Model.getInstance().evaluateClientCred(payee_address_field.getText(),password_field.getText());
            if(Model.getInstance().getClientLoginSuccessFlag()){
                Model.getInstance().getViewFactory().showClientWindow();
                //Close the login window
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                payee_address_field.setText("");
                password_field.setText("");
                error_label.setText("Authentication Failed.");
            }
        } else if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.ADMIN){
            Model.getInstance().evaluateAdminCred(payee_address_field.getText(),password_field.getText());
            if(Model.getInstance().getAdminLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showAdminWindow();
                //Close the login window
                Model.getInstance().getViewFactory().closeStage(stage);
            }else {
                payee_address_field.setText("");
                password_field.setText("");
                error_label.setText("Authentication Failed.");
            }
        }

    }

    private void setAcc_selector(){
        Model.getInstance().getViewFactory().setLoginAccountType((AccountType) acc_selector.getValue());
        //change display labels for client/admin accordingly
        if(acc_selector.getValue() == AccountType.ADMIN){
            payee_address_label.setText("Username:");
        }else{
            payee_address_label.setText("Payee Address:");
        }
    }

}
