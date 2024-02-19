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
public class UserToUpdatePassword {
    @NotNull(message = "not Null")
    @NotEmpty(message = "not Empty")
    @NotBlank(message = "not Blank")
    String oldPassword;
    @NotNull(message = "not Null")
    @NotEmpty(message = "not Empty")
    @NotBlank(message = "not Blank")
    String newPassword;
    @NotNull(message = "not Null")
    @NotEmpty(message = "not Empty")
    @NotBlank(message = "not Blank")
    String newPasswordConfirm;
}
