package com.example.camunda;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentWorkerTest {

    @Test
    void shouldReturnSuccessfulPaymentResultForValidAmount() {
        PaymentWorker worker = new PaymentWorker();
        PaymentWorker.PaymentResult result = worker.processPayment("ORD-100", 250.0);
        
        assertTrue(result.paymentSuccess(), "Payment should be successful for positive amounts");
        assertNotNull(result.transactionId(), "Transaction ID must be generated");
    }

    @Test
    void shouldFailPaymentForZeroAmount() {
        PaymentWorker worker = new PaymentWorker();
        PaymentWorker.PaymentResult result = worker.processPayment("ORD-101", 0.0);
        
        assertFalse(result.paymentSuccess(), "Payment should fail for zero amount");
    }
}
