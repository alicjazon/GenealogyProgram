/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drzewopostgresql;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ala
 */
public class InsertEditPanelController implements Initializable {
    
    @FXML
    private ComboBox<String> tree_type = new ComboBox<String>();
    
    @FXML
    Label place;
    @FXML
    Label surname;
    @FXML
    TextField surnamefield;
    @FXML
    Label surname2;
    @FXML
    TextField surname2field;
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
    @FXML
    ComboBox gender;
    @FXML
    TextField birthdatef;
    @FXML
    TextField birthplacef;
    @FXML
    TextField deathdatef;
    @FXML
    TextField deathplacef;
    @FXML
    Label birthdate;
    @FXML
    Label deathdate;
    @FXML
    Label birthplace;
    @FXML 
    Label deathplace;
    @FXML
    ComboBox isalive;
    @FXML
    TextField job;
    @FXML
    TextField mother;
    @FXML
    Label mothersurname;
    @FXML
    Label father;
    @FXML
    TextField fathersurname;
    @FXML 
    TextField secondnamef, wedding;
    @FXML
    Label secondname;
    @FXML 
    Text star, star1, star2, star3, star4;
    @FXML
    Button search;
    @FXML
    Text info;
    @FXML
    Button b1;
    @FXML
    Button b2;
    @FXML
    Label type;
    @FXML
    Label trname, mlabel;
    @FXML
    Label alivel;

    private String v1 = "Standardowe drzewo rodziny";
    private String v3 = "Drzewo bóstw z mitologii";
    private String v2 = "Drzewo rodziny królewskiej";
    
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
        
        
        if(tree_type.getValue().equals(v3))
        {
            secondname.setVisible(false);
            secondnamef.setVisible(false);
            star.setVisible(false);
            surname.setVisible(false);
            surname2.setVisible(false);
            surnamefield.setVisible(false);
            surname2field.setVisible(false);
            birthdate.setVisible(false);
            birthplace.setVisible(false);
            deathdate.setVisible(false);
            deathplace.setVisible(false);
            birthdatef.setVisible(false);
            deathdatef.setVisible(false);
            deathplacef.setVisible(false);
            isalive.setVisible(false);
            alivel.setVisible(false);
            deathplace.setVisible(false);
           deathplacef.setVisible(false);
           deathdate.setVisible(false);
           deathdatef.setVisible(false);

        }
        else
        {
            secondname.setVisible(true);
            secondnamef.setVisible(true);
            star.setVisible(true);
            surname.setVisible(true);
            surname2.setVisible(true);
            surnamefield.setVisible(true);
            surname2field.setVisible(true);
            birthdate.setVisible(true);
            birthplace.setVisible(true);
            deathdate.setVisible(true);
            deathplace.setVisible(true);
            birthdatef.setVisible(true);
            birthplacef.setVisible(true);
            deathdatef.setVisible(true);
            deathplacef.setVisible(true);
            
        }
        if(isalive.getValue().equals("Nie"))
        {
        deathdate.setVisible(true);
        deathplace.setVisible(true);
        deathdatef.setVisible(true);
        deathplacef.setVisible(true);
        }
        else
        {
        deathdate.setVisible(false);
        deathplace.setVisible(false);
        deathdatef.setVisible(false);
        deathplacef.setVisible(false);
        }
    }
    String namv;
            String genv, treev, secnamv = "", surv,  sur2v = "",  placv, birtv = "", birtplv ="Brak informacji", 
                    deatv ="", deatplv = "Brak informacji";
            boolean aliv;
            int tree;
    @FXML
    private void handleInsertAction(ActionEvent event) throws IOException {
            
        DBUtil dbaction = new DBUtil();
          String check;  
        if(treefield.getText().equals("") || namefield.getText().equals("") || placefield.getText().equals(""))
        {
            info.setText("Wypełnij wszystkie wymagane pola!");
        }
        else if(!dbaction.checkTree(treefield.getText()).equals("admin") && 
                !dbaction.checkTree(treefield.getText()).equals(LoginPageController.getUser()))
        {
            info.setText("Nie możesz edytować tego drzewa");
        }
        else if((!birthdatef.getText().equals("") && dbaction.checkDate(birthdatef.getText()) == false) 
                || (!deathdatef.getText().equals("") && dbaction.checkDate(deathdatef.getText()) == false) ||
                (dbaction.isPossible(birthdatef.getText(), deathdatef.getText()) == false && !birthdatef.getText().equals("")
                && !deathdatef.getText().equals("") ) )
        {
           info.setText("Nieprawidłowa wartość w polu data!");     
        }
        else{
            if(birthplacef.getText().equals(""))
                birtplv = "Brak informacji";
            String deatplv = deathplacef.getText();
            if(deathplacef.getText().equals(""))
                deatplv = "Brak informacji";
            
            namv = namefield.getText();
            namefield.clear();
             treev = treefield.getText();
             if(!secondnamef.getText().equals("")){
                secnamv = secondnamef.getText();
                secondnamef.clear();
             }
            surv = surnamefield.getText();
            surnamefield.clear();
            if(!surname2field.getText().equals("")){
                sur2v = surname2field.getText();
                surname2field.clear();
            }
            placv = placefield.getText();
            placefield.clear();
            if(!birthdatef.getText().equals("")){
                birtv = birthdatef.getText();
                birthdatef.clear();
            }
            if(!birthplacef.getText().equals("")){
                birtplv = birthplacef.getText();
                birthplacef.clear();
            }
            if(!deathdatef.getText().equals(""))
            {
             deatv = deathdatef.getText();
             deathdatef.clear();
            }
            if(!deathplacef.getText().equals(" "))
            {
                deatplv = deathplacef.getText();
                deathplacef.clear();
            }
            
            aliv = true;
            if(isalive.getValue().equals("Nie"))
                aliv = false;
            
        surnamefield.setVisible(true);
            surname.setVisible(true);
            if(tree_type.getValue().equals(v3))
        {
           surname2field.setVisible(false);
           mothersurname.setVisible(false);
           tree = 3;
           namefield.setVisible(false);
           wedding.setVisible(false);
           deathplace.setVisible(false);
           deathplacef.setVisible(false);
        }
            else
            {
                 deathplacef.setVisible(true);
                 deathplace.setVisible(true);
                 wedding.setVisible(true);
                 wedding.setPromptText("RRRR-MM-DD");
            }
        mother.setVisible(true);
        gender.setVisible(false);
        
        b2.setVisible(true);
        tree_type.setVisible(false);
        type.setVisible(false);
        treefield.setVisible(false);
        trname.setText("Zawód");
        mlabel.setText("Imię matki");
        mothersurname.setText("Nazwisko");
        secondname.setVisible(false);
        star.setVisible(false);
        star1.setVisible(false);
        star2.setVisible(false);
        star3.setVisible(false);
        star4.setVisible(false);
        info.setVisible(false);
        surname.setText("Imię ojca");
        surname2.setText("Nazwisko"); 
        placefield.setVisible(false);
        birthdatef.setVisible(false);
        birthplace.setVisible(false);
        place.setVisible(false);
        birthdate.setVisible(false);
        birthplace.setVisible(false);
        isalive.setVisible(false);
        alivel.setText("Data ślubu");
        job.setVisible(true);
        treefield.setVisible(false);
        deathdatef.setVisible(true);      
        deathdate.setVisible(true);
        deathdate.setText("Imię męża / żony");
        deathdatef.setPromptText("");
        deathplace.setText("Nazwisko męża / żony");
        secondnamef.setVisible(false);
        
        
        if(tree_type.getValue().equals(v2))
        {    placefield.setVisible(true);
        birthdatef.setVisible(true);
        birthplace.setVisible(true);
        place.setVisible(true);
        birthdate.setVisible(true);
        birthplace.setVisible(true);
        birthdate.setText("Początek panowania");
        birthplace.setText("Koniec panowania");
        birthplacef.setPromptText("RRRR-MM-DD");
        place.setText("Kraj panowania");
        tree = 2;
             
        }
    
        else{
                placefield.setVisible(false);
        birthdatef.setVisible(false);
        birthplace.setVisible(false);
        place.setVisible(false);
        birthdate.setVisible(false);
        birthplace.setVisible(false);
        birthplacef.setVisible(false);
        }  
         if(tree_type.getValue().equals(v1))
             tree = 1;
    }
    }
    
    @FXML
    private void handleInsertMoreAction(ActionEvent event) throws IOException, ClassNotFoundException {
                DBUtil dbaction = new DBUtil();
                int gen;
                if(gender.getValue().equals("Kobieta"))
                    gen = 1;
                else if(gender.getValue().equals("Mężczyzna"))
                    gen = 2;
                else
                    gen = 3;
                String jobv, mothv, msurv, fathv, fsurv, wedv, begin, end;
                if(job.getText().equals(""))
                    jobv = "";
                else
                    jobv = job.getText();
                if(mother.getText().equals(""))
                    mothv = "";
                else
                    mothv = mother.getText();
                if(namefield.getText().equals(""))
                    msurv = "";
                else
                    msurv = namefield.getText();
                if(surnamefield.getText().equals(""))
                    fathv = "";
                else
                    fathv = surnamefield.getText();
                if(surname2field.getText().equals(""))
                    fsurv = "";
                else
                    fsurv = surname2field.getText();
                if(wedding.getText().equals(""))
                    wedv = "";
                else
                    wedv = wedding.getText();
                if(birthplacef.getText().equals(""))
                    end = "";
                else
                    end = birthplacef.getText();
                if(birthdatef.getText().equals(""))
                    begin = "";
                else
                    begin = birthdatef.getText();
                
                int result = 0;
                if(!tree_type.getValue().equals(v3)){
                if(dbaction.checkRecord(namv, secnamv, treev, tree, mothv, msurv, fathv, fsurv) == 0)
                      result = dbaction.newRecord(namv, secnamv, gen, treev, birtplv, deatplv, jobv, placv, mothv, msurv, fathv, fsurv, 
               birtv, deatv, aliv, surv, tree);
                else
                   result = dbaction.updateRecord(namv, secnamv, gen, treev, birtplv, deatplv, jobv, placv, mothv, msurv, fathv, fsurv, 
               birtv, deatv, aliv, surv, tree);
                
                if(!(deathdatef.getText().equals("") && deathplacef.getText().equals("")))
                {
                    result = dbaction.insertMarriage(namv, surv, deathdatef.getText(),
                            deathplacef.getText(), wedv, treev, tree, gen);
                }
                
                if(!placefield.getText().equals(""))
                {
                    result = dbaction.insertKing(namv, surv, placefield.getText(),birthdatef.getText(), birthplacef.getText(),
                            treev, tree, gen);
                }
                }
                else
                {
                 result = dbaction.newGod(namv, gen, treev, jobv, placv, mothv, fathv, tree);
                 if(!(deathdatef.getText().equals("")))
                {
                    result = dbaction.insertMarriageGod(namv, deathdatef.getText(),
                            treev, tree, gen);
                }
                         }
                
                if(result == 0)
                {
                    info.setVisible(true);
                    info.setText("Wystąpił błąd");
                }
                else
                {
                    info.setVisible(true);
                    info.setText("Zmiany zostały wprowadzone");
                }
    
                System.out.println("urodz" + birtv); 
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        javafx.collections.ObservableList <String> TreeTypeList = FXCollections.observableArrayList("Standardowe drzewo rodziny", "Drzewo rodziny królewskiej", "Drzewo bóstw z mitologii");
        tree_type.setItems(TreeTypeList);
        tree_type.getSelectionModel().selectFirst();
        javafx.collections.ObservableList <String> GenderList = FXCollections.observableArrayList("Kobieta", "Mężczyzna", "Nieokreślona");
        gender.setItems(GenderList);
        gender.getSelectionModel().selectFirst();
        javafx.collections.ObservableList <String> BooleanList = FXCollections.observableArrayList("Tak", "Nie");
        isalive.setItems(BooleanList);
        isalive.getSelectionModel().selectFirst();
        mother.setVisible(false);
        deathdate.setVisible(false);
        deathplace.setVisible(false);
        deathdatef.setVisible(false);
        deathplacef.setVisible(false);
        b2.setVisible(false);
        job.setVisible(false);
        wedding.setVisible(false);
        

    }    
    
}
