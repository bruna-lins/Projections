package com.beecrowd.uri2611;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.beecrowd.uri2611.dto.MovieMinDTO;
import com.beecrowd.uri2611.projections.MovieMinProjection;
import com.beecrowd.uri2611.repositories.MovieRepository;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner{

	@Autowired
	private MovieRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<MovieMinProjection> list = repository.sqlSearch("Action");
		List<MovieMinDTO> sqlResult = list.stream().map(x -> new MovieMinDTO(x)).collect(Collectors.toList());
		
		System.out.println("\n** RESULTADO SQL **");
		for (MovieMinDTO obj : sqlResult) { 
			System.out.println(obj);
		}
		System.out.println("\n\n");
		
		List<MovieMinDTO> jpqlResult = repository.jpqlSearch("Action");
		
		System.out.println("\n** RESULTADO JPQL **");
		for (MovieMinDTO obj : jpqlResult) { 
			System.out.println(obj);
		} 
	}
}
