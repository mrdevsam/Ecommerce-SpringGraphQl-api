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
	private Float price;

	@NotNull
	private Float weight;

	@NotNull
	@NotEmpty
	private String picture1, picture2, picture3;

	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	public Product(String name, String description, Float price, Float weight, String picture1, String picture2, String picture3, Category category) {
		this.name = name;
		this.description  = description;
		this.price = price;
		this.weight = weight;
		this.picture1 = picture1;
		this.picture2 = picture2;
		this.picture3 = picture3;
		this.category = category;
	}
	
}
