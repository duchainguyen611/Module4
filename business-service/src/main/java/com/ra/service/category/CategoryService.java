package com.ra.service.category;

import com.ra.model.dto.request.CategoryRequest;
import com.ra.model.dto.response.CategoryResponse;
import com.ra.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<Category> getAll(Pageable pageable);

    Page<CategoryResponse> getAllPermitAll(Pageable pageable);
    Category findById(Long id);
    Category save(Category category);
    void delete(Long id);
    Category convertCategoryRequestToCategory(CategoryRequest categoryRequest);
    CategoryResponse convertCategoryToCategoryResponse(Category category);
}
