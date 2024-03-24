module com.example.jazzbank {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;



    opens com.example.jazzbank to javafx.fxml;
    exports com.example.jazzbank;
    exports com.example.jazzbank.Controllers;
    exports com.example.jazzbank.Controllers.Admin;
    exports com.example.jazzbank.Controllers.Client;
    exports com.example.jazzbank.Models;
    exports com.example.jazzbank.Views;

}