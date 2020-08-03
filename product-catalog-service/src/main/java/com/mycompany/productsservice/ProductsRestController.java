package com.mycompany.productsservice;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.productsservice.dao.ProductRepository;
import com.mycompany.productsservice.dto.response.GenericDtoResponse;
import com.mycompany.productsservice.model.Constancts;
import com.mycompany.productsservice.model.Product;

@CrossOrigin("*")
@RestController
@RequestMapping("api/products")
public class ProductsRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductsRestController.class);

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public GenericDtoResponse<Product> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return GenericDtoResponse.<Product>builder().statusCode(Constancts.SUCCESS).data(products).build();
	}

	@GetMapping("{id}")
	public ResponseEntity<GenericDtoResponse<Product>> getProduct(@PathParam("id") String id) {
		Optional<Product> product = productRepository.findById(id);
		return product.isPresent() ? 
				ResponseEntity.status(HttpStatus.OK).body(GenericDtoResponse.<Product>builder().statusCode(Constancts.SUCCESS).data(Arrays.asList(product.get())).build()) 
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericDtoResponse.<Product>builder().statusCode(Constancts.FAILURE).build());
	}

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<GenericDtoResponse<Product>> addProduct(@RequestBody Product newProduct) {
		try {
			newProduct = productRepository.save(newProduct);
		} catch (Exception e) {
			logger.error("failed to save the product", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(GenericDtoResponse.<Product>builder().statusCode(Constancts.FAILURE).build());
		}
		try {
			return ResponseEntity.created(new URI(newProduct.getId().toString())).build();
		} catch (URISyntaxException e) {
			logger.error("failed to save the product", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(GenericDtoResponse.<Product>builder().statusCode(Constancts.FAILURE).build());
		}

	}

}
