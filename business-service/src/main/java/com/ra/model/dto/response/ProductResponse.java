package com.ra.model.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    private String productName;
    private String categoryName;
    private String description;
    private Double unitPrice;
    private String image;
}
