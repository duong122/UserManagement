package com.example.vti_2506_usermanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestAlterUserInGroupDTO {
    @NotNull
    @Min(value = 1, message = "")
    private Integer groupId;


    private List<Integer> userIds;
}
