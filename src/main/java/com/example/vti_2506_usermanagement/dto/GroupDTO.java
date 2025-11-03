package com.example.vti_2506_usermanagement.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GroupDTO {
    @NotNull(message = "Group name không được để trống")
    @Size(min=1, max=255, message="Group name phải có độ dài từ 1 - 255 ký tự")
    private String groupName;

    @NotNull
    @Size(min=1, message="Group policy không được để trống")
    private String groupPolicy;

    @NotNull
    private String establishDay;
}
