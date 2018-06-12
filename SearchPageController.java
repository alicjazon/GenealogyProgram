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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Ala
 */
public class SearchPageController implements Initializable {
    
    @FXML
    Label place;
    @FXML
    Label surname;
    @FXML
    TextField surnamefield;
    @FXML
    Label year;
    @FXML
    TextField yearfield;
    @FXML
    TableView table;
    @FXML
    TextField namefield;
    @FXML
    TextField placefield;
    @FXML
    TextField treefield;

    private String v1 = "Standardowe drzewo rodziny";
    private String v3 = "Drzewo bóstw z mitologii";
    private String v2 = "Drzewo rodziny królewskiej";
    
    
    @FXML
    private ComboBox<String> tree_type = new ComboBox<String>();
    
    @FXML
    private void handleMenuButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
         Parent menu_page_parent;
        if(LoginPageController.getUser().equals(""))
           menu_page_parent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        else
            menu_page_parent = FXMLLoader.load(getClass().getResource("MainMenuLogged.fxml"));
        Scene menu_scene = new Scene(menu_page_parent);
        Stage menu_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        menu_stage.setScene(menu_scene);
        menu_stage.show();
    }
    
    @FXML
    private void handleComboBoxAction(ActionEvent event) throws IOException {
        
        if(tree_type.getValue().equals(v1))
        {
            place.setText("Miejsce urodzenia");
            surname.setVisible(true);
            surnamefield.setVisible(true);
            year.setVisible(true);
            yearfield.setVisible(true);
            
        }
        
        else if(tree_type.getValue().equals(v3))
        {
            place.setText("Państwo");
            surname.setVisible(false);
            surnamefield.setVisible(false);
            year.setVisible(false);
            yearfield.setVisible(false);
        }
        else
        {
            place.setText("Miejsce urodzenia");
            surname.setVisible(true);
            surnamefield.setVisible(true);
            year.setVisible(true);
            yearfield.setVisible(true);
            
        }
    }
    
    @FXML
    private void handleSearchAction(ActionEvent event) throws IOException, SQLException {
        table = new TableView();
        DBUtil dbaction = new DBUtil();
        
        String namesearch = "", surnsearch = "", datesearch = "", placesearch = "", treesearch ="";
        
        if(!(namefield.getText().equals("") && surnamefield.getText().equals("") && yearfield.getText().equals("")
                && placefield.getText().equals("") && treefield.getText().equals("")) ){
        if(namefield.getText().equals(""))
            namesearch = "%";
        else
            namesearch = namefield.getText();
          
        if(surnamefield.getText().equals(""))
            surnsearch = "%";
        else
            surnsearch = surnamefield.getText();
        
        if(yearfield.getText().equals(""))
            datesearch = "%";
        else
            datesearch = yearfield.getText() + "%";
        
        if(placefield.getText().equals(""))
            placesearch = "%";
        else
            placesearch = placefield.getText();
        
        if(treefield.getText().equals(""))
            treesearch = "%";
        else
            treesearch = treefield.getText();
        }
        
        if(tree_type.getValue() == v3)
            dbaction.searchgod(table, namesearch, placesearch, treesearch, 1);
        else if(tree_type.getValue() == v2)
            dbaction.search(table, namesearch, surnsearch, placesearch, datesearch, treesearch, 2, 1);
        else
            dbaction.search(table, namesearch, surnsearch, placesearch, datesearch, treesearch, 1, 1);
        
      
        Scene scene = new Scene(table);        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(800);
        namefield.clear();
        surnamefield.clear();
        placefield.clear();
        yearfield.clear();
        treefield.clear();
        stage.show();
        
        
    }
    
    @FXML
    private void handleSearchMoreAction(ActionEvent event) throws IOException, SQLException {
        table = new TableView();
        DBUtil dbaction = new DBUtil();
        
        String namesearch = "", surnsearch = "", datesearch = "", placesearch = "", treesearch ="";
        
        if(!(namefield.getText().equals("") && surnamefield.getText().equals("") && yearfield.getText().equals("")
                && placefield.getText().equals("") && treefield.getText().equals("")) ){
        if(namefield.getText().equals(""))
            namesearch = "%";
        else
            namesearch = namefield.getText();
          
        if(surnamefield.getText().equals(""))
            surnsearch = "%";
        else
            surnsearch = surnamefield.getText();
        
        if(yearfield.getText().equals(""))
            datesearch = "%";
        else
            datesearch = yearfield.getText() + "%";
        
        if(placefield.getText().equals(""))
            placesearch = "%";
        else
            placesearch = placefield.getText();
        
        if(treefield.getText().equals(""))
            treesearch = "%";
        else
            treesearch = treefield.getText();
        }
        if(tree_type.getValue() == v3)
            dbaction.searchgod(table, namesearch, placesearch, treesearch, 2);
        else if(tree_type.getValue() == v2)
            dbaction.search(table, namesearch, surnsearch, placesearch, datesearch, treesearch, 2, 2);
        else
            dbaction.search(table, namesearch, surnsearch, placesearch, datesearch, treesearch, 1, 2);
        
      
        Scene scene = new Scene(table);        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(800);
        namefield.clear();
        surnamefield.clear();
        placefield.clear();
        yearfield.clear();
        treefield.clear();
        stage.show();
        
        
    }
    
    @FXML
    private void handleSearchParentAction(ActionEvent event) throws IOException, SQLException {
        table = new TableView();
        DBUtil dbaction = new DBUtil();
        
        String namesearch = "", surnsearch = "", datesearch = "", placesearch = "", treesearch ="";
        
        if(!(namefield.getText().equals("") && surnamefield.getText().equals("") && yearfield.getText().equals("")
                && placefield.getText().equals("") && treefield.getText().equals("")) ){
        if(namefield.getText().equals(""))
            namesearch = "%";
        else
            namesearch = namefield.getText();
          
        if(surnamefield.getText().equals(""))
            surnsearch = "%";
        else
            surnsearch = surnamefield.getText();
        
        if(yearfield.getText().equals(""))
            datesearch = "%";
        else
            datesearch = yearfield.getText() + "%";
        
        if(placefield.getText().equals(""))
            placesearch = "%";
        else
            placesearch = placefield.getText();
        
        if(treefield.getText().equals(""))
            treesearch = "%";
        else
            treesearch = treefield.getText();
        }
        
        if(tree_type.getValue() == v3)
            dbaction.searchparent(table, namesearch, surnsearch, treesearch, 1);
        else 
            dbaction.searchparent(table, namesearch, surnsearch, treesearch, 2);

        Scene scene = new Scene(table);        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(800);
        namefield.clear();
        surnamefield.clear();
        placefield.clear();
        yearfield.clear();
        treefield.clear();
        stage.show();
        
        
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList <String> TreeTypeList = FXCollections.observableArrayList("Standardowe drzewo rodziny", "Drzewo rodziny królewskiej", "Drzewo bóstw z mitologii");
        tree_type.setItems(TreeTypeList);
        tree_type.getSelectionModel().selectFirst();
        table.setVisible(false);

        
    }
    
}

