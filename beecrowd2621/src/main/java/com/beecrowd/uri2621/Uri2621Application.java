package com.beecrowd.uri2621;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.beecrowd.uri2621.dto.ProductMinDTO;
import com.beecrowd.uri2621.projections.ProductMinProjection;
import com.beecrowd.uri2621.repositories.ProductRepository;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<ProductMinProjection> list = repository.sqlSearch(10, 20, "P");
		List<ProductMinDTO> sqlResult = list.stream().map(x -> new ProductMinDTO(x)).collect(Collectors.toList());
		
		System.out.println("\n** RESULTADO SQL **");
		for (ProductMinDTO obj : sqlResult) { 
			System.out.println(obj);
		}
		System.out.println("\n\n");
		
		List<ProductMinDTO> jpqlResult = repository.jpqlSearch(10, 20, "P");
		
		System.out.println("\n** RESULTADO JPQL **");
		for (ProductMinDTO obj : jpqlResult) { 
			System.out.println(obj);
		} 
	}
}
