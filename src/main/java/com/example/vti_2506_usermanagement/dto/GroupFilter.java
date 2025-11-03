package com.example.vti_2506_usermanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GroupFilter {
    private String groupName;
    private String groupPolicy;
    private String establishDay;
}
