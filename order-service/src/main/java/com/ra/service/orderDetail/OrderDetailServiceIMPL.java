package com.ra.service.orderDetail;

import com.ra.model.dto.response.OrderDetailResponse;
import com.ra.model.entity.Order_Detail;
import com.ra.model.entity.Orders;
import com.ra.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceIMPL implements OrderDetailService{
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public void addOrderDetail(Order_Detail order_detail) {
        orderDetailRepository.save(order_detail);
    }
    @Override
    public List<OrderDetailResponse> findByOrder(Orders orders){
        List<Order_Detail> orderDetails = orderDetailRepository.findByOrder(orders);
        return orderDetails.stream().map(this::convertOderDetailToOrderDetailResponse).toList();
    }

    @Override
    public OrderDetailResponse convertOderDetailToOrderDetailResponse(Order_Detail order_detail){
        return OrderDetailResponse.builder()
                .name(order_detail.getName())
                .unitPrice(order_detail.getUnitPrice())
                .orderQuantity(order_detail.getOrderQuantity())
                .build();
    }

}
