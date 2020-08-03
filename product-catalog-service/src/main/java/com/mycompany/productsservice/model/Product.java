package com.mycompany.productsservice.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Product {

	@Id
	private String id;
	private String name;
	private String desc;
	private String category;
	private String image;
	private String price;
}
