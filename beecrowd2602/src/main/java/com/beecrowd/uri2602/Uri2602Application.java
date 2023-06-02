package com.beecrowd.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.beecrowd.uri2602.dto.CustomerMinDTO;
import com.beecrowd.uri2602.projections.CustomerMinProjection;
import com.beecrowd.uri2602.repository.CustomerRepository;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner{

	@Autowired
	private CustomerRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<CustomerMinProjection> list = repository.sqlSearch("RS");
		List<CustomerMinDTO> sqlResult = list.stream().map(x -> new CustomerMinDTO(x)).collect(Collectors.toList());
		
		System.out.println("\n** RESULTADO SQL **");
		for (CustomerMinDTO obj : sqlResult) { 
			System.out.println(obj);
		}
		System.out.println("\n\n");
		
		List<CustomerMinDTO> jpqlResult = repository.jpqlSearch("rs");
		
		System.out.println("\n** RESULTADO JPQL **");
		for (CustomerMinDTO obj : jpqlResult) { 
			System.out.println(obj);
		}
	}
}
