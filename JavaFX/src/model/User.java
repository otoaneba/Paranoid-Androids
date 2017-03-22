package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * created by naoto on 3/20/17
 */
public class User {
    private final StringProperty userName = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final ObjectProperty<UserLevel> level = new SimpleObjectProperty<>();
    //private StringProperty homeAddress = new SimpleStringProperty();
    private final StringProperty  emailAddress = new SimpleStringProperty();

    /* **********************
     * Getters and setters for properties
     */
    public String getUserName() { return userName.get(); }
    // public void setUserName(String name) { userName.set(name); }

    public String getPassword() {return password.get(); }
    //public void setPassword(String pw) { password.set(pw); }

    public UserLevel getUserLevel() { return level.get(); }
    //public void setUserLevel(UserLevel _level) {  level.set(_level); }

    //public String getHomeAddress() { return homeAddress.get(); }
    //public void setHomeAddress(String _homeAddress) { homeAddress.set(_homeAddress); }

    public String getEmailAddress() { return emailAddress.get(); }
    //public void setEmailAddress(String _email) { emailAddress.set(_email); }


    /**
     * Make a new user
     * @param name      the user's name
     * @param pw     the user's password
     * @param l        the user's userlevel
     */
    public User(String name, String pw, UserLevel l/*, String home*/, String email) {
        userName.set(name);
        password.set(pw);
        level.set(l);
        //homeAddress.set(home);
        emailAddress.set(email);
    }


    /**
     * Make a new user
     * @param name      the user's name
     * @param pw     the user's password
     * @param l        the user's userlevel
     */
    public User(String name, String pw, UserLevel l) {
        userName.set(name);
        password.set(pw);
        level.set(l);
        //homeAddress.set("none");
        emailAddress.set("none@mail.com");
    }

    /**
     * Make a new user
     * @param name      the user's name
     * @param pw     the user's password
     */
    public User(String name, String pw) {
        userName.set(name);
        password.set(pw);
        level.set(UserLevel.USER);
        //homeAddress.set("none");
        emailAddress.set("none@mail.com");
    }

    /**
     * override the equals method
     * @param o the user to be compared
     * @return boolean to show if two users equal
     */
    @Override
    public boolean equals(Object o) {

        return (o instanceof User) && getUserName().equals(((User) o).getUserName()) && getPassword().equals(((User) o).getPassword());
    }

    public String toString() {
        return userName.get() + " " + password.get() + " " + level.get();
    }


    /**
     * Submit a Report
     * @param report    the report that need to be submitted
     * @return boolean to show if the report is submitted successfully or not
     */
    public boolean submitReport(Report report) {
        if (report == null) {
            return false;
        }
        if (!(report instanceof UserReport)) {
            return false;
        }
        String user = report.getUser();
        String title = report.getTitle();
        String date = report.getDate();
        String location = report.getLocation();
        WaterType watertype =((UserReport) report).getWaterType();
        WaterCondition watercondition = ((UserReport) report).getWaterCondition();
        String reportId = report.getReportId();

        String path = report.getPath();
        try {
            File f = new File(path);
            if (f.exists()) {
                return false;
            }
            FileWriter fw;
            try {
                fw = new FileWriter(f);
                fw.write("Report ID:" + reportId + "\n");
                fw.write("UserName:" + user + "\n");
                fw.write("Title:" + title + "\n");
                fw.write("Date:" + date + "\n");
                fw.write("Location:" + location + "\n");
                fw.write("Type:" + watertype + "\n");
                fw.write("Condition:" + watercondition + "\n");
                System.out.println("Write finished");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e){
            System.out.println("Unable to create a new file");
            e.printStackTrace();
            return false;
        }
        Reports.add(report);
        return true;
    }

    /**
     * View a Report
     * @param info    the report's filename stored in reports_s
     * @return Map a map contains information about that report
     */
    public Map<String, String> viewReport(String info) {
        String filename = info.split(",")[0];
        Map<String, String> map = new HashMap<>();
        String reportId, userName, title, date, location, path, watertype, watercondition;
        path = "./reports/" + filename + ".txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String[] id = br.readLine().split(":");
            reportId = id[1];
            String[] name = br.readLine().split(":");
            userName = name[1];
            String[] reportTitle = br.readLine().split(":");
            title = reportTitle[1];
            String[] reportDate = br.readLine().split(":");
            date = reportDate[1];
            String[] reportLocation = br.readLine().split(":");
            location = reportLocation[1];
            String[] reportType = br.readLine().split(":");
            watertype = reportType[1];
            String[] reportCondition = br.readLine().split(":");
            watercondition = reportCondition[1];
            br.close();
            map.put("reportId", reportId);
            map.put("user", userName);
            map.put("title", title);
            map.put("date", date);
            map.put("location", location);
            map.put("watertype", watertype);
            map.put("watercondition", watercondition);
            return map;
        } catch (Exception e) {
            System.out.println("File Reading fail");
            e.printStackTrace();
        }
        return null;
    }
}
