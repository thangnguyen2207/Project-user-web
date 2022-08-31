package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.model.Product;
import com.example.service.ProductService;

@Controller
public class ProductDetailController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/product/{hhId}")
	public String toProductDetailPage(@PathVariable String hhId, Model model) {
		Product product = productService.getProduct(hhId);
		model.addAttribute("product", product);
		return "product/detail";
	}
}
