package com.example.camunda.controller;

import com.example.camunda.dto.*;
import com.example.camunda.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-tasks")
public class UserTaskController {

    private final UserTaskService userTaskService;

    @Autowired
    public UserTaskController(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    @GetMapping("/process-definition/{bpmnProcessId}")
    public ResponseEntity<UserTaskResponse> getActiveTasksByProcessDefinition(
            @PathVariable String bpmnProcessId) {
        UserTaskResponse response = userTaskService.getActiveTasksByProcessDefinition(bpmnProcessId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/assignee/{assignee}")
    public ResponseEntity<UserTaskResponse> getTasksByAssignee(
            @PathVariable String assignee) {
        UserTaskResponse response = userTaskService.getTasksByAssignee(assignee);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/group/{group}")
    public ResponseEntity<UserTaskResponse> getTasksByGroup(
            @PathVariable String group) {
        UserTaskResponse response = userTaskService.getTasksByGroup(group);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/claim")
    public ResponseEntity<TaskActionResponse> claimTask(
            @RequestBody ClaimTaskRequest request) {
        TaskActionResponse response = userTaskService.claimTask(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/reassign")
    public ResponseEntity<TaskActionResponse> reassignTask(
            @RequestBody ReassignTaskRequest request) {
        TaskActionResponse response = userTaskService.reassignTask(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
