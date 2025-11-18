package com.example.vti_2506_usermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilter {
    private String firstName;
    private String lastName;
    private String address;
    private String roleName;
}
