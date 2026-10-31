package com.example.camunda.service;

import com.example.camunda.dto.DeploymentRequest;
import com.example.camunda.dto.DeploymentResponse;
import io.camunda.client.CamundaClient;
import io.camunda.client.api.response.DeploymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class DeploymentService {

    private final CamundaClient camundaClient;

    @Autowired
    public DeploymentService(CamundaClient camundaClient) {
        this.camundaClient = camundaClient;
    }

    public DeploymentResponse deployBpmnModel(DeploymentRequest request) {
        try {
            byte[] bpmnBytes = request.getFileContent().getBytes(StandardCharsets.UTF_8);
            
            DeploymentEvent deploymentEvent = camundaClient.newDeployResourceCommand()
                    .addResourceBytes(bpmnBytes, request.getResourceName())
                    .execute();

            return new DeploymentResponse(
                    String.valueOf(deploymentEvent.getKey()),
                    request.getDeploymentName(),
                    "SUCCESS",
                    "BPMN model deployed successfully",
                    200,
                    null
            );
        } catch (Exception e) {
            return new DeploymentResponse(
                    null,
                    request.getDeploymentName(),
                    "FAILED",
                    "Failed to deploy BPMN model: " + e.getMessage(),
                    500,
                    e.getClass().getName() + ": " + e.getMessage()
            );
        }
    }

    public DeploymentResponse deployDmnModel(DeploymentRequest request) {
        try {
            byte[] dmnBytes = request.getFileContent().getBytes(StandardCharsets.UTF_8);
            
            DeploymentEvent deploymentEvent = camundaClient.newDeployResourceCommand()
                    .addResourceBytes(dmnBytes, request.getResourceName())
                    .execute();

            return new DeploymentResponse(
                    String.valueOf(deploymentEvent.getKey()),
                    request.getDeploymentName(),
                    "SUCCESS",
                    "DMN model deployed successfully",
                    200,
                    null
            );
        } catch (Exception e) {
            return new DeploymentResponse(
                    null,
                    request.getDeploymentName(),
                    "FAILED",
                    "Failed to deploy DMN model: " + e.getMessage(),
                    500,
                    e.getClass().getName() + ": " + e.getMessage()
            );
        }
    }
}
