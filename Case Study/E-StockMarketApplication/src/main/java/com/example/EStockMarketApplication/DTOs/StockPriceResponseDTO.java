package com.example.EStockMarketApplication.DTOs;

import com.example.EStockMarketApplication.Models.StockPrice;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class StockPriceResponseDTO {

    private Long id;
    private BigDecimal price;
    private LocalDateTime timeStamp;
    private Long companyCode;  // Assuming you want to include the company ID in the response

    public StockPriceResponseDTO() {
    }

    public StockPriceResponseDTO(StockPrice stockPrice) {
        this.id = stockPrice.getid();
        this.price = stockPrice.getPrice();
        this.timeStamp = stockPrice.getTimeStamp();
        this.companyCode = stockPrice.getCompany().getCompanyCode();  // Assuming Company has a 'getCompanyCode' method
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getCompanyCode() {
        return companyCode;
    }

    public void setCompanyId(Long companyId) {
        this.companyCode = companyId;
    }
}
