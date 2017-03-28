package model;/**
 * Created by naoto on 3/20/2017.
 */

import javafx.application.Application;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public enum UserLevel {

    USER("User"), WORKER("Worker"), MANAGER("Manager"), ADMIN("Admin");

    private final String role;

    UserLevel(String role) {
        this.role = role;
    }

    public String toString() {
        return role;
    }
}
