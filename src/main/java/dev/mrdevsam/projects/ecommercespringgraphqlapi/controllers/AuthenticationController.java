package dev.mrdevsam.projects.ecommercespringgraphqlapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import dev.mrdevsam.projects.ecommercespringgraphqlapi.services.TokenService;

@RestController
@Slf4j
public class AuthenticationController{

	private final TokenService service;

	public AuthenticationController(TokenService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String home() {
		log.debug("inside root directory");
		return "Hello, create post request to [ http://localhost:8080/token ] to get a jwt token";
	}
	
	@PostMapping("/token")
	public String token(Authentication auth) {
	    log.debug("Token requested for user: '{}'", auth.getName());
	    String token = service.generateToken(auth);
	    log.debug("Token granted: {}", token);
	    return token;
	}
}
