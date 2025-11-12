package com.example.vti_2506_usermanagement.exception;

import com.example.vti_2506_usermanagement.common.BaseResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<Object>> handleBusinessException(BusinessException e, ServletWebRequest request) {
        return ResponseEntity.badRequest().body(new BaseResponse<>(null, e.getMessage(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, ServletWebRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(new BaseResponse<>(null, "Validation Failed", fieldErrors));
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<BaseResponse<Object>> handleConstraintViolationException(ConstraintViolationException e, ServletWebRequest request) {
//        Map<String, String> fieldErrors = new HashMap<>();
//        e.getConstraintViolations().forEach(cv -> {
//            String field = cv.getPropertyPath().toString();
//            String message = cv.getMessage();
//            fieldErrors.put(field, message);
//        });
//
//        return ResponseEntity.badRequest().body(new BaseResponse<>(null, "Validateion failed", fieldErrors));
//    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<BaseResponse<Object>> handleRunTimeException(Exception e, ServletWebRequest request) {
//        return ResponseEntity.badRequest().body(new BaseResponse<>(null, e.getMessage(), null));
//    }
}
