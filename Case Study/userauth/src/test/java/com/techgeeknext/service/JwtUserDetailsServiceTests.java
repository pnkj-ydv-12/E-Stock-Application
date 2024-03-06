//package com.techgeeknext.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//import java.util.Optional;
//
//import com.simple.service.JwtUserDetailsService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import com.simple.model.UserDao;
//import com.simple.model.UserDto;
//import com.simple.repository.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
//public class JwtUserDetailsServiceTests {
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private PasswordEncoder passwordEncoder;
//    @InjectMocks
//    private JwtUserDetailsService jwtUserDetailsService;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//    @Test
//    void testLoadUserByUsername_UserFound_ReturnsUserDetails() {
//        String username = "testUser";
//        UserDao user = new UserDao();
//        user.setUsername(username);
//        user.setPassword("encodedPassword");
//        when(userRepository.findByUsername(username)).thenReturn(user);
//        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
//        assertEquals(username, userDetails.getUsername());
//        verify(userRepository, times(1)).findByUsername(username);
//    }
//    @Test
//    void testLoadUserByUsername_UserNotFound_ThrowsException() {
//        String username = "nonExistentUser";
//        when(userRepository.findByUsername(username)).thenReturn(null);
//        assertThrows(UsernameNotFoundException.class, () -> jwtUserDetailsService.loadUserByUsername(username));
//    }
//    @Test
//    void testSave_NewUser_ReturnsSavedUser() {
//        UserDto userDto = new UserDto();
//        userDto.setUsername("newUser");
//        userDto.setPassword("password");
//        userDto.setFirstname("John");
//        userDto.setLastname("Jacob");
//        userDto.setEmail("john.jacob@example.com");
//        userDto.setContact("123456789");
//        userDto.setRole("ROLE_USER");
//        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
//        when(userRepository.save(any(UserDao.class))).thenAnswer(invocation -> invocation.getArgument(0));
//        UserDao savedUser = jwtUserDetailsService.save(userDto);
//        assertEquals(userDto.getUsername(), savedUser.getUsername());
//        assertEquals("encodedPassword", savedUser.getPassword());
//        assertEquals(userDto.getFirstname(), savedUser.getFirstname());
//        assertEquals(userDto.getLastname(), savedUser.getLastname());
//        assertEquals(userDto.getEmail(), savedUser.getEmail());
//        assertEquals(userDto.getContact(), savedUser.getContact());
//        assertEquals(userDto.getRole(), savedUser.getRole());
//    }
//    @Test
//    void testGetRole_UserExists_ReturnsUserRole() {
//        String username = "existingUser";
//        UserDao user = new UserDao();
//        user.setUsername(username);
//        user.setRole("ROLE_ADMIN");
//        when(userRepository.findByUsername(username)).thenReturn(user);
//        String role = jwtUserDetailsService.getrole(username);
//        assertEquals(user.getRole(), role);
//        verify(userRepository, times(1)).findByUsername(username);
//    }
//    @Test
//    void testGetUser_UserExists_ReturnsOptionalUser() {
//        long userId = 1L;
//        UserDao user = new UserDao();
//        user.setId(userId);
//        when(userRepository.findByid(userId)).thenReturn(user);
//        Optional<UserDao> optionalUser = jwtUserDetailsService.getUser(userId);
//        assertEquals(user, optionalUser.orElse(null));
//        verify(userRepository, times(1)).findByid(userId);
//    }
//    @Test
//    void testGetUser_UserDoesNotExist_ThrowsException() {
//        long nonExistentUserId = 999L;
//        when(userRepository.findByid(nonExistentUserId)).thenReturn(null);
//        assertThrows(UsernameNotFoundException.class, () -> jwtUserDetailsService.getUser(nonExistentUserId));
//    }
//}