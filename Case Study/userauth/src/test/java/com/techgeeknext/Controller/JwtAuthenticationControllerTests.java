//package com.techgeeknext.Controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//import java.util.Optional;
//import com.simple.controller.JwtAuthenticationController;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetails;
//import com.simple.config.JwtTokenUtil;
//import com.simple.model.JwtRequest;
//import com.simple.model.UserDao;
//import com.simple.model.UserDto;
//import com.simple.repository.UserRepository;
//import com.simple.service.JwtUserDetailsService;
//class JwtAuthenticationControllerTests {
//    @Mock
//    private AuthenticationManager authenticationManager;
//    @Mock
//    private JwtTokenUtil jwtTokenUtil;
//    @Mock
//    private JwtUserDetailsService userDetailsService;
//    @Mock
//    private UserRepository userRepository;
//    @InjectMocks
//    private JwtAuthenticationController jwtAuthenticationController;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//    @Test
//    void testCreateAuthenticationToken() throws Exception {
//        JwtRequest authenticationRequest = new JwtRequest("testUser", "testPassword");
//        UserDetails userDetails = mock(UserDetails.class);
//        when(userDetailsService.loadUserByUsername(authenticationRequest.getUsername())).thenReturn(userDetails);
//        when(jwtTokenUtil.generateToken(userDetails)).thenReturn("testToken");
//        when(jwtTokenUtil.getUsernameFromToken("testToken")).thenReturn("testUser");
//        when(userDetailsService.getrole("testUser")).thenReturn("ROLE_USER");
//        ResponseEntity<?> responseEntity = jwtAuthenticationController.createAuthenticationToken(authenticationRequest);
//        assertEquals(200, responseEntity.getStatusCodeValue());
//    }
//    @Test
//    void testSaveUser_ValidUser() throws Exception {
//        UserDto userDto = new UserDto();
//        userDto.setUsername("testUser");
//        userDto.setPassword("testPassword");
//        when(userDetailsService.save(userDto)).thenReturn(new UserDao());
//        ResponseEntity<?> responseEntity = jwtAuthenticationController.saveUser(userDto);
//        assertEquals(200, responseEntity.getStatusCodeValue());
//    }
//    @Test
//    void testAuthorize_ValidToken_ReturnsTrue() {
//        when(jwtTokenUtil.getUsernameFromToken("testToken")).thenReturn("testUser");
//        boolean result = jwtAuthenticationController.authorize("Bearer testToken");
//        assertEquals(true, result);
//    }
//    @Test
//    void testAuthorize_InvalidToken_ReturnsFalse() {
//        boolean result = jwtAuthenticationController.authorize("InvalidToken");
//        assertEquals(false, result);
//    }
//    @Test
//    void testGetRole() {
//        when(jwtTokenUtil.getUsernameFromToken("testToken")).thenReturn("testUser");
//        when(userDetailsService.getrole("testUser")).thenReturn("ROLE_USER");
//        String result = jwtAuthenticationController.getrole("Bearer testToken");
//        assertEquals("ROLE_USER", result);
//    }
//    @Test
//    void testGetRole_Invalid() {
//        String result = jwtAuthenticationController.getrole("InvalidToken");
//        assertEquals("", result);
//    }
//    @Test
//    void testGetUserByID_ValidToken() {
//        when(jwtTokenUtil.getUsernameFromToken("testToken")).thenReturn("testUser");
//        when(userDetailsService.getUser(1L)).thenReturn(Optional.of(new UserDao()));
//        ResponseEntity<?> responseEntity = jwtAuthenticationController.getUserByID(1L, "Bearer testToken");
//        assertEquals(200, responseEntity.getStatusCodeValue());
//    }
//    @Test
//    void testGetUserByID_InvalidToken() {
//        ResponseEntity<?> responseEntity = jwtAuthenticationController.getUserByID(1L, "InvalidToken");
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        assertEquals("Not Authorised", responseEntity.getBody());
//    }
//}