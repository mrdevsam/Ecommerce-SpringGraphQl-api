package dev.mrdevsam.projects.ecommercespringgraphqlapi.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@NoArgsConstructor
@Data
@Entity
public class Category {

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@NotEmpty
	@Column(unique=true)
	private String name;

	@NotNull
	@NotEmpty
	private String picture;

	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "category")
	private List<Product> products;

	public Category(String name, String picture) {
		this.name = name;
		this.picture = picture;
	}
	
}
