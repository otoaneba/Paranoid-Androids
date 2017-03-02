package com.example.olivia.myapplication.model;

/**
 * Created by zsp32 on 3/1/2017.
 */

public enum waterQuality {    ADMIN("AD"),
    WORKER("WK"),
    MANAGER("MG"),
    USER("US");

    private String userType;

    /**
     *  returns the userType of this user
     * @param userType an enum userType that
     */
    private userType(String userType) {
        this.userType = userType;
    }
}
