package com.ni.restrepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ni.models.Product;

public interface ProductRestRepository  extends JpaRepository<Product, Integer>{

}
