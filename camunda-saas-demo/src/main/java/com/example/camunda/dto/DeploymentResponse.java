package com.example.camunda.dto;

public class DeploymentResponse {
    private String deploymentKey;
    private String deploymentName;
    private String status;
    private String message;
    private int statusCode;
    private String exceptionMessage;

    public DeploymentResponse() {
    }

    public DeploymentResponse(String deploymentKey, String deploymentName, String status, String message, int statusCode, String exceptionMessage) {
        this.deploymentKey = deploymentKey;
        this.deploymentName = deploymentName;
        this.status = status;
        this.message = message;
        this.statusCode = statusCode;
        this.exceptionMessage = exceptionMessage;
    }

    public String getDeploymentKey() {
        return deploymentKey;
    }

    public void setDeploymentKey(String deploymentKey) {
        this.deploymentKey = deploymentKey;
    }

    public String getDeploymentName() {
        return deploymentName;
    }

    public void setDeploymentName(String deploymentName) {
        this.deploymentName = deploymentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
