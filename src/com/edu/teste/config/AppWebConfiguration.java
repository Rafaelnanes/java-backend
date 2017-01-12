package com.edu.teste.config;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AppWebConfiguration {

    @PostConstruct
    private void ini() {
	Locale.setDefault(new Locale("pt", "BR"));
    }

}
