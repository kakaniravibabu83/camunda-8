package com.example.camunda.dto;

import java.util.List;

public class UserTaskResponse {
    private String errorCode;
    private String errorMessage;
    private int statusCode;
    private String message;
    private List<UserTask> userTasks;

    public UserTaskResponse() {
    }

    public UserTaskResponse(String errorCode, String errorMessage, int statusCode, String message, List<UserTask> userTasks) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
        this.message = message;
        this.userTasks = userTasks;
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

    public List<UserTask> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(List<UserTask> userTasks) {
        this.userTasks = userTasks;
    }

    public static class UserTask {
        private String userTaskKey;
        private String processInstanceKey;
        private String bpmnProcessId;
        private String elementId;
        private String assignee;
        private String state;
        private String dueDate;
        private String candidateGroups;

        public UserTask() {
        }

        public UserTask(String userTaskKey, String processInstanceKey, String bpmnProcessId, String elementId, String assignee, String state, String dueDate, String candidateGroups) {
            this.userTaskKey = userTaskKey;
            this.processInstanceKey = processInstanceKey;
            this.bpmnProcessId = bpmnProcessId;
            this.elementId = elementId;
            this.assignee = assignee;
            this.state = state;
            this.dueDate = dueDate;
            this.candidateGroups = candidateGroups;
        }

        public String getUserTaskKey() {
            return userTaskKey;
        }

        public void setUserTaskKey(String userTaskKey) {
            this.userTaskKey = userTaskKey;
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

        public String getElementId() {
            return elementId;
        }

        public void setElementId(String elementId) {
            this.elementId = elementId;
        }

        public String getAssignee() {
            return assignee;
        }

        public void setAssignee(String assignee) {
            this.assignee = assignee;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

        public String getCandidateGroups() {
            return candidateGroups;
        }

        public void setCandidateGroups(String candidateGroups) {
            this.candidateGroups = candidateGroups;
        }
    }
}
