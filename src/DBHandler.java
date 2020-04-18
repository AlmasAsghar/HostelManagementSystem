
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Almas Asghar
 */
public class DBHandler {
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
   
    public DBHandler()
    {
        dbConnection();
    }
    public void dbConnection()
    {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hostelmanagement?zeroDateTimeBehavior=convertToNull","root","");
            
        } catch (Exception ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
