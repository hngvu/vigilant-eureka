package com.orchidservice.service.Impl;

import com.orchidservice.dto.CategoriesDTO;
import com.orchidservice.entity.Categories;
import com.orchidservice.repository.CategoriesRepostory;
import com.orchidservice.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesRepostory categoriesRepostory;
    @Override
    public void createCategories(Categories categories) {
        Optional<Categories> ca = categoriesRepostory.findById(categories.getId());
        if (ca.isPresent()) {
            throw new IllegalArgumentException("Category with ID " + categories.getId() + " already exists.");
        } else {
            categoriesRepostory.save(categories);
            System.out.println("Category created successfully: " + categories);
        }
    }

    @Override
    public void updateCategories(Categories categories) {
        Optional<Categories> ca = categoriesRepostory.findById(categories.getId());
        if (ca.isPresent()) {
            Categories existingCategory = ca.get();

            if (categories.getCategoryName() != null) {
                existingCategory.setCategoryName(categories.getCategoryName());
            }
            if (categories.getOrchidsList() != null) {
                existingCategory.setOrchidsList(categories.getOrchidsList());
            }

            categoriesRepostory.save(existingCategory);
            System.out.println("Category updated successfully: " + existingCategory);
        } else {
            throw new IllegalArgumentException("Category with ID " + categories.getId() + " does not exist.");
        }
    }

    @Override
    public void deleteCategories(String categoriesId) {
        Optional<Categories> ca = categoriesRepostory.findById(categoriesId);
        if (ca.isPresent()) {
            categoriesRepostory.deleteById(categoriesId);
            System.out.println("Category deleted successfully: " + categoriesId);
        } else {
            throw new IllegalArgumentException("Category with ID " + categoriesId + " does not exist.");
        }
    }

    @Override
    public Categories getCategoriesById(String categoriesId) {
        Optional<Categories> ca = categoriesRepostory.findById(categoriesId);
        return ca.get();
    }

    @Override
    public List<CategoriesDTO> getAllCategories() {
        List<Categories> categories = categoriesRepostory.findAll();
        List<CategoriesDTO> categoryDtos = new ArrayList<>();
        for (Categories category : categories) {
            CategoriesDTO categoryDto = CategoriesDTO.builder()
                    .id(category.getId())
                    .categoryName(category.getCategoryName())
                    .build();
            categoryDtos.add(categoryDto);
        }
        return  categoryDtos;
    }
}
