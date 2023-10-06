package com.ni.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ni.models.Category;
import com.ni.models.Product;
import com.ni.responsewrapper.CategoryResponseWrapper;
import com.ni.restrepositories.CategoryRestRepository;
import com.ni.restrepositories.ProductRestRepository;

@Service
public class CategoryService {
	
	@Autowired CategoryRestRepository categoryRestRepository;
	@Autowired ProductRestRepository productRestRepository;
	
	CategoryResponseWrapper categoryResponseWrapper = new CategoryResponseWrapper();
	
//	Get all category
	public ResponseEntity<?> getAllCategory() {
		List<Category> foundCategory=categoryRestRepository.findAll();
		if(foundCategory.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found in database");
		}else {
			categoryResponseWrapper.setMessage("Following Category found");
			categoryResponseWrapper.setData(foundCategory);
			return new ResponseEntity<> (categoryResponseWrapper, HttpStatus.FOUND);
		}
	}
	
//	Add a category
	public ResponseEntity<?> addCategory(Category category){
		Category savedCategory=categoryRestRepository.save(category);
		if(savedCategory == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not added in database");
		}else {
			categoryResponseWrapper.setMessage("Following category added");
			categoryResponseWrapper.setData(savedCategory);
			return new ResponseEntity<> (categoryResponseWrapper, HttpStatus.FOUND);
		}
	}
	
	
//	Get category by id
	public ResponseEntity<?> getCategoryById(int id){
		Category foundCategory=categoryRestRepository.findById(id).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
		});
		
		categoryResponseWrapper.setMessage("Following Category found");
		categoryResponseWrapper.setData(foundCategory);
		return new ResponseEntity<> (categoryResponseWrapper, HttpStatus.FOUND);			
	}
	
//	Update category by id
	public ResponseEntity<?> updateCategory(int id, Category category){
		getCategoryById(id);
		category.setId(id);
		Category updatedCategory = categoryRestRepository.save(category);
		categoryResponseWrapper.setMessage("Following Category updated");
		categoryResponseWrapper.setData(updatedCategory);
		return new ResponseEntity<> (categoryResponseWrapper, HttpStatus.OK);
	}
	
//	Delete Category by id 
	public ResponseEntity<?> deleteCategory(int id){
		getCategoryById(id);
		categoryRestRepository.deleteById(id);
		categoryResponseWrapper.setMessage("Category deleted with id "+id);
		categoryResponseWrapper.setData(null);
		return new ResponseEntity<> (categoryResponseWrapper, HttpStatus.OK);
	}
	
	
//	Assign product to category
	public ResponseEntity<?> assignCategory(int productId, int categoryId){
		 Product foundProduct = productRestRepository.findById(productId).orElseThrow(()->{
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with given id "+productId+" not found");
		 });
		 
		 Category foundCategory = categoryRestRepository.findById(categoryId).orElseThrow(()->{
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with given id "+categoryId+" not found");
		 });
		 
		 foundProduct.setCategory(foundCategory);
		 productRestRepository.save(foundProduct);
		 categoryResponseWrapper.setMessage("Category of product updated");
		 categoryResponseWrapper.setData(foundProduct);
		 return new ResponseEntity<> (categoryResponseWrapper, HttpStatus.OK);	 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
