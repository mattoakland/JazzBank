package com.example.jazzbank.Controllers.Admin;

import com.example.jazzbank.Models.Model;
import com.example.jazzbank.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) ->{
            switch (newVal){
                case Clients -> admin_parent.setCenter(Model.getInstance().getViewFactory().getManageClientsView());
                case Deposit -> admin_parent.setCenter(Model.getInstance().getViewFactory().getDepositView());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateClientView());
            }
        });
    }
}
