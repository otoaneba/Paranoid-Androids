package controller;

/**
 *
 */

import app.Main;
import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

// controller for root layout
public class MainScreenController {

    //connection to the mainapp
    private Main mainapp;

    /**
     * get the address of mainapp
     *
     * @param main the mainapp
     */
    public void setMainApp(Main main) {
        mainapp = main;
    }

    /**
     * handle close
     */
    @FXML
    private void handleClose() {
        System.exit(0);
    }

    /**
     * handle about
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login UI");
        alert.setHeaderText("About");
        alert.setContentText("Team undefined");
        alert.showAndWait();
    }

    /**
     * handle logout
     */
    @FXML
    private void handleLogOut() {
        mainapp.showLogin();
    }

}
