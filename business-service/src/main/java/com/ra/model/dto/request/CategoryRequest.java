package com.ra.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryRequest {
    @NotNull(message = "not Null")
    @NotEmpty(message = "not Empty")
    @NotBlank(message = "not Blank")
    private String categoryName;
    private String description;
    private Boolean status;
}
