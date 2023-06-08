package com.beecrowd.uri2990;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.beecrowd.uri2990.dto.EmpregadoDeptDTO;
import com.beecrowd.uri2990.projections.EmpregadoDeptProjection;
import com.beecrowd.uri2990.repositories.EmpregadoRepository;

@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<EmpregadoDeptProjection> list = repository.firstSqlSearch();
		List<EmpregadoDeptDTO> firstSqlResult = list.stream().map(x -> new EmpregadoDeptDTO(x)).collect(Collectors.toList());
		
		System.out.println("\n** RESULTADO SQL NOT IN **");
		for (EmpregadoDeptDTO obj : firstSqlResult) { 
			System.out.println(obj);
		}
		
		List<EmpregadoDeptProjection> secondList = repository.secondSqlSearch();
		List<EmpregadoDeptDTO> secondSqlResult = secondList.stream().map(x -> new EmpregadoDeptDTO(x)).collect(Collectors.toList());
		
		System.out.println("\n** RESULTADO SQL LEFT JOIN **");
		for (EmpregadoDeptDTO obj : secondSqlResult) { 
			System.out.println(obj);
		}
		
		System.out.println("\n\n");
		
		List<EmpregadoDeptDTO> jpqlResult = repository.jpqlSearch();
		
		System.out.println("\n** RESULTADO JPQL **");
		for (EmpregadoDeptDTO obj : jpqlResult) { 
			System.out.println(obj);
		}
	}
}
