package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Product;
import com.example.service.ProductService;

@Controller
public class ProductController {	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/more-product/{keyword}&{startItem}&{size}")
	public String getMoreProduct(@PathVariable Optional<String> keyword,
			@PathVariable Optional<Integer> startItem,
			@PathVariable Optional<Integer> size, Model model) {
		List<Product> productList = productService.getProductList(keyword.orElse(""), startItem.orElse(1), size.orElse(12));
		model.addAttribute("productList", productList);
		return "product/pro_item::item";
	}
	
	@PostMapping("/end-of-content/{keyword}&{total}")
	public String endOfContent(@PathVariable String keyword,
			@PathVariable Integer total) {
		if (productService.endOfContent(keyword , total)) {
			return "product/pro_item::blank";
		}
		return "product/pro_item::notBlank";
	}
}
