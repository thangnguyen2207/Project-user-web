package com.example.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "product_group")
@AllArgsConstructor
@NoArgsConstructor
public class ProductGroup {
	@Id
	private int nhId;
	
	private String nhTen;
	
	@OneToMany(mappedBy = "productGroup", cascade = CascadeType.ALL)
	private List<Product> products;
}

