package com.beecrowd.uri2602.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.beecrowd.uri2602.dto.CustomerMinDTO;
import com.beecrowd.uri2602.entities.Customer;
import com.beecrowd.uri2602.projections.CustomerMinProjection;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	//SQL
	@Query(nativeQuery = true, value = "SELECT name "
			+ "FROM customers "
			+ "WHERE UPPER(state) = UPPER(:state)" )
	List<CustomerMinProjection> sqlSearch(String state);
	
	//JPQL
	@Query("SELECT new com.beecrowd.uri2602.dto.CustomerMinDTO(obj.name) "
			+ "FROM Customer obj "
			+ "WHERE UPPER(state) = UPPER(:state)" )
	List<CustomerMinDTO> jpqlSearch(String state);

}
