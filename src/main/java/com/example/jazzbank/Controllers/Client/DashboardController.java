package com.example.jazzbank.Controllers.Client;

import com.example.jazzbank.Models.Model;
import com.example.jazzbank.Models.Transaction;
import com.example.jazzbank.Views.TransactionCellFactory;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable{
    public Text user_name;
    public Label login_date;
    public Label checking_balance;
    public Label checking_acc_num;
    public Label savings_balance;
    public Label savings_acc_num;
    public Label income_label;
    public Label expense_label;
    public ListView<Transaction> transaction_listview;
    public TextField payee_field;
    public TextField amount_field;
    public TextArea message_field;
    public Button send_money_button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindData();
        initLatestTransactions();
        transaction_listview.setItems(Model.getInstance().getLatestTransactions());
        transaction_listview.setCellFactory(e -> new TransactionCellFactory());
        send_money_button.setOnAction(event -> onSendMoney());
        accountSummary();
    }
    private void bindData(){
        user_name.textProperty().bind(Bindings.concat("Hi, ").concat(Model.getInstance().getClient().firstNameProperty()));
        login_date.setText("Today, "+ LocalDate.now());
        checking_balance.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().balanceProperty().asString());
        checking_acc_num.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().accountNumberProperty());
        savings_balance.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().balanceProperty().asString());
        savings_acc_num.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().accountNumberProperty());
    }
    private void initLatestTransactions(){
        if(Model.getInstance().getLatestTransactions().isEmpty()){
            Model.getInstance().setLatestTransactions();
        }
    }

    private void onSendMoney(){
        String receiver = payee_field.getText();
        double amount = Double.parseDouble(amount_field.getText());
        String message = message_field.getText();
        String sender = Model.getInstance().getClient().pAddressProperty().get();
        ResultSet resultSet = Model.getInstance().getDatabaseDriver().searchClient(receiver);
        try{
            if(resultSet.isBeforeFirst()){
                Model.getInstance().getDatabaseDriver().updateBalance(receiver,amount,"ADD");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //Subtract from sender's savings account in database
        Model.getInstance().getDatabaseDriver().updateBalance(sender,amount,"SUB");
        //Update savings account in the client
        Model.getInstance().getClient().savingsAccountProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getSavingsAccountBalance(sender));
        //record new transaction
        Model.getInstance().getDatabaseDriver().newTransaction(sender,receiver,amount,message);
        //clear the fields
        payee_field.setText("");
        amount_field.setText("");
        message_field.setText("");
    }

    //Method calculating all income and expenses
    private void accountSummary(){
        double income = 0;
        double expenses = 0;
        if(Model.getInstance().getAllTransactions().isEmpty()){
            Model.getInstance().setAllTransactions();
        }
        for(Transaction transaction: Model.getInstance().getAllTransactions()){
            if(transaction.senderProperty().get().equals(Model.getInstance().getClient().pAddressProperty().get())){
                expenses += transaction.amountProperty().get();
            }else {
                income += transaction.amountProperty().get();
            }
        }
        income_label.setText("+ $" + income);
        expense_label.setText("- $" + expenses);
    }
}
