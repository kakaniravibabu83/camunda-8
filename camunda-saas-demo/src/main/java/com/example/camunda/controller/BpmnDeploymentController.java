package com.example.camunda.controller;

import com.example.camunda.dto.DeploymentRequest;
import com.example.camunda.dto.DeploymentResponse;
import com.example.camunda.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bpmn")
public class BpmnDeploymentController {

    private final DeploymentService deploymentService;

    @Autowired
    public BpmnDeploymentController(DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @PostMapping("/deploy")
    public ResponseEntity<DeploymentResponse> deployBpmnModel(@RequestBody DeploymentRequest request) {
        DeploymentResponse response = deploymentService.deployBpmnModel(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
