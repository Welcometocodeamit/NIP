package com.ni.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ni.models.Product;
import com.ni.responsewrapper.ProductResponseWrapper;
import com.ni.restrepositories.ProductRestRepository;

@Service
public class ProductService {
	
    @Autowired ProductRestRepository productRestRepository;
	
	ProductResponseWrapper productResponseWrapper = new ProductResponseWrapper();
	
//	Get all products
	public ResponseEntity<?> getAllProducts() {
		List<Product> foundProducts=productRestRepository.findAll();
		if(foundProducts.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Products not found in database");
		}else {
			productResponseWrapper.setMessage("Following products found");
			productResponseWrapper.setData(foundProducts);
			return new ResponseEntity<> (productResponseWrapper, HttpStatus.FOUND);
		}
	}
	

//	Add a product
	public ResponseEntity<?> addProduct(Product product){
		Product savedProduct=productRestRepository.save(product);
		if(savedProduct == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Products not added in database");
		}else {
			productResponseWrapper.setMessage("Following products added");
			productResponseWrapper.setData(savedProduct);
			return new ResponseEntity<> (productResponseWrapper, HttpStatus.FOUND);
		}
	}
	
//	Get product by id
	public ResponseEntity<?> getProductById(int id){
		Product foundProduct=productRestRepository.findById(id).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
		});
		
		productResponseWrapper.setMessage("Following product found");
		productResponseWrapper.setData(foundProduct);
		return new ResponseEntity<> (productResponseWrapper, HttpStatus.FOUND);			
	}
	
	
//	Update product by id
	public ResponseEntity<?> updateProduct(int id, Product product){
		getProductById(id);
		product.setId(id);
		Product updatedProduct = productRestRepository.save(product);
		productResponseWrapper.setMessage("Following product updated");
		productResponseWrapper.setData(updatedProduct);
		return new ResponseEntity<> (productResponseWrapper, HttpStatus.OK);
	}
	
//	Delete product by id 
	public ResponseEntity<?> deleteProduct(int id){
		getProductById(id);
		productRestRepository.deleteById(id);
		productResponseWrapper.setMessage("PRoduct deleted with id "+id);
		productResponseWrapper.setData(null);
		return new ResponseEntity<> (productResponseWrapper, HttpStatus.OK);
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
