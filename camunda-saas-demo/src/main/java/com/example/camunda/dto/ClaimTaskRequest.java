package com.example.camunda.dto;

public class ClaimTaskRequest {
    private String userTaskKey;
    private String assignee;

    public ClaimTaskRequest() {
    }

    public ClaimTaskRequest(String userTaskKey, String assignee) {
        this.userTaskKey = userTaskKey;
        this.assignee = assignee;
    }

    public String getUserTaskKey() {
        return userTaskKey;
    }

    public void setUserTaskKey(String userTaskKey) {
        this.userTaskKey = userTaskKey;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
