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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Ala
 */
public class MainMenuController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException, SQLException {
        System.out.println("You clicked me!");
        Parent login_page_parent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene login_scene = new Scene(login_page_parent);
        Stage login_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); 

        login_stage.setScene(login_scene);
        login_stage.show(); 
    }
    
     @FXML
    private void handleSearchButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent search_page_parent = FXMLLoader.load(getClass().getResource("SearchPage.fxml"));
        Scene search_scene = new Scene(search_page_parent);
        Stage search_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        search_stage.setScene(search_scene);
        search_stage.show();
    }
    
    @FXML
    private void handleRegisterButtonAction(ActionEvent event) throws IOException, SQLException {
        System.out.println("You clicked me!");
        Parent register_page_parent = FXMLLoader.load(getClass().getResource("RegisterPage.fxml"));
        Scene register_scene = new Scene(register_page_parent);
        Stage register_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); 

        register_stage.setScene(register_scene);
        register_stage.show(); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
