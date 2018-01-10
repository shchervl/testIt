package org.softkiss.testit;

public enum Status {

    NOT_EXECUTED("NOT EXECUTED"),
    PASSED("PASSED"),
    FAILED("FAILED"),
    BLOCKED("BLOCKED");

    private String status;

    Status(String status) {
        this.status = status;
    }
}
