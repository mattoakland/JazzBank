package com.example.jazzbank.Controllers.Client;

import com.example.jazzbank.Models.Model;
import com.example.jazzbank.Models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {

    public FontAwesomeIconView in_icon;
    public FontAwesomeIconView out_icon;
    public Label trans_date_label;
    public Label sender_label;
    public Label receiver_label;
    public Label trans_amount_label;
    public Button message_button;
    private final Transaction transaction;

    public TransactionCellController(Transaction transaction){
        this.transaction = transaction;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sender_label.textProperty().bind(transaction.senderProperty());
        receiver_label.textProperty().bind(transaction.receiverProperty());
        trans_amount_label.textProperty().bind(transaction.amountProperty().asString());
        trans_date_label.textProperty().bind(transaction.dateProperty().asString());
        message_button.setOnAction(event -> Model.getInstance().getViewFactory().showMessageWindow(transaction.senderProperty().get(),transaction.messageProperty().get()));
        transactionIcons();
    }

    private void transactionIcons(){
        if(transaction.senderProperty().get().equals(Model.getInstance().getClient().pAddressProperty().get())){
            in_icon.setFill(Color.rgb(240,240,240));
            out_icon.setFill(Color.RED);
        } else {
            in_icon.setFill(Color.GREEN);
            out_icon.setFill(Color.rgb(240,240,240));
        }
    }
}
