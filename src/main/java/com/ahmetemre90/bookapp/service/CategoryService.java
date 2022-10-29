package com.ahmetemre90.bookapp.service;

import com.ahmetemre90.bookapp.dto.ErrorCode;
import com.ahmetemre90.bookapp.exception.GenericException;
import com.ahmetemre90.bookapp.model.Category;
import com.ahmetemre90.bookapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategory(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() -> GenericException.builder().errorCode(ErrorCode.CATEGORY_NOT_FOUND).build());
    }

    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> GenericException.builder().errorCode(ErrorCode.CATEGORY_NOT_FOUND).build());
    }
}
