package com.example.olivia.myapplication.model;

/**
 * Created by John on 2017-03-13.
 */

public enum waterType {
    SELECT_AN_ITEM("Select an item"),
    BOTTLED("Bottled"),
    WELL("Well"),
    STREAM("Stream"),
    LAKE("Lake"),
    SPRING("Spring"),
    OTHER("Other");

    private String waterType;

    /**
     *  returns the waterQuality of A REPORT
     * @param waterQuality an enum waterQuality that
     */
    waterType(String waterQuality) {
        this.waterType = waterType;
    }
}
