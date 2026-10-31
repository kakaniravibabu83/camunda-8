package com.example.camunda.dto;

import java.util.Map;

public class ProcessInstanceRequest {
    private String bpmnProcessId;
    private Integer version;
    private Map<String, Object> variables;

    public ProcessInstanceRequest() {
    }

    public ProcessInstanceRequest(String bpmnProcessId, Integer version, Map<String, Object> variables) {
        this.bpmnProcessId = bpmnProcessId;
        this.version = version;
        this.variables = variables;
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

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }
}
