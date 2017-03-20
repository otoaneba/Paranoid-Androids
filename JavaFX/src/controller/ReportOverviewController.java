package controller;

import app.Main;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Reports;
import model.User;
import model.Worker;

import java.util.Map;

/**
 * Created by naoto on 3/20/2017.
 */
public class ReportOverviewController {
    @FXML
    private TableView<StringProperty> reportTable;
    @FXML
    private TableColumn<StringProperty, String> fileColumn;

    @FXML
    private Label titleLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label userLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label conditionLabel;
    @FXML
    private Label virusLabel;
    @FXML
    private Label contaminantLabel;

    private Main mainapp;

    private User user;


    @FXML
    private void initialize() {
        fileColumn.setCellValueFactory(TableColumn.CellDataFeatures::getValue);
        reportTable.setItems(Reports.getReportList());
        showReportDetails(null);
        reportTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showReportDetails(newValue));
    }

    public void setMainapp(Main mainApp) {
        this.mainapp = mainApp;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void handleBack() {
        this.mainapp.showMainOverview(this.user);
    }

    private void showReportDetails(StringProperty report) {
        if (report != null) {
            if (report.get().split(",")[1].equals("workerReport")) {
                Map<String, String> map = ((Worker) user).viewReport(report.get(), true);
                titleLabel.setText(map.get("reportId") + " " + map.get("title"));
                timeLabel.setText(map.get("date"));
                userLabel.setText(map.get("user"));
                locationLabel.setText(map.get("location"));
                conditionLabel.setText(map.get("watercondition"));
                virusLabel.setText(map.get("virusPPM"));
                contaminantLabel.setText(map.get("contaminantPPM"));
                typeLabel.setText(map.get("watertype"));
            } else {
                Map<String, String> map = user.viewReport(report.get());
                titleLabel.setText(map.get("reportId") + " " + map.get("title"));
                timeLabel.setText(map.get("date"));
                userLabel.setText(map.get("user"));
                locationLabel.setText(map.get("location"));
                conditionLabel.setText(map.get("watercondition"));
                virusLabel.setText(map.get("virusPPM"));
                contaminantLabel.setText(map.get("contaminantPPM"));
                typeLabel.setText(map.get("watertype"));
            }
        } else {
            titleLabel.setText("");
            timeLabel.setText("");
            userLabel.setText("");
            locationLabel.setText("");
            typeLabel.setText("");
            conditionLabel.setText("");
        }
    }
}
