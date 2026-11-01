package com.example.camunda.service;

import com.example.camunda.dto.*;
import io.camunda.client.CamundaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.*;

@Service
public class UserTaskService {
    private static final Logger LOG = LoggerFactory.getLogger(UserTaskService.class);
    private static final String ERROR_CODE_VALIDATION = "VALIDATION_ERROR";
    private static final String ERROR_CODE_NOT_FOUND = "NOT_FOUND";
    private static final String ERROR_CODE_INTERNAL = "INTERNAL_ERROR";
    private static final String ERROR_CODE_TASK_OPERATION = "TASK_OPERATION_ERROR";

    private final CamundaClient camundaClient;
    private final RestTemplate restTemplate;

    @Value("${camunda.client.orchestration.base-url:}")
    private String orchestrationBaseUrl;

    @Value("${camunda.client.auth.client-id:}")
    private String clientId;

    @Value("${camunda.client.auth.client-secret:}")
    private String clientSecret;

    private String accessToken;
    private long tokenExpiryTime;

    public UserTaskService(CamundaClient camundaClient) {
        this.camundaClient = camundaClient;
        this.restTemplate = new RestTemplate();
    }

    public UserTaskResponse getActiveTasksByProcessDefinition(String bpmnProcessId) {
        try {
            if (bpmnProcessId == null || bpmnProcessId.trim().isEmpty()) {
                return new UserTaskResponse(
                    ERROR_CODE_VALIDATION,
                    "Process definition ID is required",
                    400,
                    "Validation failed",
                    null
                );
            }

            String url = orchestrationBaseUrl + "/user-tasks/search";
            LOG.info("Fetching active tasks for process definition ID: {}", bpmnProcessId);
            LOG.info("Orchestration base URL: {}", url);
            
            Map<String, Object> filter = Map.of(
                "state", "CREATED",
                "processDefinitionId", bpmnProcessId
            );
            Map<String, Object> body = Map.of(
                "filter", filter,
                "page", Map.of("limit", 50)
            );
            
            HttpHeaders headers = createHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Map.class
            );
            LOG.info("-----------------Response body {} ", response.getBody());
            Map<String, Object> responseBody = response.getBody();
            List<Map<String, Object>> tasks = (List<Map<String, Object>>) responseBody.get("items");
            
            if (tasks == null) {
                tasks = new ArrayList<>();
            }
            
            List<UserTaskResponse.UserTask> userTasks = mapToUserTaskList(tasks);

            return new UserTaskResponse(
                null,
                null,
                200,
                "Successfully retrieved active tasks for process definition: " + bpmnProcessId,
                userTasks
            );

        } catch (Exception e) {
            LOG.error("Error retrieving active tasks for process definition: {}", bpmnProcessId, e);
            return new UserTaskResponse(
                ERROR_CODE_INTERNAL,
                "Failed to retrieve active tasks: " + e.getMessage(),
                500,
                "Internal server error",
                null
            );
        }
    }

    public UserTaskResponse getTasksByAssignee(String assignee) {
        try {
            if (assignee == null || assignee.trim().isEmpty()) {
                return new UserTaskResponse(
                    ERROR_CODE_VALIDATION,
                    "Assignee is required",
                    400,
                    "Validation failed",
                    null
                );
            }

            String url = orchestrationBaseUrl + "/user-tasks/search";
            
            Map<String, Object> filter = Map.of(
                "state", "CREATED",
                "assignee", assignee
            );
            Map<String, Object> body = Map.of(
                "filter", filter,
                "page", Map.of("limit", 50)
            );
            
            HttpHeaders headers = createHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Map.class
            );

            Map<String, Object> responseBody = response.getBody();
            List<Map<String, Object>> tasks = (List<Map<String, Object>>) responseBody.get("items");
            
            if (tasks == null) {
                tasks = new ArrayList<>();
            }
            
            List<UserTaskResponse.UserTask> userTasks = mapToUserTaskList(tasks);

            return new UserTaskResponse(
                null,
                null,
                200,
                "Successfully retrieved tasks for assignee: " + assignee,
                userTasks
            );

        } catch (Exception e) {
            LOG.error("Error retrieving tasks for assignee: {}", assignee, e);
            return new UserTaskResponse(
                ERROR_CODE_INTERNAL,
                "Failed to retrieve tasks for assignee: " + e.getMessage(),
                500,
                "Internal server error",
                null
            );
        }
    }

    public UserTaskResponse getTasksByGroup(String group) {
        try {
            if (group == null || group.trim().isEmpty()) {
                return new UserTaskResponse(
                    ERROR_CODE_VALIDATION,
                    "Group is required",
                    400,
                    "Validation failed",
                    null
                );
            }

            String url = orchestrationBaseUrl + "/user-tasks/search";
            
            Map<String, Object> filter = Map.of(
                "state", "CREATED",
                "candidateGroup", group
            );
            Map<String, Object> body = Map.of(
                "filter", filter,
                "page", Map.of("limit", 50)
            );
            
            HttpHeaders headers = createHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Map.class
            );

            Map<String, Object> responseBody = response.getBody();
            List<Map<String, Object>> tasks = (List<Map<String, Object>>) responseBody.get("items");
            
            if (tasks == null) {
                tasks = new ArrayList<>();
            }
            
            List<UserTaskResponse.UserTask> userTasks = mapToUserTaskList(tasks);

            return new UserTaskResponse(
                null,
                null,
                200,
                "Successfully retrieved tasks for group: " + group,
                userTasks
            );

        } catch (Exception e) {
            LOG.error("Error retrieving tasks for group: {}", group, e);
            return new UserTaskResponse(
                ERROR_CODE_INTERNAL,
                "Failed to retrieve tasks for group: " + e.getMessage(),
                500,
                "Internal server error",
                null
            );
        }
    }

    public TaskActionResponse claimTask(ClaimTaskRequest request) {
        try {
            if (request.getUserTaskKey() == null || request.getUserTaskKey().trim().isEmpty()) {
                return new TaskActionResponse(
                    ERROR_CODE_VALIDATION,
                    "User task key is required",
                    400,
                    "Validation failed",
                    null
                );
            }

            if (request.getAssignee() == null || request.getAssignee().trim().isEmpty()) {
                return new TaskActionResponse(
                    ERROR_CODE_VALIDATION,
                    "Assignee is required",
                    400,
                    "Validation failed",
                    null
                );
            }

            String url = orchestrationBaseUrl + "/user-tasks/" + request.getUserTaskKey() + "/claim";
            HttpHeaders headers = createHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> body = new HashMap<>();
            body.put("assignee", request.getAssignee());
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
            );

            return new TaskActionResponse(
                null,
                null,
                200,
                "Task claimed successfully",
                request.getUserTaskKey()
            );

        } catch (Exception e) {
            LOG.error("Error claiming task: {}", request.getUserTaskKey(), e);
            return new TaskActionResponse(
                ERROR_CODE_TASK_OPERATION,
                "Failed to claim task: " + e.getMessage(),
                500,
                "Task operation failed",
                null
            );
        }
    }

    public TaskActionResponse reassignTask(ReassignTaskRequest request) {
        try {
            if (request.getUserTaskKey() == null || request.getUserTaskKey().trim().isEmpty()) {
                return new TaskActionResponse(
                    ERROR_CODE_VALIDATION,
                    "User task key is required",
                    400,
                    "Validation failed",
                    null
                );
            }

            if (request.getNewAssignee() == null || request.getNewAssignee().trim().isEmpty()) {
                return new TaskActionResponse(
                    ERROR_CODE_VALIDATION,
                    "New assignee is required",
                    400,
                    "Validation failed",
                    null
                );
            }

            String url = orchestrationBaseUrl + "/user-tasks/" + request.getUserTaskKey() + "/assign";
            HttpHeaders headers = createHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> body = new HashMap<>();
            body.put("assignee", request.getNewAssignee());
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
            );

            return new TaskActionResponse(
                null,
                null,
                200,
                "Task reassigned successfully to: " + request.getNewAssignee(),
                request.getUserTaskKey()
            );

        } catch (Exception e) {
            LOG.error("Error reassigning task: {}", request.getUserTaskKey(), e);
            return new TaskActionResponse(
                ERROR_CODE_TASK_OPERATION,
                "Failed to reassign task: " + e.getMessage(),
                500,
                "Task operation failed",
                null
            );
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = getAccessToken();
        if (token != null) {
            headers.setBearerAuth(token);
        }
        return headers;
    }

    private String getAccessToken() {
        if (accessToken != null && System.currentTimeMillis() < tokenExpiryTime) {
            return accessToken;
        }

        try {
            String tokenUrl = "https://login.cloud.camunda.io/oauth/token";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // Use zeebe.camunda.io as audience for Orchestration Cluster API
            String body = "grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret + "&audience=zeebe.camunda.io";
            
            HttpEntity<String> entity = new HttpEntity<>(body, headers);
            
            ResponseEntity<Map> response = restTemplate.exchange(
                tokenUrl,
                HttpMethod.POST,
                entity,
                Map.class
            );

            Map<String, Object> responseBody = response.getBody();
            accessToken = (String) responseBody.get("access_token");
            Integer expiresIn = (Integer) responseBody.get("expires_in");
            tokenExpiryTime = System.currentTimeMillis() + (expiresIn * 1000) - 60000; // Refresh 1 minute before expiry
            
            LOG.info("Successfully obtained OAuth token, expires in {} seconds", expiresIn);
            return accessToken;
        } catch (Exception e) {
            LOG.error("Failed to get access token from: https://login.cloud.camunda.io/oauth/token", e);
            return null;
        }
    }

    private List<UserTaskResponse.UserTask> mapToUserTaskList(List<Map<String, Object>> tasks) {
        List<UserTaskResponse.UserTask> userTasks = new ArrayList<>();
        for (Map<String, Object> task : tasks) {
            userTasks.add(mapToUserTask(task));
        }
        return userTasks;
    }

    private UserTaskResponse.UserTask mapToUserTask(Map<String, Object> task) {
        return new UserTaskResponse.UserTask(
            String.valueOf(task.getOrDefault("userTaskKey", "")),
            String.valueOf(task.getOrDefault("processInstanceKey", "")),
            String.valueOf(task.getOrDefault("processDefinitionId", "")),
            String.valueOf(task.getOrDefault("elementId", "")),
            String.valueOf(task.getOrDefault("assignee", "")),
            String.valueOf(task.getOrDefault("state", "CREATED")),
            task.get("dueDate") != null ? task.get("dueDate").toString() : null,
            task.get("candidateGroups") != null ? task.get("candidateGroups").toString() : null
        );
    }
}
