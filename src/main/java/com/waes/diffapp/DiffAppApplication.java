package com.waes.diffapp;

import com.waes.diffapp.model.DiffEntity;
import com.waes.diffapp.repository.impl.InMemoryTrackedRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DiffAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiffAppApplication.class, args);
	}

	@Bean
	@ConditionalOnProperty("diffapp.repository.inmemory")
	public InMemoryTrackedRepository<String, DiffEntity> inMemoryTrackedRepository() {
		return new InMemoryTrackedRepository<>();
	}
}
