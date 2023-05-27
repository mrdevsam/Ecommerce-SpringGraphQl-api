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

	@OneToMany(mappedBy = "category")
	private List<Product> products;
	
}
