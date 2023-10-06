package com.ni.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	@Size(min = 3, message = "Size should be minimum of 3 characters")
	private String productName;
	
	@Min(10)
	@Max(10000)
	@Column(nullable = false)
	private double price;
	
	
//	Mapping to product entity
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

}
