package com.ra.model.dto.response;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponse {
    private String categoryName;
    private String description;
    private Boolean status;
}
