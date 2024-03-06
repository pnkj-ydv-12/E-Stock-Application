//package com.example.EStockMarketApplication.Controller;
//import com.example.EStockMarketApplication.Controllers.CompanyController;
//import com.example.EStockMarketApplication.DTOs.CompanyResponseDTO;
//import com.example.EStockMarketApplication.Exceptions.CompanyNotFound;
//import com.example.EStockMarketApplication.Feign.AuthorizeClient;
//import com.example.EStockMarketApplication.Models.Company;
//import com.example.EStockMarketApplication.Service.CompanyService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import java.util.Collections;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//class CompanyControllerTest {
//    @Mock
//    private CompanyService companyService;
//    @Mock
//    private AuthorizeClient authorizeClient;
//    @InjectMocks
//    private CompanyController companyController;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getAllCompanies_WithValidToken_ReturnsListOfCompanies() {
//        when(authorizeClient.authorize(anyString())).thenReturn(true);
//        when(companyService.getAllCompanies()).thenReturn(Collections.singletonList(new CompanyResponseDTO()));
//        ResponseEntity<?> response = companyController.getAllCompanies("valid-token");
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(authorizeClient, times(1)).authorize(anyString());
//        verify(companyService, times(1)).getAllCompanies();
//    }
//    @Test
//    void getAllCompanies_WithInvalidToken_ReturnsBadRequest() {
//        when(authorizeClient.authorize(anyString())).thenReturn(false);
//        ResponseEntity<?> response = companyController.getAllCompanies("invalid-token");
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        verify(authorizeClient, times(1)).authorize(anyString());
//        verify(companyService, never()).getAllCompanies();
//    }
//    @Test
//    void registerCompany_WithValidTokenAndAdminRole_ReturnsRegisteredCompany() {
//        when(authorizeClient.authorize(anyString())).thenReturn(true);
//        when(authorizeClient.getrole(anyString())).thenReturn("admin");
//        when(companyService.RegisterCompany(any(Company.class))).thenReturn(new Company());
//        ResponseEntity<?> response = companyController.RegisterCompany(new Company(), "valid-token");
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(authorizeClient, times(1)).authorize(anyString());
//        verify(authorizeClient, times(1)).getrole(anyString());
//        verify(companyService, times(1)).RegisterCompany(any(Company.class));
//    }
//    @Test
//    void registerCompany_WithInvalidRole_ReturnsBadRequest() {
//        when(authorizeClient.authorize(anyString())).thenReturn(true);
//        when(authorizeClient.getrole(anyString())).thenReturn("user");
//        ResponseEntity<?> response = companyController.RegisterCompany(new Company(), "valid-token");
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        verify(authorizeClient, times(1)).authorize(anyString());
//        verify(authorizeClient, times(1)).getrole(anyString());
//        verify(companyService, never()).RegisterCompany(any(Company.class));
//    }
//
//    @Test
//    void updateCompany_WithValidCompanyCodeAndAdminRole_ReturnsSuccessMessage() {
//        when(authorizeClient.authorize(anyString())).thenReturn(true);
//        when(authorizeClient.getrole(anyString())).thenReturn("admin");
//        doNothing().when(companyService).UpdateCompany(anyLong(), any(Company.class));
//        ResponseEntity<String> response = companyController.updateCompany(1L, new Company(), "valid-token");
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Company Details Updated Successfully", response.getBody());
//        verify(authorizeClient, times(1)).authorize(anyString());
//        verify(authorizeClient, times(1)).getrole(anyString());
//        verify(companyService, times(1)).UpdateCompany(anyLong(), any(Company.class));
//    }
//    @Test
//    void updateCompany_WithInvalidRole_ReturnsBadRequest() {
//        when(authorizeClient.authorize(anyString())).thenReturn(true);
//        when(authorizeClient.getrole(anyString())).thenReturn("user");
//        ResponseEntity<String> response = companyController.updateCompany(1L, new Company(), "valid-token");
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        verify(authorizeClient, times(1)).authorize(anyString());
//        verify(authorizeClient, times(1)).getrole(anyString());
//        verify(companyService, never()).UpdateCompany(anyLong(), any(Company.class));
//    }
//    @Test
//    void updateCompany_WhenCompanyNotFound_ReturnsNotFound() {
//        when(authorizeClient.authorize(anyString())).thenReturn(true);
//        when(authorizeClient.getrole(anyString())).thenReturn("admin");
//        doThrow(new CompanyNotFound("Company not found")).when(companyService).UpdateCompany(anyLong(), any(Company.class));
//        ResponseEntity<String> response = companyController.updateCompany(1L, new Company(), "valid-token");
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals("Company Not Found with ID:1", response.getBody());
//        verify(authorizeClient, times(1)).authorize(anyString());
//        verify(authorizeClient, times(1)).getrole(anyString());
//        verify(companyService, times(1)).UpdateCompany(anyLong(), any(Company.class));
//    }
//    // Test for deleteCompany method
////    @Test
////    void deleteCompany_WithValidCompanyCodeAndAdminRole_ReturnsDeletedCompany() {
////        when(authorizeClient.authorize(anyString())).thenReturn(true);
////        when(authorizeClient.getrole(anyString())).thenReturn("admin");
////        when(companyService.deleteCompany(anyLong())).thenReturn(company);
////
////        ResponseEntity<?> response = companyController.DeleteCompany(1L, "valid-token");
////
////        assertEquals(HttpStatus.OK, response.getStatusCode());
////        verify(authorizeClient, times(1)).authorize(anyString());
////        verify(authorizeClient, times(1)).getrole(anyString());
////        verify(companyService, times(1)).deleteCompany(anyLong());
////    }
//    @Test
//    void deleteCompany_WithInvalidRole_ReturnsBadRequest() {
//        when(authorizeClient.authorize(anyString())).thenReturn(true);
//        when(authorizeClient.getrole(anyString())).thenReturn("user");
//        ResponseEntity<?> response = companyController.DeleteCompany(1L, "valid-token");
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        verify(authorizeClient, times(1)).authorize(anyString());
//        verify(authorizeClient, times(1)).getrole(anyString());
//        verify(companyService, never()).deleteCompany(anyLong());
//    }
//}