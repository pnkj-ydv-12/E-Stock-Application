//package com.example.EStockMarketApplication.Models;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//public class StockPriceTests {
//    private StockPrice stockPrice;
//    @BeforeEach
//    public void setUp() {
//        stockPrice = new StockPrice();
//    }
//    @Test
//    public void testCreateStockPrice() {
//        Company mockCompany = mock(Company.class);
//        when(mockCompany.getCompanyCode()).thenReturn(1L);
//        stockPrice.setPrice(new BigDecimal("100.00"));
//        stockPrice.setCompany(mockCompany);
//        stockPrice.onCreate();
//        assertEquals(1L, stockPrice.getCompany().getCompanyCode());
//        assertEquals(new BigDecimal("100.00"), stockPrice.getPrice());
//        assertNotNull(stockPrice.getTimeStamp());
//    }
//}