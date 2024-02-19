package com.ra.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {
    @NotEmpty(message = "not Empty")
    @NotBlank(message = "not Blank")
    private String productName;
    @NotNull(message = "not Null")
    private Long categoryId;
    private String description;
    @Min(value = 1,message = "min = 1")
    private Double unitPrice;
    @Min(value = 1,message = "min = 1")
    private Integer stockQuantity;
    private String image;
    private LocalDate createdAt;
}
