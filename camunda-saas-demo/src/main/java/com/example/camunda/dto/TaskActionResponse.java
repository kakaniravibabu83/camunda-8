package com.example.camunda.dto;

public class TaskActionResponse {
    private String errorCode;
    private String errorMessage;
    private int statusCode;
    private String message;
    private String userTaskKey;

    public TaskActionResponse() {
    }

    public TaskActionResponse(String errorCode, String errorMessage, int statusCode, String message, String userTaskKey) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
        this.message = message;
        this.userTaskKey = userTaskKey;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserTaskKey() {
        return userTaskKey;
    }

    public void setUserTaskKey(String userTaskKey) {
        this.userTaskKey = userTaskKey;
    }
}
