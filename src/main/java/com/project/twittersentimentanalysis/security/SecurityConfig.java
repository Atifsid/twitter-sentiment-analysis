package com.project.twittersentimentanalysis.security;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig implements Filter {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf()
	      .disable();
//	      .authorizeRequests();
//	      .antMatchers(HttpMethod.DELETE)
//	      .hasRole("ADMIN")
//	      .antMatchers("/admin/**")
//	      .hasAnyRole("ADMIN")
//	      .antMatchers("/user/**")
//	      .hasAnyRole("USER", "ADMIN")
//	      .antMatchers("/login/**")
//	      .anonymous()
//	      .anyRequest()
//	      .authenticated()
//	      .and()
//	      .httpBasic()
//	      .and()
//	      .sessionManagement()
//	      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	    return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.debug(true)
	      .ignoring();
//	      .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
	    
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With");
        chain.doFilter(request, res);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}