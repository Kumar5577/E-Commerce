package com.example.ecommerce.CustomException;

public class EmailAlreadyFound extends Exception{
    public EmailAlreadyFound(String message) {
        super(message);
    }
}
