package com.example.camunda.dto;

public class DeploymentRequest {
    private String resourceName;
    private String fileContent;
    private String deploymentName;

    public DeploymentRequest() {
    }

    public DeploymentRequest(String resourceName, String fileContent, String deploymentName) {
        this.resourceName = resourceName;
        this.fileContent = fileContent;
        this.deploymentName = deploymentName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getDeploymentName() {
        return deploymentName;
    }

    public void setDeploymentName(String deploymentName) {
        this.deploymentName = deploymentName;
    }
}
