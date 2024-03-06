//package com.example.EStockMarketApplication.Controller;
//import com.example.EStockMarketApplication.Controllers.StockPriceController;
//import com.example.EStockMarketApplication.Feign.AuthorizeClient;
//import com.example.EStockMarketApplication.Models.StockPrice;
//import com.example.EStockMarketApplication.Service.StockPriceService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//class StockPriceControllerTest {
//    @Mock
//    private StockPriceService stockPriceService;
//    @Mock
//    private AuthorizeClient authorizeClient;
//    @InjectMocks
//    private StockPriceController stockPriceController;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//    // Test for addNewPrice method
//    @Test
//    void addNewPrice_WithValidCompanyCodeAdminRole_ReturnsSuccessMessage() {
//        when(authorizeClient.authorize(anyString())).thenReturn(true);
//        when(authorizeClient.getrole(anyString())).thenReturn("admin");
//        doNothing().when(stockPriceService).UpdateStockPrice(anyLong(), any(StockPrice.class));
//        ResponseEntity<String> response = stockPriceController.addNewPrice(1L, new StockPrice(), "valid-token");
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("New Price Updated for the Company", response.getBody());
//        verify(authorizeClient, times(1)).authorize(anyString());
//        verify(authorizeClient, times(1)).getrole(anyString());
//        verify(stockPriceService, times(1)).UpdateStockPrice(anyLong(), any(StockPrice.class));
//    }
//    @Test
//    void addNewPrice_WithInvalidRole_ReturnsBadRequest() {
//        when(authorizeClient.authorize(anyString())).thenReturn(true);
//        when(authorizeClient.getrole(anyString())).thenReturn("user");
//        ResponseEntity<String> response = stockPriceController.addNewPrice(1L, new StockPrice(), "valid-token");
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        verify(authorizeClient, times(1)).authorize(anyString());
//        verify(authorizeClient, times(1)).getrole(anyString());
//        verify(stockPriceService, never()).UpdateStockPrice(anyLong(), any(StockPrice.class));
//    }
//    // Additional test cases can be added for other scenarios and edge cases
//}