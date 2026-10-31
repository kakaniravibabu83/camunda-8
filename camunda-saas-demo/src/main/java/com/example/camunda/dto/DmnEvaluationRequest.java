package com.example.camunda.dto;

import java.util.Map;

public class DmnEvaluationRequest {
    private String decisionId;
    private Map<String, Object> variables;

    public DmnEvaluationRequest() {
    }

    public DmnEvaluationRequest(String decisionId, Map<String, Object> variables) {
        this.decisionId = decisionId;
        this.variables = variables;
    }

    public String getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(String decisionId) {
        this.decisionId = decisionId;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }
}
