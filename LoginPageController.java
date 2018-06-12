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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ala
 */
public class LoginPageController implements Initializable {
    
    private static String userLogged = "";
    
    public static void changeUser(String name)
    {
        userLogged = name;
    }
    
    public static String getUser()
    {
        return userLogged;
    }
    
    @FXML
    TextField login;
    @FXML
    PasswordField pass;
    @FXML
    Label wronglogin;
    @FXML
    Label wrongemail;
    @FXML
    Label emptyfield;
    
    @FXML
    private void handleMenuButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent menu_page_parent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene menu_scene = new Scene(menu_page_parent);
        Stage menu_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        menu_stage.setScene(menu_scene);
        menu_stage.show();
    }
    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException, SQLException {
        
        if(login.getText().equals("") || pass.getText().equals(""))
        {
            emptyfield.setText("Wypełnij wszystkie pola");
        }
        else{  
         emptyfield.setText(" ");
         DBUtil dbaction = new DBUtil();
         int check1 = dbaction.checkLogin(login.getText(), pass.getText());
         
         if(check1 == 1)
        {
            wrongemail.setText("");
            wronglogin.setText("Podany login nie istnieje w bazie");
        }
        if(check1 == 2)
        {
           wronglogin.setText("");
           wrongemail.setText("Podane hasło jest nieprawidłowe");
        }
        if(check1 == 0)
        {
            wrongemail.setText("");
            wronglogin.setText("");
            userLogged = login.getText();
            Parent login_page_parent = FXMLLoader.load(getClass().getResource("MainMenuLogged.fxml"));
        Scene login_scene = new Scene(login_page_parent);
        Stage login_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); 

        login_stage.setScene(login_scene);
        login_stage.show(); 
        }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
