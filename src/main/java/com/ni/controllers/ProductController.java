package com.ni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ni.models.Product;
import com.ni.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired ProductService productService;
	
//	Get all products
	@GetMapping("")
	public ResponseEntity<?> getAllProducts() {
		return productService.getAllProducts();
	}
	

//	Add a product
	@PostMapping("")
	public ResponseEntity<?> addProduct(@RequestBody Product product){
        return productService.addProduct(product);
	}
	
//	Get product by id
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id){
		return productService.getProductById(id);
	}
	
	
//	Update product by id
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product product){
		return productService.updateProduct(id, product);
		
	}
	
//	Delete product by id 
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id){
		return productService.deleteProduct(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
