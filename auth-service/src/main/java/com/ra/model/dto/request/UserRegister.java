package com.ra.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRegister {
    @NotBlank(message = "not Blank")
    private String fullName;


    @NotBlank(message = "not Blank")
    @Length(min = 6,max = 100,message = "Minimum 6 characters, maximum 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+",message = "No character special")
    private String username;

    @NotBlank(message = "not Blank")
    private String password;

    @NotBlank(message = "not Blank")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "email must be correct format")
    private String email;

    private String avatar;

    @Pattern(regexp = "(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b")
    private String phone;

    private Boolean status;

    @NotBlank(message = "not Blank")
    private String address;

}
