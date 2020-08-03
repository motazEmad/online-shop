package com.mycompany.productsservice.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.productsservice.model.Product;

public interface ProductRepository extends CrudRepository<Product ,String> {

	List<Product> findAll();
	List<Product> findByName();
	Product findById();
}
