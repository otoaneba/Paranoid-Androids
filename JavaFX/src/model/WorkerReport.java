package model;

/**
 * Created by naoto on 3/20/2017.
 */
public class WorkerReport extends Report{
    private Condition waterCondition;
    private String virusPPM;
    private String contaminantPPM;

    public WorkerReport(String u, String title, String location, Condition waterCondition, String virusPPM, String contaminantPPM) {
        super(u, title, location);
        this.waterCondition = waterCondition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
    }

    public Condition getWaterCondition() {
        return waterCondition;
    }

    public void setWaterCondition(Condition waterCondition) {
        this.waterCondition = waterCondition;
    }

    public String getVirusPPM() {
        return virusPPM;
    }

    public void setVirusPPM(String virusPPM) {
        this.virusPPM = virusPPM;
    }

    public String getContaminantPPM() {
        return contaminantPPM;
    }

    public void setContaminantPPM(String contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
    }
}
