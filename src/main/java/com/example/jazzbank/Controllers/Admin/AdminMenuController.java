package com.example.jazzbank.Controllers.Admin;

import com.example.jazzbank.Models.Model;
import com.example.jazzbank.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {

    public Button create_client_button;
    public Button client_list_button;
    public Button deposit_button;
    public Button logout_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners(){
        create_client_button.setOnAction(event -> onCreateClient());
        client_list_button.setOnAction(event -> onClientList());
        deposit_button.setOnAction(event -> onDeposit());
        logout_button.setOnAction(event -> onLogout());
    }
    private void onCreateClient(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.Create_Client);
    }

    private void onClientList(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.Clients);
    }

    private void onDeposit() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.Deposit);
    }
    private void onLogout(){
        //get stage
        Stage stage = (Stage) client_list_button.getScene().getWindow();
        //close the client window
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().setAdminLoginSuccessFlag(false);
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
