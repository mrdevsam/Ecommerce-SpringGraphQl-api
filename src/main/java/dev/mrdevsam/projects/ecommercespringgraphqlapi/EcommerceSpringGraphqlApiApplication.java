package dev.mrdevsam.projects.ecommercespringgraphqlapi;

import dev.mrdevsam.projects.ecommercespringgraphqlapi.model.*;
import dev.mrdevsam.projects.ecommercespringgraphqlapi.repositories.*;
import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceSpringGraphqlApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceSpringGraphqlApiApplication.class, args);
	}

	@Bean
	CommandLineRunner initializer(UserRepo urepo) {
		return args -> {
			List<User> users = List.of(
				new User("aaa", "emaila@mail.com", "sdflkjs", true),
				new User("bbb", "emailb@mail.com", "sdffsdflkjs", false)
			);

			urepo.saveAll(users);
		};
	}

}
