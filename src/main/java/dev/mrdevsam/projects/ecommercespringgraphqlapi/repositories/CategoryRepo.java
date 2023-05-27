package dev.mrdevsam.projects.ecommercespringgraphqlapi.repositories;

import dev.mrdevsam.projects.ecommercespringgraphqlapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
}
