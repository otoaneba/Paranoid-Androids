package controller;

import app.Main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Database;
import model.User;
import model.UserLevel;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by naoto on 3/20/2017.
 */
public class SignupController {
    @FXML
    private TextField useridField;
    @FXML
    private PasswordField firpwField;
    @FXML
    private PasswordField secpwField;
    @FXML
    private RadioButton userRadio;
    @FXML
    private RadioButton workerRadio;
    @FXML
    private RadioButton managerRadio;
    @FXML
    private RadioButton adminRadio;
    @FXML
    private TextField emailField;
    //store stage for this interface
    private Stage dialogstage;
    //store user
    private User user;
    //decide edit profile or make a new account
    private boolean signupOrEdit;

    private Main mainapp;

    private static final Logger LOGGER = Logger.getLogger("SignupController");

    /*private void initialize() {
    }*/

    /**
     * set the stage
     *
     * @param dstage the dialog stage to be added in
     */
    public void setDialogStage(Stage dstage) {
        this.dialogstage = dstage;
    }

    public void setEditBoolean(boolean signOrnot) {
        this.signupOrEdit = signOrnot;
        if (!this.signupOrEdit) {
            useridField.setEditable(false);
        }
    }

    /**
     * sets the userInfo
     *
     * @param auser the user to be set
     */
    public void setUserInfo(model.User auser) {
        this.user = auser;
        useridField.setText(user.getUserName());
        firpwField.setText(user.getPassword());
        if (!this.signupOrEdit) {
            secpwField.setText(user.getPassword());
        }
        emailField.setText(user.getEmailAddress());
        //secpwField.setPlaceholder(new Label("No Content In List"));
        //secpwField.setText("Re-enter your password");
    }

    public void setMainapp(Main mainApp) {
        this.mainapp = mainApp;
    }

    /**
     * handle the ok button,to check the validity of the input.If pass, then register/ edit
     */
    @FXML
    private void handleSignUp() {
        try {
            Database database = new Database();
            if (!firpwField.getText().equals(secpwField.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogstage);
                alert.setTitle("Password Error");
                alert.setHeaderText("Two password not much");
                alert.setContentText("Please Re-enter the password!");
                secpwField.setText("");
                alert.showAndWait();
            } else if (database.checkExistance(useridField.getText()) && this.signupOrEdit) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogstage);
                alert.setTitle("User Error!");
                alert.setHeaderText("User already existed");
                alert.setContentText("Please Re-enter the user name!");
                useridField.setText("");
                firpwField.setText("");
                secpwField.setText("");
                alert.showAndWait();
            } else if (useridField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogstage);
                alert.setTitle("Username Error");
                alert.setHeaderText("empty username");
                alert.setContentText("Please Re-enter the username!");
                alert.showAndWait();
            } else if (firpwField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogstage);
                alert.setTitle("Password Error");
                alert.setHeaderText("empty password");
                alert.setContentText("Please Re-enter the password!");
                alert.showAndWait();
            } else if (emailField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogstage);
                alert.setTitle("Email Error");
                alert.setHeaderText("empty email address");
                alert.setContentText("Please Re-enter the email!");
                alert.showAndWait();
            } else if (!this.signupOrEdit) {
                if (userRadio.isSelected()) {
                    user = database.editProfile(this.user, useridField.getText(), firpwField.getText(), emailField.getText(), UserLevel.USER);
                    //this.user.setUserLevel(UserLevel.USER);
                } else if (workerRadio.isSelected()) {
                    user = database.editProfile(this.user, useridField.getText(), firpwField.getText(), emailField.getText(), UserLevel.WORKER);
                    //this.user.setUserLevel(UserLevel.WORKER);
                } else if (managerRadio.isSelected()) {
                    user = database.editProfile(this.user, useridField.getText(), firpwField.getText(), emailField.getText(), UserLevel.MANAGER);
                    //this.user.setUserLevel(UserLevel.MANAGER);
                } else {
                    user = database.editProfile(this.user, useridField.getText(), firpwField.getText(), emailField.getText(), UserLevel.ADMIN);
                    //this.user.setUserLevel(UserLevel.ADMIN);
                }
                //user.setPassword(firpwField.getText());
                //user.setEmailAddress(emailField.getText());
                this.mainapp.showMainOverview(user);
                dialogstage.close();
            } else {
                if (userRadio.isSelected()) {
                    user = database.registerUser(useridField.getText(), firpwField.getText(), emailField.getText(), UserLevel.USER);
                } else if (workerRadio.isSelected()) {
                    user = database.registerUser(useridField.getText(), firpwField.getText(), emailField.getText(), UserLevel.WORKER);
                } else if (managerRadio.isSelected()) {
                    user = database.registerUser(useridField.getText(), firpwField.getText(), emailField.getText(), UserLevel.MANAGER);
                } else {
                    user = database.registerUser(useridField.getText(), firpwField.getText(), emailField.getText(), UserLevel.ADMIN);
                }
                this.mainapp.showLogin();
                dialogstage.close();
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load database!!");
            e.printStackTrace();
        }
    }

    /**
     * hadle the cancel button
     */
    @FXML
    private void handleCancel() {
        dialogstage.close();
    }


}
