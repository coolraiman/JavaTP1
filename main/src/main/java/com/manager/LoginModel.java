//**********************************************************/
//Projet javaFX
//30-09-2022
//Cedric raymond  , 0867477
//Guillaume sauve , 1440441
//Mykhailo Niemtsev,2242977
//**********************************************************/

package com.manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;




public class LoginModel{
    
    
    Connection connection;
    LoginController lc;
    private int connectionStatus = 5;
    public int getConnectionStatus(){return connectionStatus;}
    
    public LoginModel(LoginController lc)
    {
        this.lc = lc;
        lc.updateConnectionStatus(false, LanguageData.getText(5, 0));
        tryConnection();
        LoginToken.reset();
    }

    //try to connect to the database
    private void tryConnection()
    {
        connection =MySqlConnection.getInstance();
        if(isDbConnected())
        {
            lc.updateConnectionStatus(true, LanguageData.getText(6, 0));
            connectionStatus = 6;
        }
        else
        {
            connectionStatus = 7;
            lc.updateConnectionStatus(false,LanguageData.getText(7, 0));
        }


    }

    //test the connection to the database
    public boolean isDbConnected() {
        
        try {
            
            if(!connection.isClosed())
            {
                connectionStatus = 6;
                return true;
            }
            else
            {
                connectionStatus = 7;
                return false;
            }
            
        } catch(SQLException e) {
            connectionStatus = 7;
            return false;
        }
        
    }
    
    
    public boolean LoginNow(String user, String pass) throws SQLException, IOException 
    {
        //fail safe
        if(!isDbConnected())
        {//try 1 more time
            tryConnection();
            if(!isDbConnected())
                return false;
        }


        PreparedStatement stm = null;
        ResultSet resultSet = null;
        boolean loginSuccess = false;
        
        
         try {
                stm = connection.prepareStatement("select username, nom, prenom from sger.gestionnaire where username = ? and password = ?");
                stm.setString(1, user);
                stm.setString(2, pass);
                resultSet = stm.executeQuery();
                
                if (resultSet.next())
                {//update the login token with the user info
                    LoginToken.setuserName(resultSet.getString("username"));
                    LoginToken.setNom(resultSet.getString("nom"));
                    LoginToken.setPrenom(resultSet.getString("prenom"));

                    loginSuccess = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
           
           }
           finally {
               stm.close();
               resultSet.close();
           }
        if(loginSuccess)
        {
            App.setRoot("manager");
            return true;
        }
         return false;
        
    }
        
}
