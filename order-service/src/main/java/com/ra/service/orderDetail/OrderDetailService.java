package com.ra.service.orderDetail;

import com.ra.model.dto.response.OrderDetailResponse;
import com.ra.model.entity.Order_Detail;
import com.ra.model.entity.Orders;

import java.util.List;

public interface OrderDetailService {
    void addOrderDetail(Order_Detail order_detail);

    public OrderDetailResponse convertOderDetailToOrderDetailResponse(Order_Detail order_detail);

    List<OrderDetailResponse> findByOrder(Orders orders);
}
