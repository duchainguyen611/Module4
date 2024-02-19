package com.ra.model.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseToUser {
    private String userName;
    private String fullName;
    private String Email;
    private String avatar;
    private String phone;
    private String address;
}
