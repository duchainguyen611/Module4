package com.ra.service.category;

import com.ra.model.dto.request.CategoryRequest;
import com.ra.model.dto.response.CategoryResponse;
import com.ra.model.entity.Category;
import com.ra.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceIMPL implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<CategoryResponse> getAllPermitAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(this::convertCategoryToCategoryResponse);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }


    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }


    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category convertCategoryRequestToCategory(CategoryRequest categoryRequest){
        return Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .description(categoryRequest.getDescription())
                .status(categoryRequest.getStatus())
                .build();
    }

    public CategoryResponse convertCategoryToCategoryResponse(Category category){
        return CategoryResponse.builder()
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .status(category.getStatus())
                .build();
    }

}
