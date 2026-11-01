package com.example.camunda.dto;

public class ReassignTaskRequest {
    private String userTaskKey;
    private String newAssignee;

    public ReassignTaskRequest() {
    }

    public ReassignTaskRequest(String userTaskKey, String newAssignee) {
        this.userTaskKey = userTaskKey;
        this.newAssignee = newAssignee;
    }

    public String getUserTaskKey() {
        return userTaskKey;
    }

    public void setUserTaskKey(String userTaskKey) {
        this.userTaskKey = userTaskKey;
    }

    public String getNewAssignee() {
        return newAssignee;
    }

    public void setNewAssignee(String newAssignee) {
        this.newAssignee = newAssignee;
    }
}
