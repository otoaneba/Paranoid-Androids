package com.example.olivia.myapplication.model;

/**
 * Created by Naoto on 2/20/2017.
 *
 * created to be used in the spinner when a user must choose during registration
 */

public enum userType {
    /**
     *  returns the userType of this user
     */

    USER {
        /**
         * returns user
         * @return user
         */
        public String toString() {
            return "user";
        }
    }, WORKER {
        /**
         * returns worker
         * @return worker
         */
        public String toString() {
            return "worker";
        }
    }, MANAGER {
        /**
         * returns manager
         * @return manager
         */
        public String toString() {
            return "manager";
        }
    }, ADMIN {
        /**
         * returns admin
         * @return admin
         */
        public String toString() {
            return "admin";
        }
    }
//
//    ADMIN("AD"),
//    WORKER("WK"),
//    MANAGER("MG"),
//    USER("US");
//
//    private String userType;
//
//    /**
//     *  returns the userType of this user
//     * @param userType an enum userType that
//     */
//    private userType(String userType) {
//        this.userType = userType;
//    }
}
