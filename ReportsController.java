/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drzewopostgresql;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ala
 */
public class ReportsController implements Initializable {

    private String v1 = "Lista wszystkich drzew";
    private String v2 = "Lista wszystkich Marianów w bazie";
    private String v3 = "Średni i maksymalny wiek życia w danym drzewie";
    private String v4 = "Lista władców panujących w XVII w.";
    private String v5 = "Lista wszystkich małżeństw zawartych po 1900 roku";
    private String v6 = "Lista wszystkich bogów związanych z morzem";
    private String v7 = "Lista nazwisk, które noszą przynajmniej dwie osoby";
    
    
    @FXML
    private ComboBox<String> report = new ComboBox<String>();
    @FXML
    TableView table;
    
    @FXML
    private void handleMenuButtonAction(ActionEvent event) throws IOException {

         Parent menu_page_parent;
        menu_page_parent = FXMLLoader.load(getClass().getResource("MainMenuLogged.fxml"));
        Scene menu_scene = new Scene(menu_page_parent);
        Stage menu_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        menu_stage.setScene(menu_scene);
        menu_stage.show();
    }
    @FXML
    private void handleSearchAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        table = new TableView();
        DBUtil dbaction = new DBUtil();
        
        dbaction.reports(table, report.getValue());
        
        Scene scene = new Scene(table);        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(800);
        stage.show();
        
        
    }
    @FXML
    Text number;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       javafx.collections.ObservableList <String> TreeTypeList = FXCollections.observableArrayList(v1,v2,v3,v4,v5,v6, v7);
        report.setItems(TreeTypeList);
        DBUtil dbaction = new DBUtil();
        int ile = dbaction.count();
        number.setText("Liczba wszystkich osób w bazie: " + ile);
    }    
    
}
