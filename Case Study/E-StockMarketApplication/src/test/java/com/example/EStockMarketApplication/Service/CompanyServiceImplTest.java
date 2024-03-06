//package com.example.EStockMarketApplication.Service;
//
//import com.example.EStockMarketApplication.DTOs.CompanyResponseDTO;
//import com.example.EStockMarketApplication.Exceptions.CompanyNotFound;
//import com.example.EStockMarketApplication.Models.Company;
//import com.example.EStockMarketApplication.Repository.CompanyRepository;
//import com.example.EStockMarketApplication.Repository.StockPriceRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class CompanyServiceImplTest {
//    @Mock
//    private CompanyRepository companyRepository;
//
//    @Mock
//    private StockPriceRepository stockPriceRepository;
//
//    @InjectMocks
//    private CompanyServiceImpl companyService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testRegisterCompany() {
//        Company company = new Company();
//        company.setCompanyTurnover(100000000);
//
//        when(companyRepository.save(any(Company.class))).thenReturn(company);
//        Company result = companyService.RegisterCompany(company);
//        assertNotNull(result);
//        assertEquals(company, result);
//    }
//
//    @Test
//    void testGetAllCompanies() {
//        when(companyRepository.findAll()).thenReturn(new ArrayList<>());
//        List<CompanyResponseDTO> result = companyService.getAllCompanies();
//
//        assertNotNull(result);
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    void testGetCompanyByID() {
//        Long companyCode = 1L;
//        Company company = new Company();
//
//        when(companyRepository.findById(companyCode)).thenReturn(Optional.of(company));
//
//        Optional<CompanyResponseDTO> result = companyService.getCompanyByID(companyCode);
//
//        assertTrue(result.isPresent());
//        assertEquals(company.getCompanyName(), result.get().getCompanyName());
//
//    }
//
////    @Test
////    void testUpdateCompany() {
////        Long companyCode = 1L;
////        Company existingCompany = new Company();
////        Company updatedCompany = new Company();
////
////        when(companyRepository.findById(companyCode)).thenReturn(Optional.of(existingCompany));
////        when(companyRepository.save(any(Company.class))).thenReturn(updatedCompany);
////
////        companyService.UpdateCompany(companyCode, updatedCompany);
////
////        verify(companyRepository, times(1)).findById(companyCode);
////        verify(companyRepository, times(1)).save(any(Company.class));
////    }
//
//    @Test
//    void testUpdateCompanyNotFound() {
//        Long companyCode = 1L;
//        Company updatedCompany = new Company();
//
//        when(companyRepository.findById(companyCode)).thenReturn(Optional.empty());
//
//        assertThrows(CompanyNotFound.class, () -> companyService.UpdateCompany(companyCode, updatedCompany));
//    }
//
//    @Test
//    void testDeleteCompany() {
//        Long companyCode = 1L;
//        Company company = new Company();
//
//        when(companyRepository.findById(companyCode)).thenReturn(Optional.of(company));
//
//        boolean result = companyService.deleteCompany(companyCode);
//
//        assertTrue(result);
//    }
//
//    @Test
//    void testDeleteCompanyNotFound() {
//        Long companyCode = 1L;
//        when(companyRepository.findById(companyCode)).thenReturn(Optional.empty());
//        assertThrows(CompanyNotFound.class, () -> companyService.deleteCompany(companyCode));
//    }
//}
