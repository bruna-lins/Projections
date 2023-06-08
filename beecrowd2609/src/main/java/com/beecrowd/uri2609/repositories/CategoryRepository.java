package com.beecrowd.uri2609.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.beecrowd.uri2609.dto.CategorySumDTO;
import com.beecrowd.uri2609.entities.Category;
import com.beecrowd.uri2609.projections.CategorySumProjection;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(nativeQuery = true, value = "SELECT c.name, SUM(p.amount) "
			+ "FROM categories c "
			+ "INNER JOIN products p ON p.id_categories = c.id "
			+ "GROUP BY c.name")
	List<CategorySumProjection> sqlSearch();
	
	//JPQL
	@Query("SELECT new com.beecrowd.uri2609.dto.CategorySumDTO(obj.category.name, SUM(obj.amount)) "
			+ "FROM Product obj "
			+ "GROUP BY obj.category.name" )
	List<CategorySumDTO> jpqlSearch();
	
}
