package com.example.camunda;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class PaymentWorker {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentWorker.class);

    public record PaymentResult(boolean paymentSuccess, String transactionId) {}

    @JobWorker(type = "process-payment")
    public PaymentResult processPayment(@Variable String orderId, @Variable Double amount) {
        LOG.info("Processing payment for order: {} for ${}", orderId, amount);
        
        boolean isSuccess = amount != null && amount > 0;
        String transactionId = UUID.randomUUID().toString();
        
        LOG.info("Payment successful: {}, Transaction ID: {}", isSuccess, transactionId);
        return new PaymentResult(isSuccess, transactionId);
    }
}
