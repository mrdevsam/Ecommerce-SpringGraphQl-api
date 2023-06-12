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
import org.springframework.security.oauth2.jwt.*;
import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.source.*;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {

	private final RsaKeyProperties keyProperties;

	public SecurityConfig(RsaKeyProperties keyProperties) {
		this.keyProperties = keyProperties;
	}

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
			.authorizeHttpRequests( auth -> auth.requestMatchers("/").permitAll() )
			.authorizeHttpRequests( auth -> auth.requestMatchers("/token").hasRole("USER") )
			.authorizeHttpRequests( auth ->	auth.anyRequest().authenticated() ) // allow all requests for a authentiocated user
			.oauth2ResourceServer(oauth2 -> {
				oauth2.jwt(jwt -> jwt.decoder(jwtDecoder()));
			})
			.sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) ) // disable session management
			.httpBasic(withDefaults()).build();
	}

	@Bean
	PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(keyProperties.publicKey()).build();
	}

	@Bean
	JwtEncoder jwtEncoder() {
		// encode the signature and sign it with private key
		JWK jwk = new RSAKey
						.Builder(keyProperties.publicKey())
						.privateKey(keyProperties.privateKey())
						.build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}
}
