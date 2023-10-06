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

import com.ni.models.Category;
import com.ni.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired CategoryService categoryService;
	
//	Get all category
	@GetMapping("")
	public ResponseEntity<?> getAllCategory() {
		return categoryService.getAllCategory();
	}
	

//	Add a category
	@PostMapping("")
	public ResponseEntity<?> addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
	}
	
//	Get category by id
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable int id){
		return categoryService.getCategoryById(id);
	}
	
	
//	Update category by id
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody Category category){
		return categoryService.updateCategory(id, category);
		
	}
	
//	Delete category by id 
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int id){
		return categoryService.deleteCategory(id);
	}
	
	
////Assign category to product
	@PutMapping("/{categoryId}/products/{productId}")
	public ResponseEntity<?> assignCategory(@PathVariable int productId, @PathVariable int categoryId){
		return categoryService.assignCategory(productId, categoryId);
	}

}
