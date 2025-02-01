package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

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

	@Bean
	public CorsWebFilter corsWebFilter() {

		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PATCH", "PUT"));
		corsConfig.addAllowedHeader("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}

}
