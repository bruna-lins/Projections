package com.beecrowd.uri2737;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.beecrowd.uri2737.dto.LawyerMinDTO;
import com.beecrowd.uri2737.projections.LawyerMinProjection;
import com.beecrowd.uri2737.repositories.LawyerRepository;

@SpringBootApplication
public class Uri2737Application implements CommandLineRunner {

	@Autowired
	private LawyerRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2737Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<LawyerMinProjection> list = repository.sqlSearch();
		List<LawyerMinDTO> sqlResult = list.stream().map(x -> new LawyerMinDTO(x)).collect(Collectors.toList());

		System.out.println("\n** RESULTADO SQL **");
		for (LawyerMinDTO obj : sqlResult) {
			System.out.println(obj);
		}

	}
}
