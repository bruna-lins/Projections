package com.beecrowd.uri2609;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.beecrowd.uri2609.dto.CategorySumDTO;
import com.beecrowd.uri2609.projections.CategorySumProjection;
import com.beecrowd.uri2609.repositories.CategoryRepository;

@SpringBootApplication
public class Uri2609Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2609Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<CategorySumProjection> list = repository.sqlSearch();
		List<CategorySumDTO> sqlResult = list.stream().map(x -> new CategorySumDTO(x)).collect(Collectors.toList());

		System.out.println("\n** RESULTADO SQL **");
		for (CategorySumDTO obj : sqlResult) {
			System.out.println(obj);
		}

		System.out.println("\n\n");

		List<CategorySumDTO> jpqlResult = repository.jpqlSearch();

		System.out.println("\n** RESULTADO JPQL **");
		for (CategorySumDTO obj : jpqlResult) {
			System.out.println(obj);
		}

	}
}