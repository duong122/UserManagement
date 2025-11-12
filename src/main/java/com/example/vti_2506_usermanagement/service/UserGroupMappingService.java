package com.example.vti_2506_usermanagement.service;

import com.example.vti_2506_usermanagement.dto.UserGroupMappingDTO;

import java.util.List;

public interface UserGroupMappingService {
    Void addUserToGroup(List<UserGroupMappingDTO> userGroupMappingDTOList);
}
