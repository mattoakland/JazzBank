package com.example.jazzbank.Controllers.Admin;

import com.example.jazzbank.Models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label fName_label;
    public Label LName_label;
    public Label pAddress_label;
    public Label check_acc_label;
    public Label saving_acc_label;
    public Label date_label;
    public Button delete_button;

    private final Client client;

    public ClientCellController(Client client){
        this.client=client;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fName_label.textProperty().bind(client.firstNameProperty());
        LName_label.textProperty().bind(client.lastNameProperty());
        pAddress_label.textProperty().bind(client.pAddressProperty());
        check_acc_label.textProperty().bind(client.checkingAccountProperty().asString());
        saving_acc_label.textProperty().bind(client.savingsAccountProperty().asString());
        date_label.textProperty().bind(client.dateProperty().asString());
    }
}
