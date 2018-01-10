package org.softkiss.testit;

import org.softkiss.createIt.Feature;
import org.softkiss.createIt.UserStory;

import java.util.Date;
import java.util.List;

public class TestCase {

    private String id;
    private Feature feature;
    private UserStory userStory;
    private String version;
    private Date createdOn;
    private String title;
    private String summary;
    private String preConditions;
    private List<TestStep> testSteps;
    private User createdBy;
    private User updatedBy;
    private Date updatedOn;
    private String notes;

}
