package com.ra.controller.User;

import com.ra.model.dto.response.OrdersResponseToUser;
import com.ra.model.dto.response.OrdersResponseToUserDetail;
import com.ra.service.orders.OrdersService;
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
@RequestMapping("/v1/user/history")
public class OrdersControllerUser {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("")
    public ResponseEntity<?> getAllByUser(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "id", name = "sort") String sort) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sort));
        Page<OrdersResponseToUser> orderResponseToUsers = ordersService.getAllByUser(pageable);
        return new ResponseEntity<>(orderResponseToUsers, HttpStatus.OK);
    }

    @GetMapping("/serial={serial}")
    public ResponseEntity<?> getOrderDetailBySerial(@PathVariable String serial){
        List<OrdersResponseToUserDetail> ordersResponseToUserDetails = ordersService.getBySerialNumber(serial);
        return new ResponseEntity<>(ordersResponseToUserDetails,HttpStatus.OK);
    }

    @GetMapping("/orderStatus={orderStatus}")
    public ResponseEntity<?> getOrderDetailByStatus(@PathVariable String orderStatus){
        List<OrdersResponseToUser> ordersResponseToUserDetails = ordersService.getByStatusUser(orderStatus);
        return new ResponseEntity<>(ordersResponseToUserDetails,HttpStatus.OK);
    }

    @GetMapping("/cancel/{id}")
    public ResponseEntity<OrdersResponseToUser> updateStatusToCancel(@PathVariable Long id){
        OrdersResponseToUser ordersResponseToUser = ordersService.updateOrderStatusWaitingToCancel(id);
        return new ResponseEntity<>(ordersResponseToUser,HttpStatus.OK);
    }
}
