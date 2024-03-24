package com.example.jazzbank.Controllers.Admin;

import com.example.jazzbank.Models.Model;
import com.example.jazzbank.Views.AccountType;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {

    public TextField firstName_field;
    public TextField lastName_field;
    public TextField password_field;
    public CheckBox payee_address_box;
    public Label payee_address_label;
    public CheckBox box_checkingAcc;
    public TextField checking_amount_field;
    public CheckBox box_savingsAcc;
    public TextField savings_amount_field;
    public Button create_client_button;
    public Label error_label;
    private String payeeAddress;
    private boolean createCheckingAccountFlag = false;
    private boolean createSavingsAccountFlag = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        error_label.setText("");
        create_client_button.setOnAction(event->createClient());
        payee_address_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
           if(newVal){
               payeeAddress = createPayeeAddress();
               onCreatePayeeAddress();
           }
        });
        box_checkingAcc.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if(newVal){
                createCheckingAccountFlag = true;
            }
        });
        box_savingsAcc.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if(newVal){
                createSavingsAccountFlag = true;
            }
        });
    }
    private void createClient(){
        if(createCheckingAccountFlag){
            createAccount("Checking");
        }
        if(createSavingsAccountFlag){
            createAccount("Savings");
        }
        //Create Client
        String fName = firstName_field.getText();
        String lName = lastName_field.getText();
        String password = password_field.getText();
        Model.getInstance().getDatabaseDriver().createClient(fName,lName,payeeAddress,password, LocalDate.now());
        error_label.setStyle("fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
        error_label.setText("Client Successfully Created");
        emptyFields();
    }
    private void createAccount(String accountType){
        double balance = Double.parseDouble(checking_amount_field.getText());
        //Generate Account Number
        String firstSection = "1001";
        String lastSection = Integer.toString((new Random().nextInt(9999)+1000));
        String accountNumber = firstSection + " " + lastSection;
        //Create the checking account
        if(accountType.equals("Checking")){
            Model.getInstance().getDatabaseDriver().createCheckingAccount(payeeAddress,accountNumber,10,balance);
        } else {
            Model.getInstance().getDatabaseDriver().createSavingsAccount(payeeAddress,accountNumber,2000,balance);
        }
    }
    private String createPayeeAddress(){
        int id = Model.getInstance().getDatabaseDriver().getLastClientID() + 1;
        char fChar = Character.toLowerCase(firstName_field.getText().charAt(0));
        return "@" + fChar + lastName_field.getText() + id;
    }
    private void onCreatePayeeAddress(){
        if(firstName_field.getText() != null & lastName_field.getText()!= null){
            payee_address_label.setText(payeeAddress);
        }
    }
    private void emptyFields(){
        firstName_field.setText("");
        lastName_field.setText("");
        password_field.setText("");
        payee_address_box.setText("");
        payee_address_label.setText("");
        box_checkingAcc.setSelected(false);
        checking_amount_field.setText("");
        box_savingsAcc.setSelected(false);
        savings_amount_field.setText("");
    }
}
