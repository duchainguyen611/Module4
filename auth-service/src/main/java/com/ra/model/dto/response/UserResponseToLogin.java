package com.ra.model.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseToLogin {
    private Long id;
    private String accessToken;
    private final String type = "Bearer";
    private String fullName;
    private String userName;
    private Boolean status;
    private String Email;
    private String avatar;
    private String phone;
    private String address;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Set<String> Roles;

}
