package com.example.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Product;
import com.example.service.ProductService;

@Controller
public class WishlistController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/wishlist")
	public String toWishlist() {
		return "wishlist/wishlist";
	}
	
	@PostMapping("/get-wishlist-item/{ids}")
	public String getFavoriteItem(@PathVariable("ids") String ids, Model model) {
		String[] tokens = ids.split("-");
		List<Product> productList = productService.getProductsByIds(Arrays.asList(tokens));
		model.addAttribute("productList", productList);
		return "wishlist/wishlist_item :: item";
	}
}
