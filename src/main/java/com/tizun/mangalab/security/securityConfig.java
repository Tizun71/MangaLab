package com.tizun.mangalab.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityConfig {
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails admin = User.builder()
								.username("admin")
								.password("{noop}admin")
								.roles("ADMIN")
								.build();
		
		UserDetails tizun = User.builder()
								.username("tizun")
								.password("{noop}123")
								.roles("CUSTOMER")
								.build();
		return new InMemoryUserDetailsManager(admin, tizun);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(configurer ->
					configurer
							.requestMatchers("/admin/**").permitAll()
							.anyRequest().authenticated()
				)
				.formLogin(form -> 
						form
							.loginPage("/showAdminLoginPage")
							.loginProcessingUrl("/authenticateTheUser")
							.permitAll()
						);
		return http.build();
	}
}
