package com.orchidservice.service;

import com.orchidservice.dto.CategoriesDTO;
import com.orchidservice.entity.Categories;

import java.util.List;

public interface CategoriesService {
    public void createCategories (Categories categories);
    public void updateCategories (Categories categories);
    public void deleteCategories (String categoriesId);
    public Categories getCategoriesById(String categoriesId);
    public List<CategoriesDTO> getAllCategories();
}
