package com.homsdev.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com.homsdev.app")//Scan for all classes stereotypes (Controllers, services & repositories) 
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter {
	
		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
		}

		
//		This bean resolves the actual view´s file path
//      Its job is to form the actual URL from the returned String from the controller
		@Bean
		public InternalResourceViewResolver getInternalResourceViewResolver() {
			InternalResourceViewResolver resolver = new InternalResourceViewResolver();
			resolver.setViewClass(JstlView.class);
			resolver.setPrefix("/WEB-INF/views/");
			resolver.setSuffix(".jsp");
			return resolver;
		}
}
