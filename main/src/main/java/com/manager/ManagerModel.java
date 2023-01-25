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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerModel
{
    private ManagerController managerController;
    private Connection connection;
    private ArrayList<Employe> employes;

    public ArrayList<Employe> getEmployes()
    {
        return employes;
    }


    public ManagerModel(ManagerController managerController)
    {
        this.managerController = managerController;
        init();
    }

    //init the manager model
    private void init()
    {
        managerController.setUsername(LoginToken.getuserName());
        connection =MySqlConnection.getInstance();

        loadDatabase();

    }

    //load all Employe from database
    private void loadDatabase()
    {
        if(!isDbConnected())
        {
            managerController.showErrorBox(LanguageData.getText(15, 0), LanguageData.getText(15, 1),
                LanguageData.getText(15, 2));
            try {
                App.setRoot("login");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return;
        }

        PreparedStatement stm = null;
        ResultSet resultSet = null;
        employes = new ArrayList<Employe>();

        try 
        {
            stm = connection.prepareStatement("select * from sger.employe");
            resultSet = stm.executeQuery();
            
            while (resultSet.next())
            {
                Employe emp = new Employe();
                emp.setId(resultSet.getInt("idEmploye"));
                emp.setNom(resultSet.getString("nom"));
                emp.setPrenom(resultSet.getString("prenom"));
                emp.setSexe(resultSet.getString("genre"));
                emp.setEmploi(resultSet.getString("poste"));
                emp.setExperience(resultSet.getInt("experience"));
                employes.add(emp);
            }
            //update the employe table view from the managerController
            managerController.updateTableView(employes);
        } catch (SQLException ex) 
        {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            managerController.showErrorBox(Integer.toString(ex.getErrorCode()), ex.getSQLState(),ex.getLocalizedMessage());
        }
       finally 
       {
        try {
            stm.close();
            resultSet.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            managerController.showErrorBox(Integer.toString(e.getErrorCode()), e.getSQLState(),e.getLocalizedMessage());
        };
       }
       
    }

    //check if an employe exist based on its ID
    public boolean employeExist(String empId)
    {
        if(!isDbConnected())
        {
            managerController.showErrorBox(LanguageData.getText(15, 0), LanguageData.getText(15, 1),
            LanguageData.getText(15, 2));
            try {
                App.setRoot("login");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        try
        {
            int id = Integer.parseInt(empId);
            return employeExist(id);
        }
        catch (NumberFormatException n)
        {
            managerController.showErrorBox(LanguageData.getText(17, 0), LanguageData.getText(17, 1),
            LanguageData.getText(17, 2));
        }

        return false;
    }

    //check if an employe exist based on its ID 
    public boolean employeExist(int empId)
    {
        if(!isDbConnected())
        {
            managerController.showErrorBox(LanguageData.getText(15, 0), LanguageData.getText(15, 1),
            LanguageData.getText(15, 2));
            try {
                App.setRoot("login");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        PreparedStatement stm = null;
        ResultSet resultSet = null;
        boolean result = false;
        try
        {

            stm = connection.prepareStatement("select idEmploye from sger.employe where idEmploye = ?");
            stm.setInt(1, empId);
            resultSet = stm.executeQuery();

            if(resultSet.next())
            {//only if the result set has at least 1 result can result be true
                result = true;
            }
        }
        catch(SQLException ex) 
        {
            managerController.showErrorBox(Integer.toString(ex.getErrorCode()), ex.getSQLState(),ex.getLocalizedMessage());
        }
        finally
        {
            try {
                stm.close();
                resultSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                managerController.showErrorBox(Integer.toString(e.getErrorCode()), e.getSQLState(),e.getLocalizedMessage());
            }
            
        }
        return result;
    }

    //add 1 employe to the database
    public boolean addEmploye(Employe emp)
    {
        boolean sucess = false;
        if(!isDbConnected())
        {
            managerController.showErrorBox(LanguageData.getText(15, 0), LanguageData.getText(15, 1),
            LanguageData.getText(15, 2));
            try {
                App.setRoot("login");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        PreparedStatement stm = null;
        int result;

        try
        {

            stm = connection.prepareStatement("INSERT INTO sger.employe values(?,?, ?, ?,?, ?)");
            stm.setInt(1, emp.getId());
            stm.setString(2, emp.getNom());
            stm.setString(3, emp.getPrenom());
            stm.setString(4, emp.getUnlocalizedSexe());
            stm.setString(5, emp.getEmploi());
            stm.setInt(6, emp.getExperience());
            result = stm.executeUpdate();
            //no row affacted = error
            if(result == 0)
            {
                managerController.showErrorBox(LanguageData.getText(25, 0), LanguageData.getText(25, 1),
                    LanguageData.getText(25, 2));
            }
            else
            {
                sucess = true;
            }
        }
        catch(SQLException ex) 
        {
            managerController.showErrorBox(Integer.toString(ex.getErrorCode()), ex.getSQLState(),ex.getLocalizedMessage());
        }
        finally
        {
            try {
                stm.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                managerController.showErrorBox(Integer.toString(e.getErrorCode()), e.getSQLState(),e.getLocalizedMessage());
            }
        }
        if(sucess)
        {
            //reload the database
            loadDatabase();
            //update the employe tableView
            managerController.updateTableView(employes);
        }
        return sucess;
    }

    //delete a list of employes
    public void deleteMultipleEmploye(ArrayList<Employe> emps)
    {
        if(!isDbConnected())
        {
            managerController.showErrorBox(LanguageData.getText(15, 0), LanguageData.getText(15, 1),
                LanguageData.getText(15, 2));
            try {
                App.setRoot("login");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return;
        }

        for(int i = 0; i < emps.size(); i++)
        {
            deleteEmploye(emps.get(i).getId(), false);
        }

        //reload the database

        loadDatabase();
        managerController.updateTableView(employes);
    }


    private void deleteEmploye(int emp, boolean updateData)
    {
        PreparedStatement stm = null;
        int result;

        try
        {

            stm = connection.prepareStatement("DELETE FROM sger.employe WHERE idEmploye = ?");
            stm.setInt(1, emp);
            result = stm.executeUpdate();
            if(result == 0)
            {
                
                managerController.showErrorBox(LanguageData.getText(16, 0), LanguageData.getText(16, 1),
                    LanguageData.getText(16, 2) + LanguageData.getText(16, 3));
            }
        }
        catch(SQLException ex) 
        {
            managerController.showErrorBox(Integer.toString(ex.getErrorCode()), ex.getSQLState(),ex.getLocalizedMessage());
        }
        finally
        {
            try {
                stm.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                managerController.showErrorBox(Integer.toString(e.getErrorCode()), e.getSQLState(),e.getLocalizedMessage());
            }
        }

        if(updateData)
        {
            loadDatabase();
            managerController.updateTableView(employes);
        }
    }

    private String sexes[] = new String[]{"Masculin", "Feminin", "Autre"};
    //convert and check if values are valids, return null if it fail
    //todo check for empty strings
    public Employe convertDataToEmploye(String id, String nom, String prenom, int sexe, String emploi, String experience)
    {
        int empId = -1;
        int empExperience = -1;

        try
        {
            empId = Integer.parseInt(id);
        }
        catch (NumberFormatException ex)
        {
            managerController.showErrorBox(LanguageData.getText(17,0), LanguageData.getText(17,1),
                LanguageData.getText(17,2));
            return null;
        }

        try
        {
            empExperience = Integer.parseInt(experience);
        }
        catch (NumberFormatException ex)
        {
            managerController.showErrorBox(LanguageData.getText(18,0), LanguageData.getText(18,1),
                LanguageData.getText(18,2));
            return null;
        }

        if(empId < 0 )
        {
            managerController.showErrorBox(LanguageData.getText(17,0), LanguageData.getText(17,1),
            LanguageData.getText(17,2));
            return null;
        }
        if(empExperience < 0)
        {
            managerController.showErrorBox(LanguageData.getText(18,0), LanguageData.getText(18,1),
                LanguageData.getText(18,2));
            return null;
        }

        if(sexe < 0 && sexe > 2)
        {
            managerController.showErrorBox(LanguageData.getText(19,0), LanguageData.getText(19,1),
                LanguageData.getText(19,2));
            return null;
        }

        if(nom.length() == 0)
        {
            managerController.showErrorBox(LanguageData.getText(20,0), LanguageData.getText(20,1),
                LanguageData.getText(20,2));
            return null;
        }
        if(prenom.length() == 0)
        {
            managerController.showErrorBox(LanguageData.getText(21,0), LanguageData.getText(21,1),
                LanguageData.getText(21,2));
            return null;
        }
        if(emploi.length() == 0)
        {
            managerController.showErrorBox(LanguageData.getText(22,0), LanguageData.getText(22,1),
                LanguageData.getText(22,2));
            return null;
        }

        return new Employe(empId,nom,prenom,sexes[sexe],emploi,empExperience);

    }

    
    public boolean modifyEmploye(Employe emp)
    {
        boolean sucess = false;
        if(emp == null)
        {
            managerController.showErrorBox(LanguageData.getText(23,0), LanguageData.getText(23,1),
                LanguageData.getText(23,2));
            return false;
        }

        PreparedStatement stm = null;
        int result;

        try
        {

            stm = connection.prepareStatement("UPDATE sger.employe SET nom = ?, prenom = ?, genre = ?, poste = ?, experience = ? WHERE idEmploye = ?");
            stm.setString(1, emp.getNom());
            stm.setString(2, emp.getPrenom());
            stm.setString(3, emp.getUnlocalizedSexe());
            stm.setString(4, emp.getEmploi());
            stm.setInt(5, emp.getExperience());
            stm.setInt(6, emp.getId());
            result = stm.executeUpdate();
            if(result == 0)
            {
                managerController.showErrorBox(LanguageData.getText(24,0), LanguageData.getText(24,1),
                    LanguageData.getText(24,2) + Integer.toString(emp.getId()) + LanguageData.getText(24,3));
            }
            else
            {
                sucess = true;
            }
        }
        catch(SQLException ex) 
        {
            managerController.showErrorBox(Integer.toString(ex.getErrorCode()), ex.getSQLState(),ex.getLocalizedMessage());
        }
        finally
        {
            try {
                stm.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                managerController.showErrorBox(Integer.toString(e.getErrorCode()), e.getSQLState(),e.getLocalizedMessage());
            }
        }

        if(sucess)
        {
            //reload the database then update the tableView
            loadDatabase();
            managerController.updateTableView(employes);
        }
        return sucess;
    }

    public boolean isIntegerPositive(String number)
    {
        try
        {
            int numb = Integer.parseInt(number);
            if(numb >= 0)
                return true;
        }
        catch (NumberFormatException ex)
        {
            return false;
        }
        return false;
    }

    //test the connection to the database
    public boolean isDbConnected() {
        
        try {
            
            return !connection.isClosed();
            
        } catch(SQLException e) {
            
            return false;
        }
        
    }
}