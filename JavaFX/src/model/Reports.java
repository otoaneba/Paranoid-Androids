package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by naoto on 3/20/2017.
 */
public class Reports {

    private final static ObservableList<StringProperty> reportList = initReportList();

    private static ObservableList<StringProperty> initReportList() {
        ObservableList<StringProperty> rl = FXCollections.observableList(new ArrayList<>());
        /*rl.addListener((ListChangeListener) change -> {
            System.out.println("Detected a change! ");
        });*/
        String path = "./reports/reports_list.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                rl.add(new SimpleStringProperty(line));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Fail to init the reportList");
            e.printStackTrace();
            return null;
        }
        return rl;
    }

    /**
     * get a reports list
     */
    public static ObservableList<StringProperty> getReportList() {
        return reportList;
    }

    /**
     * get a reports list's size
     */
    public static int getReportIdSize() {
        //int res = 0;
        String path = "./reports/reportId.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            br.close();
            return Integer.parseInt(line);
        } catch (Exception e) {
            System.out.println("Fail to init the reportList");
            e.printStackTrace();
            return -1;
        }
    }

    public static void updateReportIdSize(String id) {
        String path = "./reports/reportId.txt";
        try {
            File f = new File(path);
            if (!f.exists()) {
                return;
            }
            FileWriter fw;
            try {
                fw = new FileWriter(f);
                fw.write(id);
                System.out.println("Write finished");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e){
            System.out.println("Unable to create a new file");
            e.printStackTrace();
        }
    }

    /**
     * add a Report
     * @param r r is a instance of report
     * @return boolean to show if succeed
     */
    public static boolean add(Report r) throws NullPointerException {
        String path = "./reports/reports_list.txt";
        if (r == null) {
            return false;
        }
        if ("".equals(r.getTitle()) || "".equals(r.getLocation())) {
            return false;
        }
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(path, true));
            out.newLine();
            String reportType = "report";
            if (r instanceof UserReport) {
                reportType = "userReport";
            } else if (r instanceof WorkerReport) {
                reportType = "workerReport";
            }
            assert reportList != null;
            reportList.add(new SimpleStringProperty(r.getFilename() + "," + reportType + "," + r.getLocation() + "," + r.getUser()));
            out.write(r.getFilename() + "," + reportType + "," + r.getLocation() + "," + r.getUser());
            out.close();
            return true;
        } catch (Exception e){
            System.out.println("Unable to create a new file");
            e.printStackTrace();
            return false;
        }
    }

    public static ObservableList<WaterType> getWaterTypeList() {
        return FXCollections.observableArrayList(
                WaterType.BOTTLED,
                WaterType.LAKE,
                WaterType.SPRING,
                WaterType.STREAM,
                WaterType.WELL,
                WaterType.OTHER
        );
    }
    public static ObservableList<WaterCondition> getWaterConditionList() {
        return FXCollections.observableArrayList(
                WaterCondition.POTABLE,
                WaterCondition.TCLEAR,
                WaterCondition.WASTE,
                WaterCondition.TMUDDY,
                WaterCondition.UNKNOWN
        );
    }

    public static ObservableList<Condition> getOverCondition() {
        return FXCollections.observableArrayList(
                Condition.SAFE,
                Condition.TREATABLE,
                Condition.UNSAFE
        );
    }

    public static void main(String[] args) {
        System.out.println(Reports.reportList);
    }
}
