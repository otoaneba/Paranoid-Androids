package controller;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Report;
import model.Reports;
import model.User;
import model.UserReport;
import model.WaterCondition;
import model.WaterType;

import java.util.logging.Logger;

/**
 * Created by naoto on 3/20/2017.
 */
public class ReportViewController {
    @FXML
    private TextField titleField;
    @FXML
    private TextField locationField;
    @FXML
    private ComboBox<WaterType> typeField;
    @FXML
    private ComboBox<WaterCondition> conditionField;
    private User user;
    private Report report;
    private Reports reportList;
    private Stage dialogStage;
    private Main mainapp;
    private static final Logger LOGGER = Logger.getLogger("ReportViewController");

    @FXML
    private void initialize() {
        this.typeField.setItems(Reports.getWaterTypeList());
        this.conditionField.setItems(Reports.getWaterConditionList());
        typeField.setValue(WaterType.OTHER);
        conditionField.setValue(WaterCondition.UNKNOWN);
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

    @FXML
    private void handleFinish() {

        Report newreport = new UserReport(user.getUserName(), titleField.getText(), locationField.getText(),
                typeField.getValue(), conditionField.getValue());
        user.submitReport(newreport);
        this.mainapp.showMainOverview(this.user);
        dialogStage.close();

    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
