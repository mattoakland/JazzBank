package com.example.jazzbank.Controllers.Client;

import com.example.jazzbank.Models.Model;
import com.example.jazzbank.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_button;
    public Button transaction_button;
    public Button account_button;
    public Button profile_button;
    public Button logout_button;
    public Button report_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners(){
        dashboard_button.setOnAction(event -> onDashboard());
        transaction_button.setOnAction(event -> onTransactions());
        account_button.setOnAction(event -> onAccounts());
        logout_button.setOnAction(event -> onLogout());
    }

    private void onDashboard(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }

    private void onTransactions(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);
    }

    private void onAccounts() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }

    private void onLogout(){
        //get stage
        Stage stage = (Stage) dashboard_button.getScene().getWindow();
        //close the client window
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().setClientLoginSuccessFlag(false);
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
