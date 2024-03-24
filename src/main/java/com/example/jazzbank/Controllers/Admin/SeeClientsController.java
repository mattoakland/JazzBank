package com.example.jazzbank.Controllers.Admin;

import com.example.jazzbank.Models.Client;
import com.example.jazzbank.Models.Model;
import com.example.jazzbank.Views.ClientCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class SeeClientsController implements Initializable {

    public ListView<Client> clients_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClientsList();
        clients_listview.setItems(Model.getInstance().getClients());
        clients_listview.setCellFactory(e -> new ClientCellFactory());
    }

    //initialise listview for clients
    private void initClientsList(){
        if(Model.getInstance().getClients().isEmpty()){
            Model.getInstance().setClients();
        }
    }
}
