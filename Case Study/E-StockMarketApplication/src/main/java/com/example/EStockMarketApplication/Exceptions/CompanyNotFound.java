package com.example.EStockMarketApplication.Exceptions;

public class CompanyNotFound extends RuntimeException{
    public CompanyNotFound(String Message)
    {
        super(Message);
    }
}
