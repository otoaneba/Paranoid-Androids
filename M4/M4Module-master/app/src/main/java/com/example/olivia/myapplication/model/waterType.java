package com.example.olivia.myapplication.model;

/**
 * @author Kyung Jun Lee
 */

public enum waterType {
    SELECT_AN_ITEM("Select an item"),
    BOTTLED("Bottled"),
    WELL("Well"),
    STREAM("Stream"),
    LAKE("Lake"),
    SPRING("Spring"),
    OTHER("Other");

    /**
     *  returns the waterQuality of A REPORT
     * @param waterQuality an enum waterQuality that
     */
    waterType(String waterQuality) {
    }
}
