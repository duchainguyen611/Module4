package com.ra.controller.Admin;

import com.ra.model.dto.request.StatusOrderRequest;
import com.ra.model.dto.response.OrdersResponseToAdmin;
import com.ra.model.dto.response.OrdersResponseToUserDetail;
import com.ra.model.entity.Orders;
import com.ra.service.orders.OrdersService;
import jakarta.validation.Valid;
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
@RequestMapping("/v1/admin/orders")
public class OrdersControllerAdmin {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("")
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "id", name = "sort") String sort) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sort));
        Page<OrdersResponseToAdmin> orders = ordersService.getAll(pageable);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/status={status}")
    public ResponseEntity<?> getAllByStatus(@PathVariable String status) {
        List<OrdersResponseToAdmin> orders = ordersService.getByStatusAdmin(status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/id={id}")
    public ResponseEntity<OrdersResponseToUserDetail> getOrderDetailAdminById(@PathVariable Long id) {
       OrdersResponseToUserDetail ordersResponseToUserDetails = ordersService.getOrderDetailAdminById(id);
        return new ResponseEntity<>(ordersResponseToUserDetails, HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> updateStatusOrders(@PathVariable Long id,@RequestBody @Valid StatusOrderRequest statusOrderRequest){
        Orders orders = ordersService.updateStatusOrders(id,statusOrderRequest);
        OrdersResponseToAdmin ordersResponseToAdmin = ordersService.convertOrdersToOrdersResponseAdmin(orders);
        return new ResponseEntity<>(ordersResponseToAdmin,HttpStatus.OK);
    }
}
