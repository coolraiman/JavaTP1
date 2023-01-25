//**********************************************************/
//Projet javaFX
//30-09-2022
//Cedric raymond  , 0867477
//Guillaume sauve , 1440441
//Mykhailo Niemtsev,2242977
//**********************************************************/

package com.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class MySqlConnection {

     private static String DRIVER = "com.mysql.cj.jdbc.Driver",
             URL = "jdbc:mysql://localhost:3306/sger?serverTimezone=UTC",
             USER = "root",
             PASSWORD = "school";



private static Connection cnx = null;



private MySqlConnection() {}



public static Connection getInstance() {
try {
     if (cnx == null || cnx.isClosed()) {
         Class.forName(DRIVER);
         cnx = DriverManager.getConnection(URL, USER, PASSWORD);
     }
} catch (SQLException ex) {
     Logger.getLogger(MySqlConnection.class.getName()).log(Level.SEVERE, null, ex);
} catch (ClassNotFoundException ex) {
     Logger.getLogger(MySqlConnection.class.getName()).log(Level.SEVERE, null, ex);
}
return cnx;
}
}