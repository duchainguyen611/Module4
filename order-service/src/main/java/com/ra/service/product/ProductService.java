package com.ra.service.product;

import com.ra.model.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "http://desktop-75dise2:8082/v1/admin/products",name = "Product")
public interface ProductService {
    @GetMapping("/getAll")
    List<Product> getAll();
}
