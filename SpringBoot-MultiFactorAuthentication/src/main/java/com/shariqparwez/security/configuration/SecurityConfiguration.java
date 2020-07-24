package com.shariqparwez.security.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.shariqparwez.security.exceptions.AccessDeniedHandlerImpl;
import com.shariqparwez.security.filters.TotpAuthenticationFilter;
import com.shariqparwez.security.model.Authorities;
import com.shariqparwez.security.userdetails.AdditionalAuthenticationDetailsSource;
import com.shariqparwez.security.userdetails.AdditionalAuthenticationProvider;
import com.shariqparwez.security.userdetails.AuthenticationSuccessHandlerImpl;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AdditionalAuthenticationProvider additionalProvider;
	@Autowired
	private TotpAuthenticationFilter totpAuthFilter;
	@Autowired
	private AccessDeniedHandlerImpl accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(totpAuthFilter, UsernamePasswordAuthenticationFilter.class).
		authorizeRequests().antMatchers("/register", "/login", "/login-error", "/login-verified", "/verify/email", "/qrcode").permitAll()
		.antMatchers("/totp-login","/totp-login-error").hasAuthority(Authorities.TOTP_AUTH_AUTHORITY)
		.antMatchers("/support/admin/**").hasRole("ADMIN")
		.anyRequest().hasRole("USER").and()
		.formLogin().loginPage("/login").successHandler(new AuthenticationSuccessHandlerImpl()).failureUrl("/login-error")
		.authenticationDetailsSource(new AdditionalAuthenticationDetailsSource())
		.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/webjars/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(additionalProvider);
	}
	
	@Bean
	public RedirectStrategy getRedirectStrategy() {
		return new DefaultRedirectStrategy();
	}
	
	@Bean(name="simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver createExceptionResolver() {
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		Properties properties = new Properties();
		properties.setProperty("InvalidTOTPVerificationCode", "totp-login-error");
		resolver.setExceptionMappings(properties);
		resolver.setDefaultErrorView("error");
		return resolver;
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		DelegatingPasswordEncoder encoder =  (DelegatingPasswordEncoder)PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;	
	}	
		
}

