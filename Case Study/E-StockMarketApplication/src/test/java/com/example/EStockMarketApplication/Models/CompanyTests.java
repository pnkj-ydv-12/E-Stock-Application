//package com.example.EStockMarketApplication.Models;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//class CompanyTests {
//    private Company company;
//    @BeforeEach
//    void setUp() {
//        company = new Company();
//    }
//    @Test
//    void testGetAndSetCompanyCode() {
//        Long expectedCompanyCode = 1L;
//        company.setCompanyCode(expectedCompanyCode);
//        Long actualCompanyCode = company.getCompanyCode();
//        assertEquals(expectedCompanyCode, actualCompanyCode);
//    }
//    @Test
//    void testGetAndSetCompanyName() {
//        String expectedCompanyName = "ABC Corp";
//        company.setCompanyName(expectedCompanyName);
//        String actualCompanyName = company.getCompanyName();
//        assertEquals(expectedCompanyName, actualCompanyName);
//    }
//    @Test
//    void testGetAndSetCompanyCEO() {
//        // Arrange
//        String expectedCompanyCEO = "John Jacob";
//        company.setCompanyCEO(expectedCompanyCEO);
//        String actualCompanyCEO = company.getCompanyCEO();
//        assertEquals(expectedCompanyCEO, actualCompanyCEO);
//    }
//    @Test
//    void testGetAndSetCompanyTurnover() {
//        Integer expectedCompanyTurnover = 1000000;
//        company.setCompanyTurnover(expectedCompanyTurnover);
//        Integer actualCompanyTurnover = company.getCompanyTurnover();
//        assertEquals(expectedCompanyTurnover, actualCompanyTurnover);
//    }
//    @Test
//    void testGetAndSetCompanyWebsite() {
//        String expectedCompanyWebsite = "http://www.abc.com";
//        company.setCompanyWebsite(expectedCompanyWebsite);
//        String actualCompanyWebsite = company.getCompanyWebsite();
//        assertEquals(expectedCompanyWebsite, actualCompanyWebsite);
//    }
//    @Test
//    void testGetAndSetStockExchange() {
//        StockExchange expectedStockExchange = StockExchange.NSE;
//        company.setStockExchange(expectedStockExchange);
//        StockExchange actualStockExchange = company.getStockExchange();
//        assertEquals(expectedStockExchange, actualStockExchange);
//    }
//    @Test
//    void testGetAndSetStockPrice() {
//        List<StockPrice> expectedStockPriceList = new ArrayList<>();
//        StockPrice stockPrice = new StockPrice();
//        expectedStockPriceList.add(stockPrice);
//        company.setStockPrice(expectedStockPriceList);
//        List<StockPrice> actualStockPriceList = company.getStockPrice();
//        assertEquals(expectedStockPriceList, actualStockPriceList);
//    }
//    @Test
//    void testGetAndSetLatestStockPrice() {
//        StockPrice expectedLatestStockPrice = new StockPrice();
//        company.setLatestStockPrice(expectedLatestStockPrice);
//        StockPrice actualLatestStockPrice = company.getLatestStockPrice();
//        assertEquals(expectedLatestStockPrice, actualLatestStockPrice);
//    }
//    @Test
//    void testAddStockPrice() {
//        StockPrice stockPrice = new StockPrice();
//        stockPrice.setCompany(company);
//        List<StockPrice> stockPrices = new ArrayList<>();
//        stockPrices.add(stockPrice);
//        company.setStockPrice(stockPrices);
//        assertEquals(1, company.getStockPrice().size());
//        assertTrue(company.getStockPrice().contains(stockPrice));
//        assertEquals(company, stockPrice.getCompany());
//    }
//}