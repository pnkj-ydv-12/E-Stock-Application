//package com.example.EStockMarketApplication.DTOs;
//
//import com.example.EStockMarketApplication.Models.Company;
//import com.example.EStockMarketApplication.Models.StockExchange;
//import com.example.EStockMarketApplication.Models.StockPrice;
//import org.junit.jupiter.api.Test;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//public class CompanyResponseDTOTest {
//    @Test
//    public void testConstructor() {
//        Company company = mock(Company.class);
//        when(company.getCompanyCode()).thenReturn(1L);
//        when(company.getCompanyName()).thenReturn("TestCompany");
//        when(company.getCompanyCEO()).thenReturn("TestCEO");
//        when(company.getCompanyTurnover()).thenReturn(100000);
//        when(company.getCompanyWebsite()).thenReturn("http://testcompany.com");
//        when(company.getStockExchange()).thenReturn(StockExchange.NSE);
//        List<StockPrice> stockPrices = List.of(new StockPrice());
//        when(company.getStockPrice()).thenReturn(stockPrices);
//        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(company);
//        assertEquals(1L, companyResponseDTO.getCompanyCode());
//        assertEquals("TestCompany", companyResponseDTO.getCompanyName());
//        assertEquals("TestCEO", companyResponseDTO.getCompanyCEO());
//        assertEquals(100000, companyResponseDTO.getCompanyTurnover());
//        assertEquals("http://testcompany.com", companyResponseDTO.getCompanyWebsite());
//        assertEquals(StockExchange.NSE, companyResponseDTO.getStockExchange());
//        assertNotNull(companyResponseDTO.getLatestStockPrice());
//    }
//    @Test
//    public void testGetLatestStockPrice() {
//        Company company = mock(Company.class);
//        List<StockPrice> stockPrices = List.of(
//                new StockPrice(),
//                new StockPrice(),
//                new StockPrice()
//        );
//        when(company.getStockPrice()).thenReturn(stockPrices);
//        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(company);
//        StockPrice latestStockPrice = companyResponseDTO.getLatestStockPrice();
//        assertEquals(stockPrices.get(stockPrices.size() - 1), latestStockPrice);
//    }
//}