package com.orchidservice.controller;

import com.orchidservice.dto.CategoriesDTO;
import com.orchidservice.dto.response.ApiResponse;
import com.orchidservice.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/api/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategories() {
        List<CategoriesDTO> categoriesList = categoriesService.getAllCategories();
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Categories fetched successfully",
                200,
                categoriesList
        ));
    }
}
