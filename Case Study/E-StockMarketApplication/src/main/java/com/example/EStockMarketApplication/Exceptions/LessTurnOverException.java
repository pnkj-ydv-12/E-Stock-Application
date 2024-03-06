package com.example.EStockMarketApplication.Exceptions;

public class LessTurnOverException extends RuntimeException {
    public LessTurnOverException(String Message)
    {
        super(Message);
    }
}
