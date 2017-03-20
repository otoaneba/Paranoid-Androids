package model;

/**
 * Created by naoto on 3/20/2017.
 */
public enum WaterCondition {
    WASTE("Waste"), TCLEAR("Treatable-Clear"), TMUDDY("Treatable-Muddy"), POTABLE("Potable"), UNKNOWN("Unknown");
    private final String condition;
    WaterCondition(String condition) { this.condition = condition; }
    /*public String getWaterConditon() { return condition; }*/
    public String toString() { return condition; }
}
