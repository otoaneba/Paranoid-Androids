package model;

import javafx.beans.property.StringProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by naoto on 3/20/2017.
 */
public class Manager extends Worker {
    /**
     * Make a new user
     * @param name      the user's name
     * @param pw     the user's password
     * @param l        the user's userlevel
     */
    public Manager(String name, String pw, UserLevel l, String email) {
        super(name, pw, l, email);
    }

    /**
     * View history report
     * @param year   the year the manager wants to view
     * @return 2-D double array  the first row contains virus ppm and the second row contains contaminant ppm
     */
    public double[][] getPPM(int year) {
        double[][] rt = new double[2][12];
        List<StringProperty> list= Reports.getReportList();
        int[] count = new int[12];
        for (StringProperty stringProperty : list) {
            String report = stringProperty.get();
            if (report != null) {
                if (report.split(",")[1].equals("workerReport")) {
                    Map<String, String> map = (this).viewReport(report, false);
                    String[] date = map.get("date").split("_");
                    int reportYear = Integer.parseInt(date[0]), month = Integer.parseInt(date[1]);
                    if (reportYear == year) {
                        double virusPPM = Double.parseDouble(map.get("virusPPM"));
                        double contaminantPPM = Double.parseDouble(map.get("contaminantPPM"));
                        rt[0][month - 1] = virusPPM;
                        rt[1][month - 1] = contaminantPPM;
                        count[month - 1]++;
                    }
                }
            }
        }
        for (int i = 0; i < 12; i++) {
            if (count[i] != 0) {
                rt[0][i] /= count[i];
                rt[1][i] /= count[i];
            }
        }
        return rt;
    }
}
