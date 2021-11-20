package com.server.bugtracker.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.server.bugtracker.JWT.JwtAuthEntryPoint;
import com.server.bugtracker.JWT.JwtRequestFilter;


@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter{
	


	    @Autowired
	    private JwtAuthEntryPoint jwtAuthEntyPoint;
	    @Autowired
	    private JwtRequestFilter jwtRequestFilter;

	    @Autowired
	    private UserDetailsService UserService;

	    @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity.cors();
	        httpSecurity.csrf().disable()
	                .authorizeRequests().antMatchers("/authenticate", "/register").permitAll()
	                .antMatchers(HttpHeaders.ALLOW).permitAll()
	                .anyRequest().authenticated()
					.and()
					.formLogin()
	                .and()
	                .exceptionHandling().authenticationEntryPoint(jwtAuthEntyPoint)
	                .and()
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        ;

	        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	        authenticationManagerBuilder.userDetailsService(UserService).passwordEncoder(passwordEncoder());
	    }

}
