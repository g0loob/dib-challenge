package com.example.dibchallenge.beer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:beer.properties")
public class BeerPropertiesConfig {
}
