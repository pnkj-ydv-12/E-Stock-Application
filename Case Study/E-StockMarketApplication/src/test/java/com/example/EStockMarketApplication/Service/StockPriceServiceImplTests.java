//package com.example.EStockMarketApplication.Service;
//
//import com.example.EStockMarketApplication.Exceptions.CompanyNotFound;
//import com.example.EStockMarketApplication.Models.Company;
//import com.example.EStockMarketApplication.Models.StockPrice;
//import com.example.EStockMarketApplication.Repository.CompanyRepository;
//import com.example.EStockMarketApplication.Repository.StockPriceRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class StockPriceServiceImplTests {
//    @Mock
//    private CompanyService companyService;
//    @Mock
//    private StockPriceRepository stockPriceRepository;
//    @Mock
//    private CompanyRepository companyRepository;
//    @InjectMocks
//    private StockPriceServiceImpl stockPriceService;
//    @BeforeEach
//    public void setUp() {
//        stockPriceService = new StockPriceServiceImpl(companyService, stockPriceRepository, companyRepository);
//    }
//    @Test
//    public void testUpdateStockPriceHappy() {
//        long companyCode = 1L;
//        BigDecimal newStockPriceValue = BigDecimal.valueOf(100.0);
//        StockPrice newPrice = new StockPrice();
//        newPrice.setPrice(newStockPriceValue);
//        Company company = new Company();
//        company.setCompanyCode(companyCode);
//        List<StockPrice> stockPrices = new ArrayList<>();
//        company.setStockPrice(stockPrices);
//
//        when(companyRepository.findById(companyCode)).thenReturn(Optional.of(company));
//        stockPriceService.UpdateStockPrice(companyCode, newPrice);
//        assertEquals(1, stockPrices.size());
//        assertEquals(newStockPriceValue, stockPrices.get(0).getPrice());
//    }
//    @Test
//    public void testUpdateStockPriceCompanyNotFound() {
//        long companyCode = 2L;
//        when(companyRepository.findById(companyCode)).thenReturn(Optional.empty());
//        StockPrice newPrice = new StockPrice();
//        assertThrows(CompanyNotFound.class, () -> stockPriceService.UpdateStockPrice(companyCode, newPrice));
//    }
//}