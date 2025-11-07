package com.example.vti_2506_usermanagement.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private  T data;
    private  String message;
}
