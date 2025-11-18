package com.example.vti_2506_usermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UpdateUserDTO {
    @NotBlank(message = "first name không được để trống")
    @Size(min=1, max=20, message = "fist name phải có từ 4 đến 20 ký tự")
    private String firstName;

    @NotBlank(message = "Lastname không được để trống")
    @Size(min=1, max=20, message = "last name không được để trống")
    private String lastName;

    @NotNull(message="birthday không được để trống")
    private LocalDate birthday;

    @NotBlank(message="address không được để trống")
    private String address;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 1, max = 20, message = "Password phải có chiều dài 1-20 ký tự")
    private String password;

    private String roleName;

}
