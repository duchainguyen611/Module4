package com.ra.model.dto.response;

import com.ra.model.entity.ENUM.StatusOrders;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrdersResponseToUser {
    private String serialNumber;
    private Double totalPrice;
    private StatusOrders statusOrders;
    private String note;
}
