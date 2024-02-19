package com.ra.model.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailResponse {
    private String name;
    private Double unitPrice;
    private Integer orderQuantity;
}
