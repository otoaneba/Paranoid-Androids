package controller;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Condition;
import model.Reports;
import model.User;
import model.Worker;
import model.WorkerReport;

import java.awt.*;

/**
 * Created by naoto on 3/20/2017.
 */
public class WorkerReportController {
    @FXML
    private TextField locationField;
    @FXML
    private ComboBox<Condition> conditionField;
    @FXML
    private TextField contaminantField;
    @FXML
    private TextField virusField;
    @FXML
    private TextField titleField;
    // user to be saved
    private User user;
    //private Report report;
    //private Reports reportList;
    // dialog stage of this window
    private Stage dialogStage;
    // connection to main app
    private Main mainapp;
    //private static final Logger LOGGER =Logger.getLogger("ReportViewController");

    @FXML
    private void initialize() {
        this.conditionField.setItems(Reports.getOverCondition());
        this.conditionField.setValue(Condition.TREATABLE);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMainapp(Main mainApp) {
        this.mainapp = mainApp;
    }

    /**
     * handle when finish
     */
    @FXML
    private void handleFinish() {

        WorkerReport newreport = new WorkerReport(user.getUserName(), titleField.getText(), locationField.getText(),
                conditionField.getValue(), virusField.getText(), contaminantField.getText());
        Worker.submitWorkerReport(newreport);
        this.mainapp.showMainOverview(this.user);
        dialogStage.close();

    }

    @FXML
    private void handleUserReport() {
        this.mainapp.showReportView(user, false);
        this.dialogStage.close();
    }

    /**
     * handle when user try to cancel
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
