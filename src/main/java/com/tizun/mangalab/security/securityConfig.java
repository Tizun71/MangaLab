package com.tizun.mangalab.security;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class securityConfig {
	
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsManager() {
//		UserDetails admin = User.builder()
//								.username("admin")
//								.password("{noop}admin")
//								.roles("ADMIN")
//								.build();
//		
//		UserDetails tizun = User.builder()
//								.username("tizun")
//								.password("{noop}123")
//								.roles("CUSTOMER")
//								.build();
//		return new InMemoryUserDetailsManager(admin, tizun);
//	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(configurer ->
					configurer
							.requestMatchers("/admin/**").permitAll()
							.requestMatchers("/dashboard/**").hasRole("ADMIN")
							.requestMatchers("/").permitAll()
							.anyRequest().authenticated()
				)
				.formLogin(form -> 
						form
							.loginPage("/showAdminLoginPage")
							.loginProcessingUrl("/authenticateTheUser")
							.defaultSuccessUrl("/dashboard/")
							.permitAll()
						).logout(logout -> logout.permitAll()
						)
						.exceptionHandling(configurer ->
								configurer.accessDeniedPage("/access-denided")
								)
						;
		return http.build();
	}
}
