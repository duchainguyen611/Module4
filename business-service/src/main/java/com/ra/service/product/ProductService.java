package com.ra.service.product;

import com.ra.model.dto.request.ProductRequest;
import com.ra.model.dto.response.ProductResponse;
import com.ra.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Page<Product> getAll(Pageable pageable);
    Page<ProductResponse> getAllPermitAll(Pageable pageable);

    List<ProductResponse> findByKeyWord(String keyWord);

    ProductResponse findByIdPermitAll(Long id);
    List<ProductResponse> findByCategoryId(Long id);

    Product findById(Long id);
    Product save(ProductRequest productRequest);
    Product update(ProductRequest productRequest,Long id);
    void delete(Long id);
    Product convertProductRequestToProduct(ProductRequest productRequest);

}
