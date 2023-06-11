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
	CommandLineRunner initializer(CategoryRepo crepo, ProductRepo prepo) {
		return args -> {

			List<Category> cats = List.of(
				new Category("EE", "ppp"),
				new Category("FF", "ooo")
			);
			
			List<Product> products = List.of(
				new Product("qqqq","qqq qqq qqq qqq", 4.54F, 2.34F, "dsf", "sffs", "sfgsf",cats.get(0)),
				new Product("rrr","qqq qqq qsfsfsfsqq qqq", 2.24F, 2.84F, "dsqqweqef", "sfewewwrfs", "sfsdfdfgsf",cats.get(1))
			);

			crepo.saveAll(cats);
			prepo.saveAll(products);
		};
	}

}
