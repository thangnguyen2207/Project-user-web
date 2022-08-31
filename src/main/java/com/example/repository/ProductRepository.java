package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	@Query("SELECT COUNT(p) FROM Product p, ProductGroup pg WHERE p.productGroup.nhId = pg.nhId AND pg.nhTen LIKE %:name%")
	public Integer countByName(String name);

}
