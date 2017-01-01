package com.edu.teste.config;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.edu.teste.controllers")
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

    @PostConstruct
    private void ini() {
	Locale.setDefault(new Locale("pt", "BR"));
    }

}
