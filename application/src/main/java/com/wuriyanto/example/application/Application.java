package com.wuriyanto.example.application;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// SpringApplication.run(DemoApplication.class, args);

		// override existing application.properties with additional config from .env
        Map<String, Object> env = Dotenv.load()
                .entries()
                .stream()
                .collect(
                        Collectors.toMap(DotenvEntry::getKey, DotenvEntry::getValue));

						
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
		builder.environment(new StandardEnvironment() {
			@Override
			protected void customizePropertySources(MutablePropertySources propertySources) {
				super.customizePropertySources(propertySources);
				propertySources.addLast(new MapPropertySource("dotenvProperties", env));
			}
		});
		
		builder.run(args);
	}

}
