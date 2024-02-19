package com.ra.model.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseToAdmin {
    private Long id;
    private String fullName;
    private String userName;
    private Boolean status;
    private String Email;
    private String avatar;
    private String phone;
    private String address;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<String> roleName;

}
