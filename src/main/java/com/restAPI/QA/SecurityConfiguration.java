package com.restAPI.QA;

import com.restAPI.QA.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}




	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/static/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//https://stackoverflow.com/questions/33673300/spring-security-java-configuration-for-authenticated-users-with-a-role
		http.csrf().disable()
				.authorizeRequests()
					//.antMatchers( "/api/user/**").hasRole("USER")
					.antMatchers(  "/api/auth/registration", "/api/auth/login").permitAll()
					.antMatchers("/css/**", "/js/**", "/images/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().accessDeniedPage("/403").and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/", true)

				.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.permitAll();
	}


}
