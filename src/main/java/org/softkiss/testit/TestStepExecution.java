package org.softkiss.testit;

import java.net.URL;

public class TestStepExecution {

    private TestStep testStep;
    private Status status = Status.NOT_EXECUTED;
    private URL linkToDefect;

    public TestStep getTestStep() {
        return testStep;
    }

    public void setTestStep(TestStep testStep) {
        this.testStep = testStep;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public URL getLinkToDefect() {
        return linkToDefect;
    }

    public void setLinkToDefect(URL linkToDefect) {
        this.linkToDefect = linkToDefect;
    }
}
