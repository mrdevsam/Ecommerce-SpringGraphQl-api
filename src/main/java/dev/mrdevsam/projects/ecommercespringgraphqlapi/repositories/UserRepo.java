package dev.mrdevsam.projects.ecommercespringgraphqlapi.repositories;

import dev.mrdevsam.projects.ecommercespringgraphqlapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
	
}
