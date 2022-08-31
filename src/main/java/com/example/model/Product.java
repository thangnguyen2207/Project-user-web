package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	private String hhId;
	
	private String hhTen;
	
	private String hhDonViTinh;
	
	private int hhSoLuongTon;
	
	private int hhDonGia;
	
	private String hhMoTa;
	
	@ManyToOne
	private ProductGroup productGroup;
	
}

