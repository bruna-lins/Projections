package com.beecrowd.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.beecrowd.uri2621.dto.ProductMinDTO;
import com.beecrowd.uri2621.entities.Product;
import com.beecrowd.uri2621.projections.ProductMinProjection;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(nativeQuery = true, value = "SELECT products.name FROM products "
			+ "INNER JOIN providers ON products.id_providers = providers.id "
			+ "WHERE products.amount BETWEEN :min AND :max "
			+ "AND providers.name LIKE CONCAT(:beginName, '%')")
	List<ProductMinProjection> sqlSearch(Integer min, Integer max, String beginName);
	
	@Query("SELECT new com.beecrowd.uri2621.dto.ProductMinDTO(obj.name) FROM Product obj "
			+ "WHERE obj.amount BETWEEN :min AND :max "
			+ "AND obj.provider.name LIKE CONCAT(:beginName, '%')")
	List<ProductMinDTO> jpqlSearch(Integer min, Integer max, String beginName);

}
