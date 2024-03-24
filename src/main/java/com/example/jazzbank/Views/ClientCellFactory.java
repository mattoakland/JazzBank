package com.example.jazzbank.Views;

import com.example.jazzbank.Controllers.Admin.ClientCellController;
import com.example.jazzbank.Controllers.Client.TransactionCellController;
import com.example.jazzbank.Models.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ClientCellFactory extends ListCell<Client> {
    @Override
    protected void updateItem(Client client, boolean empty) {
        super.updateItem(client, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        } else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/ClientCell.fxml"));
            ClientCellController controller = new ClientCellController(client);
            loader.setController(controller);
            setText(null);
            try{
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
