package dev.mrdevsam.projects.ecommercespringgraphqlapi.controllers;

import dev.mrdevsam.projects.ecommercespringgraphqlapi.model.*;
import dev.mrdevsam.projects.ecommercespringgraphqlapi.repositories.*;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@Slf4j
public class ProductGraphQlController {

	private final ProductRepo prepo;
	private final CategoryRepo crepo;

	public ProductGraphQlController(ProductRepo prepo, CategoryRepo crepo) {
		this.prepo = prepo;
		this.crepo = crepo;
	}

	@PreAuthorize("hasRole('USER')")
	@QueryMapping
	public List<Product> findAllProducts() {
		log.debug("getting all products details from database");
		return prepo.findAll();
	}

	@PreAuthorize("hasRole('USER')")
	@QueryMapping()
	public Product findProduct(@Argument Integer id ) {
		log.debug("getting product details for id: " + id);
		return prepo.findById(id).get();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@MutationMapping()
	public List<Product> createProduct(@Argument Integer categoryid, @Argument String name, @Argument String description, @Argument Float price, @Argument Float weight, @Argument String picture1, @Argument String picture2, @Argument String picture3) {
		Category catgry = crepo.findById(categoryid).orElse(null);

		if(catgry == null) {
			log.debug("no category found for categoryid: " + categoryid);
			catgry = new Category();
			catgry.setName("undefined");
			catgry.setPicture("undefined");
		}

		Product newProduct = new Product(name, description, price, weight, picture1, picture2, picture3, catgry);
		log.debug("saving new product");
		crepo.save(catgry);
		prepo.save(newProduct);
		return prepo.findAll();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@MutationMapping()
	public Product updateProduct(@Argument Integer id, @Argument Integer categoryid, @Argument ProductUpdater update) {
		Product prdctToUp = prepo.findById(id).orElse(null);
		Category ctToUp = crepo.findById(categoryid).orElse(null);

		if(prdctToUp == null) {
			throw new RuntimeException("Invalid product!!!");
		}

		if(ctToUp == null) {
			throw new RuntimeException("Invalid category");
		}

		prdctToUp.setId(id);
		prdctToUp.setName(update.name());
		prdctToUp.setDescription(update.description());
		prdctToUp.setPrice(update.price());
		prdctToUp.setWeight(update.weight());
		prdctToUp.setPicture1(update.picture1());
		prdctToUp.setPicture2(update.picture2());
		prdctToUp.setPicture3(update.picture3());
		prdctToUp.setCategory(ctToUp);
		
		log.debug("saving updated Product");
		prepo.save(prdctToUp);
		
		return prepo.findById(id).get();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@MutationMapping
	public List<Product> deleteProduct(@Argument Integer id) {
		if(prepo.existsById(id)) {
			prepo.deleteById(id);
		}else{
			throw new RuntimeException("Invalid Product!!!");
		}
		
		return prepo.findAll();
	}

}
