package dev.mrdevsam.projects.ecommercespringgraphqlapi.repositories;

import dev.mrdevsam.projects.ecommercespringgraphqlapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	
}
