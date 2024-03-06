package com.example.EStockMarketApplication.Service;

import com.example.EStockMarketApplication.Models.StockPrice;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public interface StockPriceService {
    void UpdateStockPrice(long companyCode, StockPrice newPrice);
    List<StockPrice> getAllStockPrices(long companyCode);

}
