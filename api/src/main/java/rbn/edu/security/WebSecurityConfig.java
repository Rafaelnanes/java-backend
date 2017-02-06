package rbn.edu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import rbn.edu.filters.CORSFilter;
import rbn.edu.filters.JWTAuthenticationFilter;
import rbn.edu.filters.JWTLoginFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.headers().cacheControl();
	http.csrf().disable() //
		.authorizeRequests().antMatchers("/").permitAll().antMatchers(HttpMethod.POST, "/login").permitAll()
		.anyRequest().authenticated().and() //
		.addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class)
		.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
			UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws
    // Exception {
    // auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
    // }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
	PasswordEncoder encoder = new BCryptPasswordEncoder();
	return encoder;
    }

}
