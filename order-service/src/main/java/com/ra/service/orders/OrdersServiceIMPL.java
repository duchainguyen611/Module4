package com.ra.service.orders;

import com.ra.model.dto.request.StatusOrderRequest;
import com.ra.model.dto.response.OrderDetailResponse;
import com.ra.model.dto.response.OrdersResponseToAdmin;
import com.ra.model.dto.response.OrdersResponseToUser;
import com.ra.model.dto.response.OrdersResponseToUserDetail;
import com.ra.model.entity.ENUM.StatusOrders;
import com.ra.model.entity.Orders;
import com.ra.repository.OrdersRepository;
import com.ra.service.orderDetail.OrderDetailService;
import com.ra.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServiceIMPL implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderDetailService orderDetailService;

    Logger logger = LoggerFactory.getLogger(OrdersServiceIMPL.class);

    @Override
    public Page<OrdersResponseToAdmin> getAll(Pageable pageable) {
        Page<Orders> orders = ordersRepository.findAll(pageable);
        return orders.map(this::convertOrdersToOrdersResponseAdmin);
    }

    @Override
    public void addOrders(Orders orders) {
        ordersRepository.save(orders);
    }

    @Override
    public Page<OrdersResponseToUser> getAllByUser(Pageable pageable) {
        Page<Orders> orders = ordersRepository.findAllByUser(userService.userLogin(), pageable);
        return orders.map(this::convertOrdersToOrdersResponseUser);
    }

    @Override
    public List<OrdersResponseToUserDetail> getBySerialNumber(String serial) {
        List<Orders> orders = ordersRepository.findAllBySerialNumberContainingIgnoreCase(serial, userService.userLogin().getId());
        return orders.stream()
                .map(this::convertOrdersToOrdersResponseUserDetail)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrdersResponseToUser> getByStatusUser(String status) {
        List<Orders> orders = ordersRepository.findAllByStatusOrdersContainingIgnoreCaseForUser(status, userService.userLogin().getId());
        return orders.stream()
                .map(this::convertOrdersToOrdersResponseUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrdersResponseToAdmin> getByStatusAdmin(String status) {
        List<Orders> orders = ordersRepository.findAllByStatusOrdersContainingIgnoreCase(status);
        if (orders.isEmpty()) {
            logger.error("Status not found");
            throw new RuntimeException();
        }
        return orders.stream()
                .map(this::convertOrdersToOrdersResponseAdmin)
                .collect(Collectors.toList());
    }

    public Orders findById(Long id) {
        return ordersRepository.findById(id).orElse(null);
    }

    @Override
    public OrdersResponseToUserDetail getOrderDetailAdminById(Long id) {
        Orders orders = findById(id);
        if (orders == null) {
            logger.error("ID not found");
            throw new RuntimeException();
        }
        return convertOrdersToOrdersResponseUserDetail(orders);
    }

    @Override
    public Orders updateStatusOrders(Long id, StatusOrderRequest statusOrderRequest) {
        Orders orders = findById(id);
        if (orders == null) {
            logger.error("ID not found");
            throw new RuntimeException();
        }
        boolean check = false;
        StatusOrders sto = null;
        for (StatusOrders statusOrders : StatusOrders.values()) {
            if (statusOrders.toString().equalsIgnoreCase(statusOrderRequest.getStatusOrders())) {
                check = true;
                sto = statusOrders;
            }
        }
        if (!check) {
            logger.error("Status not true");
            throw new RuntimeException();
        }
        orders.setStatusOrders(sto);
        return ordersRepository.save(orders);
    }

    @Override
    public OrdersResponseToUser updateOrderStatusWaitingToCancel(Long id) {
        Orders orders = ordersRepository.findByIdAndUserAndStatusOrders(id,userService.userLogin(),StatusOrders.WAITING);
        orders.setStatusOrders(StatusOrders.CANCEL);
        Orders ordersNew = ordersRepository.save(orders);
        return convertOrdersToOrdersResponseUser(ordersNew);
    }

    @Override
    public OrdersResponseToAdmin convertOrdersToOrdersResponseAdmin(Orders orders) {
        return OrdersResponseToAdmin.builder()
                .serialNumber(orders.getSerialNumber())
                .totalPrice(orders.getTotalPrice())
                .statusOrders(orders.getStatusOrders())
                .note(orders.getNote())
                .receiveName(orders.getReceiveName())
                .receiveAddress(orders.getReceiveAddress())
                .receivePhone(orders.getReceivePhone())
                .receivedAt(orders.getReceivedAt())
                .userId(orders.getUser().getId())
                .build();
    }



    public OrdersResponseToUser convertOrdersToOrdersResponseUser(Orders orders) {
        return OrdersResponseToUser.builder()
                .serialNumber(orders.getSerialNumber())
                .totalPrice(orders.getTotalPrice())
                .statusOrders(orders.getStatusOrders())
                .note(orders.getNote())
                .build();
    }

    public OrdersResponseToUserDetail convertOrdersToOrdersResponseUserDetail(Orders orders) {
        List<OrderDetailResponse> orderDetailResponses = orderDetailService.findByOrder(orders);
        return OrdersResponseToUserDetail.builder()
                .serialNumber(orders.getSerialNumber())
                .totalPrice(orders.getTotalPrice())
                .statusOrders(orders.getStatusOrders())
                .note(orders.getNote())
                .receiveName(orders.getReceiveName())
                .receiveAddress(orders.getReceiveAddress())
                .receivePhone(orders.getReceivePhone())
                .createdAt(orders.getCreatedAt())
                .receivedAt(orders.getReceivedAt())
                .orderDetailResponses(orderDetailResponses)
                .build();
    }
}
