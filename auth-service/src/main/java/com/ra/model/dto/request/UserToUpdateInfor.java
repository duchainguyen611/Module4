package com.ra.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserToUpdateInfor {
    @NotNull(message = "not Null")
    @NotEmpty(message = "not Empty")
    @NotBlank(message = "not Blank")
    private String fullName;
    @NotBlank(message = "not Blank")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "email must be correct format")
    private String Email;
    private String avatar;
    @Pattern(regexp = "(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b")
    private String phone;
    @NotNull(message = "not Null")
    @NotEmpty(message = "not Empty")
    @NotBlank(message = "not Blank")
    private String address;
}
