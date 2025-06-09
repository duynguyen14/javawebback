package com.example.back.service;


import com.example.back.dto.response.Category.CategoryDTO;
import com.example.back.entity.Catalog;
import com.example.back.entity.Category;
import com.example.back.mapper.CategoryMapper;
import com.example.back.repository.CatalogRepository;
import com.example.back.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CatalogRepository catalogRepository;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO addCategory(CategoryDTO dto) {
        Catalog catalog = catalogRepository.findById(dto.getCatalogId())
                .orElseThrow(() -> new RuntimeException("Catalog not found"));

        Category category = CategoryMapper.toEntity(dto, catalog);
        Category saved = categoryRepository.save(category);
        return CategoryMapper.toDTO(saved);
    }

    public CategoryDTO updateCategory(Integer id, CategoryDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(dto.getName());
        Category updated = categoryRepository.save(category);
        return CategoryMapper.toDTO(updated);
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}

