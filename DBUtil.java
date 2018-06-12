/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drzewopostgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 *
 * @author Ala
 */
public class DBUtil {
    
    private static final String url = "jdbc:postgresql://localhost/drzewosql";
    private static final String user = "udrzewo";
    private static final String password = "udrzewo";
    
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Połączenie z serwerem PostgreSQL przebiegło pomyślnie.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    } 
    
    public void newUser(String login, String email, String pass) throws ClassNotFoundException
    {
        String SQL = "INSERT INTO Uzytkownik VAlUES (DEFAULT,?,?,?);";

        try (Connection conn = connect();
                PreparedStatement statm = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
 
            statm.setString(1, login);
            statm.setString(2, email);
            statm.setString(3, pass);
            
            statm.executeUpdate();
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public int checkUser(String login, String email)
    {
        String check_login = "SELECT NowyUzytkownik (?,?);";
        
        try (Connection conn = connect();
                PreparedStatement login_stm = conn.prepareStatement(check_login,
                Statement.RETURN_GENERATED_KEYS)) {
 
            login_stm.setString(1, login);
            login_stm.setString(2, email);
            
            ResultSet check1 = login_stm.executeQuery();
            check1.next();
            return check1.getInt(1);
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 1;
        
    }

   public int checkLogin(String login, String pass)
    {
        String check_login = "SELECT Logowanie (?,?);";
        
        try (Connection conn = connect();
                PreparedStatement login_stm = conn.prepareStatement(check_login,
                Statement.RETURN_GENERATED_KEYS)) {
 
            login_stm.setString(1, login);
            login_stm.setString(2, pass);
            
            ResultSet check1 = login_stm.executeQuery();
            check1.next();
            return check1.getInt(1);
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 1;
        
    }

        public boolean checkDate(String date)
    {
        String SQL1 = "SELECT SprawdzDate(?);";
        
        try (Connection conn = connect();
                PreparedStatement stm1 = conn.prepareStatement(SQL1,
                Statement.RETURN_GENERATED_KEYS)) {
 
            stm1.setString(1, date);            
            ResultSet check1 = stm1.executeQuery();
            check1.next();
                return check1.getBoolean(1);
    
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
        
    }
        public boolean isPossible(String date, String date2)
    {
        String SQL = "SELECT LiczWiek(?,?);";
        
        try (Connection conn = connect();
                PreparedStatement stm1 = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
 
             stm1.setString(1, date);
             stm1.setString(2, date2);
            
              ResultSet check1 = stm1.executeQuery();
            check1.next();
                if(check1.getInt(1) < 0)
                return false;
     
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
        
    }
       public String checkTree(String tree)
    {
        String check_date = "SELECT SzukajLogin(?);";
        
        try (Connection conn = connect();
                PreparedStatement login_stm = conn.prepareStatement(check_date,
                Statement.RETURN_GENERATED_KEYS)) {
 
            login_stm.setString(1, tree);
            
            ResultSet check1 = login_stm.executeQuery();
            check1.next();
            String result;
            if(check1.getString(1) == null)
                result = LoginPageController.getUser();
            else
                result = check1.getString(1);
            return result;
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
        
    } 
   
   public int insertMarriage (String name, String surname, String name2, String surname2, String weddate,
           String tree, int type, int gend) throws ClassNotFoundException
    {
        String SQL = "INSERT INTO Malzenstwa VALUES (SzukajID_C(?,?, SzukajID_Dr(?, ?, SzukajID_Uzytk(?)),?)"
                + ",SzukajID_C(?,?, SzukajID_Dr(?, ?, SzukajID_Uzytk(?)),?), ?);";

           int result = 0;
        try (Connection conn = connect();
                PreparedStatement statm = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
 
            statm.setString(1, name);
            statm.setString(2, surname);
            statm.setString(3, tree);
            statm.setInt(4, type);
            statm.setString(5, LoginPageController.getUser());
            statm.setInt(6, gend);
            statm.setString(7, name2);
            statm.setString(8, surname2);
            statm.setString(9, tree);
            statm.setInt(10, type);
            statm.setString(11, LoginPageController.getUser());
            if(gend == 2)
                statm.setInt(12, 1);
            else
                statm.setInt(12, 2);
            statm.setString(13, weddate);
            
            result = statm.executeUpdate();
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return result;
    }
   public int insertMarriageGod (String name, String name2, 
           String tree, int type, int gend) throws ClassNotFoundException
    {
        String SQL = "INSERT INTO Malzenstwa (Maz, Zona) VALUES (SzukajID_B(?, SzukajID_Dr(?, ?, SzukajID_Uzytk(?)),?)"
                + ",SzukajID_B(?, SzukajID_Dr(?, ?, SzukajID_Uzytk(?)),?));";

           int result = 0;
        try (Connection conn = connect();
                PreparedStatement statm = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
 
            statm.setString(1, name);
            statm.setString(2, tree);
            statm.setInt(3, type);
            statm.setString(4, LoginPageController.getUser());
            statm.setInt(5, gend);
            statm.setString(6, name2);
            statm.setString(7, tree);
            statm.setInt(8, type);
            statm.setString(9, LoginPageController.getUser());
            if(gend == 2)
                statm.setInt(10, 1);
            else
                statm.setInt(10, 2);
            
            result = statm.executeUpdate();
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return result;
    }
   
   public int insertKing (String name, String surname, String place, String begin, String end,
           String tree, int type, int gend) throws ClassNotFoundException
    {
        String SQL = "INSERT INTO Wladcy VALUES (DEFAULT, SzukajKraj(?), ?, ? , SzukajID_C(?,?, SzukajID_Dr(?, ?, SzukajID_Uzytk(?)),?));";

           int result = 0;
        try (Connection conn = connect();
                PreparedStatement statm = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
            
            statm.setString(1, place);
            statm.setString(2, begin);
            statm.setString(3, end);
            statm.setString(4, name);
            statm.setString(5, surname);
            statm.setString(6, tree);
            statm.setInt(7, type);
            statm.setString(8, LoginPageController.getUser());
            statm.setInt(9, gend);
            
            result = statm.executeUpdate();
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return result;
    }
   
    public int checkRecord (String name, String surname, String tree, int type, String mname, String msurname, String fname, String fsurname) throws ClassNotFoundException
    {
        String SQL = "SELECT CzyIstnieje(?,?,SzukajID_Dr(?, ?, SzukajID_Uzytk(?)),?,?,?,?);";

        try (Connection conn = connect();
                PreparedStatement statm = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
 
            statm.setString(1, name);
            statm.setString(2, surname);
            statm.setString(3, tree);
            statm.setInt(4, type);
            statm.setString(5, LoginPageController.getUser());
            statm.setString(6, mname);
            statm.setString(7, msurname);
            statm.setString(8, fname);
            statm.setString(9, fsurname);
            
            
            ResultSet result = statm.executeQuery();
            result.next();
            return result.getInt(1);
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return 0;
    }
    
    public int newRecord(String name, String sname, int gend, String tree, String bplace, String dplace, 
            String job, String country, String mname, String msurname, String fname, String fsurname,
            String bdate, String ddate, boolean alive, String surname, int type) throws ClassNotFoundException
    {
        String SQL = "WITH ins1 AS (INSERT INTO Osoby (\n" +
"Imie, Drugie_Imie, ID_Plec, ID_Drzewa, Miejsce_Urodzenia, Miejsce_Pochowku, Zawod, \n" +
"	Kraj_Pochodzenia, Matka, Ojciec, Data_Urodzenia, Czy_Zyje, Data_Smierci\n" +
"	) VALUES (?, ?,  ?, SzukajID_Dr(?, ?, SzukajID_Uzytk(?)), "
                + "SzukajMiejsce(?), SzukajMiejsce(?), SzukajZawod(?), \n" +
"	SzukajKraj(?), SzukajID_C(?,?, SzukajID_Dr(?, ?, SzukajID_Uzytk(?)),1) ,"
                + "SzukajID_C(?,?,SzukajID_Dr(?, ?, SzukajID_Uzytk(?)),2), ?, ?, ?) RETURNING ID_Os AS id ) \n" +
"INSERT INTO Nazwisko_Osoby SELECT id, SzukajNazwisko(?) FROM ins1; ";
        int result = 0;
        try (Connection conn = connect();
                PreparedStatement statm = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
 
               statm.setString(1, name);
               statm.setString(2, sname);
               statm.setInt(3, gend);
               statm.setString(4, tree); 
               statm.setString(6, LoginPageController.getUser());  
               statm.setInt(5, type);  
               statm.setString(7, bplace);
               statm.setString(8, dplace);
               statm.setString(9, job);
               statm.setString(10, country);
               statm.setString(11, mname);
               statm.setString(12, msurname);
               statm.setString(13, tree); 
               statm.setInt(14, type);
               statm.setString(15, LoginPageController.getUser()); 
               statm.setString(16, fname);
               statm.setString(17, fsurname);
               statm.setString(18, tree);
               statm.setInt(19, type);
               statm.setString(20, LoginPageController.getUser());
               statm.setString(21, bdate);
               statm.setBoolean(22, alive);
               statm.setString(23, ddate);
               statm.setString(24, surname);
              
            
            result = statm.executeUpdate();
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
    public int newGod (String name, int gend, String tree,  
            String job, String country, String mname, String fname, int type) throws ClassNotFoundException
    {
        String SQL = "INSERT INTO Osoby (\n" +
"Imie, Drugie_Imie, ID_Plec, ID_Drzewa, Miejsce_Urodzenia, Miejsce_Pochowku, Zawod, \n" +
"	Kraj_Pochodzenia, Matka, Ojciec, Data_Urodzenia, Czy_Zyje, Data_Smierci\n" +
"	) VALUES (?, '',  ?, SzukajID_Dr(?, ?, SzukajID_Uzytk(?)), "
                + "SzukajMiejsce(''), SzukajMiejsce(''), SzukajZawod(?), \n" +
"	SzukajKraj(?), SzukajID_B(?, SzukajID_Dr(?, ?, SzukajID_Uzytk(?)),1) ,"
                + "SzukajID_B(?,SzukajID_Dr(?, ?, SzukajID_Uzytk(?)),2), '', null, '');";
        
        int result = 0;
        try (Connection conn = connect();
                PreparedStatement statm = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
 
               statm.setString(1, name);
               statm.setInt(2, gend);
               statm.setString(3, tree); 
               statm.setString(5, LoginPageController.getUser());  
               statm.setInt(4, type);  
               statm.setString(6, job);
               statm.setString(7, country);
               statm.setString(8, mname);
               statm.setString(9, tree); 
               statm.setInt(10, type);
               statm.setString(11, LoginPageController.getUser()); 
               statm.setString(12, fname);
               statm.setString(13, tree);
               statm.setInt(14, type);
               statm.setString(15, LoginPageController.getUser());
            
            result = statm.executeUpdate();
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
    public int updateRecord(String name, String sname, int gend, String tree, String bplace, String dplace, 
            String job, String country, String mname, String msurname, String fname, String fsurname,
            String bdate, String ddate, boolean alive, String surname, int type) throws ClassNotFoundException
    {
        String SQL = "UPDATE Osoby"
                + "SET Miejsce_Urodzenia = SzukajMiejsce(?), Miejsce_Pochowku = SzukajMiejsce(?), Zawod = SzukajZawod(?),"
                + "Data_Urodzenia = ?, Data_Smierci = ?, Czy_Zyje = ?, Drugie_Imie = ? WHERE Imie = ? "
                + "AND ID_Drzewa = SzukajID_Dr(?,?,SzukajID_Uzytk(?)) "
                + "AND Matka = SzukajID_C(?,?,SzukajID_Dr(?,?,SzukajID_Uzytk(?)),1) "
                + "AND Ojciec = SzukajID_C(?,?,SzukajID_Dr(?,?,SzukajID_Uzytk(?)),2)";
        
        int result = 0;
        try (Connection conn = connect();
                PreparedStatement statm = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
 
               statm.setString(1, bplace);
               statm.setString(2, dplace);
               statm.setString(3, job);
               statm.setString(4, bdate); 
               statm.setString(5, ddate);  
               statm.setBoolean(6, alive);
               statm.setString(7, sname);
               statm.setString(8, name);
               statm.setString(9, tree);
               statm.setInt(10, type);
               statm.setString(11, LoginPageController.getUser());
               statm.setString(12, mname);
               statm.setString(13, msurname);
               statm.setString(14, tree); 
               statm.setInt(15, type);
               statm.setString(16, LoginPageController.getUser()); 
               statm.setString(17, fname);
               statm.setString(18, fsurname);
               statm.setString(19, tree);
               statm.setInt(20, type);
               statm.setString(21, LoginPageController.getUser());
              
            
            result = statm.executeUpdate();
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
        
    }
    
    
    public void search(TableView tableview, String name, String surname, String place, String date, String tree, int id, int mode) throws SQLException
    {
        String SQL;
        if(mode == 1)
            SQL = "SELECT * FROM widok_osoby WHERE Imie LIKE ? AND Nazwisko LIKE ? AND Nazwa LIKE ? AND Data_Urodzenia LIKE ? AND Nazwa_Drzewa LIKE ? AND Typ_Drzewa = ?";
       else
            SQL = "SELECT * FROM widok_osoby_wszystko WHERE Imie LIKE ? AND Nazwisko LIKE ? AND Miejsce_Urodzenia LIKE ? AND Data_Urodzenia LIKE ? AND Nazwa_Drzewa LIKE ? AND Typ_Drzewa = ?";
               
            ObservableList<ObservableList> data = FXCollections.observableArrayList();
               List<String> cellnames = new ArrayList<String>();
               cellnames.add("Imię");
               if(mode == 2)
                   cellnames.add("Drugie imię");
               cellnames.add("Nazwisko");
               if(mode == 2)
                   cellnames.add("Płeć");
               cellnames.add("Miejsce urodzenia");
               cellnames.add("Data urodzenia");
               if(mode == 2)
               {
                   cellnames.add("Data śmierci");
                   cellnames.add("Zawód");
                   cellnames.add("Kraj pochodzenia");
               }
               cellnames.add("Nazwa drzewa");
               cellnames.add("Typ drzewa");
               
          try(Connection conn = connect();
                PreparedStatement statm = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)){
              
            statm.setString(1, name);
            statm.setString(2, surname);
            statm.setString(3, place);
            statm.setString(4, date);
            statm.setString(5, tree);
            statm.setInt(6, id);
              
            ResultSet rs = statm.executeQuery();
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;                
                TableColumn col = new TableColumn(cellnames.get(i));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
               
                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            while(rs.next()){

                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){

                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            tableview.setItems(data);
            tableview.refresh();
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
    }
    
    public void searchgod(TableView tableview, String name, String place,  String tree, int mode) throws SQLException
    {
        String SQL;
        if(mode == 1)
            SQL = "SELECT * FROM widok_bostwa WHERE Imie LIKE ? AND Nazwa LIKE ? AND Nazwa_Drzewa LIKE ? ";
        else
            SQL = "SELECT * FROM widok_bostwa_wszystko WHERE Imie LIKE ? AND Kraj_Pochodzenia LIKE ? AND Nazwa_Drzewa LIKE ? ";
       
               ObservableList<ObservableList> data = FXCollections.observableArrayList();
               List<String> cellnames = new ArrayList<String>();
               cellnames.add("Imię");
               if(mode == 2)
               {
                   cellnames.add("Płeć");
               }
               cellnames.add("Państwo");
               if(mode == 2)
               {
                   cellnames.add("Zawód");
               }
               cellnames.add("Nazwa drzewa");
   
          try(Connection conn = connect();
                PreparedStatement statm = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)){
              
            statm.setString(1, name);
            statm.setString(2, place);
            statm.setString(3, tree);
              
            ResultSet rs = statm.executeQuery();

            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;                
                TableColumn col = new TableColumn(cellnames.get(i));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
               
                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            while(rs.next()){

                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            tableview.setItems(data);
            tableview.refresh();
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
    }
    
    public void searchparent(TableView tableview, String name, String surname, String tree, int id) throws SQLException
    {
        String SQL;
        if(id == 2)
            SQL = "SELECT * FROM szukaj_rodzicow WHERE ID_Os = SzukajMatka(?,?) OR ID_Os = SzukajOjciec(?,?) AND Nazwa_Drzewa = ? ";
        else
            SQL = "SELECT * FROM szukaj_rodzicow WHERE ID_Os = SzukajMatka(?,'') OR ID_Os = SzukajOjciec(?,'') AND Nazwa_Drzewa = ?  ";
       
               ObservableList<ObservableList> data = FXCollections.observableArrayList();
               List<String> cellnames = new ArrayList<String>();
               cellnames.add("ID");
               cellnames.add("Imię");
               cellnames.add("Nazwisko");
               cellnames.add("Nazwa drzewa");
               
          try(Connection conn = connect();
                PreparedStatement statm = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)){
            if(id == 2){  
            statm.setString(1, name);
            statm.setString(2, surname);
            statm.setString(3, name);
            statm.setString(4, surname);
            statm.setString(5, tree);
            }
            else
            {
                statm.setString(1, name);
            statm.setString(2, name);
            statm.setString(3, tree);
            }
              
            ResultSet rs = statm.executeQuery();

            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;                
                TableColumn col = new TableColumn(cellnames.get(i));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
               
                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            while(rs.next()){

                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            tableview.setItems(data);
            tableview.refresh();
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
    }
    
    
        public void reports(TableView tableview, String value) throws ClassNotFoundException
    {
       final String v1 = "Lista wszystkich drzew", v2 = "Lista wszystkich Marianów w bazie", v3 = "Średni i maksymalny wiek życia w danym drzewie",
               v4 = "Lista władców panujących w XVII w.", v5 = "Lista wszystkich małżeństw zawartych po 1900 roku",
               v6 = "Lista wszystkich bogów związanych z morzem", v7 = "Lista nazwisk, które noszą przynajmniej dwie osoby";;
        
        String SQL = "";
                ObservableList<ObservableList> data = FXCollections.observableArrayList();
               List<String> cellnames = new ArrayList<String>();
        
        switch(value)
        {
            case v1:
            {
                SQL = "SELECT * FROM lista_drzew";
               cellnames.add("Nazwa");
               cellnames.add("Użytkownik");
               break;
            }
            case v2:
            {
                SQL = "SELECT * FROM widok_osoby WHERE Imie = 'Marian' OR Imie = 'marian' OR Imie = 'MARIAN';";
               cellnames.add("Imię");
               cellnames.add("Nazwisko");
               cellnames.add("Miejsce urodzenia");
               cellnames.add("Data urodzenia");
               cellnames.add("Nazwa drzewa");
               cellnames.add("Typ drzewa");
               break;
            }
            case v3:
            {
                SQL = "SELECT * FROM statystyki";
                cellnames.add("Nazwa drzewa");
               cellnames.add("Najwyższy wiek życia");
               cellnames.add("Średni wiek życia");
               break;
            }
            case v4:
            {
                SQL = "SELECT * FROM widok_wladcy WHERE Poczatek_Panowania LIKE '16%' OR Koniec_Panowania LIKE '16%' ORDER BY Poczatek_Panowania;";
              cellnames.add("Imię");
               cellnames.add("Nazwisko");
                 cellnames.add("Kraj panowania");
               cellnames.add("Początek panowania");
                cellnames.add("Koniec panowania");
                break;
            }
            case v5:
            {
                SQL = "SELECT * FROM widok_malzenstwa WHERE Data_Slubu >= '1900';";
               cellnames.add("Imię męża");
               cellnames.add("Nazwisko męża");
               cellnames.add("Imię żony");
               cellnames.add("Nazwisko żony");
               cellnames.add("Data ślubu");
               break;
            }
            case v6:
            {
                SQL = "SELECT * FROM widok_bostwa_wszystko WHERE Zawod LIKE '%morz%' OR Zawod LIKE '%mórz%' OR Zawod LIKE '%mors%';";
                cellnames.add("Imię");
                cellnames.add("Płeć");
               cellnames.add("Państwo");
              cellnames.add("Zawód");
               cellnames.add("Nazwa drzewa");
               break;
            }
            case v7:
            {
                SQL = "SELECT * FROM widok_nazwiska ;";
                cellnames.add("Nazwisko");
                cellnames.add("Liczba osób");
                break;
            }
        }
          try(Connection conn = connect();
                PreparedStatement statm = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)){
              
            ResultSet rs = statm.executeQuery();

            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;                
                TableColumn col = new TableColumn(cellnames.get(i));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
               
                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            while(rs.next()){

                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);
            }
            tableview.setItems(data);
            tableview.refresh();
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
        
    }
        public int count ()
    {
        String SQL = "SELECT COUNT(*) FROM Osoby;";
        
        try (Connection conn = connect();
                PreparedStatement login_stm = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {

            
            ResultSet check1 = login_stm.executeQuery();
            check1.next();
            return check1.getInt(1);
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
        
    }
}
