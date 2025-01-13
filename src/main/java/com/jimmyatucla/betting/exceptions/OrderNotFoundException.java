package com.jimmyatucla.betting.exceptions;



public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
} 
