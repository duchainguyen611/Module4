package com.ra.model.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShoppingCartResponse {
    String productName;
    Double unitPrice;
    Integer orderQuantity;
}
