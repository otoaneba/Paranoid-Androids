package model;/**
 * Created by naoto on 3/20/2017.
 */

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Report {
    private String user;
    private String title;
    private String date;
    private String location;
    private final String path;
    private final String filename;
    private String reportId;

    public Report(String u, String title, String date, String location) {
        this.user = u;
        this.title =title;
        this.date = date;
        this.location = location;
        this.filename = u + title + date;
        this.path = "./reports/" + filename + ".txt";
        this.reportId = String.format("%05d", Reports.getReportIdSize() + 1);
        Reports.updateReportIdSize(reportId);
    }

    public Report(String u, String title, String location) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        this.user = u;
        this.title =title;
        this.date = dateFormat.format(now);
        this.location = location;
        this.filename = u + title + date;
        this.path = "./reports/" + filename + ".txt";
        this.reportId = String.format("%05d", Reports.getReportIdSize() + 1);
        Reports.updateReportIdSize(reportId);
    }

    /* **********************
     * Getters and setters for properties
     */
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public StringProperty getTitleProperty() {
        return new SimpleStringProperty(this.title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public StringProperty getDateProperty() {
        return new SimpleStringProperty(this.date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPath() {
        return path;
    }

    public String getFilename() {
        return filename;
    }

    public String getReportId() { return reportId; }
    public void setReportId(String reportId) { this.reportId = reportId; }

}
