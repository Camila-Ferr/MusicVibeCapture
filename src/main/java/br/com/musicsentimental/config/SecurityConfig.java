package br.com.musicsentimental.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig  {
	@Autowired
	private UserDetailsServ userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new MessageDigestPasswordEncoder("SHA-256");
	}
	
	@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers
                        .frameOptions().disable())
                .authorizeRequests(authorizeConfig ->
                        authorizeConfig
                                .antMatchers("/dashboard/**").authenticated()
                                .antMatchers("/profile/**").authenticated()
                                .antMatchers("/ranking/**").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login") 
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll());

        return http.build();
    }
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
}
