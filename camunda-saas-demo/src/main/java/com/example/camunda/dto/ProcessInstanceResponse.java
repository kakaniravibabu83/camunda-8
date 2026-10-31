package com.example.camunda.dto;

public class ProcessInstanceResponse {
    private String processInstanceKey;
    private String bpmnProcessId;
    private Integer version;
    private String status;
    private String message;
    private int statusCode;
    private String exceptionMessage;

    public ProcessInstanceResponse() {
    }

    public ProcessInstanceResponse(String processInstanceKey, String bpmnProcessId, Integer version, String status, String message, int statusCode, String exceptionMessage) {
        this.processInstanceKey = processInstanceKey;
        this.bpmnProcessId = bpmnProcessId;
        this.version = version;
        this.status = status;
        this.message = message;
        this.statusCode = statusCode;
        this.exceptionMessage = exceptionMessage;
    }

    public String getProcessInstanceKey() {
        return processInstanceKey;
    }

    public void setProcessInstanceKey(String processInstanceKey) {
        this.processInstanceKey = processInstanceKey;
    }

    public String getBpmnProcessId() {
        return bpmnProcessId;
    }

    public void setBpmnProcessId(String bpmnProcessId) {
        this.bpmnProcessId = bpmnProcessId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
