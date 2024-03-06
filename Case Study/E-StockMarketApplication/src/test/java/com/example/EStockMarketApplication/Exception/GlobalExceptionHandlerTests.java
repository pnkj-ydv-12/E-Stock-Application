//package com.example.EStockMarketApplication.Exception;
//
//import com.example.EStockMarketApplication.Exceptions.CompanyNotFound;
//import com.example.EStockMarketApplication.Exceptions.GlobalExceptionHandler;
//import com.example.EStockMarketApplication.Exceptions.LessTurnOverException;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//class GlobalExceptionHandlerTests {
//    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
//    @Test
//    void testHandleCompanyNotFoundException() {
//
//        String errorMessage = "Company not found";
//        CompanyNotFound companyNotFoundException = new CompanyNotFound(errorMessage);
//        ResponseEntity<String> responseEntity = globalExceptionHandler.handleCompanyNotFoundException(companyNotFoundException);
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals(errorMessage, responseEntity.getBody());
//    }
//    @Test
//    void testHandleLessTurnOverExceptionException() {
//        String errorMessage = "Less turnover exception";
//        LessTurnOverException lessTurnOverException = new LessTurnOverException(errorMessage);
//        ResponseEntity<String> responseEntity = globalExceptionHandler.handleLessTurnOverExceptionException(lessTurnOverException);
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals(errorMessage, responseEntity.getBody());
//    }
//}