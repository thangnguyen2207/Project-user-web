package com.example.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Product;
import com.example.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private EntityManager entityManager;
	
	private static final Map<String, Product> productMap = new HashMap<>();
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getProductList(String keyword , int startItem, int size) {
		if (startItem == 0) {
			productMap.clear();
		}
		List<Product> list = entityManager.createQuery(
			"SELECT p FROM Product p, ProductGroup pg WHERE p.productGroup.nhId = pg.nhId" + 
			(keyword.equals("") ? "" : " AND pg.nhTen LIKE '%" + keyword + "%'"), Product.class).getResultList();
		list.forEach(product -> {
			productMap.put(product.getHhId(), product);
		});
		List<Product> productList = new ArrayList<>(productMap.values());
		if (productList.size() < startItem) {
			return Collections.emptyList();
		}
		int toIndex = Math.min(startItem + size, productList.size());
		return productList.subList(startItem, toIndex);
	}

	@Override
	public Boolean endOfContent(String keyword , int startItem) {
		return productRepository.countByName(keyword) <= startItem;
	}

	@Override
	public Product getProduct(String hhId) {
		return productRepository.findById(hhId).orElse(null);
	}

	@Override
	public List<Product> getProductsByIds(List<String> ids) {
		return productRepository.findAllById(ids);
	}
}
