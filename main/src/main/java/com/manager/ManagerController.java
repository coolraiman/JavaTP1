//**********************************************************/
//Projet javaFX
//30-09-2022
//Cedric raymond  , 0867477
//Guillaume sauve , 1440441
//Mykhailo Niemtsev,2242977
//**********************************************************/

package com.manager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ManagerController implements Initializable{

    private ManagerModel managerModel;
    private ObservableList<Employe> oLEmploye;

    @FXML
    private Label languageSettingLabel;
    @FXML
    private ComboBox<String> languageSettingBox;

    @FXML
    private Label titleLabel;
    @FXML
    private Label IDLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label sexeLabel;
    @FXML
    private Label emploiLabel;
    @FXML
    private Label experienceLabel;

    @FXML
    private Button logoutButton;
    @FXML
    private Label username;
    @FXML
    private TextField textID;
    @FXML
    private TextField textNom;
    @FXML
    private TextField textPrenom;
    @FXML
    private ComboBox<String> sexeBox;
    @FXML
    private TextField emploiText;
    @FXML
    private TextField experience;
    @FXML 
    private TableView<Employe> dataTable;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button eraseButton;
    @FXML
    private Button unSelectButton;

    @FXML
    private TableColumn<Employe, Integer> empId;
    @FXML
    private TableColumn<Employe, String> empNom;
    @FXML
    private TableColumn<Employe, String> empPrenom;
    @FXML
    private TableColumn<Employe, String> empGenre;
    @FXML
    private TableColumn<Employe, String> empEmploi;
    @FXML
    private TableColumn<Employe, Integer> empExperience;

    //logout the user and load the login.fxml
    @FXML 
    void onLogoutAction(ActionEvent event)
    {
        try {
            App.setRoot("login");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    //add a new employe
    @FXML
    void onAddAction(ActionEvent event)
    {
        //get all the data and try to convert it as an Employe
        Employe emp = managerModel.convertDataToEmploye(
            textID.getText(), textNom.getText(), textPrenom.getText(), sexeBox.getSelectionModel().getSelectedIndex(),
             emploiText.getText(), experience.getText());
        //add the employe to the database
        if(emp != null)
        {
            if(managerModel.addEmploye(emp))
            {
                clearFields();
                addButton.setDisable(true);
                modifyButton.setDisable(true);
            }
            textID.requestFocus();
        }
    }

    //erase all fields
    @FXML
    void onEraseAction(ActionEvent event)
    {
        clearFields();
        addButton.setDisable(true);
        modifyButton.setDisable(true);
        
    }
    //delete all selected items in tableview
    @FXML
    void onDeleteAction(ActionEvent event)
    {//get all selected employes and convert it to an arrayList
        ArrayList<Employe> emps = new ArrayList<>(dataTable.getSelectionModel().getSelectedItems());
        //call the manager model to delete all employes
        managerModel.deleteMultipleEmploye(emps);
        deleteButton.setDisable(true);
        unSelectButton.setDisable(true);
    }

    @FXML
    void onModifyAction(ActionEvent event)
    {
        //convert all the textField to an Employe
        Employe emp = managerModel.convertDataToEmploye(
            textID.getText(), textNom.getText(), textPrenom.getText(), sexeBox.getSelectionModel().getSelectedIndex(),
             emploiText.getText(), experience.getText());
        //call the manager model to modify the employe
        if(emp != null)
        {
            if(managerModel.modifyEmploye(emp))
            {
                clearFields();
                addButton.setDisable(true);
                modifyButton.setDisable(true);
            }
            textID.requestFocus();
        }
    }

    @FXML
    void onUnselectAction(ActionEvent event)
    {//unselect everything in the dataTable
        dataTable.getSelectionModel().clearSelection();
        //set buttons avalaibality
        deleteButton.setDisable(true);
        unSelectButton.setDisable(true);
    }

    //when the user click on the dataTable and select an item
    @FXML
    void onSelectAction(MouseEvent event)
    {   //only for left click
        if(event.getButton().equals(MouseButton.PRIMARY))
        {//get the number of selected items
            int nSelected = dataTable.getSelectionModel().getSelectedIndices().size();
            if(nSelected == 0)
            {
                deleteButton.setDisable(true);
                unSelectButton.setDisable(true);
            }
            else if(nSelected == 1)
            {//display the employe data in the textFields
                displayEmploye(dataTable.getSelectionModel().getSelectedItem());
                deleteButton.setDisable(false);
                unSelectButton.setDisable(false);
            }
            else//multiple seleted items
            {
                deleteButton.setDisable(false);
                unSelectButton.setDisable(false);
            }

        }
    }

    //-------------------------------
    //Methodes for textfields inputs
    private void onIdEnterPress(KeyCode code)
    {
        if(textID.isFocused() && code.getCode() == KeyCode.ENTER.getCode() && textID.getText().length() > 0)
        {
            if(managerModel.isIntegerPositive(textID.getText()))
                textNom.requestFocus();
            else
            {
                showErrorBox(LanguageData.getText(17,0), LanguageData.getText(17, 1),
                    LanguageData.getText(17, 2));
            }
        }
        else
        {
            isAnyFieldValueEmptyAndUpdateButton();
        }
    }

    private void onNomEnterPress(KeyCode code)
    {
        if(textNom.isFocused() && code.getCode() == KeyCode.ENTER.getCode() && textNom.getText().length() > 0)
        {
            textPrenom.requestFocus();
        }
        else
        {
            isAnyFieldValueEmptyAndUpdateButton();
        }
    }

    private void onPrenomEnterPress(KeyCode code)
    {
        if(textPrenom.isFocused() && code.getCode() == KeyCode.ENTER.getCode() && textPrenom.getText().length() > 0)
        {
            sexeBox.requestFocus();
        }
        else
        {
            isAnyFieldValueEmptyAndUpdateButton();
        }
    }

    private void onSexeEnterPress(KeyCode code)
    {
        if(sexeBox.isFocused() && code.getCode() == KeyCode.ENTER.getCode())
        {
            emploiText.requestFocus();
        }
        else
        {
            isAnyFieldValueEmptyAndUpdateButton();
        }
    }

    private void onEmploiEnterPress(KeyCode code)
    {
        if(emploiText.isFocused() && code.getCode() == KeyCode.ENTER.getCode() && emploiText.getText().length() > 0)
        {
            experience.requestFocus();
        }
        else
        {
            isAnyFieldValueEmptyAndUpdateButton();
        }
    }

    private void onExperienceEnterPress(KeyCode code)
    {
        if(experience.isFocused() && code.getCode() == KeyCode.ENTER.getCode() && experience.getText().length() > 0)
        {//validate if experience is integer
            if(managerModel.isIntegerPositive(experience.getText()))
            {
                //if employe exist
                if(!isAnyFieldValueEmptyAndUpdateButton())
                {
                    if(managerModel.employeExist(textID.getText()))
                    {
                        onModifyAction(null);
                    }
                    else
                    {
                        onAddAction(null);
                    }
                }
            }
            else
            {
                showErrorBox(LanguageData.getText(18, 0), LanguageData.getText(18, 1),
                    LanguageData.getText(18, 2));
            }
        }
        else
        {
            isAnyFieldValueEmptyAndUpdateButton();
        }
    }
    //-----------------------------------------------

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //configure each column of the tableView
        empId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        empNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        empPrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        empGenre.setCellValueFactory(new PropertyValueFactory<>("Sexe"));
        empEmploi.setCellValueFactory(new PropertyValueFactory<>("Emploi"));
        empExperience.setCellValueFactory(new PropertyValueFactory<>("Experience"));
        //set the selection mode to multiple
        dataTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //when user click on the dataTable
        dataTable.setOnMouseClicked((MouseEvent event) -> 
        {
            onSelectAction(event);
        });
        //config the gender comboBox values
        sexeBox.getItems().addAll("Masculin", "Feminin", "Autre");
        sexeBox.getSelectionModel().select(0);
        isAnyFieldValueEmptyAndUpdateButton();

        textID.setOnKeyReleased(event -> onIdEnterPress(event.getCode()));
        textNom.setOnKeyReleased(event -> onNomEnterPress(event.getCode()));
        textPrenom.setOnKeyReleased(event -> onPrenomEnterPress(event.getCode()));
        sexeBox.setOnKeyReleased(event -> onSexeEnterPress(event.getCode()));
        emploiText.setOnKeyReleased(event -> onEmploiEnterPress(event.getCode()));
        experience.setOnKeyReleased(event -> {

            onExperienceEnterPress(event.getCode());
        });

        initStyle();

        

        //instantiante the managerModel
        managerModel = new ManagerModel(this);

        languageSettingBox.getItems().addAll(LanguageData.getTextArray(10));
        languageSettingBox.setOnAction(event -> onLanguageSettingClick());
        updateLanguageText();
    }

    private void initStyle()
    {
        //Initialize the style of buttons
        initButton(addButton);
        initButton(deleteButton);
        initButton(modifyButton);
        initButton(eraseButton);
        initButton(unSelectButton);
        //Initialize the style of our textfields
        initTextfield(textID);
        initTextfield(textNom);
        initTextfield(textPrenom);
        initTextfield(emploiText);
        initTextfield(experience);
        //Initialize the style of the combobox
        initCombobox(sexeBox);
    }

    private void onLanguageSettingClick()
    {
        if(languageSettingBox.getSelectionModel().getSelectedIndex() != LanguageData.getLangId())
        {
            LanguageData.setLang(languageSettingBox.getSelectionModel().getSelectedIndex());
            updateLanguageText();
        }
    }

    private void updateLanguageText()
    {
        IDLabel.setText(LanguageData.getText(11, 0));
        nomLabel.setText(LanguageData.getText(11, 1));
        prenomLabel.setText(LanguageData.getText(11, 2));
        sexeLabel.setText(LanguageData.getText(11, 3));
        emploiLabel.setText(LanguageData.getText(11, 4));
        experienceLabel.setText(LanguageData.getText(11, 5));

        empId.setText(LanguageData.getText(11, 0));
        empNom.setText(LanguageData.getText(11, 1));
        empPrenom.setText(LanguageData.getText(11, 2));
        empGenre.setText(LanguageData.getText(11, 3));
        empEmploi.setText(LanguageData.getText(11, 4));
        empExperience.setText(LanguageData.getText(11, 5));

        int tempSelection = sexeBox.getSelectionModel().getSelectedIndex();
        sexeBox.getItems().setAll(LanguageData.getTextArray(12));
        sexeBox.getSelectionModel().select(tempSelection);


        addButton.setText(LanguageData.getText(13, 0));
        modifyButton.setText(LanguageData.getText(13, 1));
        eraseButton.setText(LanguageData.getText(13, 2));
        deleteButton.setText(LanguageData.getText(13, 3));
        logoutButton.setText(LanguageData.getText(13, 5));
        unSelectButton.setText(LanguageData.getText(13, 4));

        titleLabel.setText(LanguageData.getText(14, 0));

        
        languageSettingLabel.setText(LanguageData.getText(4, 0));
        languageSettingBox.getItems().setAll(LanguageData.getTextArray(10));
        languageSettingBox.getSelectionModel().select(LanguageData.getLangId());

        refreshTableView();
    }

    private void initButton(Button a)
    {
        InnerShadow Inner_shadow2 = new InnerShadow(BlurType.values()[1],
        Color.BURLYWOOD, 5, 3.0f, 3.0f, 3.0f);
        a.setEffect(Inner_shadow2);
        a.setStyle("-fx-background-radius: 15px;");
    }  

    private void initTextfield(TextField b)
    {
        b.setStyle("-fx-background-radius: 15px;");
    }  

    private void initCombobox(ComboBox c)
    {
        c.setStyle("-fx-background-radius: 15px;");
    } 

    //display an employe data in the text fields
    public void displayEmploye(Employe emp)
    {
        textID.setText(Integer.toString(emp.getId()));
        textNom.setText(emp.getNom());
        textPrenom.setText(emp.getPrenom());
        if(emp.getUnlocalizedSexe().equals("Masculin"))
        {
            sexeBox.getSelectionModel().select(0);
        }
        else if(emp.getUnlocalizedSexe().equals("Feminin"))
        {
            sexeBox.getSelectionModel().select(1);
        }
        else
        {
            sexeBox.getSelectionModel().select(2);
        }

        emploiText.setText(emp.getEmploi());
        experience.setText(Integer.toString(emp.getExperience()));
        isAnyFieldValueEmptyAndUpdateButton();
    }
    
    //reset every fields
    public void clearFields()
    {
        textID.setText("");
        textNom.setText("");
        textPrenom.setText("");
        sexeBox.getSelectionModel().select(0);
        emploiText.setText("");
        experience.setText("");
        isAnyFieldValueEmptyAndUpdateButton();
    }

    public void setUsername(String username)
    {
        this.username.setText(username);
    }

    public void refreshTableView()
    {
        dataTable.getItems().clear();
        oLEmploye = FXCollections.observableArrayList(managerModel.getEmployes());
        dataTable.setItems(oLEmploye);
    }

    //update the tableView with new data (overwrite all)
    public void updateTableView(ArrayList<Employe> emps)
    {
        oLEmploye = FXCollections.observableArrayList(emps);
        dataTable.setItems(oLEmploye);
    }

    //check if any field is empty and update the add and modify button disable value
    private boolean isAnyFieldValueEmptyAndUpdateButton()
    {
       
        if(textID.getText().length() == 0 || textNom.getText().length() == 0 || textPrenom.getText().length() == 0 
            || emploiText.getText().length() == 0|| experience.getText().length() == 0)
        {
            addButton.setDisable(true);
            modifyButton.setDisable(true);
            return true;
        }

        addButton.setDisable(false);
        modifyButton.setDisable(false);
        return false;
    }


    public void showErrorBox(String title, String header, String content)
    {
        Alert dialog = new Alert(AlertType.ERROR);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);

        dialog.showAndWait();
    }
}
