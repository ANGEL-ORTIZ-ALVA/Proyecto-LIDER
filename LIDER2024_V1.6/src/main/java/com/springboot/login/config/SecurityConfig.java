package com.springboot.login.config;

import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.springboot.login.security.CustomUserDetailsService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
			.csrf(csrf -> csrf.disable())
			//.httpBasic(Customizer.withDefaults())
			.httpBasic().disable()
			.authorizeHttpRequests((request)-> request
					.requestMatchers("/login", "/resources/**").permitAll()
					.requestMatchers("/restaurar-contrasena/**").permitAll()
					.requestMatchers("/registrarse-usuario/**").permitAll()
					.requestMatchers("/registrarse-personal/**").permitAll()
					.requestMatchers("/registrarse-cliente/**").permitAll()
					.requestMatchers("/registro/**").permitAll()
					.requestMatchers("/registro-personal/**").permitAll()
					.requestMatchers("/registro-cliente/**").permitAll()
					.requestMatchers("/pages-login/**").permitAll()
					.requestMatchers("/styles/**").permitAll()
					.requestMatchers("/img/**").permitAll()
					.requestMatchers("/js/**").permitAll()
					.requestMatchers("/admin/**").hasRole("administrador")
					.requestMatchers("/client/**").hasRole("cliente")
					.requestMatchers("/postventa/**").hasRole("trabajador")
					.requestMatchers("/almacen/**").hasRole("jefe_de_almacen")
					.requestMatchers("/solicitudes").hasAnyRole("cliente", "jefe_de_almacen", "trabajador")
	                .requestMatchers("/sri").hasAnyRole("trabajador","jefe_de_almacen")
	                .requestMatchers("/sorea").hasRole("jefe_de_almacen")
	                .requestMatchers("/insumo").hasRole("jefe_de_almacen")
	                .requestMatchers("/proveedores").hasRole("jefe_de_almacen")
	                .requestMatchers("/usuarios").hasRole("administrador")
					.anyRequest().authenticated()
					)
			.formLogin((form)-> form
					.loginPage("/login")
					.permitAll()
					.successHandler(customAuthenticationSuccessHandler())
					)
			.logout((logout)->logout
					.invalidateHttpSession(true)
					.permitAll()
					);
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}
	
	public static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	        @Override
	        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	                                            Authentication authentication) throws IOException, ServletException {
	            String redirectUrl = "/index";
	            response.sendRedirect(redirectUrl);
	        }
	    }
	/*public static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			String redirectUrl = "";
			
			if(authentication.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_administrador"))) {
				redirectUrl = "/index";
			} else if(authentication.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_cliente"))) {
				redirectUrl = "/index";
			} else if(authentication.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_trabajador"))) {
				redirectUrl = "/index";
			} else if(authentication.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_jefe_de_almacen"))) {
				redirectUrl = "/index";
			}
			response.sendRedirect(redirectUrl);
		}
		
	}*/
	
	public static class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			response.sendRedirect("/login?error");
		}
		
	}
	
}
