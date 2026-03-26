package com.runbook.gateway;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/logs")
public class LogIngestionController {

    private final RabbitTemplate rabbitTemplate;
    
    //@Value("${runbook.queue.name}")
    //private String queueName;
    private final String queueName = "log.ingestion.queue";

    // Constructor injection is a best practice for Spring
    public LogIngestionController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/ingest")
    public ResponseEntity<String> ingestLog(@RequestBody LogEvent logEvent) {
        
        if (logEvent.getServiceName() == null || logEvent.getErrorMessage() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Missing required fields: serviceName or errorMessage");
        }

        // Send the object to RabbitMQ!
        rabbitTemplate.convertAndSend(queueName, logEvent);

        System.out.println("✅ Published log to RabbitMQ. Trace ID: " + logEvent.getTraceId());

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Log queued for AI processing. Trace ID: " + logEvent.getTraceId());
    }
}