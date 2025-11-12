package com.example.vti_2506_usermanagement.controller;

import com.example.vti_2506_usermanagement.common.BaseResponse;
import com.example.vti_2506_usermanagement.dto.UserGroupMappingDTO;
import com.example.vti_2506_usermanagement.service.UserGroupMappingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/userGroupMapping")
public class UserGroupMappingController {
    private final UserGroupMappingService userGroupMappingService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> addUsersToGroup(@RequestBody @Valid List<UserGroupMappingDTO> userGroupMappingDTOList) {
        return ResponseEntity.ok(new BaseResponse<>(userGroupMappingService.addUserToGroup(userGroupMappingDTOList),
                                "Add users to the group successfully", null));
    }

}






