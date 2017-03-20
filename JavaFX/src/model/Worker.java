package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by naoto on 3/20/2017.
 */
public class Worker extends User{
    /**
     * Make a new user
     * @param name      the user's name
     * @param pw     the user's password
     * @param l        the user's userlevel
     */
    public Worker(String name, String pw, UserLevel l, String email) {
        super(name, pw, l, email);
    }


    /**
     * Make a new user
     * @param name      the user's name
     * @param pw     the user's password
     * @param l        the user's userlevel
     */
    public Worker(String name, String pw, UserLevel l) {
        super(name, pw,l);
    }

    /**
     * Make a new user
     * @param name      the user's name
     * @param pw     the user's password
     */
    Worker(String name, String pw) {
        super(name, pw);
    }

    /**
     * used to submit the worker's water report
     * @param report the worker's report to be submit
     */
    public static void submitWorkerReport(WorkerReport report) {
        String user = report.getUser();
        String title = report.getTitle();
        String date = report.getDate();
        String location = report.getLocation();
        Condition waterCondition = (report).getWaterCondition();
        String virusPPM = (report).getVirusPPM();
        String contaminantPPM = (report).getContaminantPPM();
        String reportId = report.getReportId();

        String path = report.getPath();
        try {
            File f = new File(path);
            if (f.exists()) {
                return;
            }
            FileWriter fw;
            try {
                fw = new FileWriter(f);
                fw.write("Report ID:" + reportId + "\n");
                fw.write("UserName:" + user + "\n");
                fw.write("Title:" + title + "\n");
                fw.write("Date:" + date + "\n");
                fw.write("Location:" + location + "\n");
                fw.write("WaterCondition:" + waterCondition + "\n");
                fw.write("VirusPPM:" + virusPPM + "\n");
                fw.write("ContaminantPPM:" + contaminantPPM + "\n");
                System.out.println("Write finished");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } catch (Exception e){
            System.out.println("Unable to create a new file");
            e.printStackTrace();
            //return;
        }
        Reports.add(report);
    }

    /**
     * View a Worker Report
     * @param info    the report's filename stored in reports_s
     * @return Map a map contains information about that report
     */
    public Map<String, String> viewReport(String info, boolean isReportType) {
        String[] fileInfo = info.split(",");
        //String reportType = fileInfo[1];
        String filename = fileInfo[0];
        Map<String, String> map = new HashMap<>();
        String reportId, userName, title, date, location, path, overallCondition, virusPPM, contaminantPPM;
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
            String[] watercondition = br.readLine().split(":");
            overallCondition = watercondition[1];
            String[] VPPM = br.readLine().split(":");
            virusPPM = VPPM[1];
            String[] CPPM = br.readLine().split(":");
            contaminantPPM = CPPM[1];
            br.close();
            map.put("reportId", reportId);
            map.put("user", userName);
            map.put("title", title);
            map.put("date", date);
            map.put("location", location);
            map.put("watercondition", overallCondition);
            map.put("virusPPM", virusPPM);
            map.put("contaminantPPM", contaminantPPM);
            return map;
        } catch (Exception e) {
            System.out.println("File Reading fail");
            e.printStackTrace();
        }
        return null;
    }
}
