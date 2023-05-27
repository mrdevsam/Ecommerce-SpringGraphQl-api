package dev.mrdevsam.projects.ecommercespringgraphqlapi.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@NoArgsConstructor
@Data
@Entity
public class Product {

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@NotEmpty
	private String description;

	@NotNull
	private double price;

	@NotNull
	private double weight;

	@NotNull
	@NotEmpty
	private String picture1, picture2, picture3;

	@ManyToOne
	private Category category;
	
}
