package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
			RouteLocatorBuilder builder,
			@Value("${demo.character.url}") String characterUrl,
			@Value("${demo.archetype.url}") String archetypeUrl,
			@Value("${demo.element.url}") String elementUrl,
			@Value("${demo.gateway.host}") String host
	) {
		return builder
				.routes()
				.route("elements", route -> route
						.host(host)
						.and()
						.path(
								"/api/elements",
								"/api/elements/name/{elementName}",
								"/api/elements/{uuid}"
						)
						.uri(elementUrl)
				)
				.route("archetypes", route -> route
						.host(host)
						.and()
						.path(
								"/api/archetypes/{uuid}",
								"/api/archetypes",
								"/api/archetypes/name/{archetypeName}"
						)
						.uri(archetypeUrl)
				)
				.route("characters", route -> route
						.host(host)
						.and()
						.path(
								"/api/characters",
								"/api/characters/**",
								"/api/characters/{uuid}",
								"/api/characters/name/{name}",
								"/api/archetypes/{archetypeId}/characters"
						)
						.uri(characterUrl)
				)
				.build();
	}

}
