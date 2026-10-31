package com.example.camunda;

import io.camunda.client.CamundaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamundaApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(CamundaApplication.class);
    private final CamundaClient camundaClient;

    public CamundaApplication(CamundaClient camundaClient) {
        this.camundaClient = camundaClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(CamundaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            var topology = camundaClient.newTopologyRequest().send().join();
            LOG.info("Connected to Camunda cluster successfully");
            LOG.info("Topology: {}", topology);
        } catch (Exception e) {
            LOG.error("Failed to connect to Camunda cluster. Please verify your credentials and cluster details.", e);
        }
    }
}
