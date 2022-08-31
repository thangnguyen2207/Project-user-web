package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.service.ProductGroupService;

@Controller
public class ShopController {
	@Autowired
	private ProductGroupService productGroupService;
	

	@GetMapping("/shop")
	public String toShopPage(Model model) {
		model.addAttribute("productGroup", productGroupService.getProductGroupList());
		return "shop/shop";
	}
	
	@GetMapping("/shop/{keyword}")
	public String toShopPage2(@PathVariable String keyword, RedirectAttributes rAttributes) {
		rAttributes.addAttribute("keyword", keyword);
		return "redirect:/shop";
	}
}
