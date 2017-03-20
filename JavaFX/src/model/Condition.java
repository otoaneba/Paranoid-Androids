package model;

/**
 * Created by naoto on 3/20/2017.
 */
public enum Condition {
    SAFE("Safe"), TREATABLE("Treatable"), UNSAFE("Unsafe");

    private final String condition;

    Condition(String condition) {
        this.condition = condition;
    }

    /*public String getCondition() {
        return condition;
    }*/

    public String toString() {
        return condition;
    }
}
