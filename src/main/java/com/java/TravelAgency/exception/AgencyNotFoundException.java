package com.java.TravelAgency.exception;

public class AgencyNotFoundException extends RuntimeException{

    public AgencyNotFoundException( String message){
        super(message);
    }
}
