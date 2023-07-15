package com.example.diagnose.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.diagnose.entity.AppConfig;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Autowired private AppConfig appConfig;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String imageDirUri = appConfig.getImageDir().toURI().toString();
		registry.addResourceHandler("/images/**").addResourceLocations(imageDirUri);
	}
}
