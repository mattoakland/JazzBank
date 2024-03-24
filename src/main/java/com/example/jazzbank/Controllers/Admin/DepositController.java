package com.example.jazzbank.Controllers.Admin;

import com.example.jazzbank.Models.Client;
import com.example.jazzbank.Models.Model;
import com.example.jazzbank.Views.ClientCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {

    public TextField payeeAddress_field;
    public Button search_button;
    public ListView<Client> result_listview;
    public TextField amount_field;
    public Button deposit_button;

    private Client client;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_button.setOnAction(e->onClientSearch());
        deposit_button.setOnAction(e->onDeposit());
    }

    private void onClientSearch(){
        ObservableList<Client> searchResults = Model.getInstance().searchClient(payeeAddress_field.getText());
        result_listview.setItems(searchResults);
        result_listview.setCellFactory(e -> new ClientCellFactory());
        client = searchResults.getFirst();
    }
    private void onDeposit(){
        double amount = Double.parseDouble(amount_field.getText());
        double newBalance = amount + client.savingsAccountProperty().get().balanceProperty().get();
        if(amount_field.getText() != null){
            Model.getInstance().getDatabaseDriver().depositSavings(client.pAddressProperty().get(), newBalance);
        }
        emptyFields();
    }
    private void emptyFields(){
        payeeAddress_field.setText("");
        amount_field.setText("");
    }
}
