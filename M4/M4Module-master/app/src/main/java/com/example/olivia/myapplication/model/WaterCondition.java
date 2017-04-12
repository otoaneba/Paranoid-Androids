package com.example.olivia.myapplication.model;

/**
 * @author Kyung Jun Lee
 */
public enum WaterCondition {
    SELECT_AN_ITEM("Select an item"),
    WASTE("waste"),
    TREATABLE_CLEAR("Treatable-Clear"),
    TREATABLE_MUDDY("Treatable-Muddy"),
    PORTABLE("Portable");

    /**
     *  returns the waterQuality of A REPORT
     * @param waterCondition an enum waterQuality that
     */
    WaterCondition(String waterCondition) {
    }
}
