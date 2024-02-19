package com.ra.controller.PermitAll;

import com.ra.model.dto.response.ProductResponse;
import com.ra.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class PermitAllProducts {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "productName", name = "sort") String sort) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sort));
        Page<ProductResponse> productResponses = productService.getAllPermitAll(pageable);
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @GetMapping("/search/{keyWord}")
    public ResponseEntity<?> getByNameOrDesc(@PathVariable String keyWord){
        List<ProductResponse> productResponseList = productService.findByKeyWord(keyWord);
        return new ResponseEntity<>(productResponseList,HttpStatus.OK);
    }

    @GetMapping("/new-products")
    public ResponseEntity<?> getAllNewProduct(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "id", name = "sort") String sort) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        Page<ProductResponse> productResponses = productService.getAllPermitAll(pageable);
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getByCategoryId(@PathVariable Long id){
        List<ProductResponse> productResponseList = productService.findByCategoryId(id);
        return new ResponseEntity<>(productResponseList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> search(@PathVariable Long id){
        ProductResponse productResponse = productService.findByIdPermitAll(id);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }
}
