package com.homsdev.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource datasource;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	/*DATABASE BASED AUTHENTICATION*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login")
		.usernameParameter("username")
		.passwordParameter("password");
	
		http.formLogin()
		.defaultSuccessUrl("/market/products")
		.failureUrl("/login");
		
		http.logout().logoutSuccessUrl("/login");
		
		http.exceptionHandling().accessDeniedPage("/login");
		
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/market/**").hasAnyAuthority("ADMIN","USER")
		.antMatchers("/**/add").access("hasRole('ADMIN')");
		
		http.csrf().disable();
	}

	/*
	 * IN MEMORY BASED AUTHENTICATION
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.inMemoryAuthentication()
	 * .withUser("user").password("password").roles("USER") .and()
	 * .withUser("admin").password("password").roles("USER","ADMIN"); }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		String SQL_USER_INFO = "SELECT username,password,enable FROM USERS WHERE username = ? ";
		auth.jdbcAuthentication()
		.dataSource(datasource)
		.usersByUsernameQuery(SQL_USER_INFO)
		.authoritiesByUsernameQuery("SELECT username,role FROM USERS WHERE username=?")
		.passwordEncoder(new BCryptPasswordEncoder());
	}

}
