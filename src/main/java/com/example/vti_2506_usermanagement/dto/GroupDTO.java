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
    @NotNull(message = "Group name không được để trống")
    @Min(value = 1)
    @Max(value = 255)
    private String groupName;

    @NotNull
    @Size(min=1, message="Group policy không được để trống")
    private String groupPolicy;

    @NotNull
    @PastOrPresent
    private LocalDate establishDay;
}
