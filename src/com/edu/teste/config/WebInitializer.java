package com.edu.teste.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//se quiser iniciar pelo tomcat
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
	return new Class[] {};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
	return new Class[] { AppWebConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {
	return new String[] { "/*" };
    }

}
