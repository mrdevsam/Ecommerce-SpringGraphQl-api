package dev.mrdevsam.projects.ecommercespringgraphqlapi.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails user = User.builder()
								.username("user")
								.passwordEncoder(str -> passEncoder().encode(str))
								.password("passwordu")
								.roles("USER")
								.build();

		UserDetails admin = User.builder()
								.username("admin")
								.passwordEncoder(str -> passEncoder().encode(str))
								.password("passworda")
								.roles("USER", "ADMIN")
								.build();

		return new InMemoryUserDetailsManager(user,admin);
	}
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http
			.csrf(csrf -> csrf.disable()) 
			.authorizeHttpRequests( auth -> {
				auth.anyRequest().authenticated();
			}) // allow all requests for a authentiocated user
			.sessionManagement(
				session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			) // disable session management
			.httpBasic(withDefaults()).build();
	}

	@Bean
	PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
}
