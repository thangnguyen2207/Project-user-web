package com.example.service;

import java.util.List;

import com.example.model.Product;

public interface ProductService {
	public List<Product> getProductList(String keyword, int startItem, int size);
	
	public Product getProduct(String hhId);
	
	public List<Product> getProductsByIds(List<String> ids);
	
	public Boolean endOfContent(String keyword ,int startItem);
}
