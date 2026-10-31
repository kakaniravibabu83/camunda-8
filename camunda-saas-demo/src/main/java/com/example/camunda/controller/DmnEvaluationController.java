package com.example.camunda.controller;

import com.example.camunda.dto.DmnEvaluationRequest;
import com.example.camunda.dto.DmnEvaluationResponse;
import com.example.camunda.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dmn")
public class DmnEvaluationController {

    private final DeploymentService deploymentService;

    @Autowired
    public DmnEvaluationController(DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @PostMapping("/evaluate")
    public ResponseEntity<DmnEvaluationResponse> evaluateDmnDecision(@RequestBody DmnEvaluationRequest request) {
        DmnEvaluationResponse response = deploymentService.evaluateDmnDecision(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
