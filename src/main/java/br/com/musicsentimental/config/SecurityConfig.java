package br.com.musicsentimental.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig  {
	@Autowired
	private UserDetailsServ userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers
                        .frameOptions().sameOrigin()
                        .xssProtection(xss -> xss
                                .xssProtectionEnabled(true)
                                .block(true))
                        .xssProtection())
                .authorizeRequests(authorizeConfig ->
                        authorizeConfig
                                .antMatchers("/dashboard/**").authenticated()
                                .antMatchers("/profile/**").authenticated()
                                .antMatchers("/ranking/**").authenticated()
                                .antMatchers("/rating/**").authenticated()
                                .antMatchers("/music/**").authenticated()
                                .antMatchers("/usuarios/cadastrar").permitAll()
                                .antMatchers("/usuarios/**").authenticated()
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