package dev.mrdevsam.projects.ecommercespringgraphqlapi.controllers;

import dev.mrdevsam.projects.ecommercespringgraphqlapi.model.Category;
import dev.mrdevsam.projects.ecommercespringgraphqlapi.model.CategoryUpdater;
import dev.mrdevsam.projects.ecommercespringgraphqlapi.repositories.CategoryRepo;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class CategoryGraphQlController {

	private final CategoryRepo repo;

	public CategoryGraphQlController(CategoryRepo repo) {
		this.repo = repo;
	}

	@QueryMapping
	public List<Category> findAllCategories() {
		log.info("getting all categories from database");
		return repo.findAll();
	}

	@QueryMapping()
	public Category findCategory(@Argument Integer id) {
		log.info("requesting category details for id: " + id);
		return repo.findById(id).get();
	}

	@MutationMapping()
	public List<Category> createCategory(@Argument String name, @Argument String picture) {
		Category newcat = new Category(name, picture);
		repo.save(newcat);
		return repo.findAll();
	}

	@MutationMapping()
	public Category updateCategory(@Argument Integer id, @Argument CategoryUpdater update) {
		Category catToUp = repo.findById(id).orElse(null);

		if(catToUp == null) {
			throw new RuntimeException("Invalid category!!!");
		}

		catToUp.setId(id);
		catToUp.setName(update.name());
		catToUp.setPicture(update.picture());
		//log.info("saving updated user details: " + username);
		repo.save(catToUp);
		//log.info("getting updated user details: " + username);
		return repo.findById(id).get();
	}

	@MutationMapping
	public List<Category> deleteCategory(@Argument Integer id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
		}else{
			throw new RuntimeException("Invalid user!!!");
		}
		
		return repo.findAll();
	}

}
