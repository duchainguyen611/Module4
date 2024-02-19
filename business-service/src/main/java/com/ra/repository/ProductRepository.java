package com.ra.repository;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT p.* FROM Product p WHERE p.productName LIKE CONCAT('%', ?1, '%') OR p.description LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<Product> findByProductNameOrDescription(String keyword);
    List<Product> findAllByCategory(Category category);

}
