package com.munaf.A23_STOCK_TRADING_GRPC.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
