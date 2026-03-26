package com.runbook.ai_analyzer_service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class LogConsumer {

    // Yeh line lagatar queue ko check karti rahegi
    @RabbitListener(queues = "log.ingestion.queue")
    public void consumeLog(LogEvent logEvent) {
        System.out.println("🚨 NAYA LOG AAYA HAI! 🚨");
        System.out.println("Service: " + logEvent.getServiceName());
        System.out.println("Error: " + logEvent.getErrorMessage());
        System.out.println("AI Analysis shuru ho rahi hai...\n");
        
        // Next step mein hum yahan AI/LLM ka code likhenge!
    }
}