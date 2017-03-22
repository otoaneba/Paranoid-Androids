package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Manager;
import model.User;

/**
 * Created by naoto on 3/20/2017.
 */
public class HistoryReportController {


    @FXML
    private LineChart<String, Number> graph;
    private User user;
    private Stage dialogStage;
    //private static final Logger LOGGER =Logger.getLogger("HistoryReportController");
    @FXML
    private ComboBox<Integer> year;

    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*public void setMainapp(MainApp mainApp) {
    }*/

    @FXML
    private void initialize() {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        for (int i = 2016; i >= 2000; i--) {
            list.add(i);
        }
        year.setItems(list);
        year.setValue(2016);
    }

    @FXML
    private void handleView() {
        double[][] data = ((Manager) user).getPPM(year.getValue());

        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        for (int i = 0; i < 12; i++) {
            min = Math.min(min, data[0][i]);
            min = Math.min(min, data[1][i]);
            max = Math.max(max, data[0][i]);
            max = Math.max(max, data[1][i]);
            System.out.println(data[0][i] + "," + data[1][i]);
        }

        graph.setTitle("PPM Overview, " + year.getValue());

        ObservableList<XYChart.Series<String, Number>> lineChartData = FXCollections.observableArrayList();
        LineChart.Series<String, Number> series1 = new LineChart.Series<>();
        series1.setName("Virus PPM");

        series1.getData().add(new XYChart.Data<>("Jan", data[0][0]));
        series1.getData().add(new XYChart.Data<>("Feb", data[0][1]));
        series1.getData().add(new XYChart.Data<>("Mar", data[0][2]));
        series1.getData().add(new XYChart.Data<>("Apr", data[0][3]));
        series1.getData().add(new XYChart.Data<>("May", data[0][4]));
        series1.getData().add(new XYChart.Data<>("Jun", data[0][5]));
        series1.getData().add(new XYChart.Data<>("Jul", data[0][6]));
        series1.getData().add(new XYChart.Data<>("Aug", data[0][7]));
        series1.getData().add(new XYChart.Data<>("Sep", data[0][8]));
        series1.getData().add(new XYChart.Data<>("Oct", data[0][9]));
        series1.getData().add(new XYChart.Data<>("Nov", data[0][10]));
        series1.getData().add(new XYChart.Data<>("Dec", data[0][11]));

        lineChartData.add(series1);

        LineChart.Series<String, Number> series2 = new LineChart.Series<>();
        series2.setName("Contaminant PPM");
        series2.getData().add(new XYChart.Data<>("Jan", data[1][0]));
        series2.getData().add(new XYChart.Data<>("Feb", data[1][1]));
        series2.getData().add(new XYChart.Data<>("Mar", data[1][2]));
        series2.getData().add(new XYChart.Data<>("Apr", data[1][3]));
        series2.getData().add(new XYChart.Data<>("May", data[1][4]));
        series2.getData().add(new XYChart.Data<>("Jun", data[1][5]));
        series2.getData().add(new XYChart.Data<>("Jul", data[1][6]));
        series2.getData().add(new XYChart.Data<>("Aug", data[1][7]));
        series2.getData().add(new XYChart.Data<>("Sep", data[1][8]));
        series2.getData().add(new XYChart.Data<>("Oct", data[1][9]));
        series2.getData().add(new XYChart.Data<>("Nov", data[1][10]));
        series2.getData().add(new XYChart.Data<>("Dec", data[1][11]));

        lineChartData.add(series2);

        graph.setData(lineChartData);
        graph.createSymbolsProperty();

    }

    /**
     * handle when user try to cancel
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
