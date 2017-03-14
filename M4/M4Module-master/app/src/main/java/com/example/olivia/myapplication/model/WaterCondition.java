package com.example.olivia.myapplication.model;

/**
 * Created by John on 2017-03-14.
 */

public enum WaterCondition {
    WASTE("waste"),
    TREATABLE_CLEAR("Treatable-Clear"),
    TREATABLE_MUDDY("Treatable-Muddy"),
    PORTABLE("Portable");

    private String waterCondition;

    /**
     *  returns the waterQuality of A REPORT
     * @param waterCondition an enum waterQuality that
     */
    WaterCondition(String waterCondition) {
        this.waterCondition = waterCondition;
    }
}
