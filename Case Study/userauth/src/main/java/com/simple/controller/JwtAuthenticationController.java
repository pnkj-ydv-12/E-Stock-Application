package com.simple.controller;

//import com.simple.Kafka.AuthPublisher;
import com.simple.config.JwtTokenUtil;
import com.simple.model.JwtRequest;
import com.simple.model.JwtResponse;
import com.simple.model.UserDao;
import com.simple.model.UserDto;
import com.simple.repository.UserRepository;
import com.simple.service.JwtUserDetailsService;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UserRepository repo;

//	@Autowired
//	private AuthPublisher authPublisher;

	@RequestMapping(value = "/auth/v1.0/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

//		authPublisher.setTempObj("Token Generated");
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		final String userName=jwtTokenUtil.getUsernameFromToken(token);
		System.out.println("username is"+userName);
		final String role = userDetailsService.getrole(userName);
		System.out.println("user role is"+role);

		return ResponseEntity.ok(new JwtResponse(token,role));
	}

	@RequestMapping(value = "/auth/v1.0/add/user", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody @Valid UserDto user) throws Exception {
//		authPublisher.setTempObj("New User Added");
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PostMapping("/authorize")
	public boolean authorize(@RequestHeader("Authorization") String token) {
//		authPublisher.setTempObj("User Authenticated");
		String authToken = null;
		String user = null;
		if(token!=null && token.startsWith("Bearer ")) {
			authToken = token.substring(7);
			user = jwtTokenUtil.getUsernameFromToken(authToken);
		}

		if(user == null) {
			System.out.println(user);
			return false;
		}
		System.out.println(user);
		return true;
	}

	@PostMapping("/getrole")
	public String getrole(@RequestHeader("Authorization") String token) {
//		authPublisher.setTempObj("get Role Method is called");
		String authToken = null;
		String user = null;
		if(token!=null && token.startsWith("Bearer ")) {
			authToken = token.substring(7);
			user = jwtTokenUtil.getUsernameFromToken(authToken);
		}
		else {
			return "";
		}
		return userDetailsService.getrole(user);
	}
	@GetMapping("/api/v1.0/user/info/{id}")
	public ResponseEntity<?> getUserByID(@PathVariable long id,@RequestHeader("Authorization") String token) {
		String authToken = null;
		String user = null;
		if(token!=null && token.startsWith("Bearer ")) {
			Optional<UserDao> userDao=userDetailsService.getUser(id);
			return ResponseEntity.ok(userDao);
		}
		else {
			return ResponseEntity.ok("Not Authorised");
		}

	}

}
