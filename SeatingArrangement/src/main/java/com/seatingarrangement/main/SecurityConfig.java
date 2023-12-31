package com.seatingarrangement.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.seatingarrangement.main.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userService;
	
	private AuthenticationProvider getProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(getEncoder());
		
		dao.setUserDetailsService(userService);
		return dao;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  
         auth.authenticationProvider(getProvider());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
      
         http.authorizeRequests()
         .antMatchers(HttpMethod.POST,"/userdetails/add").hasAuthority("SUPERADMIN")
        
         .anyRequest().permitAll()
         .and()
         .httpBasic()
         .and()
         .csrf().disable();
    }

	 @Bean
	 public BCryptPasswordEncoder getEncoder() {
		 return new BCryptPasswordEncoder();
	 }
}


