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
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class LoginController implements Initializable
{
    private LoginModel loginModel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label languageSettinLabel;

    @FXML
    private ComboBox<String> languageSettingBox;

    @FXML
    private Label copyrightLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML 
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorMessage;

    @FXML
    private Label connectionStatus;

    @FXML
    private Button loginButton;

    @FXML 
    private ImageView loadingIcon;

    @FXML
    private ImageView connectedIcon;

    private void tryUserLogin()
    {
        try {
            if(!loginModel.LoginNow(username.getText(), password.getText()))
            {
                errorMessage.setText(LanguageData.getText(9, 0));
            }
        } catch (IOException e1) {
            errorMessage.setText(e1.toString());
        } catch (SQLException e) {
            errorMessage.setText(e.toString());
        }
    }

    @FXML
    private void onLoginAction(ActionEvent e) throws SQLException
    {
        tryUserLogin();
    }

    private void onUsernameEnterPressed(KeyCode code)
    {
        updateLoginButton(loginModel.isDbConnected());
        if(username.isFocused() && code.getCode() == KeyCode.ENTER.getCode() && !username.getText().equals(""))
        {
            password.requestFocus();
        }
    }

    private void onPasswordEnterPressed(KeyCode code)
    {
        if(!updateLoginButton(loginModel.isDbConnected()) && password.isFocused() && code.getCode() == KeyCode.ENTER.getCode() && !password.getText().equals(""))
            tryUserLogin();
    }


    //update the connection status message and icon
    public void updateConnectionStatus(boolean connected, String message)
    {
        connectionStatus.setText(message);

        if(connected)
        {
            loadingIcon.setVisible(false);
            connectedIcon.setVisible(true);
        }
        else
        {
            loadingIcon.setVisible(true);
            connectedIcon.setVisible(false);
        }
        updateLoginButton(connected);
    }

    //update the login button availability
    private boolean updateLoginButton(boolean connected)
    {
        if(connected && username.getText().length() > 0 && password.getText().length() > 0)
        {
            loginButton.setDisable(false);
            return false;
        }
        else
        {
            loginButton.setDisable(true);
            return true;
        }
    }

    private void onLanguageSettingClick()
    {
        if(languageSettingBox.getSelectionModel().getSelectedIndex() != LanguageData.getLangId())
        {
            LanguageData.setLang(languageSettingBox.getSelectionModel().getSelectedIndex());
            updateLanguageText();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loginModel = new LoginModel(this);
        errorMessage.setText("");
        username.setOnKeyReleased(event -> onUsernameEnterPressed(event.getCode()));
        password.setOnKeyReleased(event -> onPasswordEnterPressed(event.getCode()));

        languageSettingBox.getItems().addAll(LanguageData.getTextArray(10));
        languageSettingBox.setOnAction(event -> onLanguageSettingClick());
        
        updateLanguageText();
        
    }

    private void updateLanguageText()
    {
        titleLabel.setText(LanguageData.getText(0, 0));
        usernameLabel.setText(LanguageData.getText(1, 0));
        passwordLabel.setText(LanguageData.getText(2, 0));
        loginButton.setText(LanguageData.getText(3, 0));
        languageSettinLabel.setText(LanguageData.getText(4, 0));

        languageSettingBox.getItems().setAll(LanguageData.getTextArray(10));
        languageSettingBox.getSelectionModel().select(LanguageData.getLangId());

        connectionStatus.setText(LanguageData.getText(loginModel.getConnectionStatus(), 0));
        copyrightLabel.setText(LanguageData.getText(8, 0));

        errorMessage.setText("");

    }
}