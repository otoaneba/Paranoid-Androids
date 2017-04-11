package com.example.olivia.myapplication.model;

/**
 * Created by Shuopeng Zhou on 3/1/2017.
 * Enums for Water quality
 */

public enum waterQuality {
    SAFE("SAFE"),
    TREATABLE("TREATABLE"),
    UNTREATABLE("UNTREATABLE");
    private String waterQuality1;
    /**
     *  returns the waterQuality of A REPORT
     * @param waterQuality an enum waterQuality that
     */
     waterQuality(String waterQuality) {
         waterQuality1 = waterQuality;
    }
}
