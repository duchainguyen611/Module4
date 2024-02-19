package com.ra.controller.Admin;

import com.ra.model.dto.request.CategoryRequest;
import com.ra.model.entity.Category;
import com.ra.service.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    // Hien thi tat ca
    @GetMapping("")
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "5",name = "limit")int limit,
            @RequestParam(defaultValue = "0",name = "page") int page,
            @RequestParam(defaultValue = "categoryName",name = "sort") String sort){
        Pageable pageable = PageRequest.of(page,limit,Sort.by(sort));
        Page<Category> categories = categoryService.getAll(pageable);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    // Them
    @PostMapping("")
    public ResponseEntity<Category> save(@RequestBody @Valid CategoryRequest categoryRequest){
            categoryRequest.setStatus(true);
            Category categoryValue = categoryService.convertCategoryRequestToCategory(categoryRequest);
            Category categoryNew = categoryService.save(categoryValue);
            return new ResponseEntity<>(categoryNew,HttpStatus.CREATED);
    }
    //Tim kiem theo id
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id){
        Category category = categoryService.findById(id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    //Sua toan bo thong tin
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@RequestBody @Valid CategoryRequest categoryRequest,@PathVariable Long id){
        Category categoryValue = categoryService.convertCategoryRequestToCategory(categoryRequest);
        categoryValue.setId(id);
        Category categoryUpdate = categoryService.save(categoryValue);
        return new ResponseEntity<>(categoryUpdate,HttpStatus.OK);
    }

    //Xoa theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        categoryService.delete(id);
        return new ResponseEntity<>("Delete complete",HttpStatus.NO_CONTENT);
    }
}
