package com.codeWithMerald.fashionBlog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class FashionBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(FashionBlogApplication.class, args);
	}

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
