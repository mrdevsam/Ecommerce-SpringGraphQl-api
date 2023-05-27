package dev.mrdevsam.projects.ecommercespringgraphqlapi.controllers;

import dev.mrdevsam.projects.ecommercespringgraphqlapi.model.User;
import dev.mrdevsam.projects.ecommercespringgraphqlapi.repositories.UserRepo;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class UserGraphQlController {

	private final UserRepo repo;

	public UserGraphQlController(UserRepo repo) {
		this.repo = repo;
	}

	@QueryMapping
	public List<User> findAllUsers() {
		log.info("getting all users from database");
		return repo.findAll();
	}
}
