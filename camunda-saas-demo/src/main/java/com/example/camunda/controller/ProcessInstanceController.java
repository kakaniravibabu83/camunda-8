package com.example.camunda.controller;

import com.example.camunda.dto.ProcessInstanceRequest;
import com.example.camunda.dto.ProcessInstanceResponse;
import com.example.camunda.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/process-instance")
public class ProcessInstanceController {

    private final DeploymentService deploymentService;

    @Autowired
    public ProcessInstanceController(DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @PostMapping("/start")
    public ResponseEntity<ProcessInstanceResponse> startProcessInstance(@RequestBody ProcessInstanceRequest request) {
        ProcessInstanceResponse response = deploymentService.startProcessInstance(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
