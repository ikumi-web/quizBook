package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
			.authorizeHttpRequests(auth -> auth
//			.requestMatchers(HttpMethod.POST,"/quizbook/**").hasRole("MANAGER")
			.requestMatchers("/quizbook/**").hasAnyRole("MANAGER","TEST")
			.anyRequest().permitAll()
		).formLogin(login -> login
			.loginPage("/login")
			.failureUrl("/login?failure")
			.defaultSuccessUrl("/quizbook/entry")
		).exceptionHandling(e -> e
			.accessDeniedPage("/access-denied")
		).logout(logout -> logout
			.logoutSuccessUrl("/logout"));
		
		return http.build();
	}
	//インメモリ用のUserDetails動作確認用
	@Bean
	UserDetailsService userDetailsService() {
		
		UserDetails taro = User.builder()
				.username("taro").password("{noop}taro123").roles("TEST").build();
		UserDetails ikumi = User.builder()
				.username("ikumi").password("{noop}ikumi193Ikumi").roles("MANAGER").build();
		UserDetails andy = User.builder()
				.username("andy").password("{noop}andy456").roles("USER").build();
		
		return new InMemoryUserDetailsManager(taro,ikumi,andy);
	}


}
