package com.ra.repository;

import com.ra.model.entity.Order_Detail;
import com.ra.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<Order_Detail,Long> {
    List<Order_Detail> findByOrder(Orders order);
}
