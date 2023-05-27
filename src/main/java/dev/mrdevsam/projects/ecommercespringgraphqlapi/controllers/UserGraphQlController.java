package dev.mrdevsam.projects.ecommercespringgraphqlapi.controllers;

import dev.mrdevsam.projects.ecommercespringgraphqlapi.model.User;
import dev.mrdevsam.projects.ecommercespringgraphqlapi.model.UserUpdater;
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

	@QueryMapping()
	public User findUserByName(@Argument String username) {
		log.info("requesting user details for username: " + username);
		return repo.findById(username).get();
	}

	@QueryMapping()
	public User findUserByNameForAdmin(@Argument String username) {
		log.info("For Admin: requesting user details for username: " + username);
		return repo.findById(username).get();
	}

	@MutationMapping()
	public User createUser(@Argument String username, @Argument String email, @Argument String password, @Argument boolean admin) {
		User newUser = new User(username, email, password, admin);
		log.info("saving new user details: " + username);
		repo.save(newUser);
		log.info("getting new user details: " + username);
		return repo.findById(username).get();
	}

	@MutationMapping()
	public User updateUser(@Argument String username, @Argument UserUpdater update) {
		User userToUp = repo.findById(username).orElse(null);

		if(userToUp == null) {
			throw new RuntimeException("Invalid user!!!");
		}

		userToUp.setUsername(username);
		userToUp.setEmail(update.email());
		userToUp.setPassword(update.password());
		userToUp.setAdmin(update.admin());
		log.info("saving updated user details: " + username);
		repo.save(userToUp);
		log.info("getting updated user details: " + username);
		return repo.findById(username).get();
	}

}
