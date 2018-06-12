/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drzewopostgresql;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ala
 */
public class MainMenuLoggedController implements Initializable {
    
    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws IOException {
        
        Parent search_page_parent = FXMLLoader.load(getClass().getResource("SearchPage.fxml"));
        Scene search_scene = new Scene(search_page_parent);
        Stage search_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        search_stage.setScene(search_scene);
        search_stage.show();
    }
    
    @FXML
    private void handleInsertButtonAction(ActionEvent event) throws IOException {
        
        Parent search_page_parent = FXMLLoader.load(getClass().getResource("InsertEditPanel.fxml"));
        Scene search_scene = new Scene(search_page_parent);
        Stage search_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        search_stage.setScene(search_scene);
        search_stage.show();
    }
    
    @FXML
    private void handleLogoutButtonAction(ActionEvent event) throws IOException {
        
        LoginPageController.changeUser("");
        
        Parent search_page_parent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene search_scene = new Scene(search_page_parent);
        Stage search_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        search_stage.setScene(search_scene);
        search_stage.show();
    }
    
    @FXML
    private void handleRaportButtonAction(ActionEvent event) throws IOException {
       
        Parent search_page_parent = FXMLLoader.load(getClass().getResource("Reports.fxml"));
        Scene search_scene = new Scene(search_page_parent);
        Stage search_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        search_stage.setScene(search_scene);
        search_stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
