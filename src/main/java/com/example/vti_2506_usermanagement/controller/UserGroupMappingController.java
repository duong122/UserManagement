package com.example.vti_2506_usermanagement.controller;

import com.example.vti_2506_usermanagement.common.BaseResponse;
import com.example.vti_2506_usermanagement.dto.RequestAlterUserInGroupDTO;
import com.example.vti_2506_usermanagement.service.UserGroupMappingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ugm")
@Validated
public class UserGroupMappingController {
    private final UserGroupMappingService userGroupMappingService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/alter")
    public ResponseEntity<BaseResponse<Object>> alterUsersInGroup(
            @Valid @RequestBody RequestAlterUserInGroupDTO dto
    ) {
        userGroupMappingService.alterUsersInGroup(dto);
        return ResponseEntity.ok().body(new BaseResponse<>(null,
                                "Add users to group successfully", null));
    }
}
