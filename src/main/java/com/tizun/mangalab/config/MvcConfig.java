package com.tizun.mangalab.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	//Config Static Resource
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		Path dir =Paths.get("./uploads");
		String uploadPath = dir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("uploads/**").addResourceLocations("file:/" + uploadPath + "/");
	}

}
