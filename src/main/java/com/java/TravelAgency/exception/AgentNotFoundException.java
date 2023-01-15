package com.java.TravelAgency.exception;

public class AgentNotFoundException  extends RuntimeException {

    public AgentNotFoundException(String message) {
        super(message);
    }
}