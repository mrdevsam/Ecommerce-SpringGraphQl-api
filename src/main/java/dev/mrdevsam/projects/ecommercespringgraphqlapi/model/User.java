package dev.mrdevsam.projects.ecommercespringgraphqlapi.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@NoArgsConstructor
@Data
@Entity(name = "users")
public class User {

	@Id
	@Column(unique = true)
	@NotNull
	@NotEmpty
	private String username;

	@NotNull
	@NotEmpty
	private String email;

	@NotNull
	private String password;
	
	private boolean isAdmin;
	
}
