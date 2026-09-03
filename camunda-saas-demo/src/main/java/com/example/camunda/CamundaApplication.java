package com.example.camunda;

import io.camunda.zeebe.client.ZeebeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Map;

@SpringBootApplication
public class CamundaApplication  {
//implements CommandLineRunner
    private static final Logger LOG = LoggerFactory.getLogger(CamundaApplication.class);
    private final ZeebeClient zeebeClient;

    public CamundaApplication(ZeebeClient zeebeClient) {
        this.zeebeClient = zeebeClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(CamundaApplication.class, args);
    }

//    @Override
//    public void run(String... args) {
//        try {
//            var event = zeebeClient.newCreateInstanceCommand()
//                    .bpmnProcessId("payment-process")
//                    .latestVersion()
//                    .variables(Map.of("orderId", "ORD-2026", "amount", 150.75))
//                    .send()
//                    .join();
//            LOG.info("Started process instance: {}", event.getProcessInstanceKey());
//        } catch (Exception e) {
//            LOG.warn("Could not start instance. Ensure 'payment-process' is deployed. Error: {}", e.getMessage());
//        }
//    }
}
