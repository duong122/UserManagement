package com.example.vti_2506_usermanagement.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Getter
@Setter
public class GroupDTO {
    @NotBlank(message = "Group name không được để trống")
    @Size(min=1, max=255, message = "Group name có độ dài từ 1-255 ký tự")
    private String groupName;

    @NotBlank(message = "Group Policy không được để trống")
    @Size(min=1, message="Group policy không được để trống")
    private String groupPolicy;

    @NotNull
    @PastOrPresent
    private LocalDate establishDay;
}
