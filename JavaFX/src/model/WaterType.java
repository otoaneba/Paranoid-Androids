package model;

/**
 * Created by naoto on 3/20/2017.
 */
public enum WaterType {
    BOTTLED("Bottled"), WELL("Well"), STREAM("Stream"), LAKE("Lake"), SPRING("Spring"), OTHER("Other");
    private final String type;
    WaterType(String type) {this.type = type;}

    /*public String getWaterType() { return this.type; }*/
    public String toString() { return this.type;}
}
