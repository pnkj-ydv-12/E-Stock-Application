//package com.techgeeknext.advice;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import java.sql.SQLIntegrityConstraintViolationException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.simple.advice.ApplicationExceptionHandler;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//class ApplicationExceptionHandlerTests {
//    @Mock
//    private MethodArgumentNotValidException methodArgumentNotValidException;
//    @Mock
//    private BindingResult bindingResult;
//    @Mock
//    private DataIntegrityViolationException dataIntegrityViolationException;
//    @InjectMocks
//    private ApplicationExceptionHandler applicationExceptionHandler;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//    @Test
//    void testHandleInvalidArgument() {
//        // Mocking behavior for getFieldErrors
//        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
//        when(bindingResult.getFieldErrors())
//                .thenReturn(List.of(new FieldError("user", "fieldName", "errorMessage")));
//        Map<String, String> result = applicationExceptionHandler.handleInvalidArgument(methodArgumentNotValidException);
//        Map<String, String> expected = new HashMap<>();
//        expected.put("fieldName", "errorMessage");
//        assertEquals(expected, result);
//    }
//    @Test
//    void testExceptionDuplicateEmail() {
//        SQLIntegrityConstraintViolationException rootCause = mock(SQLIntegrityConstraintViolationException.class);
//        when(rootCause.toString()).thenReturn("Duplicate entry 'test@example.com' for key 'email_UNIQUE'");
//        when(dataIntegrityViolationException.getRootCause()).thenReturn(rootCause);
//        String result = applicationExceptionHandler.handle(dataIntegrityViolationException);
//        assertEquals("Email already exist enter new email", result);
//    }
//    @Test
//    void testExceptionDuplicateUsername() {
//        SQLIntegrityConstraintViolationException rootCause = mock(SQLIntegrityConstraintViolationException.class);
//        when(rootCause.toString()).thenReturn("Duplicate entry 'username' for key 'username_UNIQUE'");
//        when(dataIntegrityViolationException.getRootCause()).thenReturn(rootCause);
//        String result = applicationExceptionHandler.handle(dataIntegrityViolationException);
//        assertEquals("Username already taken", result);
//    }
//}