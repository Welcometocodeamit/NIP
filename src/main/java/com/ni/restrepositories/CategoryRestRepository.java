package com.ni.restrepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ni.models.Category;

public interface CategoryRestRepository extends JpaRepository<Category, Integer> {

}
