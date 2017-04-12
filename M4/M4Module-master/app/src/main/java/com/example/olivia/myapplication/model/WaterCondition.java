package com.example.olivia.myapplication.model;

/**
 * Created by John on 2017-03-14.
 */

public enum WaterCondition {
    SELECT_AN_ITEM("Select an item"),
    WASTE("waste"),
    TREATABLE_CLEAR("Treatable-Clear"),
    TREATABLE_MUDDY("Treatable-Muddy"),
    PORTABLE("Portable");

    final private String waterCondition1;
    /**
     *  returns the waterQuality of A REPORT
     * @param waterCondition an enum waterQuality that
     */
    WaterCondition(String waterCondition) {
        waterCondition1 = waterCondition;
    }
}
