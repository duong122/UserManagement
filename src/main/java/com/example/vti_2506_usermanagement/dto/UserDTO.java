package com.example.vti_2506_usermanagement.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UserDTO {
    @NotBlank(message = "first name không được để trống")
    @Size(min=4, max=20, message = "fist name phải có từ 4 đến 20 ký tự")
    private String firstName;

    @NotBlank(message = "Lastname không được để trống")
    private String lastName;
    @Size(min=4, max=20, message = "last name không được để trống")

    @NotBlank(message="birthday không được bỏ trống")
    private LocalDate birthday;

    @NotBlank(message="address không được để trống")
    private String address;
}
