package com.example.dibchallenge;

import com.example.dibchallenge.beer.PunkApiBeerStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class DibChallengeApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(DibChallengeApplication.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private BeerStrategyRegistry beerStrategyRegistry;

	public static void main(String[] args) {
		SpringApplication.run(DibChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("beans={}", Arrays.asList(applicationContext.getBeanDefinitionNames()));
		beerStrategyRegistry.add("PAB", PunkApiBeerStrategy.class);
	}
}
