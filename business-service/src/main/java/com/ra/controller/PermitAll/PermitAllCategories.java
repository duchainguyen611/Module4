package com.ra.controller.PermitAll;

import com.ra.model.dto.response.CategoryResponse;
import com.ra.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/categories")
public class PermitAllCategories {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "5",name = "limit")int limit,
                                    @RequestParam(defaultValue = "0",name = "page") int page,
                                    @RequestParam(defaultValue = "categoryName",name = "sort") String sort){
        Pageable pageable = PageRequest.of(page,limit, Sort.by(sort));
        Page<CategoryResponse> categoryResponses = categoryService.getAllPermitAll(pageable);
        return new ResponseEntity<>(categoryResponses, HttpStatus.OK);
    }

}

