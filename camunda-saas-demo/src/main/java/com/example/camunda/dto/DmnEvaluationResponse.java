package com.example.camunda.dto;

import java.util.Map;

public class DmnEvaluationResponse {
    private String decisionId;
    private Map<String, Object> result;
    private String status;
    private String message;
    private int statusCode;
    private String exceptionMessage;

    public DmnEvaluationResponse() {
    }

    public DmnEvaluationResponse(String decisionId, Map<String, Object> result, String status, String message, int statusCode, String exceptionMessage) {
        this.decisionId = decisionId;
        this.result = result;
        this.status = status;
        this.message = message;
        this.statusCode = statusCode;
        this.exceptionMessage = exceptionMessage;
    }

    public String getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(String decisionId) {
        this.decisionId = decisionId;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
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
