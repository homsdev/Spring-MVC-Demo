package com.homsdev.app.config;

import java.util.ArrayList;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.UrlPathHelper;


@Configuration
@EnableWebMvc
@ComponentScan("com.homsdev.app") // Scan for all classes stereotypes (Controllers, services & repositories)
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter {

	// Enables MVC Features when extending WebMvcConfigurerAdapter
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	//Configures static resources for Spring¿MVC
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**").addResourceLocations("/resources/images/");
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);
		configurer.setUrlPathHelper(urlPathHelper);
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

	// Configures a new message resource
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		return resource;
	}
	
	//Configuring JSON view
	@Bean
	public MappingJackson2JsonView jsonView() {
		MappingJackson2JsonView jsonView=new MappingJackson2JsonView();
		jsonView.setPrettyPrint(true);
		return jsonView;
	}
	
	
	//configuring Content negotiation view resolver
	@Bean
	public ViewResolver negotiationViewResolver(ContentNegotiationManager contentManager) {
		ContentNegotiatingViewResolver resolver=new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(contentManager);
		ArrayList<View> views=new ArrayList<View>();
		views.add(jsonView());
		resolver.setDefaultViews(views);
		return resolver;
	}
	
}
