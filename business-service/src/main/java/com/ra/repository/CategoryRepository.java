package com.ra.repository;

import com.ra.model.entity.Category;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Collectors;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
