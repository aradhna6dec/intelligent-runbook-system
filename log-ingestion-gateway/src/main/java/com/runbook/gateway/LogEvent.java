package com.runbook.gateway;

import java.time.Instant;
import java.util.UUID;

public class LogEvent {
    
    private String traceId;
    private String serviceName;
    private String level;
    private String errorMessage;
    private String stackTrace;
    private Instant timestamp;

    public LogEvent() {
        // Automatically generate a trace ID and timestamp if the client doesn't provide them
        this.traceId = UUID.randomUUID().toString();
        this.timestamp = Instant.now(); 
    }

    // --- Getters and Setters ---
    public String getTraceId() { return traceId; }
    public void setTraceId(String traceId) { this.traceId = traceId; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public String getStackTrace() { return stackTrace; }
    public void setStackTrace(String stackTrace) { this.stackTrace = stackTrace; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}